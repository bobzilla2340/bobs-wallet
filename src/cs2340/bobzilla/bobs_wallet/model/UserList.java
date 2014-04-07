package cs2340.bobzilla.bobs_wallet.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.Map.Entry;

/**
 * The UserList class maps String userNames to User objects with a HashMap.
 * 
 * @author Farhan
 */
public class UserList implements UserListModel, Serializable {

    /**
     * This is the serial version ID for when the application is being saved to
     * the disk.
     */
    private static final long serialVersionUID = -2882484650116333101L;
    /**
     * This is the data structure that keeps track of the users.
     */
    private HashMap<String, User> userList;

    /**
     * Constructs a new UserList object.
     */
    public UserList() {
        userList = new HashMap<String, User>();
    }

    @Override
    public final void addUser(final User user) {
        userList.put(user.getUserName(), user);
    }

    @Override
    public final void removeUser(final User user) {
        userList.remove(user.getUserName());
    }

    @Override
    public final User getUser(final String userName) {
        return userList.get(userName);
    }

    @Override
    public final boolean isInUserListByUserObject(final User user) {
        return userList.containsValue(user);
    }

    @Override
    public final boolean isInUserListByUserName(final String user) {
        return userList.containsKey(user);
    }

    @Override
    public final int size() {
        return userList.size();
    }

    /**
     * Gets a list of User objects.
     * 
     * @return a userList
     */
    public final List<User> getUserSet() {
        List<User> returnList = new LinkedList<User>();
        for (Entry<String, User> entry : userList.entrySet()) {
            returnList.add(entry.getValue());
        }
        return returnList;
    }
}
