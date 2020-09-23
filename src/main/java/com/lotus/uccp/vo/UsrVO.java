package  com.lotus.uccp.vo;

import java.util.Date;


public class UsrVO   {

	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public Date getLastpasswordresetdate() {
		return lastpasswordresetdate;
	}
	public void setLastpasswordresetdate(Date lastpasswordresetdate) {
		this.lastpasswordresetdate = lastpasswordresetdate;
	}
	public int getUserAuthorityId() {
		return userAuthorityId;
	}
	public void setUserAuthorityId(int userAuthorityId) {
		this.userAuthorityId = userAuthorityId;
	}
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String email;
	private Boolean enabled;
	private Date lastpasswordresetdate;
	private int userAuthorityId;

	private String result ;
	private String userRole ;
	
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}

}
