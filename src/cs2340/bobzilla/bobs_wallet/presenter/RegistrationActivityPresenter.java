package cs2340.bobzilla.bobs_wallet.presenter;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import cs2340.bobzilla.bobs_wallet.exceptions.InvalidRegistrationException;
import cs2340.bobzilla.bobs_wallet.model.RegistrationVerifier;
import cs2340.bobzilla.bobs_wallet.model.User;
import cs2340.bobzilla.bobs_wallet.model.UserList;
import cs2340.bobzilla.bobs_wallet.model.UserListSingleton;
import cs2340.bobzilla.bobs_wallet.view.ClickListener;
import cs2340.bobzilla.bobs_wallet.view.RegistrationActivityView;

/**
 * A presenter class for the registration activity.
 * 
 * @author jack
 */
public class RegistrationActivityPresenter implements ClickListener {
    /**
     * This is the activity view that is tied to this listener.
     */
    private RegistrationActivityView registrationActivityView;

    /**
     * Constructs the RegistrationActivityPresenter based on the corresponding
     * view.
     * 
     * @param view
     *            the corresponding view.
     */
    public RegistrationActivityPresenter(final RegistrationActivityView view) {
        registrationActivityView = view;
    }

    @Override
    public final void onClick() throws InvalidRegistrationException {
        String userName = registrationActivityView.getUserName();
        String firstName = registrationActivityView.getFirstName();
        String lastName = registrationActivityView.getLastName();
        String password = registrationActivityView.getPassword();
        String confirmation = registrationActivityView
                .getPasswordConfirmation();
        String email = registrationActivityView.getEmailAddress();

        UserList userList = UserListSingleton.getInstance().getUserList();

        if (!RegistrationVerifier.isUserNameValid(userList, userName)) {
            throw new InvalidRegistrationException("Please enter a user name!");
        } else if (RegistrationVerifier.isUserNameTaken(userList, userName)) {
            throw new InvalidRegistrationException("That username is taken!");
        } else if (!RegistrationVerifier.isfirstNameValid(firstName)) {
            throw new InvalidRegistrationException("Please enter a first name!");
        } else if (!RegistrationVerifier.isLastNameValid(lastName)) {
            throw new InvalidRegistrationException("Please enter a last name!");
        } else if (!RegistrationVerifier.checkPasswordMatch(password,
                confirmation)) {
            throw new InvalidRegistrationException(
                    "Password and Confirmation do not match!");
        } else if (!RegistrationVerifier.verifyEmail(email)) {
            throw new InvalidRegistrationException("Please enter a vaid email!");
        } else {
            createUser(userList, userName, firstName, lastName, password, email);
        }
    }

    /**
     * Registers a new user.
     * 
     * @param userList
     *            the userList in which to place the new user
     * @param userName
     *            the desired username
     * @param firstName
     *            the user's first name
     * @param lastName
     *            the user's last name
     * @param password
     *            the desired password
     * @param email
     *            the user's email address
     */
    private void createUser(final UserList userList, final String userName,
            final String firstName, final String lastName,
            final String password, final String email) {
        //TODO: cloud-based everything. No more local storage.
        
        // Add user to local list
        User newUser = new User(userName, firstName, lastName, password, email);
        userList.addUser(newUser);
        
        // Add user to cloud
        createParseUser(newUser);
        
    }
    
    private void createParseUser(final User localUser) {
        // Create ParseUser object
        final ParseUser user = new ParseUser();
        user.setEmail(localUser.getEmail());
        user.setUsername(localUser.getUserName());
        user.setPassword(localUser.getPassword());
        user.put("firstName", localUser.getFirstName());
        user.put("lastName", localUser.getLastName());
        
     // Sign user up in non-blocking manner
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    localUser.setObjectId(user.getObjectId());
                }
                //TODO: handle error case.
            }
        });
    }
}
