package com.lotus.uccp.consentmgmt.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.lotus.uccp.consentmgmt.dto.PersonConsent;

@SuppressWarnings("serial")
public class ConsentException implements java.io.Serializable {

	private BigDecimal consentexceptionid;
	private ConsentGroup consentGroup;
	private PersonConsent personConsent;
	private String consentstatus ;
	private String createdby;
	private Date createdon;
	private String lastupdatedby;
	private Date lastupdatedo;

	public ConsentException() {
	}

	public ConsentException(BigDecimal consentexceptionid) {
		this.consentexceptionid = consentexceptionid;
	}

	public ConsentException(BigDecimal consentexceptionid, ConsentGroup consentGroup,
			PersonConsent personConsent, String createdby, Date createdon, String lastupdatedby,
			Date lastupdatedo) {
		this.consentexceptionid = consentexceptionid;
		this.consentGroup = consentGroup;
		this.personConsent = personConsent;
		this.createdby = createdby;
		this.createdon = createdon;
		this.lastupdatedby = lastupdatedby;
		this.lastupdatedo = lastupdatedo;
	}

	public BigDecimal getConsentexceptionid() {
		return this.consentexceptionid;
	}

	public void setConsentexceptionid(BigDecimal consentexceptionid) {
		this.consentexceptionid = consentexceptionid;
	}

	public ConsentGroup getConsentGroup() {
		return this.consentGroup;
	}

	public void setConsentGroup(ConsentGroup consentGroup) {
		this.consentGroup = consentGroup;
	}

	public PersonConsent getPersonConsent() {
		return this.personConsent;
	}

	public void setPersonConsent(PersonConsent personConsent) {
		this.personConsent = personConsent;
	}

	public String getConsentstatus() {
		return consentstatus;
	}

	public void setConsentstatus(String consentstatus) {
		this.consentstatus = consentstatus;
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

	public Date getLastupdatedo() {
		return this.lastupdatedo;
	}

	public void setLastupdatedo(Date lastupdatedo) {
		this.lastupdatedo = lastupdatedo;
	}

}
