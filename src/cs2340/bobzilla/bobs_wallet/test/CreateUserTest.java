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
 * 
 * @author jennifer
 * 
 */
public class CreateUserTest extends TestCase {
<<<<<<< HEAD
    /**
     * The mock view that is used in this test case.
     */
    private MockView view = new MockView("jh", "j", "h", "2340", "2340",
            "jh@gmail.com");
    /**
     * This is the presenter that is tested.
     */
    private RegistrationActivityPresenter presenter = null;
    /**
     * This is the list of the users.
     */
    private UserList users;

    /**
     * This tests a regular scenario when creating a user.
     * @throws InvalidRegistrationException
     *          This is thrown when the user enters in bad information.
     */
    public void testRegular() throws InvalidRegistrationException {
        users = clearUsers();
        assertEquals(users.size(), 0);

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

    /**
     * This tests to see if the user name is valid.
     */
    public void testUsername() {
        users = clearUsers();
        assertEquals(users.size(), 0);

        view.username = "";
        presenter = new RegistrationActivityPresenter(
                (RegistrationActivityView) view);
        try {
            presenter.onClick();
        } catch (InvalidRegistrationException e) {
            assertEquals("Please enter a user name!", e.getMessage());
        }
    }

    /**
     * This tests the first name to see if it is correct.
     */
    public void testFirstname() {
        users = clearUsers();
        assertEquals(users.size(), 0);

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

    /**
     * This tests the last name to see if it is correct.
     */
    public void testLastname() {
        users = clearUsers();
        assertEquals(users.size(), 0);

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

    /**
     * This tests the password that the user enters.
     */
    public void testPassword1() {
        users = clearUsers();
        assertEquals(users.size(), 0);

        view.password = "";
        presenter = new RegistrationActivityPresenter(
                (RegistrationActivityView) view);
        try {
            presenter.onClick();
        } catch (InvalidRegistrationException e) {
            assertEquals("Password and Confirmation do not match!",
                    e.getMessage());
        }
        assertEquals(0, users.size());
    }

    /**
     * This tests to see if the password is correct.
     */
    public void testPassword2() {
        users = clearUsers();
        assertEquals(users.size(), 0);

        view.passwordConfirmation = "";
        presenter = new RegistrationActivityPresenter(
                (RegistrationActivityView) view);
        try {
            presenter.onClick();
        } catch (InvalidRegistrationException e) {
            assertEquals("Password and Confirmation do not match!",
                    e.getMessage());
        }
        assertEquals(0, users.size());
    }

    /**
     * This test to see if the password passes.
     */
    public void testPassword3() {
        users = clearUsers();
        assertEquals(users.size(), 0);

        view.password = "";
        view.passwordConfirmation = "";
        presenter = new RegistrationActivityPresenter(
                (RegistrationActivityView) view);
        try {
            presenter.onClick();
        } catch (InvalidRegistrationException e) {
            assertEquals("Password and Confirmation do not match!",
                    e.getMessage());
        }
        assertEquals(0, users.size());
    }

    /**
     * This tests the password again.
     */
    public void testPassword4() {
        users = clearUsers();
        assertEquals(users.size(), 0);

        view.password = "a";
        view.passwordConfirmation = "b";
        presenter = new RegistrationActivityPresenter(
                (RegistrationActivityView) view);
        try {
            presenter.onClick();
        } catch (InvalidRegistrationException e) {
            assertEquals("Password and Confirmation do not match!",
                    e.getMessage());
        }
        assertEquals(0, users.size());
    }

    /**
     * This tests to see if the password throws the 
     * exception.
     * @throws InvalidRegistrationException
     *          Thrown when the confirmation does not match.
     */
    public void testPassword5() throws InvalidRegistrationException {
        users = clearUsers();
        assertEquals(users.size(), 0);

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

    /**
     * This tests to see if the user name is taken.
     * @throws InvalidRegistrationException
     *          Thrown when the username is already taken.
     */
    public void testUsernameTaken1() throws InvalidRegistrationException {
        users = clearUsers();
        assertEquals(users.size(), 0);

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

    /**
     * This tests to see if the username is already taken.
     * @throws InvalidRegistrationException
     *          Thrown when the username is taken.
     */
    public void testUsernameTaken2() throws InvalidRegistrationException {
        users = clearUsers();
        assertEquals(users.size(), 0);

        presenter = new RegistrationActivityPresenter(
                (RegistrationActivityView) view);
        presenter.onClick();
        assertEquals(1, users.size());

        view.username = "jm";
        presenter.onClick();

        assertEquals(2, users.size());
    }

    /**
     * Tests the email.
     */
    public void testEmail() {
        users = clearUsers();
        assertEquals(users.size(), 0);

        view.email = "a@.com";
        presenter = new RegistrationActivityPresenter(
                (RegistrationActivityView) view);
        try {
            presenter.onClick();
        } catch (InvalidRegistrationException e) {
            assertEquals("Please enter a vaid email!", e.getMessage());
        }
    }

    /**
     * Tests the clear users functionality.
     * @return The list of empty users.
     */
    private UserList clearUsers() {
        users = UserListSingleton.getInstance().getUserList();
        List<User> userList = users.getUserSet();

        for (User u : userList) {
            users.removeUser(u);
        }
        return users;
    }

    /**
     * This is used in the test for making mock objects.
     * @author sai
     *
     */
    private class MockView implements RegistrationActivityView {
        /**
         * The user name.
         */
        public String username;
        /**
         * The first name.
         */
        public String firstname;
        /**
         * The last name.
         */
        public String lastname;
        /**
         * The password.
         */
        public String password;
        /**
         * The password confirmation.
         */
        public String passwordConfirmation;
        /**
         * The email of the user.
         */
        public String email;

        /**
         * This makes the mock object.
         * @param inputUsername
         *          The user name that is entered.
         * @param inputFirstname
         *          The first name that is entered.
         * @param inputLastname
         *          The last name that is entered.
         * @param inputPassword
         *          The password that is entered.
         * @param inputPasswordConfirmation
         *          The password confirmation that is entered.
         * @param inputEmail
         *          The email that is entered.
         */
        public MockView(String inputUsername, String inputFirstname, String inputLastname,
                String inputPassword, String inputPasswordConfirmation, String inputEmail) {
            this.username = inputUsername;
            this.firstname = inputFirstname;
            this.lastname = inputLastname;
            this.password = inputPassword;
            this.passwordConfirmation = inputPasswordConfirmation;
            this.email = inputEmail;
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
=======
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
>>>>>>> f34e4a542e55103d8ced0380b17c3acec07b55ab
}
