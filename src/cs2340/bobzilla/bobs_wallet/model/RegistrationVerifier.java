package cs2340.bobzilla.bobs_wallet.model;

public class RegistrationVerifier {
	
	public static boolean isUserNameValid(UserList userList, String userName) {
        return !userName.equals("");
	}
	
	public static boolean isUserNameTaken(UserList userList, String userName) {
		return userList.isInUserListByUserName(userName);
	}
	
	public static boolean isfirstNameValid(String firstName) {
        return !firstName.equals("");
	}
	
	public static boolean isLastNameValid(String lastName) {
        return !lastName.equals("");
	}
	
	public static boolean checkPasswordMatch(String password, String confirmation) {
        return !(password.equals("") || confirmation.equals(""))
                && password.equals(confirmation);
	}
	
	public static boolean verifyEmail(String email) {
		return email.matches("[a-zA-Z0-9]*@[a-zA-Z0-9]*.(com|edu|net|org)");
	}
}
