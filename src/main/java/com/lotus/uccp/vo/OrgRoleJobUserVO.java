package  com.lotus.uccp.vo;


import java.math.BigDecimal;
import java.util.Date;


public class OrgRoleJobUserVO   {

	private BigDecimal orgrolejobuserid;
	public BigDecimal getOrgrolejobuserid() {
		return orgrolejobuserid;
	}

	public void setOrgrolejobuserid(BigDecimal orgrolejobuserid) {
		this.orgrolejobuserid = orgrolejobuserid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public BigDecimal getJobid() {
		return jobid;
	}

	public void setJobid(BigDecimal jobid) {
		this.jobid = jobid;
	}

	public BigDecimal getOrganisationunitid() {
		return organisationunitid;
	}

	public void setOrganisationunitid(BigDecimal organisationunitid) {
		this.organisationunitid = organisationunitid;
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

	private String username;
	private String rolename;
	private BigDecimal jobid;
	private BigDecimal organisationunitid;
	private String createdby;
	private Date createdon;
	private String lastupdatedby;
	private Date lastupdatedon;

	public OrgRoleJobUserVO() {
	}

	
}
