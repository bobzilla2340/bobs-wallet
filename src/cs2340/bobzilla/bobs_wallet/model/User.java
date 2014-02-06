package cs2340.bobzilla.bobs_wallet.model;

public class User {
	
	private String userName;
	private String firstName;
	private String lastName;
	private String password;
	private String email;

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
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
}
