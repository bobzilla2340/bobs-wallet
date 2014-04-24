package cs2340.bobzilla.bobs_wallet.presenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.util.Log;
import android.widget.ArrayAdapter;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import cs2340.bobzilla.bobs_wallet.exceptions.InvalidAccountCreationException;
import cs2340.bobzilla.bobs_wallet.model.CurrentUser;
import cs2340.bobzilla.bobs_wallet.model.FinanceAccount;
import cs2340.bobzilla.bobs_wallet.model.ParseAccountHelper;
import cs2340.bobzilla.bobs_wallet.model.User;
import cs2340.bobzilla.bobs_wallet.view.ClickListener;
import cs2340.bobzilla.bobs_wallet.view.UserAccountActivityView;

/**
 * A presenter class for the user account activity.
 * 
 * @author jack
 */
public class UserAccountActivityPresenter implements ClickListener {
    /**
     * This is the view that is tied to this activity.
     */
    private UserAccountActivityView userAccountActivityView;
    
    public static final String TAG = "UserAccountActivityPresenter";

    /**
     * Constructs a UserAccountActivityPresenter for the corresponding view.
     * 
     * @param inputView
     *            the corresponding view
     */
    public UserAccountActivityPresenter(final UserAccountActivityView inputView) {
        userAccountActivityView = inputView;
    }

    /**
     * Gets the set of finance account names associated with the given username.
     * 
     * @param userName
     *            the username for which to find associated accounts
     * @return the names of all the user's finance accounts
     */
    public final Set<String> getFinanceAccountNames(final String userName) {
        User user = CurrentUser.getCurrentUser();
        Log.d(TAG, "user is :" + user);
        Map<String, FinanceAccount> financeAccounts = user
                .getFinanceAccountList();
        Log.i("UserAccountPresenter", financeAccounts.toString());
        return financeAccounts.keySet();
//        ParseUser user = ParseUser.getCurrentUser();
//        ParseRelation<ParseObject> relation = user.getRelation("accounts");
//        relation.getQuery().findInBackground(new FindCallback<ParseObject>() {
//            public void done(List<ParseObject> results, ParseException e) {
//                if (e != null) {
//                    
//                }
//                else {
//                    // TODO: handle error cases
//                }
//            }
//        });
    }

    @Override
    public final void onClick() throws InvalidAccountCreationException {
        // Get user input values
        final String accountName = userAccountActivityView.getAccountName();
        String interestRate = userAccountActivityView.getInterestRate();
        String userName = userAccountActivityView.getUserName();
        final double interest;

        if (accountName.equals("")) {
            throw new InvalidAccountCreationException(
                    "Please enter a valid account name!");
        } else if (interestRate.equals("")) {
            throw new InvalidAccountCreationException(
                    "Please enter a valid interest rate!");
        } else if (Double.parseDouble(interestRate) <= 0) {
            throw new InvalidAccountCreationException(
                    "Please enter a valid interest rate!");
        } else {
            interest = Double.parseDouble(interestRate);
        }
        
        ParseUser user = ParseUser.getCurrentUser();
        ParseObject account = createParseAccount(accountName, interest, userName);
        addAccountToUser(account);
        user.saveInBackground(new SaveCallback() {

            @Override
            public void done(ParseException e) {
                // TODO Auto-generated method stub
                if (e == null) {
                    // If successful save on server, add to local user
                    CurrentUser.getCurrentUser().addFinanceAccount(accountName, interest);
                }
                else {
                    Log.i("AccountActivityPresenter", e.getCode() + ": " + e.getMessage());
                }
            }
            
        });
        
        

    }
    
    
    private ParseObject createParseAccount(String accountName, double interestRate, String userName) {
        // Create the account with default parameters
        ParseObject account = new ParseObject("Account");
        account.put(FinanceAccount.PARSE_BALANCE_KEY, 0);
        
        // Set given parameters
        account.put(FinanceAccount.PARSE_ACCOUNT_NAME_KEY, accountName);
        account.put(FinanceAccount.PARSE_INTEREST_RATE_KEY, interestRate);
        account.put(FinanceAccount.PARSE_ASSOCIATED_USER_USERNAME, userName);
        
        // Add empty transactions list
        ArrayList<ParseObject> transactions = new ArrayList<ParseObject>();
        account.put(FinanceAccount.PARSE_TRANSACTIONS_KEY, transactions);
        
        return account;
    }
    
    private void addAccountToUser(ParseObject account) {
        ParseUser user = ParseUser.getCurrentUser();
        ArrayList<ParseObject> accounts;
        if (CurrentUser.getCurrentUser().getAccounts().isEmpty()) {
            accounts = new ArrayList<ParseObject>();
            Log.i("AccountActivityPresenter", "new arraylist of accounts created.");
        }
        else {
            accounts = (ArrayList<ParseObject>) user.get(User.parseAccountsKey);
            Log.i("AccountActivityPresenter", "accounts retrieved.");
        }
        
        accounts.add(account);
        user.put("accounts", accounts);
    }
    
    public void loadAccountsFromParse(final ArrayAdapter<String> adapter) {
        // Get local user from the app 
        final User user = CurrentUser.getCurrentUser();
        
        // Get accounts from Parse
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Account");
        query.whereEqualTo(FinanceAccount.PARSE_ASSOCIATED_USER_USERNAME, user.getUserName());
        query.include(FinanceAccount.PARSE_TRANSACTIONS_KEY);
        query.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> parseAccounts, ParseException e) {
                if (e == null) {
                    // Convert the accounts from Parse to FinanceAccount objects
                    Collections.reverse(parseAccounts);
                    ParseAccountHelper.addParseAccountsToUser(parseAccounts);
                    for (String account : user
                            .getFinanceAccountList().keySet()) {
                        adapter.add(account);
                    }
//                    Log.i("UserAccountActivityPresenter", accounts.toString());
//                    user.setAccounts(accounts);
                }
                else {
                    Log.e("UserAccountActivityPresenter", e.getCode() + ": " + e.getMessage());
                }
                
            }
            
        });
        
    }
    
}
