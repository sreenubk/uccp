package com.lotus.uccp.authentication.model;

import com.lotus.uccp.util.Constant;

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

@Entity
@Table(name = "authorizationpolicyset", schema = Constant.SCHEMA )
public class AuthorizationPolicySet implements java.io.Serializable {

	private BigDecimal policysetid;
	private String consentstatus;
	private String classificationlevel;
	private String comments;
	private String createdby;
	private Date createdon;
	private String lastupdatedby;
	private Date lastupdatedon;
	private String name;
	private String transactionType;

	private Set<AuthorizationPolicy> authorizationPolicy = new HashSet<AuthorizationPolicy>(0);

	public Set<AuthorizationPolicy> getAuthorizationPolicy() {
		return authorizationPolicy;
	}

	public void setAuthorizationPolicy(Set<AuthorizationPolicy> authorizationPolicy) {
		this.authorizationPolicy = authorizationPolicy;
	}

	public AuthorizationPolicySet() {
	}

	public AuthorizationPolicySet(BigDecimal policysetid) {
		this.policysetid = policysetid;
	}

	public AuthorizationPolicySet(BigDecimal policysetid, String consentstatus, String classificationlevel,
								  String comments, String createdby, Date createdon, String lastupdatedby, Date lastupdatedon, Set<AuthorizationPolicy> authorizationPolicy) {

		this.policysetid = policysetid;
		this.consentstatus = consentstatus;
		this.classificationlevel = classificationlevel;
		this.comments = comments;
		this.createdby = createdby;
		this.createdon = createdon;
		this.lastupdatedby = lastupdatedby;
		this.lastupdatedon = lastupdatedon;
		this.authorizationPolicy =authorizationPolicy ;
	}

	@Id
	@SequenceGenerator(name = "policySetSeq", sequenceName = "policySetSeq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="policySetSeq")
	@Column(name = "policysetid", unique = true, nullable = false, scale = 0)
	public BigDecimal getPolicysetid() {
		return this.policysetid;
	}

	public void setPolicysetid(BigDecimal policysetid) {
		this.policysetid = policysetid;
	}

	@Column(name = "consentstatus", length = 50)
	public String getConsentstatus() {
		return this.consentstatus;
	}

	public void setConsentstatus(String consentstatus) {
		this.consentstatus = consentstatus;
	}

	@Column(name = "classificationlevel", length = 100)
	public String getClassificationlevel() {
		return this.classificationlevel;
	}

	public void setClassificationlevel(String classificationlevel) {
		this.classificationlevel = classificationlevel;
	}

	@Column(name = "comments", length = 300)
	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

}
