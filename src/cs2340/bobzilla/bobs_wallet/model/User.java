package cs2340.bobzilla.bobs_wallet.model;

public class User {
	
	private String userName;
	private String firstName;
	private String lastName;
	private String password;
	
	public User(String userName, String firstName, String lastName, String password) {
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
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
}
