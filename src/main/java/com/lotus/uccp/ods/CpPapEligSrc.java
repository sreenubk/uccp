package com.lotus.uccp.ods;
// Generated May 31, 2018 4:53:55 PM by Hibernate Tools 5.2.6.Final

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

/**
 * CpPapEligSrc generated by hbm2java
 */
import com.lotus.uccp.util.Constant; 
@Entity
@Table(schema = Constant.SCHEMANAME_ODS , name = "CP_PAP_ELIG_SRC")
public class CpPapEligSrc implements java.io.Serializable {

	private BigDecimal cpPapEligSeqNum;
	private ClientProfilePapSrc clientProfilePapSrc;
	private String eligStatusCashProg;
	private String partCasembrCashProg;
	private String foodStamps;
	private String eligStatusCalfresh;
	private String partCasembrCalfresh;
	private String hasMedical;
	private String partCasembrMedical;
	private String eligStatusMedical;
	private String cashProg;
	private String caseId;
	private String programId;
	private String cin;
	private String progNm;
	private String progStatus;
	private Date statusDate;
	private Date renewalDate;

	public CpPapEligSrc() {
	}

	public CpPapEligSrc(BigDecimal cpPapEligSeqNum, ClientProfilePapSrc clientProfilePapSrc) {
		this.cpPapEligSeqNum = cpPapEligSeqNum;
		this.clientProfilePapSrc = clientProfilePapSrc;
	}

	public CpPapEligSrc(BigDecimal cpPapEligSeqNum, ClientProfilePapSrc clientProfilePapSrc, String eligStatusCashProg,
			String partCasembrCashProg, String foodStamps, String eligStatusCalfresh, String partCasembrCalfresh,
			String hasMedical, String partCasembrMedical, String eligStatusMedical, String cashProg, String caseId,
			String programId, String cin, String progNm, String progStatus, Date statusDate, Date renewalDate) {
		this.cpPapEligSeqNum = cpPapEligSeqNum;
		this.clientProfilePapSrc = clientProfilePapSrc;
		this.eligStatusCashProg = eligStatusCashProg;
		this.partCasembrCashProg = partCasembrCashProg;
		this.foodStamps = foodStamps;
		this.eligStatusCalfresh = eligStatusCalfresh;
		this.partCasembrCalfresh = partCasembrCalfresh;
		this.hasMedical = hasMedical;
		this.partCasembrMedical = partCasembrMedical;
		this.eligStatusMedical = eligStatusMedical;
		this.cashProg = cashProg;
		this.caseId = caseId;
		this.programId = programId;
		this.cin = cin;
		this.progNm = progNm;
		this.progStatus = progStatus;
		this.statusDate = statusDate;
		this.renewalDate = renewalDate;
	}

	@Id

	@Column(name = "CP_PAP_ELIG_SEQ_NUM", unique = true, nullable = false, precision = 31, scale = 0)
	public BigDecimal getCpPapEligSeqNum() {
		return this.cpPapEligSeqNum;
	}

	public void setCpPapEligSeqNum(BigDecimal cpPapEligSeqNum) {
		this.cpPapEligSeqNum = cpPapEligSeqNum;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLIENT_PROFILE_PAP_SEQ_NUM", nullable = false)
	public ClientProfilePapSrc getClientProfilePapSrc() {
		return this.clientProfilePapSrc;
	}

	public void setClientProfilePapSrc(ClientProfilePapSrc clientProfilePapSrc) {
		this.clientProfilePapSrc = clientProfilePapSrc;
	}

	@Column(name = "ELIG_STATUS_CASH_PROG", length = 30)
	public String getEligStatusCashProg() {
		return this.eligStatusCashProg;
	}

	public void setEligStatusCashProg(String eligStatusCashProg) {
		this.eligStatusCashProg = eligStatusCashProg;
	}

	@Column(name = "PART_CASEMBR_CASH_PROG", length = 60)
	public String getPartCasembrCashProg() {
		return this.partCasembrCashProg;
	}

	public void setPartCasembrCashProg(String partCasembrCashProg) {
		this.partCasembrCashProg = partCasembrCashProg;
	}

	@Column(name = "FOOD_STAMPS", length = 3)
	public String getFoodStamps() {
		return this.foodStamps;
	}

	public void setFoodStamps(String foodStamps) {
		this.foodStamps = foodStamps;
	}

	@Column(name = "ELIG_STATUS_CALFRESH", length = 30)
	public String getEligStatusCalfresh() {
		return this.eligStatusCalfresh;
	}

	public void setEligStatusCalfresh(String eligStatusCalfresh) {
		this.eligStatusCalfresh = eligStatusCalfresh;
	}

	@Column(name = "PART_CASEMBR_CALFRESH", length = 60)
	public String getPartCasembrCalfresh() {
		return this.partCasembrCalfresh;
	}

	public void setPartCasembrCalfresh(String partCasembrCalfresh) {
		this.partCasembrCalfresh = partCasembrCalfresh;
	}

	@Column(name = "HAS_MEDICAL", length = 3)
	public String getHasMedical() {
		return this.hasMedical;
	}

	public void setHasMedical(String hasMedical) {
		this.hasMedical = hasMedical;
	}

	@Column(name = "PART_CASEMBR_MEDICAL", length = 30)
	public String getPartCasembrMedical() {
		return this.partCasembrMedical;
	}

	public void setPartCasembrMedical(String partCasembrMedical) {
		this.partCasembrMedical = partCasembrMedical;
	}

	@Column(name = "ELIG_STATUS_MEDICAL", length = 60)
	public String getEligStatusMedical() {
		return this.eligStatusMedical;
	}

	public void setEligStatusMedical(String eligStatusMedical) {
		this.eligStatusMedical = eligStatusMedical;
	}

	@Column(name = "CASH_PROG", length = 3)
	public String getCashProg() {
		return this.cashProg;
	}

	public void setCashProg(String cashProg) {
		this.cashProg = cashProg;
	}

	@Column(name = "CASE_ID", length = 60)
	public String getCaseId() {
		return this.caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	@Column(name = "PROGRAM_ID", length = 60)
	public String getProgramId() {
		return this.programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
	}

	@Column(name = "CIN", length = 90)
	public String getCin() {
		return this.cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	@Column(name = "PROG_NM", length = 30)
	public String getProgNm() {
		return this.progNm;
	}

	public void setProgNm(String progNm) {
		this.progNm = progNm;
	}

	@Column(name = "PROG_STATUS", length = 30)
	public String getProgStatus() {
		return this.progStatus;
	}

	public void setProgStatus(String progStatus) {
		this.progStatus = progStatus;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "STATUS_DATE", length = 10)
	public Date getStatusDate() {
		return this.statusDate;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "RENEWAL_DATE", length = 10)
	public Date getRenewalDate() {
		return this.renewalDate;
	}

	public void setRenewalDate(Date renewalDate) {
		this.renewalDate = renewalDate;
	}

}
