package com.lotus.uccp.consentmgmt.wcm.dto;

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
@Table(name = "WCM_REGISTRATION_REQUEST", schema = Constant.SCHEMA)
public class RegistrationRequest implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BigDecimal reqReqtID;
	private BigDecimal cohortID;
	private BigDecimal ruleID;
	private String reasoncode;
	private Date createdDate;
	private String createdByUser;
	private String sourceSys;
	private String sourceSysID;
	private String fName;
	private String lname;
	private String status;
	private String comments;
	private BigDecimal targetId;

	public RegistrationRequest() {

	}

	public RegistrationRequest(BigDecimal cohortID, BigDecimal ruleID, String reasoncode, Date createdDate,
			String createdByUser, String sourceSys, String sourceSysID, String fName, String lname, String status,
			String comments, BigDecimal targetId) {
		super();
		this.cohortID = cohortID;
		this.ruleID = ruleID;
		this.reasoncode = reasoncode;
		this.createdDate = createdDate;
		this.createdByUser = createdByUser;
		this.sourceSys = sourceSys;
		this.sourceSysID = sourceSysID;
		this.fName = fName;
		this.lname = lname;
		this.status = status;
		this.comments = comments;
		this.targetId = targetId;
	}

	public RegistrationRequest(BigDecimal reqReqtID, BigDecimal cohortID, BigDecimal ruleID, String reasoncode,
			Date createdDate, String createdByUser, String sourceSys, String sourceSysID, String fName, String lname,
			String status, String comments, BigDecimal targetId) {
		super();
		this.reqReqtID = reqReqtID;
		this.cohortID = cohortID;
		this.ruleID = ruleID;
		this.reasoncode = reasoncode;
		this.createdDate = createdDate;
		this.createdByUser = createdByUser;
		this.sourceSys = sourceSys;
		this.sourceSysID = sourceSysID;
		this.fName = fName;
		this.lname = lname;
		this.status = status;
		this.comments = comments;
		this.targetId = targetId;
	}

	@Id
	@SequenceGenerator(schema = Constant.SCHEMA, name = "REGREQTIDseq", sequenceName = "REGREQTIDseq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REGREQTIDseq")
	@Column(name = "REGREQTID", unique = true, nullable = false, scale = 0)
	public BigDecimal getReqReqtID() {
		return reqReqtID;
	}

	public void setReqReqtID(BigDecimal reqReqtID) {
		this.reqReqtID = reqReqtID;
	}

	@Column(name = "COHORTID", nullable = false, length = 25)
	public BigDecimal getCohortID() {
		return cohortID;
	}

	public void setCohortID(BigDecimal cohortID) {
		this.cohortID = cohortID;
	}

	@Column(name = "ruleID", nullable = false, length = 19)
	public BigDecimal getRuleID() {
		return ruleID;
	}

	public void setRuleID(BigDecimal ruleID) {
		this.ruleID = ruleID;
	}

	@Column(name = "reasoncode", nullable = false, length = 40)
	public String getReasoncode() {
		return reasoncode;
	}

	public void setReasoncode(String reasoncode) {
		this.reasoncode = reasoncode;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATEDDATE", length = 10)
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "CREATEDBYUSER", nullable = false, length = 40)
	public String getCreatedByUser() {
		return createdByUser;
	}

	public void setCreatedByUser(String createdByUser) {
		this.createdByUser = createdByUser;
	}

	@Column(name = "SOURCESYS", nullable = false, length = 40)
	public String getSourceSys() {
		return sourceSys;
	}

	public void setSourceSys(String sourceSys) {
		this.sourceSys = sourceSys;
	}

	@Column(name = "SOURCESYSID", nullable = false, length = 40)
	public String getSourceSysID() {
		return sourceSysID;
	}

	public void setSourceSysID(String sourceSysID) {
		this.sourceSysID = sourceSysID;
	}

	@Column(name = "FNAME", nullable = false, length = 30)
	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	@Column(name = "LNAME", nullable = false, length = 40)
	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	@Column(name = "STATUS", nullable = false, length = 40)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "COMMENTS", nullable = false, length = 255)
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Column(name = "TARGETID", nullable = false, length = 25)
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