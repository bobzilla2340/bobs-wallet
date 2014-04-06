package cs2340.bobzilla.bobs_wallet.test;

import junit.framework.TestCase;
import cs2340.bobzilla.bobs_wallet.exceptions.InvalidAccountCreationException;
import cs2340.bobzilla.bobs_wallet.model.User;
import cs2340.bobzilla.bobs_wallet.model.UserList;
import cs2340.bobzilla.bobs_wallet.model.UserListSingleton;
import cs2340.bobzilla.bobs_wallet.presenter.UserAccountActivityPresenter;
import cs2340.bobzilla.bobs_wallet.view.UserAccountActivityView;

/**
 * Tests the UserAccountActivityPresenter on click method, which facilitates
 * user finance account creation.
 * 
 * @author Julia Ting
 * 
 */
public class UserAccountActivityPresenterTest extends TestCase {

    private UserListSingleton userListSingleton;
    private UserAccountActivityView view0, view1, view2, view3, view4;
    private UserAccountActivityPresenter presenter0, presenter1, presenter2,
            presenter3, presenter4;

    // @Override
    public void setUp() {
        userListSingleton = UserListSingleton.getInstance();
        view0 = new UserAccountActivityMock("joolz", "", "5");
        view1 = new UserAccountActivityMock("joolz", "empty", "");
        view2 = new UserAccountActivityMock("joolz", "whee", "-1000");
        view3 = new UserAccountActivityMock("joolz", "whee", "0");
        view4 = new UserAccountActivityMock("joolz", "valid", "10");
        presenter0 = new UserAccountActivityPresenter(view0);
        presenter1 = new UserAccountActivityPresenter(view1);
        presenter2 = new UserAccountActivityPresenter(view2);
        presenter3 = new UserAccountActivityPresenter(view3);
        presenter4 = new UserAccountActivityPresenter(view4);

        UserList list = userListSingleton.getUserList();
        User user1 = new User("joolz", "julia", "ting", "12345",
                "julia.ting@gatech.edu");
        list.addUser(user1);
    }

    public void testFailedAccountCreationEmptyName() {
        try {
            presenter0.onClick();
            fail("The presenter allowed a user to create an account with an "
                    + "empty account name");
        } catch (InvalidAccountCreationException e) {
            // In this case, I do nothing. The test passes if it
            // catches the exception.
        }
    }

    public void testFailedAccountCreationEmptyInterest() {
        try {
            presenter1.onClick();
            fail("The presenter allowed a user to create an account with an empty"
                    + "interest rate field.");
        } catch (InvalidAccountCreationException e) {
            // In this case, I do nothing. The test passes
            // if it catches an exception.
        }
    }

    public void testFailedAccountCreationNegativeInterest() {
        try {
            presenter2.onClick();
            fail("The presenter allowed a user to create an account with"
                    + " a negative interest rate.");
        } catch (InvalidAccountCreationException e) {
            // In this case, I do nothing here. The test passes
            // if it catches the exception.
        }
    }

    public void testFailedAccountCreationZeroInterest() {
        try {
            presenter3.onClick();
            fail("The presenter allowed a user to create an account with "
                    + "an interest rate of 0.");
        } catch (InvalidAccountCreationException e) {
            // In this case, I do nothing here. The test passes
            // if it catches the exception.
        }
    }

    public void testAccountCreationIsSuccessful() {
        try {
            presenter4.onClick();
        } catch (InvalidAccountCreationException e) {
            fail("The presenter did not allow a user to create a finance"
                    + " account with valid information.");
        }
    }

    // @Override
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

    private class UserAccountActivityMock implements UserAccountActivityView {
        private String accountName;
        private String userName;
        private String interestRate;

        public UserAccountActivityMock(String userName, String accountName,
                String interestRate) {
            this.userName = userName;
            this.accountName = accountName;
            this.interestRate = interestRate;
        }

        @Override
        public String getUserName() {
            return userName;
        }

        @Override
        public String getAccountName() {
            return accountName;
        }

        @Override
        public String getInterestRate() {
            return interestRate;
        }

    }
}
