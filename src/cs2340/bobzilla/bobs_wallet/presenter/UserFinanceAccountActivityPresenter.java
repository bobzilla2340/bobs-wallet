package cs2340.bobzilla.bobs_wallet.presenter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;
import cs2340.bobzilla.bobs_wallet.exceptions.InvalidTransactionCreationException;
import cs2340.bobzilla.bobs_wallet.model.FinanceAccount;
import cs2340.bobzilla.bobs_wallet.model.Transaction;
import cs2340.bobzilla.bobs_wallet.model.TransactionType;
import cs2340.bobzilla.bobs_wallet.model.User;
import cs2340.bobzilla.bobs_wallet.model.UserList;
import cs2340.bobzilla.bobs_wallet.model.UserListSingleton;
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
        String userName = view.getUsername();
        User user = UserListSingleton.getInstance().getUserList()
                .getUser(userName);
        String category = view.getCategory();
        TransactionType type = view.getTransactionType();

        FinanceAccount account = user.getFinanceAccountList().get(accountName);
        if (transactionAmount.equals("")) {
            throw new InvalidTransactionCreationException(
                    "Please enter a valid amount!");
        } else if (amountNum <= 0) {
            throw new InvalidTransactionCreationException(
                    "Transaction amounts cannot be negative or zero.");
        } else {
            account.addTransaction(amountNum, type, category);
        }

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

        UserList userList = UserListSingleton.getInstance().getUserList();
        User user = userList.getUser(userName);
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
        User user = UserListSingleton.getInstance().getUserList()
                .getUser(view.getUsername());
        return df.format(user.getFinanceAccountList().get(accountName)
                .getCurrentBalance());
    }
}
