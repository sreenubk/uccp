package  com.lotus.uccp.vo;

import java.math.BigDecimal;
import java.util.Date;

public class AuthorizationGroupVO   {

	private String authGroupName;
	private BigDecimal authorizationGroupId;
	private long controlGroup;
	private long organizationUnit;
	private String securityRole;
	private String jobUserName ;
	private String organizationName ;
	private String controlGroupName ;
	private String job ;
	private String jobName ;
	private String users;
	private BigDecimal jobId;
	private String createdby;
	private Date createdon;
	private String lastupdatedby;
	private Date lastupdatedon;
	public String getAuthGroupName() {
		return authGroupName;
	}
	public void setAuthGroupName(String authGroupName) {
		this.authGroupName = authGroupName;
	}
	public BigDecimal getAuthorizationGroupId() {
		return authorizationGroupId;
	}
	public void setAuthorizationGroupId(BigDecimal authorizationGroupId) {
		this.authorizationGroupId = authorizationGroupId;
	}
	public long getControlGroup() {
		return controlGroup;
	}
	public void setControlGroup(long controlGroup) {
		this.controlGroup = controlGroup;
	}
	public long getOrganizationUnit() {
		return organizationUnit;
	}
	public void setOrganizationUnit(long organizationUnit) {
		this.organizationUnit = organizationUnit;
	}
	public String getSecurityRole() {
		return securityRole;
	}
	public void setSecurityRole(String securityRole) {
		this.securityRole = securityRole;
	}
	public String getJobUserName() {
		return jobUserName;
	}
	public void setJobUserName(String jobUserName) {
		this.jobUserName = jobUserName;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public String getControlGroupName() {
		return controlGroupName;
	}
	public void setControlGroupName(String controlGroupName) {
		this.controlGroupName = controlGroupName;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getUsers() {
		return users;
	}
	public void setUsers(String users) {
		this.users = users;
	}
	public BigDecimal getJobId() {
		return jobId;
	}
	public void setJobId(BigDecimal jobId) {
		this.jobId = jobId;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public Date getCreatedon() {
		return createdon;
	}
	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}
	public String getLastupdatedby() {
		return lastupdatedby;
	}
	public void setLastupdatedby(String lastupdatedby) {
		this.lastupdatedby = lastupdatedby;
	}
	public Date getLastupdatedon() {
		return lastupdatedon;
	}
	public void setLastupdatedon(Date lastupdatedon) {
		this.lastupdatedon = lastupdatedon;
	}
	
	
	
}
