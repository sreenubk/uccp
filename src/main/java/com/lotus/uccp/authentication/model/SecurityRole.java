package com.lotus.uccp.authentication.model;

import com.lotus.uccp.util.Constant;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "securityrole", schema = Constant.SCHEMA )
public class SecurityRole implements java.io.Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String roleName;
	private String orgName;
	private String jobnName;
	private String roleLevel;
	private Date lastwritten;

	@Id
	@Column(name = "rolename", unique = true, nullable = false, length = 50)

	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	@Column(name = "orgname", length = 50)
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getJobnName() {
		return jobnName;
	}
	public void setJobnName(String jobnName) {
		this.jobnName = jobnName;
	}

	@Column(name = "ROLELEVEL", length = 50)
	public String getRoleLevel() {
		return roleLevel;
	}
	public void setRoleLevel(String roleLevel) {
		this.roleLevel = roleLevel;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "lastwritten", length = 13)
	public Date getLastwritten() {
		return lastwritten;
	}
	public void setLastwritten(Date lastwritten) {
		this.lastwritten = lastwritten;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}





}

