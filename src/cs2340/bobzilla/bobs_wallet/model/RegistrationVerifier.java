package cs2340.bobzilla.bobs_wallet.model;

/**
 * RegistrationVerifier is used to verify registrations.
 * 
 * @author Julia
 * 
 */
public class RegistrationVerifier {

    /**
     * Ensures the user name is not an empty string. Everything else is valid.
     * 
     * @param userList
     *          The list of users.
     * @param userName
     *          The user name to search for.
     * @return true if the user name is valid, false if otherwise
     */
    public static boolean isUserNameValid(String userName) {
        return !userName.equals("");
    }

    /**
     * Ensures the first name is not an empty string.
     * 
     * @param firstName
     *          User's first name.
     * @return true if not an empty string, false if otherwise
     */
    public static boolean isfirstNameValid(String firstName) {
        return !firstName.equals("");
    }

    /**
     * Ensures the last name is not an empty string (valid).
     * 
     * @param lastName
     *          User's last name.
     * @return true if not an empty string, false if otherwise
     */
    public static boolean isLastNameValid(String lastName) {
        return !lastName.equals("");
    }

    /**
     * Checks to make sure neither of the password spots are empty and that they
     * both match.
     * 
     * @param password
     *          User's password.
     * @param confirmation
     *          Confirmation password the user entered in.
     * @return true if they match & are valid, false if otherwise
     */
    public static boolean checkPasswordMatch(String password,
            String confirmation) {
        return !(password.equals("") || confirmation.equals(""))
                && password.equals(confirmation);
    }

    /**
     * Ensures the e-mail matches proper e-mail formatting.
     * 
     * @param email
     *          The user's email address.
     * @return true if valid e-mail, false if otherwise
     */
    public static boolean verifyEmail(String email) {
        return email.matches("[a-zA-Z0-9]*@[a-zA-Z0-9]*.(com|edu|net|org)");
    }
}
