package cs2340.bobzilla.bobs_wallet.model;

public class UserListSingleton {
	
	private static UserListSingleton userListSingleton;
	private UserList userList;
	
	public UserListSingleton() {
		userList = new UserList();
	}
	
	public static UserListSingleton getInstance() {
		if(userListSingleton == null) {
			userListSingleton = new UserListSingleton();
		}
		return userListSingleton;
	}
	
	public UserList getUserList() {
		return userList;
	}
	
}
