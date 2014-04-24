package cs2340.bobzilla.bobs_wallet.presenter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import cs2340.bobzilla.bobs_wallet.exceptions.InvalidTransactionCreationException;
import cs2340.bobzilla.bobs_wallet.model.CurrentUser;
import cs2340.bobzilla.bobs_wallet.model.FinanceAccount;
import cs2340.bobzilla.bobs_wallet.model.Transaction;
import cs2340.bobzilla.bobs_wallet.model.TransactionType;
import cs2340.bobzilla.bobs_wallet.model.User;
import cs2340.bobzilla.bobs_wallet.view.ClickListener;
import cs2340.bobzilla.bobs_wallet.view.UserFinanceAccountActivityView;

/**
 * Handles page for any given finance account. Logic for entering transactions
 * and seeing account balance is here.
 * 
 * @author Julia
 * 
 */
public class UserFinanceAccountActivityPresenter implements ClickListener {

    /**
     * This is the reference to the view that is tied to this presenter class.
     */
    private UserFinanceAccountActivityView view;
    /**
     * This is a reference to a decimal formatter in order to format money.
     */
    private final DecimalFormat df = new DecimalFormat("$###,##0.00");
    /**
     * Double tab for formatting.
     */
    private String doubleTab = "\t\t";
    
    private final String TAG = "UserFinanceAccountActivityPresenter";

    /**
     * Creates a UserFinanceAccountActivityPresenter associated with the
     * corresponding view.
     * 
     * @param inputView
     *            - the view to be tied to the presenter.
     */
    public UserFinanceAccountActivityPresenter(
            final UserFinanceAccountActivityView inputView) {
        this.view = inputView;
    }

    /**
     * Listener that handles when users hit "Create".
     * 
     * @throws InvalidTransactionCreationException
     *             - occurs when an invalid transaction occurs.
     */
    @Override
    public final void onClick() throws InvalidTransactionCreationException {
        String accountName = view.getAccountName();
        String transactionAmount = view.getTransactionAmount();
        double amountNum = Double.parseDouble(transactionAmount);
        String category = view.getCategory();
        TransactionType type = view.getTransactionType();
        
        FinanceAccount account = CurrentUser.getCurrentUser().getFinanceAccountList().get(accountName);
        
        if (transactionAmount.equals("")) {
            throw new InvalidTransactionCreationException(
                    "Please enter a valid amount!");
        } else if (amountNum <= 0) {
            throw new InvalidTransactionCreationException(
                    "Transaction amounts cannot be negative or zero.");
        } else {
            // Create the transaction and add it to local and cloud accounts
            Transaction localTransaction = new Transaction(amountNum, type, category);
            addTransaction(localTransaction, accountName);
            
        }

    }
    
    private void addTransaction(Transaction localTransaction, String accountName) {
        // Get objects
        ParseUser user = ParseUser.getCurrentUser();
        
        // Create transaction
        ParseObject parseTransaction = createParseTransaction(localTransaction, accountName);
        
        // Add transaction to both local and cloud accounts
        addTransactionToAccount(localTransaction, parseTransaction, accountName);
    }
    
    private ParseObject createParseTransaction(Transaction localTransaction, String accountName) {
        // Create transaction with default parameters
        ParseObject transaction = new ParseObject("Transaction");
        transaction.put(Transaction.PARSE_AMOUNT_KEY, localTransaction.getAmount());
        transaction.put(Transaction.PARSE_TRANSACTION_TYPE_KEY, localTransaction.getTransactionType().name());
        transaction.put(Transaction.PARSE_DATE_KEY, localTransaction.getTransactionDate());
        transaction.put(Transaction.PARSE_CATEGORY_KEY, localTransaction.getCategory());
        transaction.put(Transaction.PARSE_ASSOCIATED_USER_KEY, CurrentUser.getCurrentUser().getUserName());
        transaction.put(Transaction.PARSE_ASSOCIATED_ACCOUNT_NAME_KEY, accountName);
        
        return transaction;
    }
    
    private void addTransactionToAccount(Transaction transaction, final ParseObject parseTransaction, String accountName) {
        // Add to local account
        FinanceAccount account = CurrentUser.getCurrentUser().getAccount(accountName);
        account.addTransaction(transaction);

        // Get account object from Parse
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Account");
        query.whereEqualTo(FinanceAccount.PARSE_ASSOCIATED_USER_USERNAME, ParseUser.getCurrentUser().getUsername());
        query.whereEqualTo(FinanceAccount.PARSE_ACCOUNT_NAME_KEY, accountName);
        query.include(FinanceAccount.PARSE_TRANSACTIONS_KEY);
        query.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> parseAccounts, ParseException e) {
                if (e == null) {
                    // TODO: Make sure accounts with same names cannot be added.
                    // For now, just get the first account.
                    // Assumption: Account will always exist, because user had to be on account page to add a transaction
                    // Add transaction to account
                    addTransactionToParseAccount(parseTransaction, parseAccounts.get(0));
                }
                // TODO: Error handling
                else {
                    
                }   
            } 
        });
    }
    
    private void addTransactionToParseAccount(ParseObject parseTransaction, ParseObject parseAccount) {
        ArrayList<ParseObject> transactions = (ArrayList<ParseObject>) parseAccount.get(FinanceAccount.PARSE_TRANSACTIONS_KEY);
        transactions.add(parseTransaction);
        
        // Change current balance
        TransactionType type = getParseTransactionType(parseTransaction);
        double amount = parseTransaction.getDouble(Transaction.PARSE_AMOUNT_KEY);
        
        switch(type) {
        case DEPOSIT:
            break;
        case WITHDRAWAL:
            amount = -amount;
            break;
        }
        parseAccount.increment(FinanceAccount.PARSE_BALANCE_KEY, amount);
        
        parseAccount.saveInBackground();
        
    }
    
    private TransactionType getParseTransactionType(ParseObject transaction) {
        return TransactionType.valueOf(transaction.getString(Transaction.PARSE_TRANSACTION_TYPE_KEY));
    }

    /**
     * Returns the total list of transactions currently in the finance account
     * found by userName and account name.
     * 
     * @param userName
     *            the username for which to find transactions
     * @param financeAccountName
     *            the finance account for which to find transactions
     * @return the list of transactions
     */
    public final ArrayList<String> getFormattedTransactions(
            final String userName, final String financeAccountName) {

        User user = CurrentUser.getCurrentUser();
        Map<String, FinanceAccount> financeAccounts = user
                .getFinanceAccountList();
        FinanceAccount account = financeAccounts.get(financeAccountName);
        ArrayList<Transaction> transactions = account.getTransactions();
        ArrayList<String> formatted = new ArrayList<String>();

        for (Transaction t : transactions) {
            String display = "";
            if (t.getTransactionType().equals(TransactionType.WITHDRAWAL)) {
                display = display + "W \t -";
            } else {
                display = display + "D \t +";
            }
            display = display + df.format(t.getAmount()) + doubleTab
                    + t.getCategory() + doubleTab + t.getTransactionDate();
            formatted.add(display);
        }
        return formatted;

    }

    /**
     * This method gets the current account balance and formats it to the
     * appropriate monetary format.
     * 
     * @param accountName
     *            - the name of the account to get the balance from.
     * @return formattedAccountBalance - the balance of the account in formatted
     *         with the appropriate monetary symbol.
     */
    public final String getFormattedCurrentAccountBalance(
            final String accountName) {
        User user = CurrentUser.getCurrentUser();
        return df.format(user.getAccount(accountName)
                .getCurrentBalance());
    }
}
