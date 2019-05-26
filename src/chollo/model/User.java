package chollo.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {

	private long id;
	private String username;
	private String email;
	private String password;
	
	
	
	public  boolean comprobarCampos(String username, String email, String password) {
		
		String emailRegexp = "^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		String usernameRegexp = "[a-zA-Z1-9]";
		String passRegexp = "";
		
		
		return false;
	}
	
	public  boolean comprobarPasword(String password) {
		
		if (password.equals(this.password)) {
			return true;
		}
		
		return false;
		
	}
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

	
	
}
