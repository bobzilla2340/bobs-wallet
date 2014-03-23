package cs2340.bobzilla.bobs_wallet.model;

import java.io.Serializable;
import java.util.HashMap;

public class UserList implements UserListModel, Serializable {

	private static final long serialVersionUID = -2882484650116333101L;
	private HashMap<String, User> userList;
	
	public UserList() {
		userList = new HashMap<String, User>();
	}
	
	@Override
	public void addUser(User user) {
		userList.put(user.getUserName(), user);
	}
	
	@Override
	public void removeUser(User user) {
		userList.remove(user.getUserName());
	}
	
	@Override
	public User getUser(String userName) {
		return userList.get(userName);
	}
	
	@Override
	public boolean isInUserListByUserObject(User user) {
		return userList.containsValue(user);
	}
	
	@Override
	public boolean isInUserListByUserName(String user) {
		return userList.containsKey(user);
	}
	
	@Override
	public int size() {
		return userList.size();
	}
	
}
