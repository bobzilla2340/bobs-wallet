package cs2340.bobzilla.bobs_wallet.test;

import junit.framework.TestCase;
import cs2340.bobzilla.bobs_wallet.exceptions.InvalidLoginException;
import cs2340.bobzilla.bobs_wallet.model.User;
import cs2340.bobzilla.bobs_wallet.model.UserList;
import cs2340.bobzilla.bobs_wallet.model.UserListSingleton;
import cs2340.bobzilla.bobs_wallet.presenter.RegistrationActivityPresenter;
import cs2340.bobzilla.bobs_wallet.view.RegistrationActivityView;

/**
 * This class will test if the registration presenter class
 * works properly.
 * @author jack
 *
 */
public class RegistrationPresenterTest extends TestCase {

    private UserListSingleton userListSingleton;
    private RegistrationActivityView view0, view1, view2, view3, view4;
    private RegistrationActivityPresenter presenter0, presenter1, presenter2, presenter3, presenter4;

    public void setUp() {
        userListSingleton = UserListSingleton.getInstance();
        view0 = new RegistrationActivityMock("", "", "");
        view1 = new RegistrationActivityMock("sai", "1234",);
        view2 = new RegistrationActivityMock("lumber", "5555");
        view3 = new RegistrationActivityMock("medusa", "snakes");
        view4 = new RegistrationActivityMock("awesome", "awesome");
        presenter0 = new RegistrationActivityPresenter(view0);
        presenter1 = new RegistrationActivityPresenter(view1);
        presenter2 = new RegistrationActivityPresenter(view2);
        presenter3 = new RegistrationActivityPresenter(view3);
        presenter4 = new RegistrationActivityPresenter(view4);

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

    private class RegistrationActivityMock implements RegistrationActivityView {
        private String userName;
        private String password;
        private String email;
        private String lastName;

        public RegistrationActivityMock(String userName, String password, String email, String lastName) {
            this.userName = userName;
            this.password = password;
            this.email = email;
            this.lastName = lastName;
        }

        @Override
        public String getUserName() {
            return userName;
        }

        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public String getEmailAddress() {
            return email;
        }

        @Override
        public String getLastName() {
            return lastName;
        }
    }
}
