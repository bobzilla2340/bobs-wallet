package cs2340.bobzilla.bobs_wallet.model;

public class LoginVerifier {
	
	public static boolean verifyUserName(UserList userList, String userName) {
		if(userList == null) {
			return false;
		}
		else {
			for(User user: userList.getUserList()) {
				if(user.getUserName().equals(userName)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean veirfyUserPassword(UserList userList, String password) {
		if(userList == null) {
			return false;
		}
		else {
			for(User user: userList.getUserList()) {
				if(user.getPassword().equals(password)) {
					return true;
				}
			}
		}
		return false;
	}
	
}
