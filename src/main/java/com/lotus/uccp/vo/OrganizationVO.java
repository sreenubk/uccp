package  com.lotus.uccp.vo;

import java.math.BigDecimal;
import java.util.Date;


public class OrganizationVO   {

	private BigDecimal organizationId;
	private BigDecimal organizationUnitId;
	private BigDecimal parentOrganizationUnitId;
	private String createdby;
	private Date createdon;
	private String lastupdatedby;
	private Date lastupdatedon;
	
	public BigDecimal getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(BigDecimal organizationId) {
		this.organizationId = organizationId;
	}
	public BigDecimal getOrganizationUnitId() {
		return organizationUnitId;
	}
	public void setOrganizationUnitId(BigDecimal organizationUnitId) {
		this.organizationUnitId = organizationUnitId;
	}
	public BigDecimal getParentOrganizationUnitId() {
		return parentOrganizationUnitId;
	}
	public void setParentOrganizationUnitId(BigDecimal parentOrganizationUnitId) {
		this.parentOrganizationUnitId = parentOrganizationUnitId;
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
