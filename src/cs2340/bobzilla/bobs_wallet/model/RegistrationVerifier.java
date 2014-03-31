package cs2340.bobzilla.bobs_wallet.model;
/**
 * RegistrationVerifier is used to verify registrations.
 * @author Julia
 *
 */
public class RegistrationVerifier {
	
	/**
	 * Ensures the user name is not an empty string. Everything
	 * else is valid.
	 * 
	 * @param userList
	 * @param userName
	 * @return true if the user name is valid, false if otherwise
	 */
	public static boolean isUserNameValid(UserList userList, String userName) {
		if(userName.equals("")) {
			return false;
		}
		return true;
	}
	
	/**
	 * Ensures the entered user name is not already taken as a
	 * username.
	 * 
	 * @param userList
	 * @param userName
	 * @return true if userName is taken, false if otherwise
	 */
	public static boolean isUserNameTaken(UserList userList, String userName) {
		return userList.isInUserListByUserName(userName);
	}
	
	/**
	 * Ensures the first name is not an empty string.
	 * 
	 * @param firstName
	 * @return true if not an empty string, false if otherwise
	 */
	public static boolean isfirstNameValid(String firstName) {
		if(firstName.equals("")) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Ensures the last name is not an empty string (valid).
	 * 
	 * @param lastName
	 * @return true if not an empty string, false if otherwise
	 */
	public static boolean isLastNameValid(String lastName) {
		if(lastName.equals("")) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Checks to make sure neither of the password spots are empty and that
	 * they both match.
	 * 
	 * @param password
	 * @param confirmation
	 * @return true if they match & are valid, false if otherwise
	 */
	public static boolean checkPasswordMatch(String password, String confirmation) {
		if(password.equals("") || confirmation.equals("")) {
			return false;
		}
		return password.equals(confirmation);
	}
	
	/**
	 * Ensures the e-mail matches proper e-mail formatting.
	 * 
	 * @param email
	 * @return true if valid e-mail, false if otherwise
	 */
	public static boolean verifyEmail(String email) {
		return email.matches("[a-zA-Z0-9]*@[a-zA-Z0-9]*.(com|edu|net|org)");
	}
}
