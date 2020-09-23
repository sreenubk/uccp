package com.lotus.uccp.wcm.vo;

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

public class AlertRequestVO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private BigDecimal reqReqtID;
	private BigDecimal cohortID;
	private BigDecimal ruleID;
	private String reasoncode;
	private String createdDate;
	private String createdByUser;
	private String sourceSys;
	private String sourceSysID;
	private String fName;
	private String lname;
	private String alertMessage;
	private String status;
	private String comments;
	private BigDecimal targetId;

	public BigDecimal getReqReqtID() {
		return reqReqtID;
	}

	public void setReqReqtID(BigDecimal reqReqtID) {
		this.reqReqtID = reqReqtID;
	}

	public BigDecimal getCohortID() {
		return cohortID;
	}

	public void setCohortID(BigDecimal cohortID) {
		this.cohortID = cohortID;
	}

	public BigDecimal getRuleID() {
		return ruleID;
	}

	public void setRuleID(BigDecimal ruleID) {
		this.ruleID = ruleID;
	}

	public String getReasoncode() {
		return reasoncode;
	}

	public void setReasoncode(String reasoncode) {
		this.reasoncode = reasoncode;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedByUser() {
		return createdByUser;
	}

	public void setCreatedByUser(String createdByUser) {
		this.createdByUser = createdByUser;
	}

	public String getSourceSys() {
		return sourceSys;
	}

	public void setSourceSys(String sourceSys) {
		this.sourceSys = sourceSys;
	}

	public String getSourceSysID() {
		return sourceSysID;
	}

	public void setSourceSysID(String sourceSysID) {
		this.sourceSysID = sourceSysID;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getAlertMessage() {
		return alertMessage;
	}

	public void setAlertMessage(String alertMessage) {
		this.alertMessage = alertMessage;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public BigDecimal getTargetId() {
		return targetId;
	}

	public void setTargetId(BigDecimal targetId) {
		this.targetId = targetId;
	}

}
/*
 * CREATE TABLE ALERT_request ( alertreqReqtID number(25,2) NOT NULL, cohortID
 * number(25,2) NOT NULL, ruleID number(19,2) NOT NULL, reasoncode varchar2(40)
 * NOT NULL, createdDate date NOT NULL, createdByUser varchar2(40) NOT NULL,
 * sourceSys varchar2(40) NOT NULL, sourceSysID varchar2(40) NOT NULL, fName
 * varchar2(30) NOT NULL, lName varchar2(40) NOT NULL, status varchar2(40) NOT
 * NULL, comments varchar2(100) NOT NULL, CONSTRAINT ALERT_request_pk PRIMARY
 * KEY (alertreqReqtID) ) ;
 */
