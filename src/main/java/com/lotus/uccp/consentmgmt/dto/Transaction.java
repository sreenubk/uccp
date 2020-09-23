package com.lotus.uccp.consentmgmt.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class Transaction implements java.io.Serializable {
	private BigDecimal transactionid;
	private String transactiontype;
	private String transactionname;
	private String defaultaccess;
	private String createdby;
	private Date createdon;
	private String lastupdatedby;
	private Date lastupdatedon;
	private byte versionno;
	private Set<ConsentGroupTxn> consentGroupTxns = new HashSet<ConsentGroupTxn>(0);
	private Set<ControlObject> controlObjects = new HashSet<ControlObject>(0);

	public Transaction() {
	}

	public Transaction(BigDecimal transactionid, String transactiontype, String transactionname,
			String defaultaccess, byte versionno) {
		this.transactionid = transactionid;
		this.transactiontype = transactiontype;
		this.transactionname = transactionname;
		this.defaultaccess = defaultaccess;
		this.versionno = versionno;
	}

	public Transaction(BigDecimal transactionid, String transactiontype, String transactionname,
			String defaultaccess, String createdby, Date createdon, String lastupdatedby, Date lastupdatedon,
			byte versionno, Set<ConsentGroupTxn> consentGroupTxns,
			Set<ControlObject> controlObjects) {
		this.transactionid = transactionid;
		this.transactiontype = transactiontype;
		this.transactionname = transactionname;
		this.defaultaccess = defaultaccess;
		this.createdby = createdby;
		this.createdon = createdon;
		this.lastupdatedby = lastupdatedby;
		this.lastupdatedon = lastupdatedon;
		this.versionno = versionno;
		/*this.consentGroupTxns = consentGroupTxns;
		this.controlObjects = controlObjects;*/
	}

	public BigDecimal getTransactionid() {
		return this.transactionid;
	}

	public void setTransactionid(BigDecimal transactionid) {
		this.transactionid = transactionid;
	}

	public String getTransactiontype() {
		return this.transactiontype;
	}

	public void setTransactiontype(String transactiontype) {
		this.transactiontype = transactiontype;
	}

	public String getTransactionname() {
		return this.transactionname;
	}

	public void setTransactionname(String transactionname) {
		this.transactionname = transactionname;
	}

	public String getDefaultaccess() {
		return this.defaultaccess;
	}

	public void setDefaultaccess(String defaultaccess) {
		this.defaultaccess = defaultaccess;
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

	public byte getVersionno() {
		return this.versionno;
	}

	public void setVersionno(byte versionno) {
		this.versionno = versionno;
	}

	public Set<ConsentGroupTxn> getConsentGroupTxns() {
		return this.consentGroupTxns;
	}

	public void setConsentGroupTxns(Set<ConsentGroupTxn> consentGroupTxns) {
		this.consentGroupTxns = consentGroupTxns;
	}

	public Set<ControlObject> getControlObjects() {
		return this.controlObjects;
	}

	public void setControlObjects(Set<ControlObject> controlObjects) {
		this.controlObjects = controlObjects;
	}

}
