package cs2340.bobzilla.bobs_wallet.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * This class provides a template for any user of Bob's Wallet. It allows you to
 * query and set for basic information about a user, namely, user name, first
 * name, last name, password, and e-mail.
 * 
 * @author Sai
 * 
 */
public class User implements Serializable {

    /**
     * This is the serial version ID for when the object is written to the disk.
     */
    private static final long serialVersionUID = -2202715094107986888L;
    /**
     * This is the user's user name.
     */
    private String userName;
    /**
     * This is the user's first name.
     */
    private String firstName;
    /**
     * This is the user's last name.
     */
    private String lastName;
    /**
     * This is the user's password.
     */
    private String password;
    /**
     * This is the user's email address.
     */
    private String email;
    /**
     * This keeps a map for all the accounts the user has.
     */
    private HashMap<String, FinanceAccount> accountMap;
    /**
     * This string is the object id of the user in the server.
     */
    private String parseObjectId;

    /**
     * This is the constructor for the user object.
     * 
     * @param inputUserName
     *            The user's user name.
     * @param inputFirstName
     *            The user's first name.
     * @param inputLastName
     *            The user's last name.
     * @param inputPassword
     *            The user's password.
     * @param inputEmail
     *            The user's email address.
     */
    public User(final String inputUserName, final String inputFirstName,
            final String inputLastName, final String inputPassword,
            final String inputEmail) {
        this.userName = inputUserName;
        this.firstName = inputFirstName;
        this.lastName = inputLastName;
        this.password = inputPassword;
        this.email = inputEmail;
        this.accountMap = new HashMap<String, FinanceAccount>();
    }

    /**
     * This method returns the user's user name.
     * 
     * @return userName The user's user name.
     */
    public final String getUserName() {
        return userName;
    }

    /**
     * This method returns the user's first name.
     * 
     * @return firstName The user's first name.
     */
    public final String getFirstName() {
        return firstName;
    }

    /**
     * This method returns the user's last name.
     * 
     * @return lastName The user's last name.
     */
    public final String getLastName() {
        return lastName;
    }

    /**
     * This method returns the user's password.
     * 
     * @return password The user's password.
     */
    public final String getPassword() {
        return password;
    }

    /**
     * This method returns the user's email address.
     * 
     * @return email The user's email address.
     */
    public final String getEmail() {
        return email;
    }

    /**
     * This method gets the map of all the accounts that the user has created.
     * 
     * @return The list of finance accounts.
     */
    public final Map<String, FinanceAccount> getFinanceAccountList() {
        return accountMap;
    }

    /**
     * This method allows you to set the user's user name.
     * 
     * @param inputUserName
     *            The desired user name for the user.
     */
    public final void setUserName(final String inputUserName) {
        this.userName = inputUserName;
    }

    /**
     * This method allows you to set the user's first name.
     * 
     * @param inputFirstName
     *            The desired first name for the user.
     */
    public void setFirstName(String inputFirstName) {
        this.firstName = inputFirstName;
    }

    /**
     * This method allows you to set the user's last name.
     * 
     * @param inputLastName
     *            The desired last name for the user.
     */
    public void setLastName(String inputLastName) {
        this.lastName = inputLastName;
    }

    /**
     * This method allows you to set the user's password.
     * 
     * @param inputPassword
     *            The desired password for the user.
     */
    public void setPassword(String inputPassword) {
        this.password = inputPassword;
    }

    /**
     * This method allows you to set the user's email account.
     * 
     * @param inputEmail
     *            The desired email for the user.
     */
    public void setEmail(String inputEmail) {
        this.email = inputEmail;
    }

    /**
     * This method adds a finance account to the user.
     * @param accountName
     *          The name of the account the user wants to add.
     * @param interest
     *          The interest rate that the account acrews.
     */
    public void addFinanceAccount(String accountName, double interest) {
        accountMap.put(accountName, new FinanceAccount(accountName, interest));
    }
    
    /**
     * This method removes the finance account.
     * @param accountName
     *      
     */
    public void removeFinanceAccount(String accountName) {
        accountMap.remove(accountName);
    }

    @Override
    public int hashCode() {
        return userName.hashCode() + firstName.hashCode() + lastName.hashCode()
                + email.hashCode();
    }

    public Collection<FinanceAccount> getAccounts() {
        return accountMap.values();
    }
    
    public String getObjectId() {
        return parseObjectId;
    }
    
    public void setObjectId(String objectId) {
        parseObjectId = objectId;
    }
}
