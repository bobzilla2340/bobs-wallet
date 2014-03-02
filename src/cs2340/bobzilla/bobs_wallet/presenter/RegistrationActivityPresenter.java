package cs2340.bobzilla.bobs_wallet.presenter;

import cs2340.bobzilla.bobs_wallet.exceptions.InvalidRegistrationException;
import cs2340.bobzilla.bobs_wallet.model.RegistrationVerifier;
import cs2340.bobzilla.bobs_wallet.model.User;
import cs2340.bobzilla.bobs_wallet.model.UserList;
import cs2340.bobzilla.bobs_wallet.model.UserListSingleton;
import cs2340.bobzilla.bobs_wallet.view.ClickListener;
import cs2340.bobzilla.bobs_wallet.view.RegistrationActivityView;

public class RegistrationActivityPresenter implements ClickListener {
	private RegistrationActivityView registrationActivityView;
	
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
			User newUser = new User(userName, firstName, lastName, password, email);
			userList.addUser(newUser);
			return;
		}
	}
}
