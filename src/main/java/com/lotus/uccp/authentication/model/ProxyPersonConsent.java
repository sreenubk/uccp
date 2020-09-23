package com.lotus.uccp.authentication.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lotus.uccp.util.Constant;
/**
 * ProxyPersonConsent generated by hbm2java
 */
@Entity
@Table(name = "proxypersonconsent", schema = Constant.SCHEMA )
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

	@Id

	@Column(name = "proxyconsentid", unique = true, nullable = false, scale = 0)
	public BigDecimal getProxyconsentid() {
		return this.proxyconsentid;
	}

	public void setProxyconsentid(BigDecimal proxyconsentid) {
		this.proxyconsentid = proxyconsentid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "personconsentid")
	public PersonConsent getPersonConsent() {
		return this.personConsent;
	}

	public void setPersonConsent(PersonConsent personConsent) {
		this.personConsent = personConsent;
	}

	@Column(name = "concernroleid", scale = 0)
	public BigDecimal getConcernroleid() {
		return this.concernroleid;
	}

	public void setConcernroleid(BigDecimal concernroleid) {
		this.concernroleid = concernroleid;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "startdate", length = 13)
	public Date getStartdate() {
		return this.startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "enddate", length = 13)
	public Date getEnddate() {
		return this.enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	@Column(name = "proxyconsentreason", length = 500)
	public String getProxyconsentreason() {
		return this.proxyconsentreason;
	}

	public void setProxyconsentreason(String proxyconsentreason) {
		this.proxyconsentreason = proxyconsentreason;
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