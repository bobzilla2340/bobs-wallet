package cs2340.bobzilla.bobs_wallet.presenter;

import java.util.ArrayList;
import java.util.Map;

import android.util.Log;

import cs2340.bobzilla.bobs_wallet.exceptions.InvalidTransactionCreationException;
import cs2340.bobzilla.bobs_wallet.model.FinanceAccount;
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
	
	public UserFinanceAccountActivityPresenter(UserFinanceAccountActivityView view) {
		this.view = view;
	}
	
	/**
	 * Listener that handles when users hit "Deposit".
	 */
	@Override
	public void onClick() throws InvalidTransactionCreationException {
		String accountName = view.getAccountName();
		String transactionAmount = view.getTransactionAmount();
		String userName = view.getUsername();
		User user = UserListSingleton.getInstance().getUserList().getUser(userName);

		FinanceAccount account = user.getFinanceAccountList().get(accountName);
		if (transactionAmount.equals("")) {
			throw new InvalidTransactionCreationException("Please enter a valid amount!");
		} else if (Double.parseDouble(transactionAmount) <= 0) {
			throw new InvalidTransactionCreationException("Transaction amounts cannot be negative or zero.");
		} else {
			account.addTransaction(Double.parseDouble(transactionAmount));
			account.addDeposit(Double.parseDouble(transactionAmount));
			account.setCurrentBalance(Double.parseDouble(transactionAmount) + account.getCurrentBalance());
		}
		
	}
	
	/**
	 * Listener that runs when user hits "Withdraw".
	 * 
	 * @throws InvalidTransactionCreationException
	 */
	public void onClickWithdrawal() throws InvalidTransactionCreationException {
		String accountName = view.getAccountName();
		String transactionAmount = view.getTransactionAmount();
		User user = UserListSingleton.getInstance().getUserList().getUser(view.getUsername());
		
		FinanceAccount account = user.getFinanceAccountList().get(accountName);
		if (transactionAmount.equals("")) {
			throw new InvalidTransactionCreationException("Please enter a valid amount!");
		} else if (Double.parseDouble(transactionAmount) <= 0) {
			throw new InvalidTransactionCreationException("Transaction amounts cannot be negative or zero.");
		} else {
			account.addTransaction(Double.parseDouble(transactionAmount));
			account.addWithdrawal(Double.parseDouble(transactionAmount));
			account.setCurrentBalance(account.getCurrentBalance() - Double.parseDouble(transactionAmount));
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
	public ArrayList<Double> getTransactions(String userName, String financeAccountName) {
		UserList userList = UserListSingleton.getInstance().getUserList();
		User user = userList.getUser(userName);
		Map<String, FinanceAccount> financeAccounts = user.getFinanceAccountList();
		FinanceAccount account = financeAccounts.get(financeAccountName);
		return account.getTransactions();
	}

	/**
	 * Returns the current account balance from the user, given the account name.
	 * 
	 * @param accountName
	 * @return
	 */
	public double getCurrentAccountBalance(String accountName) {
		User user = UserListSingleton.getInstance().getUserList().getUser(view.getUsername());
		return user.getFinanceAccountList().get(accountName).getCurrentBalance();
	}
}
