package cs2340.bobzilla.bobs_wallet.model;

import java.util.HashMap;
import java.util.Map;

public class UserList {
	
	//public List<User> userList;
	public Map<String, User> userList;
	
	public UserList() {
		userList = new HashMap<String, User>();
	}
	
	public void addUser(User user) {
		userList.put(user.getUserName(), user);
	}
	
	public void removeUser(User user) {
		userList.remove(user.getUserName());
	}
	
	public User getUser(String userName) {
		return userList.get(userName);
	}
	
	public boolean isInUserListByUserObject(User user) {
		return userList.containsValue(user);
	}
	
	public boolean isInUserListByUserName(String user) {
		return userList.containsKey(user);
	}
	
	public int size() {
		return userList.size();
	}
	
}
