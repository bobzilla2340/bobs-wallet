package cs2340.bobzilla.bobs_wallet.presenter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;

import android.util.Log;

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
 * Handles page for any given finance account. Logic for entering transactions and seeing 
 * account balance is here.
 * 
 * @author Julia
 *
 */
public class UserFinanceAccountActivityPresenter implements ClickListener {

	private UserFinanceAccountActivityView view;
	private final DecimalFormat df = new DecimalFormat("$###,##0.00");

	
	public UserFinanceAccountActivityPresenter(UserFinanceAccountActivityView view) {
		this.view = view;
	}
	
	/**
	 * Listener that handles when users hit "Create"
	 */
	@Override
	public void onClick() throws InvalidTransactionCreationException {
		String accountName = view.getAccountName();
		String transactionAmount = view.getTransactionAmount();
		double amountNum = Double.parseDouble(transactionAmount);
		String userName = view.getUsername();
		User user = UserListSingleton.getInstance().getUserList().getUser(userName);
		String category = view.getCategory();
		TransactionType type = view.getTransactionType();

		FinanceAccount account = user.getFinanceAccountList().get(accountName);
		if (transactionAmount.equals("")) {
			throw new InvalidTransactionCreationException("Please enter a valid amount!");
		} else if (amountNum <= 0) {
			throw new InvalidTransactionCreationException("Transaction amounts cannot be negative or zero.");
		} else {
			account.addTransaction(amountNum, type, category);
		}
		
	}

	/**
	 * Returns the total list of transactions currently in the finance account
	 * found by userName and account name.
	 * 
	 * @param userName
	 * @param financeAccountName
	 * @return
	 */
	public ArrayList<String> getFormattedTransactions(String userName, String financeAccountName) {
		UserList userList = UserListSingleton.getInstance().getUserList();
		User user = userList.getUser(userName);
		Map<String, FinanceAccount> financeAccounts = user.getFinanceAccountList();
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
			display = display + df.format(t.getAmount()) + "\t\t" + t.getCategory() + "\t\t" + t.getTransactionDate();
			formatted.add(display);
		}
		return formatted;
	}

	/**
	 * Returns the current account balance from the user, given the account name.
	 * 
	 * @param accountName
	 * @return
	 */
	public String getFormattedCurrentAccountBalance(String accountName) {
		User user = UserListSingleton.getInstance().getUserList().getUser(view.getUsername());
		return df.format(user.getFinanceAccountList().get(accountName).getCurrentBalance());
	}
}
