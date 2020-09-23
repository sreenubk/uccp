package com.lotus.uccp.consentmgmt.dto;

import java.math.BigDecimal;
import java.util.Date;


@SuppressWarnings("serial")
public class ConsentGroupTxn implements java.io.Serializable {

	private BigDecimal consentgrouptxnid;
	private Transaction transaction;
	private ConsentGroup cosdconsentgroup;
	private String createdby;
	private Date createdon;
	private String lastupdatedby;
	private Date lastupdatedon;

	public ConsentGroupTxn() {
	}

	public ConsentGroupTxn(BigDecimal consentgrouptxnid, Transaction transaction,
			ConsentGroup cosdconsentgroup) {
		this.consentgrouptxnid = consentgrouptxnid;
		this.transaction = transaction;
		this.cosdconsentgroup = cosdconsentgroup;
	}

	public ConsentGroupTxn(BigDecimal consentgrouptxnid, Transaction transaction,
			ConsentGroup cosdconsentgroup, String createdby, Date createdon, String lastupdatedby,
			Date lastupdatedon) {
		this.consentgrouptxnid = consentgrouptxnid;
		this.transaction = transaction;
		this.cosdconsentgroup = cosdconsentgroup;
		this.createdby = createdby;
		this.createdon = createdon;
		this.lastupdatedby = lastupdatedby;
		this.lastupdatedon = lastupdatedon;
	}

	public BigDecimal getConsentgrouptxnid() {
		return this.consentgrouptxnid;
	}

	public void setConsentgrouptxnid(BigDecimal consentgrouptxnid) {
		this.consentgrouptxnid = consentgrouptxnid;
	}

	public Transaction getTransaction() {
		return this.transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public ConsentGroup getConsentGroup() {
		return this.cosdconsentgroup;
	}

	public void setConsentGroup(ConsentGroup cosdconsentgroup) {
		this.cosdconsentgroup = cosdconsentgroup;
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
