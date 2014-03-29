package cs2340.bobzilla.bobs_wallet.presenter;

import cs2340.bobzilla.bobs_wallet.exceptions.InvalidRegistrationException;
import cs2340.bobzilla.bobs_wallet.model.RegistrationVerifier;
import cs2340.bobzilla.bobs_wallet.model.User;
import cs2340.bobzilla.bobs_wallet.model.UserList;
import cs2340.bobzilla.bobs_wallet.model.UserListSingleton;
import cs2340.bobzilla.bobs_wallet.view.ClickListener;
import cs2340.bobzilla.bobs_wallet.view.RegistrationActivityView;

/**
 * A presenter class for the registration activity.
 * @author jack
 */
public class RegistrationActivityPresenter implements ClickListener {
	private RegistrationActivityView registrationActivityView;

    /**
     * Constructs the RegistrationActivityPresenter based on the corresponding view.
     * @param view the corresponding view.
     */
	public RegistrationActivityPresenter(RegistrationActivityView view) {
		registrationActivityView = view;
	}
	
	@Override
	public void onClick() throws InvalidRegistrationException {
		String userName = registrationActivityView.getUserName();
		String firstName = registrationActivityView.getFirstName();
		String lastName = registrationActivityView.getLastName();
		String password = registrationActivityView.getPassword();
		String confirmation = registrationActivityView.getPasswordConfirmation();
		String email = registrationActivityView.getEmailAddress();
		
		UserList userList = UserListSingleton.getInstance().getUserList();
		
		if(!RegistrationVerifier.isUserNameValid(userList, userName)) {
			throw new InvalidRegistrationException("Please enter a user name!");
		}
		else if(RegistrationVerifier.isUserNameTaken(userList, userName)) {
			throw new InvalidRegistrationException("That username is taken!");
		}
		else if(!RegistrationVerifier.isfirstNameValid(firstName)) {
			throw new InvalidRegistrationException("Please enter a first name!");
		}
		else if(!RegistrationVerifier.isLastNameValid(lastName)) {
			throw new InvalidRegistrationException("Please enter a last name!");
		}
		else if(!RegistrationVerifier.checkPasswordMatch(password, confirmation)) {
			throw new InvalidRegistrationException("Password and Confirmation do not match!");
		}
		else if(!RegistrationVerifier.verifyEmail(email)) {
			throw new InvalidRegistrationException("Please enter a vaid email!");
		}
		else {
			createUser(userList, userName, firstName, lastName, password, email);
		}
	}

    /**
     * Registers a new user
     * @param userList the userList in which to place the new user
     * @param userName the desired username
     * @param firstName the user's first name
     * @param lastName the user's last name
     * @param password the desired password
     * @param email the user's email address
     */
	private void createUser(UserList userList, String userName, String firstName,
			String lastName, String password, String email) {
		User newUser = new User(userName, firstName, lastName, password, email);
		userList.addUser(newUser);
	}
}
