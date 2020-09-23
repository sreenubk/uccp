package  com.lotus.uccp.consentmgmt.dto;

public class UserAuthority implements java.io.Serializable {

	private int userId;
	private Authority authority;
	private Usr usr;

	public UserAuthority() {
	}

	public UserAuthority(Usr usr) {
		this.usr = usr;
	}

	public UserAuthority(Authority authority, Usr usr) {
		this.authority = authority;
		this.usr = usr;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Authority getAuthority() {
		return this.authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

	public Usr getUsr() {
		return this.usr;
	}

	public void setUsr(Usr usr) {
		this.usr = usr;
	}

}
