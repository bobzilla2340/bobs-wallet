package cs2340.bobzilla.bobs_wallet.presenter;

import cs2340.bobzilla.bobs_wallet.exceptions.InvalidLoginException;
import cs2340.bobzilla.bobs_wallet.view.ClickListener;
import cs2340.bobzilla.bobs_wallet.view.LoginActivityView;

/**
 * A presenter class for the login activity.
 * 
 * @author jack
 */
public class LoginActivityPresenter implements ClickListener {

    /**
     * This is the view that is tied with this presenter.
     */
    private LoginActivityView loginActivityView;

    /**
     * Constructs the LoginActivityPresenter based on the corresponding view.
     * 
     * @param view
     *            the corresponding LoginActivityView.
     */
    public LoginActivityPresenter(final LoginActivityView view) {
        loginActivityView = view;
    }

    @Override
    public final void onClick() throws InvalidLoginException {
        String userName = loginActivityView.getUserName();
        String password = loginActivityView.getPassword();

        
//        ParseUser.logInInBackground(userName, password, new LogInCallback() {
//            public void done(ParseUser user, ParseException e) {
//                if (user != null) {
//                    System.out.println("user is not null");
//                    return;
//                }
//                else {
//                    // TODO: handle error case.
//                    // TODO: handle current user
//                    switch(e.getCode()) {
//                    case USERNAME_MISSING:
//                    case PASSWORD_MISSING:
//                    case OBJECT_NOT_FOUND:
//                        Toast.makeText(getActivity(), "Please enter a valid Username or Password!", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }
//        });

        if (userName.equals("") || password.equals((""))) {
            throw new InvalidLoginException();
        }
    }
}
