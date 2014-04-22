package cs2340.bobzilla.bobs_wallet.model;

import com.parse.ParseUser;

public class CurrentUser {
    private static User currentUser;
    public static User getCurrentUser() {
        return currentUser;
    }
    
    public static void setCurrentUser(User user) {
        currentUser = user;
    }
    
    public static void createCurrentUser(String userName, String firstName, String lastName,
            String email) {
        User user = new User(userName, firstName, lastName, email);
        setCurrentUser(user);
    }
    
    public static void createCurrentUser() {
        ParseUser parseUser = ParseUser.getCurrentUser();
        String userName = parseUser.getUsername();
        String firstName = (String) parseUser.get(User.parseFirstNameKey);
        String lastName = (String) parseUser.get(User.parseLastNameKey);
        String email = (String) parseUser.getEmail();
        User user = new User(userName, firstName, lastName, email);
        user.setObjectId(parseUser.getObjectId());
        
        setCurrentUser(user);
    }
    
}
