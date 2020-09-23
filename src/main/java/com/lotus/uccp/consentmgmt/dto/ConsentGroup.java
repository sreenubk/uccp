package com.lotus.uccp.consentmgmt.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class ConsentGroup implements java.io.Serializable {

	private BigDecimal consentgroupid;
	private String description;
	private String name;
	private String createdby;
	private Date createdon;
	private String lastupdatedby;
	private Date lastupdatedon;
	private Set<ConsentGroupTxn> consentGroupTxns = new HashSet<ConsentGroupTxn>(0);
	private Set<ConsentException> consentExceptions = new HashSet<ConsentException>(0);

	public ConsentGroup() {
	}

	public ConsentGroup(BigDecimal consentgroupid) {
		this.consentgroupid = consentgroupid;
	}

	public ConsentGroup(BigDecimal consentgroupid, String description, String name, String createdby,
			Date createdon, String lastupdatedby, Date lastupdatedon, Set<ConsentGroupTxn> consentGroupTxns,
			Set<ConsentException> consentExceptions) {
		this.consentgroupid = consentgroupid;
		this.description = description;
		this.name = name;
		this.createdby = createdby;
		this.createdon = createdon;
		this.lastupdatedby = lastupdatedby;
		this.lastupdatedon = lastupdatedon;
		this.consentGroupTxns = consentGroupTxns;
		this.consentExceptions = consentExceptions;
	}

	public BigDecimal getConsentgroupid() {
		return this.consentgroupid;
	}

	public void setConsentgroupid(BigDecimal consentgroupid) {
		this.consentgroupid = consentgroupid;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Set<ConsentGroupTxn> getConsentGroupTxns() {
		return this.consentGroupTxns;
	}

	public void setConsentGroupTxns(Set<ConsentGroupTxn> consentGroupTxns) {
		this.consentGroupTxns = consentGroupTxns;
	}

	public Set<ConsentException> getConsentExceptions() {
		return this.consentExceptions;
	}

	public void setConsentExceptions(Set<ConsentException> consentExceptions) {
		this.consentExceptions = consentExceptions;
	}

}
