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

/**
 * A presenter class for the user account activity.
 * @author jack
 */
public class UserAccountActivityPresenter implements ClickListener {
	private UserAccountActivityView userAccountActivityView;

    /**
     * Constructs a UserAccountActivityPresenter for the corresponding view.
     * @param view the corresponding view
     */
	public UserAccountActivityPresenter(UserAccountActivityView view) {
		userAccountActivityView = view;
	}

    /**
     * Gets the set of finance account names associated with the given username.
     * @param userName the username for which to find associated accounts
     * @return the names of all the user's finance accounts
     */
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
