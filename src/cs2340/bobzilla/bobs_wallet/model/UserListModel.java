package cs2340.bobzilla.bobs_wallet.model;

/**
 * This interface defines a contract to any class that keeps track or otherwise
 * can get access to the list of all users that use the application.
 * 
 * @author sai
 * 
 */
public interface UserListModel {

    /**
     * This method allows the addition of users.
     * 
     * @param user
     *            The user to be added.
     */
    void addUser(User user);

    /**
     * This method allows the access of a particular user.
     * 
     * @param user
     *            The user to be accessed.
     * @return the user object that corresponds to the user that was searched
     *         for.
     */
    User getUser(String user);

    /**
     * This method allows us to see if a user is in the user list by using the
     * user name stirng.
     * 
     * @param user
     *            The string of the user name.
     * @return A boolean indicating if the user is in the list or not.
     */
    boolean isInUserListByUserName(String user);

    /**
     * This method allows us to see if a user in the user list by using the user
     * object.
     * 
     * @param user
     *            The user object to search for.
     * @return A boolean indicating if the user is in the list or not.
     */
    boolean isInUserListByUserObject(User user);

    /**
     * This method allows us to remove a user.
     * 
     * @param user
     *            The user to remove from the list.
     */
    void removeUser(User user);

    /**
     * Gets the number of users that are registered.
     * 
     * @return The number of users that are registered.
     */
    int size();
}
