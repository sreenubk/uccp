package com.lotus.uccp.authentication.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.lotus.uccp.util.Constant;

@Entity
@Table(name = "users", schema = Constant.SCHEMA )
public class Users implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private String rolename;
	private String firstname;
	private String lastname;
	private String email;
	private BigDecimal orgUnitId;
	private String orgName;
	private Set<AuthorizationGroup> cosdauthorizationgroups = new HashSet<AuthorizationGroup>(0);

	public Users() {
	}

	public Users(String username) {
		this.username = username;
	}

	public Users(String username, String password, String rolename, String firstname, String lastname, String email,
			BigDecimal orgUnitId, String orgName,	Set<AuthorizationGroup> cosdauthorizationgroups) {
		this.username = username;
		this.password = password;
		this.rolename = rolename;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.orgUnitId = orgUnitId;
		this.orgName = orgName;
		this.cosdauthorizationgroups = cosdauthorizationgroups;
	}

	@Id
	@Column(name = "username", unique = true, nullable = false, length = 64)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", length = 60)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "rolename", length = 50)
	public String getRolename() {
		return this.rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	@Column(name = "firstname", length = 50)
	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@Column(name = "lastname", length = 50)
	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Column(name = "email", length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	public Set<AuthorizationGroup> getAuthorizationGroups() {
		return this.cosdauthorizationgroups;
	}

	public void setAuthorizationGroups(Set<AuthorizationGroup> cosdauthorizationgroups) {
		this.cosdauthorizationgroups = cosdauthorizationgroups;
	}
	
	@Column(name = "ORGANISATIONUNITID", length = 50)
	public BigDecimal getOrgUnitId() {
		return orgUnitId;
	}

	public void setOrgUnitId(BigDecimal orgUnitId) {
		this.orgUnitId = orgUnitId;
	}
	
	@Column(name = "ORGNAME", length = 50)
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
}
