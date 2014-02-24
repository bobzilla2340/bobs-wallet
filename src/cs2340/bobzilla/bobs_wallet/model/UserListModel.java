package cs2340.bobzilla.bobs_wallet.model;

public interface UserListModel {
	public void addUser(User user);
	public User getUser(String user);
	public boolean isInUserListByUserName(String user);
	public boolean isInUserListByUserObject(User user);
	public void removeUser(User user);
	public int size();
}
