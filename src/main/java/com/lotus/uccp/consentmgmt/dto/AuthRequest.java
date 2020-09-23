/* Licensed Materials - 
 Property of IBM 6949 - 67L 
 Copyright IBM Corp. 2017, 2018 All Rights Reserved */
package  com.lotus.uccp.consentmgmt.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lotus.uccp.util.Constant;
@Entity
@Table(name = "connect360_audit", schema = Constant.SCHEMA)
public class AuthRequest {
	
	/*
	 * auditid
	transactionid
	constraintid
	username
	clientid
	srcsystem
	transname
	constraintname
	constraintvalue
	privilegerequested
	accessreason
	apiswitch
	requestdt
	 */
    
	private BigDecimal auditid;	
	private String userName ;
	private String clientID ;
	private String srcSystem ;
	private BigDecimal transactionid;
	private String transName ;
	private BigDecimal constraintid;
	private String constraintName ;
	private String constraintValue ;
	private String privilegeRequested ;
	private String accessReason ;
	private String apiSwith ;
	private Date requestdt;
	private String result;
	
	
	@Id	 
	@SequenceGenerator(schema = Constant.SCHEMA,name  = "auditSeq", sequenceName = "auditSeq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="auditSeq")
	@Column(name = "auditid", unique = true, nullable = false, scale = 0)
	public BigDecimal getAuditid() {
		return auditid;
	}
	public void setAuditid(BigDecimal auditid) {
		this.auditid = auditid;
	}
	
	@Column(name = "username", nullable = false, length = 20)
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name = "clientid", nullable = false, length = 20)
	public String getClientID() {
		return clientID;
	}
	public void setClientID(String clientID) {
		this.clientID = clientID;
	}
	
	@Column(name = "srcsystem", nullable = false, length = 20)
	public String getSrcSystem() {
		return srcSystem;
	}
	public void setSrcSystem(String srcSystem) {
		this.srcSystem = srcSystem;
	}
	
	@Column(name = "transactionid", scale = 0)
	public BigDecimal getTransactionid() {
		return transactionid;
	}
	public void setTransactionid(BigDecimal transactionid) {
		this.transactionid = transactionid;
	}
	
	@Column(name = "transname", nullable = false, length = 10)
	public String getTransName() {
		return transName;
	}
	public void setTransName(String transName) {
		this.transName = transName;
	}
	
	@Column(name = "constraintid", scale = 0)
	public BigDecimal getConstraintid() {
		return constraintid;
	}
	public void setConstraintid(BigDecimal constraintid) {
		this.constraintid = constraintid;
	}
	
	@Column(name = "constraintname", nullable = false, length = 20)
	public String getConstraintName() {
		return constraintName;
	}
	public void setConstraintName(String constraintName) {
		this.constraintName = constraintName;
	}
	
	@Column(name = "constraintvalue", nullable = false, length = 20)
	public String getConstraintValue() {
		return constraintValue;
	}
	public void setConstraintValue(String constraintValue) {
		this.constraintValue = constraintValue;
	}
	
	@Column(name = "privilegerequested", nullable = false, length = 20)
	public String getPrivilegeRequested() {
		return privilegeRequested;
	}
	public void setPrivilegeRequested(String privilegeRequested) {
		this.privilegeRequested = privilegeRequested;
	}
	
	@Column(name = "accessreason", nullable = false, length = 20)
	public String getAccessReason() {
		return accessReason;
	}
	public void setAccessReason(String accessReason) {
		this.accessReason = accessReason;
	}
	
	@Column(name = "apiswitch", nullable = false, length = 20)
	public String getApiSwith() {
		return apiSwith;
	}
	public void setApiSwith(String apiSwith) {
		this.apiSwith = apiSwith;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "requestdt", length = 13)
	public Date getRequestdt() {
		return requestdt;
	}
	public void setRequestdt(Date requestdt) {
		this.requestdt = requestdt;
	}
	
	@Column(name = "result", nullable = false, length = 200)
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
