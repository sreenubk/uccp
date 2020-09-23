package com.lotus.uccp.consentmgmt.dto;

import java.math.BigDecimal;
import java.util.Date;

@SuppressWarnings("serial")
public class ProxyPersonConsent implements java.io.Serializable {

	private BigDecimal proxyconsentid;
	private PersonConsent personConsent;
	private BigDecimal concernroleid;
	private Date startdate;
	private Date enddate;
	private String proxyconsentreason;
	private String createdby;
	private Date createdon;
	private String lastupdatedby;
	private Date lastupdatedon;

	public ProxyPersonConsent() {
	}

	public ProxyPersonConsent(BigDecimal proxyconsentid) {
		this.proxyconsentid = proxyconsentid;
	}

	public ProxyPersonConsent(BigDecimal proxyconsentid, PersonConsent personConsent,
			BigDecimal concernroleid, Date startdate, Date enddate, String proxyconsentreason, String createdby,
			Date createdon, String lastupdatedby, Date lastupdatedon) {
		this.proxyconsentid = proxyconsentid;
		this.personConsent = personConsent;
		this.concernroleid = concernroleid;
		this.startdate = startdate;
		this.enddate = enddate;
		this.proxyconsentreason = proxyconsentreason;
		this.createdby = createdby;
		this.createdon = createdon;
		this.lastupdatedby = lastupdatedby;
		this.lastupdatedon = lastupdatedon;
	}

	public BigDecimal getProxyconsentid() {
		return this.proxyconsentid;
	}

	public void setProxyconsentid(BigDecimal proxyconsentid) {
		this.proxyconsentid = proxyconsentid;
	}

	public PersonConsent getPersonConsent() {
		return this.personConsent;
	}

	public void setPersonConsent(PersonConsent personConsent) {
		this.personConsent = personConsent;
	}

	public BigDecimal getConcernroleid() {
		return this.concernroleid;
	}

	public void setConcernroleid(BigDecimal concernroleid) {
		this.concernroleid = concernroleid;
	}

	public Date getStartdate() {
		return this.startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return this.enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public String getProxyconsentreason() {
		return this.proxyconsentreason;
	}

	public void setProxyconsentreason(String proxyconsentreason) {
		this.proxyconsentreason = proxyconsentreason;
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
