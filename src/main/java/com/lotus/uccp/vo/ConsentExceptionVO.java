package  com.lotus.uccp.vo;

import java.math.BigDecimal;
import java.util.Date;


public class ConsentExceptionVO   {

	private BigDecimal consentexceptionid;
	public BigDecimal getConsentexceptionid() {
		return consentexceptionid;
	}
	public void setConsentexceptionid(BigDecimal consentexceptionid) {
		this.consentexceptionid = consentexceptionid;
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
	public Date getLastupdatedo() {
		return lastupdatedo;
	}
	public void setLastupdatedo(Date lastupdatedo) {
		this.lastupdatedo = lastupdatedo;
	}
	private long consentGroup;
	private long personConsent;
	private String createdby;
	private Date createdon;
	private String lastupdatedby;
	private Date lastupdatedo;
	private String  consentstatus;
	
	public String getConsentstatus() {
		return consentstatus;
	}

	public void setConsentstatus(String consentstatus) {
		this.consentstatus = consentstatus;
	}
	public long getConsentGroup() {
		return consentGroup;
	}
	public void setConsentGroup(long consentGroup) {
		this.consentGroup = consentGroup;
	}
	public long getPersonConsent() {
		return personConsent;
	}
	public void setPersonConsent(long personConsent) {
		this.personConsent = personConsent;
	}
}
