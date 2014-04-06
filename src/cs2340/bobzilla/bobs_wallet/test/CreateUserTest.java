package cs2340.bobzilla.bobs_wallet.test;

import java.util.List;

import junit.framework.TestCase;
import cs2340.bobzilla.bobs_wallet.exceptions.InvalidRegistrationException;
import cs2340.bobzilla.bobs_wallet.model.User;
import cs2340.bobzilla.bobs_wallet.model.UserList;
import cs2340.bobzilla.bobs_wallet.model.UserListSingleton;
import cs2340.bobzilla.bobs_wallet.presenter.RegistrationActivityPresenter;
import cs2340.bobzilla.bobs_wallet.view.RegistrationActivityView;

/**
* This class will test if the registration presenter class's onClick method
* works properly.
* @author jennifer
*
*/
public class CreateUserTest extends TestCase {
	private MockView view = new MockView("jh", "j",
			"h", "2340", "2340", "jh@gmail.com");
	private RegistrationActivityPresenter presenter = null;
	private UserList users;
	
	public void testRegular() throws InvalidRegistrationException{
		users = clearUsers();
		assertEquals(users.size(),0);
		
		presenter = new RegistrationActivityPresenter(
				(RegistrationActivityView) view);
		
		presenter.onClick();
		
		assertEquals("jh", users.getUser("jh").getUserName());
		assertEquals("j", users.getUser("jh").getFirstName());
		assertEquals("h", users.getUser("jh").getLastName());
		assertEquals("2340", users.getUser("jh").getPassword());
		assertEquals("jh@gmail.com", users.getUser("jh").getEmail());
		assertEquals(users.size(), 1);
	}
	
	public void testUsername() {
		users = clearUsers();
		assertEquals(users.size(),0);
		
		view.username = "";
		presenter = new RegistrationActivityPresenter(
				(RegistrationActivityView) view);
		try {
			presenter.onClick();
		} catch (InvalidRegistrationException e) {
			assertEquals("Please enter a user name!", e.getMessage());
		}
	}
	
	public void testFirstname() {
		users = clearUsers();
		assertEquals(users.size(),0);
		
		view.firstname = "";
		presenter = new RegistrationActivityPresenter(
				(RegistrationActivityView) view);
		try {
			presenter.onClick();
		} catch (InvalidRegistrationException e) {
			assertEquals("Please enter a first name!", e.getMessage());
		}
		assertEquals(0, users.size());
	}
	
	public void testLastname() {
		users = clearUsers();
		assertEquals(users.size(),0);
		
		view.lastname = "";
		presenter = new RegistrationActivityPresenter(
				(RegistrationActivityView) view);
		try {
			presenter.onClick();
		} catch (InvalidRegistrationException e) {
			assertEquals("Please enter a last name!", e.getMessage());
		}
		assertEquals(0, users.size());
	}
	
	public void testPassword1() {
		users = clearUsers();
		assertEquals(users.size(),0);
		
		view.password = "";
		presenter = new RegistrationActivityPresenter(
				(RegistrationActivityView) view);
		try {
			presenter.onClick();
		} catch (InvalidRegistrationException e) {
			assertEquals("Password and Confirmation do not match!", e.getMessage());
		}
		assertEquals(0, users.size());
	}
	
	public void testPassword2() {
		users = clearUsers();
		assertEquals(users.size(),0);
		
		view.passwordConfirmation = "";
		presenter = new RegistrationActivityPresenter(
				(RegistrationActivityView) view);
		try {
			presenter.onClick();
		} catch (InvalidRegistrationException e) {
			assertEquals("Password and Confirmation do not match!", e.getMessage());
		}
		assertEquals(0, users.size());
	}
	
	public void testPassword3() {
		users = clearUsers();
		assertEquals(users.size(),0);
		
		view.password = "";
		view.passwordConfirmation = "";
		presenter = new RegistrationActivityPresenter(
				(RegistrationActivityView) view);
		try {
			presenter.onClick();
		} catch (InvalidRegistrationException e) {
			assertEquals("Password and Confirmation do not match!", e.getMessage());
		}
		assertEquals(0, users.size());
	}
	
	public void testPassword4() {
		users = clearUsers();
		assertEquals(users.size(),0);
		
		view.password = "a";
		view.passwordConfirmation = "b";
		presenter = new RegistrationActivityPresenter(
				(RegistrationActivityView) view);
		try {
			presenter.onClick();
		} catch (InvalidRegistrationException e) {
			assertEquals("Password and Confirmation do not match!", e.getMessage());
		}
		assertEquals(0, users.size());
	}
	
	public void testPassword5() throws InvalidRegistrationException{
		users = clearUsers();
		assertEquals(users.size(),0);
		
		view.password = "a";
		view.passwordConfirmation = "a";
		presenter = new RegistrationActivityPresenter(
				(RegistrationActivityView) view);
		
		presenter.onClick();
		assertEquals(1, users.size());
		assertEquals("jh", users.getUser("jh").getUserName());
		assertEquals("j", users.getUser("jh").getFirstName());
		assertEquals("h", users.getUser("jh").getLastName());
		assertEquals("a", users.getUser("jh").getPassword());
		assertEquals("jh@gmail.com", users.getUser("jh").getEmail());
	}
	
	public void testUsernameTaken1() throws InvalidRegistrationException{
		users = clearUsers();
		assertEquals(users.size(),0);
		
		presenter = new RegistrationActivityPresenter(
				(RegistrationActivityView) view);
		presenter.onClick();
		assertEquals(1, users.size());
		
		try {
			presenter.onClick();
		} catch (InvalidRegistrationException e) {
			assertEquals("That username is taken!", e.getMessage());
		}
		
		assertEquals(1, users.size());
	}
	
	public void testUsernameTaken2() throws InvalidRegistrationException{
		users = clearUsers();
		assertEquals(users.size(),0);
		
		presenter = new RegistrationActivityPresenter(
				(RegistrationActivityView) view);
		presenter.onClick();
		assertEquals(1, users.size());
		
		view.username = "jm";
		presenter.onClick();
		
		assertEquals(2, users.size());
	}
	
	public void testEmail() {
		users = clearUsers();
		assertEquals(users.size(),0);
		
		view.email = "a@.com";
		presenter = new RegistrationActivityPresenter(
				(RegistrationActivityView) view);
		try {
			presenter.onClick();
		} catch (InvalidRegistrationException e) {
			assertEquals("Please enter a vaid email!", e.getMessage());
		}
	}
	
	private UserList clearUsers() {
		users = UserListSingleton.getInstance().getUserList();
		List<User> userList = users.getUserSet();
		
		for (User u : userList) {
			users.removeUser(u);
		}
		return users;
	}
	
	private class MockView implements RegistrationActivityView {
		public String username;
		public String firstname;
		public String lastname;
		public String password;
		public String passwordConfirmation;
		public String email;
		
		public MockView(String username, String firstname, String lastname,
				String password, String passwordConfirmation, String email) {
			this.username = username;
			this.firstname = firstname;
			this.lastname = lastname;
			this.password = password;
			this.passwordConfirmation = passwordConfirmation;
			this.email = email;
		}
		
		@Override
		public String getUserName() {
			return username;
		}
		
		@Override
		public String getFirstName() {
			return firstname;
		}
		
		@Override
		public String getLastName() {
			return lastname;
		}
		
		@Override
		public String getPassword() {
			return password;
		}
		
		@Override
		public String getPasswordConfirmation() {
			return passwordConfirmation;
		}
		
		@Override
		public String getEmailAddress() {
			return email;
		}
	}
}
