

/** Object to represent client user name and password */
public class SingleClient {
	private String username;
	private String  password;

	public SingleClient(String username, String password) {
		this.username = username;
		this.password = password;
	}

	//Getter for user name
	public String getUsername() {
		return  username; 
	}
	
	//Getter for password
		public String getPassword() {
			return  password; 
		}
}
