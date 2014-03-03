package cs2340.bobzilla.bobs_wallet.presenter;

import java.util.Map;
import java.util.Set;

import cs2340.bobzilla.bobs_wallet.exceptions.InvalidAccountCreationException;
import cs2340.bobzilla.bobs_wallet.model.FinanceAccount;
import cs2340.bobzilla.bobs_wallet.model.User;
import cs2340.bobzilla.bobs_wallet.model.UserList;
import cs2340.bobzilla.bobs_wallet.model.UserListSingleton;
import cs2340.bobzilla.bobs_wallet.view.ClickListener;
import cs2340.bobzilla.bobs_wallet.view.UserAccountActivityView;

public class UserAccountActivityPresenter implements ClickListener {
	private UserAccountActivityView userAccountActivityView;
	
	public UserAccountActivityPresenter(UserAccountActivityView view) {
		userAccountActivityView = view;
	}
	
	public Set<String> getFinanceAccountNames(String userName) {
		UserList userList = UserListSingleton.getInstance().getUserList();
		User user = userList.getUser(userName);
		Map<String, FinanceAccount> financeAccounts = user.getFinanceAccountList();
		return financeAccounts.keySet();
	}
	
	@Override
	public void onClick() throws InvalidAccountCreationException {
		String account = userAccountActivityView.getAccountName();
		String interestRate = userAccountActivityView.getInterestRate();
		String userName = userAccountActivityView.getUserName();
		
		UserList userList = UserListSingleton.getInstance().getUserList();
		User user = userList.getUser(userName);
		
		double interest;
		if(account.equals("")) {
			throw new InvalidAccountCreationException("Please enter a valid account name!");
		}
		else if(interestRate.equals("")) {
			throw new InvalidAccountCreationException("Please enter a valid interest rate!");
		}
		else if(Double.parseDouble(interestRate) <= 0) {
			throw new InvalidAccountCreationException("Please enter a valid interest rate!");
		}
		else {
			interest = Double.parseDouble(interestRate);
			user.addFinanceAccount(account, interest);
		}			
	}
	
}
