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

        if (userName.equals("") || password.equals((""))) {
            throw new InvalidLoginException();
        }
    }
}
