package  com.lotus.uccp.vo;

import java.math.BigDecimal;
import java.util.Date;

public class ConsentGroupTxnVO   {

	private BigDecimal consentgrouptxnid;
	public BigDecimal getConsentgrouptxnid() {
		return consentgrouptxnid;
	}

	public void setConsentgrouptxnid(BigDecimal consentgrouptxnid) {
		this.consentgrouptxnid = consentgrouptxnid;
	}

	public long getTransaction() {
		return transaction;
	}

	public void setTransaction(long transaction) {
		this.transaction = transaction;
	}

	public long getConsentGroup() {
		return consentGroup;
	}

	public void setConsentGroup(long consentGroup) {
		this.consentGroup = consentGroup;
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

	private long transaction;
	private long consentGroup;
	private String createdby;
	private Date createdon;
	private String lastupdatedby;
	private Date lastupdatedon;

	public ConsentGroupTxnVO() {
	}

	

}
