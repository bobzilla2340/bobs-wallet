package cs2340.bobzilla.bobs_wallet.test;

import junit.framework.TestCase;
import cs2340.bobzilla.bobs_wallet.exceptions.InvalidLoginException;
import cs2340.bobzilla.bobs_wallet.model.User;
import cs2340.bobzilla.bobs_wallet.model.UserList;
import cs2340.bobzilla.bobs_wallet.model.UserListSingleton;
import cs2340.bobzilla.bobs_wallet.presenter.LoginActivityPresenter;
import cs2340.bobzilla.bobs_wallet.view.LoginActivityView;

/**
 * This class will test if the login presenter class works properly.
 * 
 * @author sai
 * 
 */
public class LoginPresenterTest extends TestCase {

    /**
     * The singleton that is used in this test.
     */
    private UserListSingleton userListSingleton;
    /**
     * Test view 1.
     */
    private LoginActivityView view0;
    /**
     * Test view 2.
     */
    private LoginActivityView view1;
    /**
     * Test view 3.
     */
    private LoginActivityView view2;
    /**
     * Test view 4.
     */
    private LoginActivityView view3;
    /**
     * Test view 5.
     */
    private LoginActivityView view4;
    /**
     * Test presenter 1.
     */
    private LoginActivityPresenter presenter0;
    /**
     * Test presenter 2.
     */
    private LoginActivityPresenter presenter1;
    /**
     * Test presenter 3.
     */
    private LoginActivityPresenter presenter2;
    /**
     * Test presenter 4.
     */
    private LoginActivityPresenter presenter3;
    /**
     * Test presenter 5.
     */
    private LoginActivityPresenter presenter4;
    /**
     * First user name.
     */
    private String username1 = "sai";
    /**
     * Second user name.
     */
    private String username2 = "lumber";
    /**
     * Third user name.
     */
    private String username3 = "medusa";
    /**
     * Fourth user name.
     */
    private String username4 = "awesome";
    /**
     * Repeated password 1.
     */
    private String password1 = "5555";

    @Override
    public void setUp() {
        userListSingleton = UserListSingleton.getInstance();
        view0 = new LoginActivityMock("", "");
        view1 = new LoginActivityMock(username1, "1234");
        view2 = new LoginActivityMock(username2, password1);
        view3 = new LoginActivityMock(username3, "snakes");
        view4 = new LoginActivityMock(username4, username4);
        presenter0 = new LoginActivityPresenter(view0);
        presenter1 = new LoginActivityPresenter(view1);
        presenter2 = new LoginActivityPresenter(view2);
        presenter3 = new LoginActivityPresenter(view3);
        presenter4 = new LoginActivityPresenter(view4);

        UserList list = userListSingleton.getUserList();
        User user1 = new User(username1, username1, "paladugu", "12345",
                "spaladugu3@gatech.edu");
        User user2 = new User("lumberjack", username2, "jack", password1,
                "lumberjack@tree.com");
        User user3 = new User(username4, username4, username4, username4,
                "awesome@awesome.com");
        list.addUser(user1);
        list.addUser(user2);
        list.addUser(user3);
    }

    /**
     * This tests to see if login fails on empty user name and password.
     */
    public void testFailedLoginEmptyFields() {
        try {
            presenter0.onClick();
            fail("The presenter allowed a user to login with empty fields");
        } catch (InvalidLoginException e) {
            // In this case, I do nothing. The test passes if it
            // catches the exception.
        }
    }

    /**
     * This tests to see if login fails on an incorrect password.
     */
    public void testFailedLoginBadPassword() {
        try {
            presenter1.onClick();
            fail("The presenter allowed a user to login with an invalid password");
        } catch (InvalidLoginException e) {
            // In this case, I do nothing. The test passes
            // if it catches an exception.
        }
    }

    /**
     * This tests to see if login fails on an invalid user name.
     */
    public void testFailedLoginBadUserName() {
        try {
            presenter2.onClick();
            fail("The presenter allowed a user who improperly entered their username to login");
        } catch (InvalidLoginException e) {
            // In this case, I do nothing here. The test passes
            // if it catches the exception.
        }
    }

    /**
     * This tests to see if login fails for an unregistered user.
     */
    public void testFailedLoginUserNotRegistered() {
        try {
            presenter3.onClick();
            fail("The presenter allowed a user who was not registered to login");
        } catch (InvalidLoginException e) {
            // In this case, I do nothing here. The test passes
            // if it catches the exception.
        }
    }

    /**
     * This tests to see if login is successful.
     */
    public void testLoginIsSuccessful() {
        try {
            presenter4.onClick();
        } catch (InvalidLoginException e) {
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

    /**
     * This is the mock class that is used to test the login feature.
     * @author sai
     *
     */
    private class LoginActivityMock implements LoginActivityView {
        /**
         * The the user name.
         */
        private String userName;
        /**
         * The password.
         */
        private String password;

        /**
         * Constructs the mock object.
         * @param inputUserName
         *          User name of the user.
         * @param inputPassword
         *          User's password.
         */
        public LoginActivityMock(String inputUserName, String inputPassword) {
            this.userName = inputUserName;
            this.password = inputPassword;
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
