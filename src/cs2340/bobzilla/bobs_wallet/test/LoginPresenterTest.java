package cs2340.bobzilla.bobs_wallet.test;

import junit.framework.TestCase;
import cs2340.bobzilla.bobs_wallet.exceptions.InvalidLoginException;
import cs2340.bobzilla.bobs_wallet.model.User;
import cs2340.bobzilla.bobs_wallet.model.UserList;
import cs2340.bobzilla.bobs_wallet.model.UserListSingleton;
import cs2340.bobzilla.bobs_wallet.presenter.LoginActivityPresenter;
import cs2340.bobzilla.bobs_wallet.view.LoginActivityView;

/**
 * This class will test if the login presenter class 
 * works properly.
 * @author sai
 *
 */
public class LoginPresenterTest extends TestCase {
	
	private UserListSingleton userListSingleton;
	private LoginActivityView view0, view1, view2, view3, view4;
	private LoginActivityPresenter presenter0, presenter1, presenter2, presenter3, presenter4;
	
	
	@Override
	public void setUp() {
		userListSingleton = UserListSingleton.getInstance();
		view0 = new LoginActivityMock("", "");
		view1 = new LoginActivityMock("sai", "1234");
		view2 = new LoginActivityMock("lumber", "5555");
		view3 = new LoginActivityMock("medusa", "snakes");
		view4 = new LoginActivityMock("awesome", "awesome");
		presenter0 = new LoginActivityPresenter(view0);
		presenter1 = new LoginActivityPresenter(view1);
		presenter2 = new LoginActivityPresenter(view2);
		presenter3 = new LoginActivityPresenter(view3);
		presenter4 = new LoginActivityPresenter(view4);
		
		UserList list = userListSingleton.getUserList();
		User user1 = new User("sai", "sai", "paladugu", "12345", "spaladugu3@gatech.edu");
		User user2 = new User("lumberjack", "lumber", "jack", "5555", "lumberjack@tree.com");
		User user3 = new User("awesome", "awesome", "awesome", "awesome", "awesome@awesome.com");
		list.addUser(user1);
		list.addUser(user2);
		list.addUser(user3);
	}
	
	public void testFailedLoginEmptyFields() {
		try {
			presenter0.onClick();
			fail("The presenter allowed a user to login with empty fields");
		}
		catch(InvalidLoginException e) {
			//In this case, I do nothing. The test passes if it
			//catches the exception.
		}
	}
	
	public void testFailedLoginBadPassword() {
		try{
			presenter1.onClick();
			fail("The presenter allowed a user to login with an invalid password");
		}
		catch(InvalidLoginException e) {
			//In this case, I do nothing. The test passes
			//if it catches an exception.
		}
	}
	
	public void testFailedLoginBadUserName() {
		try {
			presenter2.onClick();
			fail("The presenter allowed a user who improperly entered their username to login");
		}
		catch(InvalidLoginException e) {
			//In this case, I do nothing here. The test passes
			//if it catches the exception.
		}
	}
	
	public void testFailedLoginUserNotRegistered() {
		try{
			presenter3.onClick();
			fail("The presenter allowed a user who was not registered to login");
		}
		catch(InvalidLoginException e) {
			//In this case, I do nothing here. The test passes
			//if it catches the exception.
		}
	}
	
	public void testLoginIsSuccessful() {
		try{
			presenter4.onClick();
		}
		catch(InvalidLoginException e) {
			fail("The presenter did not allow a user with valid credentials to login");
		}
	}
	
	@Override
	public void tearDown() {
		view1 = null;
		view2 = null;
		view3 = null;
		presenter1 = null;
		presenter2 = null;
		presenter3 = null;
		presenter4 = null;
		userListSingleton = null;
	}
	
	private class LoginActivityMock implements LoginActivityView {
		private String userName;
		private String password;
		
		public LoginActivityMock(String userName, String password) {
			this.userName = userName;
			this.password = password;
		}
		
		@Override
		public String getUserName() {
			// TODO Auto-generated method stub
			return userName;
		}

		@Override
		public String getPassword() {
			// TODO Auto-generated method stub
			return password;
		}
	}
}
