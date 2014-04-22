package cs2340.bobzilla.bobs_wallet.presenter;

import java.util.Map;
import java.util.Set;

import com.parse.ParseObject;
import com.parse.ParseRelation;
import com.parse.ParseUser;

import cs2340.bobzilla.bobs_wallet.exceptions.InvalidAccountCreationException;
import cs2340.bobzilla.bobs_wallet.model.CurrentUser;
import cs2340.bobzilla.bobs_wallet.model.FinanceAccount;
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
        Map<String, FinanceAccount> financeAccounts = user
                .getFinanceAccountList();
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
        String accountName = userAccountActivityView.getAccountName();
        String interestRate = userAccountActivityView.getInterestRate();
        String userName = userAccountActivityView.getUserName();

//        UserList userList = UserListSingleton.getInstance().getUserList();
//        User user = userList.getUser(userName);
//
//        double interest;
//        if (account.equals("")) {
//            throw new InvalidAccountCreationException(
//                    "Please enter a valid account name!");
//        } else if (interestRate.equals("")) {
//            throw new InvalidAccountCreationException(
//                    "Please enter a valid interest rate!");
//        } else if (Double.parseDouble(interestRate) <= 0) {
//            throw new InvalidAccountCreationException(
//                    "Please enter a valid interest rate!");
//        } else {
//            interest = Double.parseDouble(interestRate);
//            user.addFinanceAccount(account, interest);
//        }
        ParseObject account = createParseAccount(accountName, interestRate, userName);
        ParseUser user = ParseUser.getCurrentUser();
        addAccountToUser(user, account);
        user.saveInBackground();
    }
    
    
    private ParseObject createParseAccount(String accountName, String interestRate, String userName) {
        // Create the account with default parameters
        ParseObject account = new ParseObject("account");
        account.put("Balance", 0);
        //TODO: Do I have to make withdrawals, deposits, transactions now?
        
        // Set given parameters
        account.put("name", accountName);
        account.put("interest rate", interestRate);
        account.put("user username", userName);
        
        return account;
    }
    
    private void addAccountToUser(ParseUser user, ParseObject account) {
        ParseRelation<ParseObject> relation = user.getRelation("accounts");
        relation.add(account);
    }
    
//    private Set<String> getAccountNames(List<ParseObject> accounts) {
//        
//        for (ParseObject account: accounts) {
//            
//        }
//    }
    
}
