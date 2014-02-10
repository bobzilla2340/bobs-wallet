package cs2340.bobzilla.bobs_wallet.model;

public class LoginVerifier {
	
	public static boolean verifyUserName(UserList userList, String userName) {
		return userList.isInUserListByUserName(userName);
	}
	
	public static boolean veirfyUserPassword(UserList userList, String userName, String password) {
		return userList.getUser(userName).getPassword().equals(password);
	}
	
}
