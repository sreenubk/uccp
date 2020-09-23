package  com.lotus.uccp.consentmgmt.dto;

import java.util.HashSet;
import java.util.Set;

public class Authority implements java.io.Serializable {

	private int id;
	private String name;
	private Set<UserAuthority> userAuthorities = new HashSet<UserAuthority>(0);

	public Authority() {
	}

	public Authority(int id) {
		this.id = id;
	}

	public Authority(int id, String name, Set<UserAuthority> userAuthorities) {
		this.id = id;
		this.name = name;
		this.userAuthorities = userAuthorities;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<UserAuthority> getUserAuthorities() {
		return this.userAuthorities;
	}

	public void setUserAuthorities(Set<UserAuthority> userAuthorities) {
		this.userAuthorities = userAuthorities;
	}

}
