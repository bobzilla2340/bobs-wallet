package cs2340.bobzilla.bobs_wallet.view;

/**
 * Declares the interface for a login activity. Has methods for getting relevant
 * data for the login activity.
 *
 * @author jack
 */
public interface LoginActivityView {

    /**
     * Get the username.
     *
     * @return the username as a String
     */
    String getUserName();

    /**
     * Get the user's password.
     *
     * @return the password as a String
     */
    String getPassword();
}
