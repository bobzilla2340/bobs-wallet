package cs2340.bobzilla.bobs_wallet.model;

public class RegistrationVerifier {
	public static boolean isUserNameTaken(UserList userList, String userName) {
		return userList.isInUserListByUserName(userName);
	}
	
	public static boolean checkPasswordMatch(String password, String confirmation) {
		if(password.equals("") || confirmation.equals("")) {
			return false;
		}
		return password.equals(confirmation);
	}
	
	public static boolean verifyEmail(String email) {
		return email.matches("[a-zA-Z0-9]*@[a-zA-Z0-9]*.(com|edu|net|org)");
	}
}
