
package com.lotus.uccp.ods;
import com.lotus.uccp.util.Constant; 

import javax.persistence.Entity;
import javax.persistence.Table;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(schema = Constant.SCHEMANAME_ODS , name = "CP_CJ_Hearing_src")
public class CPCJHearingSrc {

	BigDecimal CP_CJ_Charge_SEQ_NUM ;
	String caseNum ;
	Date courtDate ;
	String courtDept ;
	String courtLocation;
	Date hearingDate ;
	String hearingTime ;
	String hearingAddress ;
	String hearingType ;
	BigDecimal CLIENT_PROFILE_CJ_SEQ_NUM;
	
	
	@Id
	@Column(name = "CP_CJ_Hearing_SEQ_NUM", unique = true, nullable = false, precision = 31, scale = 0)	
	public BigDecimal getCP_CJ_Charge_SEQ_NUM() {
		return CP_CJ_Charge_SEQ_NUM;
	}
	public void setCP_CJ_Charge_SEQ_NUM(BigDecimal cP_CJ_Charge_SEQ_NUM) {
		CP_CJ_Charge_SEQ_NUM = cP_CJ_Charge_SEQ_NUM;
	}
	
	@Column(name = "caseNum", length = 100)
	public String getCaseNum() {
		return caseNum;
	}
	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "courtDate", length = 10)
	public Date getCourtDate() {
		return courtDate;
	}
	public void setCourtDate(Date courtDate) {
		this.courtDate = courtDate;
	}
	
	
	@Column(name = "courtDept", length = 100)
	public String getCourtDept() {
		return courtDept;
	}
	public void setCourtDept(String courtDept) {
		this.courtDept = courtDept;
	}
	
	@Column(name = "courtLocation", length = 100)
	public String getCourtLocation() {
		return courtLocation;
	}
	public void setCourtLocation(String courtLocation) {
		this.courtLocation = courtLocation;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "hearingDate", length = 10)
	public Date getHearingDate() {
		return hearingDate;
	}
	public void setHearingDate(Date hearingDate) {
		this.hearingDate = hearingDate;
	}
	
	@Column(name = "hearingTime", length = 100)
	public String getHearingTime() {
		return hearingTime;
	}
	public void setHearingTime(String hearingTime) {
		this.hearingTime = hearingTime;
	}
	
	@Column(name = "hearingAddress", length = 100)
	public String getHearingAddress() {
		return hearingAddress;
	}
	public void setHearingAddress(String hearingAddress) {
		this.hearingAddress = hearingAddress;
	}
	
	@Column(name = "hearingType", length = 100)
	public String getHearingType() {
		return hearingType;
	}
	public void setHearingType(String hearingType) {
		this.hearingType = hearingType;
	}
	
	@Column(name = "CLIENT_PROFILE_CJ_SEQ_NUM", length = 30)
	public BigDecimal getCLIENT_PROFILE_CJ_SEQ_NUM() {
		return CLIENT_PROFILE_CJ_SEQ_NUM;
	}
	public void setCLIENT_PROFILE_CJ_SEQ_NUM(BigDecimal cLIENT_PROFILE_CJ_SEQ_NUM) {
		CLIENT_PROFILE_CJ_SEQ_NUM = cLIENT_PROFILE_CJ_SEQ_NUM;
	}
	
}

/*CREATE TABLE CP_CJ_Hearing_src
(
 CP_CJ_Hearing_SEQ_NUM NUMBER(31) NOT NULL,
 caseNum VARCHAR2(100 CHAR),
courtDate Date,
courtDept VARCHAR2(100 CHAR),
courtLocation VARCHAR2(100 CHAR),
hearingDate Date,
hearingTime VARCHAR2(100 CHAR),
hearingAddress VARCHAR2(100 CHAR),
hearingType VARCHAR2(100 CHAR),
 CLIENT_PROFILE_CJ_SEQ_NUM NUMBER(31) NOT NULL,
 PRIMARY KEY (CP_CJ_Hearing_SEQ_NUM));*/
