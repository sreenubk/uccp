package com.lotus.uccp.ods;
import com.lotus.uccp.util.Constant; 
import java.math.BigDecimal;
import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(schema = Constant.SCHEMANAME_ODS , name = "CP_CJ_Charge_src")
public class CPCJChargeSrc implements java.io.Serializable {
	
	BigDecimal CPCJChargeSEQNUM ;
	String caseNum ;
	String criminalCharge ;
	Date chargedDate ;
	String count;
	String status;
	String description;
	
	@Id
	@Column(name = "CP_CJ_Charge_SEQ_NUM", unique = true, nullable = false, precision = 31, scale = 0)
	public BigDecimal getCPCJChargeSEQNUM() {
		return CPCJChargeSEQNUM;
	}
	public void setCPCJChargeSEQNUM(BigDecimal cPCJChargeSEQNUM) {
		CPCJChargeSEQNUM = cPCJChargeSEQNUM;
	}
	
	@Column(name = "caseNum", length = 100)
	public String getCaseNum() {
		return caseNum;
	}
	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}
	
	@Column(name = "criminalCharge", length = 100)
	public String getCriminalCharge() {
		return criminalCharge;
	}
	public void setCriminalCharge(String criminalCharge) {
		this.criminalCharge = criminalCharge;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "chargedDate", length = 10)
	public Date getChargedDate() {
		return chargedDate;
	}
	public void setChargedDate(Date chargedDate) {
		this.chargedDate = chargedDate;
	}
	
	@Column(name = "count", length = 100)
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	
	@Column(name = "status", length = 100)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "description", length = 100)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	BigDecimal CLIENT_PROFILE_CJ_SEQ_NUM;
	
	@Column(name = "CLIENT_PROFILE_CJ_SEQ_NUM", length = 30)
	public BigDecimal getCLIENT_PROFILE_CJ_SEQ_NUM() {
		return CLIENT_PROFILE_CJ_SEQ_NUM;
	}
	public void setCLIENT_PROFILE_CJ_SEQ_NUM(BigDecimal cLIENT_PROFILE_CJ_SEQ_NUM) {
		CLIENT_PROFILE_CJ_SEQ_NUM = cLIENT_PROFILE_CJ_SEQ_NUM;
	}
	
}
/*
CREATE TABLE CP_CJ_Charge_src 
(
 CP_CJ_Charge_SEQ_NUM NUMBER(31) NOT NULL,
 caseNum VARCHAR2(100 CHAR),
 criminalCharge VARCHAR2(100 CHAR),
 chargedDate Date,
 count VARCHAR2(50 CHAR),
 status  VARCHAR2(100 CHAR),
 description VARCHAR2(100 CHAR),
 CLIENT_PROFILE_CJ_SEQ_NUM NUMBER(31) NOT NULL,
 PRIMARY KEY (CP_CJ_Charge_SEQ_NUM));*/