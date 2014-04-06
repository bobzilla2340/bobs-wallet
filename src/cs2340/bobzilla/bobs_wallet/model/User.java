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
     * This is the serial version ID for when the object
     * is written to the disk.
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
     * This is the constructor for the user object.
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
     * This method gets the map of all the accounts
     * that the user has created.
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
    public final void setFirstName(final String inputFirstName) {
        this.firstName = inputFirstName;
    }

    /**
     * This method allows you to set the user's last name.
     *
     * @param inputLastName
     *            The desired last name for the user.
     */
    public final void setLastName(final String inputLastName) {
        this.lastName = inputLastName;
    }

    /**
     * This method allows you to set the user's password.
     *
     * @param inputPassword
     *            The desired password for the user.
     */
    public final void setPassword(final String inputPassword) {
        this.password = inputPassword;
    }

    /**
     * This method allows you to set the user's email account.
     *
     * @param inputEmail
     *            The desired email for the user.
     */
    public final void setEmail(final String inputEmail) {
        this.email = inputEmail;
    }

    /**
     * This method adds a finance account to the user's list
     * of finance accounts.
     *
     * @param accountName
     *              The name of the account.
     * @param interest
     *              The interest the account acrews.
     */
    public final void addFinanceAccount(final String accountName,
            final double interest) {
        accountMap.put(accountName, new FinanceAccount(accountName, interest));
    }

    /**
     * This method removes a finance account.
     * @param accountName
     *              The name of the account to remove
     */
    public final void removeFinanceAccount(final String accountName) {
        accountMap.remove(accountName);
    }

    @Override
    public final int hashCode() {
        return userName.hashCode() + firstName.hashCode() + lastName.hashCode()
                + email.hashCode();
    }

    /**
     * Gets the collection that stores all of the accounts.
     * @return Gets a collection of the user's finance accounts.
     */
    public final Collection<FinanceAccount> getAccounts() {
        return accountMap.values();
    }

}
