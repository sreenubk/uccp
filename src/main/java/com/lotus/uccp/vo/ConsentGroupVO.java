package  com.lotus.uccp.vo;

import java.math.BigDecimal;
import java.util.Date;

public class ConsentGroupVO   {

	private BigDecimal consentgroupid;
	public BigDecimal getConsentgroupid() {
		return consentgroupid;
	}

	public void setConsentgroupid(BigDecimal consentgroupid) {
		this.consentgroupid = consentgroupid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	private String description;
	private String name;
	private String createdby;
	private Date createdon;
	private String lastupdatedby;
	private Date lastupdatedon;
	
	public ConsentGroupVO() {
	}

	
}
