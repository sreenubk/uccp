package com.lotus.uccp.authentication.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.lotus.uccp.util.Constant;

/**
 * PersonConsentHistory generated by hbm2java
 */
@Entity
@Table(name = "personconsenthistory", schema = Constant.SCHEMA )
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

	@Id
	@SequenceGenerator(name = "consentPersonH", sequenceName = "consentPersonH", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="consentPersonH")		
	@Column(name = "consenthistoryid", unique = true, nullable = false, scale = 0)
	public BigDecimal getConsenthistoryid() {
		return this.consenthistoryid;
	}

	public void setConsenthistoryid(BigDecimal consenthistoryid) {
		this.consenthistoryid = consenthistoryid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "personconsentid")
	public PersonConsent getPersonConsent() {
		return this.personConsent;
	}

	public void setPersonConsent(PersonConsent personConsent) {
		this.personConsent = personConsent;
	}

	@Column(name = "actiontype", length = 10)
	public String getActiontype() {
		return this.actiontype;
	}

	public void setActiontype(String actiontype) {
		this.actiontype = actiontype;
	}

	@Column(name = "cancelreason", length = 300)
	public String getCancelreason() {
		return this.cancelreason;
	}

	public void setCancelreason(String cancelreason) {
		this.cancelreason = cancelreason;
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

}
