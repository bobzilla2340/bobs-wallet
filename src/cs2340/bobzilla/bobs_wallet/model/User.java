package cs2340.bobzilla.bobs_wallet.model;

import java.util.HashMap;
import java.util.Map;

/**
 * This class provides a template for any user of Bob's Wallet.
 * It allows you to query and set for basic information about 
 * a user, namely, user name, first name, last name, password, and
 * e-mail.
 * 
 * @author Sai
 *
 */
public class User {
	
	private String userName;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private Map<String, FinanceAccount> accountMap;

	/**
	 * 
	 * @param userName The user's user name.
	 * @param firstName The user's first name.
	 * @param lastName The user's last name.
	 * @param password The user's password.
	 * @param email The user's email address.
	 */
	public User(String userName, String firstName, String lastName, String password, String email) {
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.accountMap = new HashMap<String, FinanceAccount>();
	}
	
	/**
	 * This method returns the user's user name.
	 * @return userName The user's user name.
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * This method returns the user's first name.
	 * @return firstName The user's first name.
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * This method returns the user's last name.
	 * @return lastName The user's last name.
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * This method returns the user's password.
	 * @return password The user's password.
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * This method returns the user's email address.
	 * @return email The user's email address.
	 */
	public String getEmail() {
		return email;
	}
	
	public Map<String, FinanceAccount> getFinanceAccountList() {
		return accountMap;
	}
	
	/**
	 * This method allows you to set the user's user name.
	 * @param userName The desired user name for the user.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	/**
	 * This method allows you to set the user's first name.
	 * @param firstName The desired first name for the user.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * This method allows you to set the user's last name.
	 * @param lastName The desired last name for the user. 
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * This method allows you to set the user's password.
	 * @param password The desired password for the user.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * This method allows you to set the user's email account.
	 * @param email The desired email for the user.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void addFinanceAccount(String accountName) {
		accountMap.put(accountName, new FinanceAccount(accountName));
	}
	
	public void removeFinanceAccount(String accountName) {
		accountMap.remove(accountName);
	}
	
	@Override
	public int hashCode() {
		return userName.hashCode() + firstName.hashCode() + lastName.hashCode() + email.hashCode();
	}
	
}
