package cs2340.bobzilla.bobs_wallet.model;

//import java.util.LinkedList;
import java.util.HashMap;
import java.util.Map;
//import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class UserList implements Parcelable{
	
	//public List<User> userList;
	public Map<String, User> userList;
	
	public UserList() {
		userList = new HashMap<String, User>();
	}
	
	private UserList(Parcel in) {
		int numElements = in.readInt();
		userList = new HashMap<String, User>();
		
		for(int i = 0; i < numElements; i++) {
			userList.put(in.readString(), (User)in.readParcelable(User.class.getClassLoader()));
		}
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
	
	@Override
	public int describeContents() {
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel out, int flags) {
		final int numElements = userList.size();
		out.writeInt(numElements);
		
		if(numElements > 0) {
			for(Map.Entry<String, User> entry: userList.entrySet()) {
				out.writeString(entry.getKey());
				out.writeParcelable(entry.getValue(), entry.getValue().describeContents());
			}
		}
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
