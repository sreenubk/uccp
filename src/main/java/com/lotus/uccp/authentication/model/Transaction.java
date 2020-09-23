package com.lotus.uccp.authentication.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.lotus.uccp.util.Constant;

/**
 * Transaction generated by hbm2java
 */
@Entity
@Table(name = "cosdabactransaction", schema = Constant.SCHEMA )
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

	@Id	 
	@SequenceGenerator(name = "transactionSeq", sequenceName = "transactionSeq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="transactionSeq")
	@Column(name = "transactionid", unique = true, nullable = false, scale = 0)
	public BigDecimal getTransactionid() {
		return this.transactionid;
	}

	public void setTransactionid(BigDecimal transactionid) {
		this.transactionid = transactionid;
	}

	@Column(name = "transactiontype", nullable = false, length = 10)
	public String getTransactiontype() {
		return this.transactiontype;
	}

	public void setTransactiontype(String transactiontype) {
		this.transactiontype = transactiontype;
	}

	@Column(name = "transactionname", nullable = false, length = 100)
	public String getTransactionname() {
		return this.transactionname;
	}

	public void setTransactionname(String transactionname) {
		this.transactionname = transactionname;
	}

	@Column(name = "defaultaccess", nullable = false, length = 10)
	public String getDefaultaccess() {
		return this.defaultaccess;
	}

	public void setDefaultaccess(String defaultaccess) {
		this.defaultaccess = defaultaccess;
	}

	@Column(name = "createdby", length = 64)
	public String getCreatedby() {
		return this.createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "createdon", length = 13)
	public Date getCreatedon() {
		return this.createdon;
	}

	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}

	@Column(name = "lastupdatedby", length = 64)
	public String getLastupdatedby() {
		return this.lastupdatedby;
	}

	public void setLastupdatedby(String lastupdatedby) {
		this.lastupdatedby = lastupdatedby;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "lastupdatedon", length = 13)
	public Date getLastupdatedon() {
		return this.lastupdatedon;
	}

	public void setLastupdatedon(Date lastupdatedon) {
		this.lastupdatedon = lastupdatedon;
	}

	@Column(name = "versionno", nullable = false, precision = 2, scale = 0)
	public byte getVersionno() {
		return this.versionno;
	}

	public void setVersionno(byte versionno) {
		this.versionno = versionno;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "transaction")
	public Set<ConsentGroupTxn> getConsentGroupTxns() {
		return this.consentGroupTxns;
	}

	public void setConsentGroupTxns(Set<ConsentGroupTxn> consentGroupTxns) {
		this.consentGroupTxns = consentGroupTxns;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "transaction")
	public Set<ControlObject> getControlObjects() {
		return this.controlObjects;
	}

	public void setControlObjects(Set<ControlObject> controlObjects) {
		this.controlObjects = controlObjects;
	}

}
