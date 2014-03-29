package cs2340.bobzilla.bobs_wallet.model;

/**
 * LoginVerifier is used to verify logins.
 * @author Farhan Khan
 */
public class LoginVerifier {

    /**
     * Verifies if the userName exists within the userList.
     * @param userList, the UserList userList
     * @param userName, the String userName
     * @return boolean T or F
     */
	public static boolean verifyUserName(UserList userList, String userName) {
		return userList.isInUserListByUserName(userName);
	}

    /**
     * Verifies if the password for the given userName equals the password for the
     * userName in the userList.
     * @param userList, the UserList userList
     * @param userName, the String userName
     * @param password, the String password
     * @return boolean T or F
     */
	public static boolean veirfyUserPassword(UserList userList, String userName, String password) {
		return userList.getUser(userName).getPassword().equals(password);
	}
	
}
