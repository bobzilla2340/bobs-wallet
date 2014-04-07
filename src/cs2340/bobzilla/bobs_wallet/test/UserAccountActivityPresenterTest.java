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

    /**
     * This is the link to the singleton.
     */
    private UserListSingleton userListSingleton;
    /**
     * View test case 1.
     */
    private UserAccountActivityView view0;
    /**
     * View test case 2.
     */
    private UserAccountActivityView view1;
    /**
     * View test case 3.
     */
    private UserAccountActivityView view2;
    /**
     * View test case 4.
     */
    private UserAccountActivityView view3;
    /**
     * View Test case 5.
     */
    private UserAccountActivityView view4;
    /**
     * Presenter test case 1.
     */
    private UserAccountActivityPresenter presenter0;
    /**
     * Presenter test case 2.
     */
    private UserAccountActivityPresenter presenter1;
    /**
     * Presenter test case 3.
     */
    private UserAccountActivityPresenter presenter2;
    /**
     * Presenter test case 4.
     */
    private UserAccountActivityPresenter presenter3;
    /**
     * Presenter test case 5.
     */
    private UserAccountActivityPresenter presenter4;
    /**
     * The username used in test cases.
     */
    private String userName = "joolz";
    /**
     * Account name.
     */
    private String accountName = "whee";

    @Override
    public void setUp() {
        userListSingleton = UserListSingleton.getInstance();
        view0 = new UserAccountActivityMock(userName, "", "5");
        view1 = new UserAccountActivityMock(userName, "empty", "");
        view2 = new UserAccountActivityMock(userName, accountName, "-1000");
        view3 = new UserAccountActivityMock(userName, accountName, "0");
        view4 = new UserAccountActivityMock(userName, "valid", "10");
        presenter0 = new UserAccountActivityPresenter(view0);
        presenter1 = new UserAccountActivityPresenter(view1);
        presenter2 = new UserAccountActivityPresenter(view2);
        presenter3 = new UserAccountActivityPresenter(view3);
        presenter4 = new UserAccountActivityPresenter(view4);

        UserList list = userListSingleton.getUserList();
        User user1 = new User(userName, "julia", "ting", "12345",
                "julia.ting@gatech.edu");
        list.addUser(user1);
    }

    /**
     * This tests to see if an account is created with an empty name.
     */
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
    
    /**
     * This test to see if an account is created without an interest rate.
     */
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

    /**
     * This test to see if an account is created with a negative interest.
     */
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

    /**
     * This tests to see if an account is created with a zero interest.
     */
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

    /**
     * This tests to see if the account creation is successful.
     */
    public void testAccountCreationIsSuccessful() {
        try {
            presenter4.onClick();
        } catch (InvalidAccountCreationException e) {
            fail("The presenter did not allow a user to create a finance"
                    + " account with valid information.");
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
     * This is a mock class that test the user account activity view.
     * 
     * @author sai
     *
     */
    private class UserAccountActivityMock implements UserAccountActivityView {
        /**
         * The name of the account.
         */
        private String accountName;
        /**
         * The user name.
         */
        private String userName;
        /**
         * The interest rate.
         */
        private String interestRate;

        /**
         * This makes the mock object.
         * @param inputUserName
         *          The name of the user.
         * @param inputAccountName
         *          The name of the account.
         * @param inputIterestRate
         *          The interest rate of the account.
         */
        public UserAccountActivityMock(String inputUserName, String inputAccountName,
                String inputIterestRate) {
            this.userName = inputUserName;
            this.accountName = inputAccountName;
            this.interestRate = inputIterestRate;
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
