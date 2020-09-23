package com.lotus.uccp.ods;
// Generated May 31, 2018 4:53:55 PM by Hibernate Tools 5.2.6.Final

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ClientProfileHsngSrc generated by hbm2java
 */
import com.lotus.uccp.util.Constant; 
@Entity
@Table(schema = Constant.SCHEMANAME_ODS , name = "CLIENT_PROFILE_HSNG_SRC")
public class ClientProfileHsngSrc implements java.io.Serializable {

	private BigDecimal clientProfileHsngSeqNum;
	private String clientId;
	private String sourceSystemId;
	private String programStatus;
	private BigDecimal assistVal;
	private String inVash;
	private Date applDate;
	private String caseworker;
	private Date inspectionDate;
	private BigDecimal utilitiesAllowance;
	private String yearBuilt;
	private String sqFt;
	private String numBedroom;
	private String leadPaintExpsr;
	private BigDecimal rentalRate;
	private String owner;
	private String payee;
	private String payeeAddr;
	private BigDecimal amtOwed;
	private Date pmtDate;
	private String pmtHold;
	private String pmtHoldReason;
	private Date processTs;

	public ClientProfileHsngSrc() {
	}

	public ClientProfileHsngSrc(BigDecimal clientProfileHsngSeqNum) {
		this.clientProfileHsngSeqNum = clientProfileHsngSeqNum;
	}

	public ClientProfileHsngSrc(BigDecimal clientProfileHsngSeqNum, String clientId, String sourceSystemId,
			String programStatus, BigDecimal assistVal, String inVash, Date applDate, String caseworker,
			Date inspectionDate, BigDecimal utilitiesAllowance, String yearBuilt, String sqFt, String numBedroom,
			String leadPaintExpsr, BigDecimal rentalRate, String owner, String payee, String payeeAddr,
			BigDecimal amtOwed, Date pmtDate, String pmtHold, String pmtHoldReason, Date processTs) {
		this.clientProfileHsngSeqNum = clientProfileHsngSeqNum;
		this.clientId = clientId;
		this.sourceSystemId = sourceSystemId;
		this.programStatus = programStatus;
		this.assistVal = assistVal;
		this.inVash = inVash;
		this.applDate = applDate;
		this.caseworker = caseworker;
		this.inspectionDate = inspectionDate;
		this.utilitiesAllowance = utilitiesAllowance;
		this.yearBuilt = yearBuilt;
		this.sqFt = sqFt;
		this.numBedroom = numBedroom;
		this.leadPaintExpsr = leadPaintExpsr;
		this.rentalRate = rentalRate;
		this.owner = owner;
		this.payee = payee;
		this.payeeAddr = payeeAddr;
		this.amtOwed = amtOwed;
		this.pmtDate = pmtDate;
		this.pmtHold = pmtHold;
		this.pmtHoldReason = pmtHoldReason;
		this.processTs = processTs;
	}

	@Id

	@Column(name = "CLIENT_PROFILE_HSNG_SEQ_NUM", unique = true, nullable = false, precision = 31, scale = 0)
	public BigDecimal getClientProfileHsngSeqNum() {
		return this.clientProfileHsngSeqNum;
	}

	public void setClientProfileHsngSeqNum(BigDecimal clientProfileHsngSeqNum) {
		this.clientProfileHsngSeqNum = clientProfileHsngSeqNum;
	}

	@Column(name = "CLIENT_ID", length = 60)
	public String getClientId() {
		return this.clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	@Column(name = "SOURCE_SYSTEM_ID", length = 40)
	public String getSourceSystemId() {
		return this.sourceSystemId;
	}

	public void setSourceSystemId(String sourceSystemId) {
		this.sourceSystemId = sourceSystemId;
	}

	@Column(name = "PROGRAM_STATUS", length = 50)
	public String getProgramStatus() {
		return this.programStatus;
	}

	public void setProgramStatus(String programStatus) {
		this.programStatus = programStatus;
	}

	@Column(name = "ASSIST_VAL", scale = 4)
	public BigDecimal getAssistVal() {
		return this.assistVal;
	}

	public void setAssistVal(BigDecimal assistVal) {
		this.assistVal = assistVal;
	}

	@Column(name = "IN_VASH", length = 50)
	public String getInVash() {
		return this.inVash;
	}

	public void setInVash(String inVash) {
		this.inVash = inVash;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "APPL_DATE", length = 10)
	public Date getApplDate() {
		return this.applDate;
	}

	public void setApplDate(Date applDate) {
		this.applDate = applDate;
	}

	@Column(name = "CASEWORKER", length = 100)
	public String getCaseworker() {
		return this.caseworker;
	}

	public void setCaseworker(String caseworker) {
		this.caseworker = caseworker;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "INSPECTION_DATE", length = 10)
	public Date getInspectionDate() {
		return this.inspectionDate;
	}

	public void setInspectionDate(Date inspectionDate) {
		this.inspectionDate = inspectionDate;
	}

	@Column(name = "UTILITIES_ALLOWANCE", scale = 4)
	public BigDecimal getUtilitiesAllowance() {
		return this.utilitiesAllowance;
	}

	public void setUtilitiesAllowance(BigDecimal utilitiesAllowance) {
		this.utilitiesAllowance = utilitiesAllowance;
	}

	@Column(name = "YEAR_BUILT", length = 5)
	public String getYearBuilt() {
		return this.yearBuilt;
	}

	public void setYearBuilt(String yearBuilt) {
		this.yearBuilt = yearBuilt;
	}

	@Column(name = "SQ_FT", length = 25)
	public String getSqFt() {
		return this.sqFt;
	}

	public void setSqFt(String sqFt) {
		this.sqFt = sqFt;
	}

	@Column(name = "NUM_BEDROOM", length = 2)
	public String getNumBedroom() {
		return this.numBedroom;
	}

	public void setNumBedroom(String numBedroom) {
		this.numBedroom = numBedroom;
	}

	@Column(name = "LEAD_PAINT_EXPSR", length = 3)
	public String getLeadPaintExpsr() {
		return this.leadPaintExpsr;
	}

	public void setLeadPaintExpsr(String leadPaintExpsr) {
		this.leadPaintExpsr = leadPaintExpsr;
	}

	@Column(name = "RENTAL_RATE", scale = 4)
	public BigDecimal getRentalRate() {
		return this.rentalRate;
	}

	public void setRentalRate(BigDecimal rentalRate) {
		this.rentalRate = rentalRate;
	}

	@Column(name = "OWNER", length = 100)
	public String getOwner() {
		return this.owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	@Column(name = "PAYEE", length = 100)
	public String getPayee() {
		return this.payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}

	@Column(name = "PAYEE_ADDR")
	public String getPayeeAddr() {
		return this.payeeAddr;
	}

	public void setPayeeAddr(String payeeAddr) {
		this.payeeAddr = payeeAddr;
	}

	@Column(name = "AMT_OWED", scale = 4)
	public BigDecimal getAmtOwed() {
		return this.amtOwed;
	}

	public void setAmtOwed(BigDecimal amtOwed) {
		this.amtOwed = amtOwed;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PMT_DATE", length = 10)
	public Date getPmtDate() {
		return this.pmtDate;
	}

	public void setPmtDate(Date pmtDate) {
		this.pmtDate = pmtDate;
	}

	@Column(name = "PMT_HOLD", length = 3)
	public String getPmtHold() {
		return this.pmtHold;
	}

	public void setPmtHold(String pmtHold) {
		this.pmtHold = pmtHold;
	}

	@Column(name = "PMT_HOLD_REASON", length = 50)
	public String getPmtHoldReason() {
		return this.pmtHoldReason;
	}

	public void setPmtHoldReason(String pmtHoldReason) {
		this.pmtHoldReason = pmtHoldReason;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PROCESS_TS", length = 26)
	public Date getProcessTs() {
		return this.processTs;
	}

	public void setProcessTs(Date processTs) {
		this.processTs = processTs;
	}

}