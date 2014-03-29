package cs2340.bobzilla.bobs_wallet.model;

/**
 * The UserListSingleton class is used to access the UserList
 * data structure.
 * @author Farhan
 */
public class UserListSingleton {
	
	private static UserListSingleton userListSingleton;
	private UserList userList;

    /**
     * Creates a new UserListSingleton object.
     */
	public UserListSingleton() {
		userList = new UserList();
	}

    /**
     * Gets the userListSingleton
     * @return userListSingleton
     */
	public static UserListSingleton getInstance() {
		if(userListSingleton == null) {
			userListSingleton = new UserListSingleton();
		}
		return userListSingleton;
	}

    /**
     * Gets the user list.
     * @return userList
     */
	public UserList getUserList() {
		return userList;
	}
	
}
