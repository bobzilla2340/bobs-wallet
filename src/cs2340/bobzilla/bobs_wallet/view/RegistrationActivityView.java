package cs2340.bobzilla.bobs_wallet.view;

/**
 * This is an interface between the presenter
 * and the view that pulls user interface 
 * elements from the activity. 
 * 
 * @author sai
 *
 */
public interface RegistrationActivityView {
	
	/**
	 * This method gets the user name from
	 * the activity.
	 * @return String username the username that
	 * was typed in
	 */
	public String getUserName();
	
	/**
	 * This method gets the user's first name
	 * from the activity.
	 * @return String firstName, the user's
	 * first name.
	 */
	public String getFirstName();
	
	/**
	 * This method gets the user's last name
	 * from the activity.
	 * @return String lastName, the user's
	 * last name.
	 */
	public String getLastName();
	
	/**
	 * This method gets the user's passoword form
	 * the activity.
	 * @return String password, the user's password.
	 */
	public String getPassword();
	
	/**
	 * This method gets the user's confirmation
	 * password from the activity.
	 * @return String confirmationPassword, the users'
	 * confirmation password.
	 */
	public String getPasswordConfirmation();
	
	/**
	 * This method gets the user's email
	 * address from the activity.
	 * @return String email address, the user's
	 * email address.
	 */
	public String getEmailAddress();
}
