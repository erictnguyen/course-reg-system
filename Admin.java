
public class Admin {

	private String username;
	private String password;

//	default constructor
	public Admin() {
		this.username = null;
		this.password = null;
	}
	
//	overloaded constructor
	public Admin(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
//	getter methods
	public String getUsername() {
		return this.username;
	}
	public String getPassword() {
		return this.password;
	}
	
//	setter methods
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
