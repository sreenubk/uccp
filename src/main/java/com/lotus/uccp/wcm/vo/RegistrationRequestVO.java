package com.lotus.uccp.wcm.vo;

import java.math.BigDecimal;

public class RegistrationRequestVO implements java.io.Serializable {

	/**
	 * 
	 */
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

/*-- Table: Registration_request
CREATE TABLE Registration_request (
    REGREQTID number(25,2)  NOT NULL,
    cohortID number(25,2)  NOT NULL,
    ruleID number(19,2)  NOT NULL,
    reasoncode varchar2(40)  NOT NULL,
    createdDate date  NOT NULL,
    createdByUser varchar2(40)  NOT NULL,
    sourceSys varchar2(40)  NOT NULL,
    sourceSysID varchar2(40)  NOT NULL,
    fName varchar2(30)  NOT NULL,
    lName varchar2(40)  NOT NULL,
    status varchar2(40)  NOT NULL,
    comments varchar2(100)  NOT NULL,
    CONSTRAINT Registration_request_pk PRIMARY KEY (reqReqtID)
) ; */