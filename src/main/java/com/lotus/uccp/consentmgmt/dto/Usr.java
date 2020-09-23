package  com.lotus.uccp.consentmgmt.dto;

import java.util.Date;

@SuppressWarnings("serial")
public class Usr implements java.io.Serializable {

	private int id;
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String email;
	private Boolean enabled;
	private Date lastpasswordresetdate;
	private UserAuthority userAuthority;

	public Usr() {
	}

	public Usr(int id) {
		this.id = id;
	}

	public Usr(int id, String username, String password, String firstname, String lastname, String email,
			Boolean enabled, Date lastpasswordresetdate, UserAuthority userAuthority) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.enabled = enabled;
		this.lastpasswordresetdate = lastpasswordresetdate;
		this.userAuthority = userAuthority;
	}
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Date getLastpasswordresetdate() {
		return this.lastpasswordresetdate;
	}

	public void setLastpasswordresetdate(Date lastpasswordresetdate) {
		this.lastpasswordresetdate = lastpasswordresetdate;
	}

	public UserAuthority getUserAuthority() {
		return this.userAuthority;
	}

	public void setUserAuthority(UserAuthority userAuthority) {
		this.userAuthority = userAuthority;
	}

}
