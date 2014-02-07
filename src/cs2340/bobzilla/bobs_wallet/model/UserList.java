package cs2340.bobzilla.bobs_wallet.model;

import java.util.LinkedList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class UserList implements Parcelable{
	
	private List<User> userList;
	
	public UserList() {
		userList = new LinkedList<User>();
	}
	
	private UserList(Parcel in) {
		userList = in.createTypedArrayList(User.CREATOR);
	}
	
	public void addUser(User user) {
		userList.add(user);
	}
	
	public void removeUser(User user) {
		userList.remove(user);
	}
	
	public boolean isInUserList(User user) {
		return userList.contains(user);
	}
	
	public List<User> getUserList() {
		return userList;
	}
	
	@Override
	public int describeContents() {
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeTypedList(userList);
	}
	
	public static final Parcelable.Creator<UserList> CREATOR
		= new Parcelable.Creator<UserList>() {
		
		public UserList createFromParcel(Parcel in) {
			return new UserList(in);
		}
		
		public UserList[] newArray(int size) {
			return new UserList[size];
		}
	};
}
