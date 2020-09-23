package  com.lotus.uccp.vo;

import java.math.BigDecimal;
import java.util.Date;


public class PersonConsentHistoryVO   {

	private BigDecimal consenthistoryid;
	public BigDecimal getConsenthistoryid() {
		return consenthistoryid;
	}
	public void setConsenthistoryid(BigDecimal consenthistoryid) {
		this.consenthistoryid = consenthistoryid;
	}
	public long getCosdpersonconsent() {
		return cosdpersonconsent;
	}
	public void setCosdpersonconsent(long cosdpersonconsent) {
		this.cosdpersonconsent = cosdpersonconsent;
	}
	public String getActiontype() {
		return actiontype;
	}
	public void setActiontype(String actiontype) {
		this.actiontype = actiontype;
	}
	public String getCancelreason() {
		return cancelreason;
	}
	public void setCancelreason(String cancelreason) {
		this.cancelreason = cancelreason;
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
	private long cosdpersonconsent;
	private String actiontype;
	private String cancelreason;
	private String createdby;
	private Date createdon;
	private String lastupdatedby;
	private Date lastupdatedon;

	
}
