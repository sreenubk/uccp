package com.lotus.uccp.consentmgmt.dto;

import java.math.BigDecimal;
import java.util.Date;

@SuppressWarnings("serial")
public class PersonConsentHistory implements java.io.Serializable {

	private BigDecimal consenthistoryid;
	private PersonConsent personConsent;
	private String actiontype;
	private String cancelreason;
	private String createdby;
	private Date createdon;
	private String lastupdatedby;
	private Date lastupdatedon;

	public PersonConsentHistory() {
	}

	public PersonConsentHistory(BigDecimal consenthistoryid) {
		this.consenthistoryid = consenthistoryid;
	}

	public PersonConsentHistory(BigDecimal consenthistoryid, PersonConsent personConsent, String actiontype,
			String cancelreason, String createdby, Date createdon, String lastupdatedby, Date lastupdatedon) {
		this.consenthistoryid = consenthistoryid;
		this.personConsent = personConsent;
		this.actiontype = actiontype;
		this.cancelreason = cancelreason;
		this.createdby = createdby;
		this.createdon = createdon;
		this.lastupdatedby = lastupdatedby;
		this.lastupdatedon = lastupdatedon;
	}

	public BigDecimal getConsenthistoryid() {
		return this.consenthistoryid;
	}

	public void setConsenthistoryid(BigDecimal consenthistoryid) {
		this.consenthistoryid = consenthistoryid;
	}

	public PersonConsent getPersonConsent() {
		return this.personConsent;
	}

	public void setPersonConsent(PersonConsent personConsent) {
		this.personConsent = personConsent;
	}

	public String getActiontype() {
		return this.actiontype;
	}

	public void setActiontype(String actiontype) {
		this.actiontype = actiontype;
	}

	public String getCancelreason() {
		return this.cancelreason;
	}

	public void setCancelreason(String cancelreason) {
		this.cancelreason = cancelreason;
	}

	public String getCreatedby() {
		return this.createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public Date getCreatedon() {
		return this.createdon;
	}

	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}

	public String getLastupdatedby() {
		return this.lastupdatedby;
	}

	public void setLastupdatedby(String lastupdatedby) {
		this.lastupdatedby = lastupdatedby;
	}

	public Date getLastupdatedon() {
		return this.lastupdatedon;
	}

	public void setLastupdatedon(Date lastupdatedon) {
		this.lastupdatedon = lastupdatedon;
	}

}
