package cs2340.bobzilla.bobs_wallet.model;

public class RegistrationVerifier {
	
	public static boolean isUserNameValid(UserList userList, String userName) {
		if(userName.equals("")) {
			return false;
		}
		return true;
	}
	
	public static boolean isUserNameTaken(UserList userList, String userName) {
		return userList.isInUserListByUserName(userName);
	}
	
	public static boolean isfirstNameValid(String firstName) {
		if(firstName.equals("")) {
			return false;
		}
		
		return true;
	}
	
	public static boolean isLastNameValid(String lastName) {
		if(lastName.equals("")) {
			return false;
		}
		
		return true;
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
