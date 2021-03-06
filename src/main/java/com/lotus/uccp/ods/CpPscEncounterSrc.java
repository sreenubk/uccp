package com.lotus.uccp.ods;
// Generated May 31, 2018 4:53:55 PM by Hibernate Tools 5.2.6.Final

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * CpPscEncounterSrc generated by hbm2java
 */
import com.lotus.uccp.util.Constant; 
@Entity
@Table(schema = Constant.SCHEMANAME_ODS , name = "CP_PSC_ENCOUNTER_SRC")
public class CpPscEncounterSrc implements java.io.Serializable {

	private BigDecimal cpPscEncounterSeqNum;
	private ClientProfilePscSrc clientProfilePscSrc;
	private String courtCaseNum;

	public CpPscEncounterSrc() {
	}

	public CpPscEncounterSrc(BigDecimal cpPscEncounterSeqNum, ClientProfilePscSrc clientProfilePscSrc) {
		this.cpPscEncounterSeqNum = cpPscEncounterSeqNum;
		this.clientProfilePscSrc = clientProfilePscSrc;
	}

	public CpPscEncounterSrc(BigDecimal cpPscEncounterSeqNum, ClientProfilePscSrc clientProfilePscSrc,
			String courtCaseNum) {
		this.cpPscEncounterSeqNum = cpPscEncounterSeqNum;
		this.clientProfilePscSrc = clientProfilePscSrc;
		this.courtCaseNum = courtCaseNum;
	}

	@Id

	@Column(name = "CP_PSC_ENCOUNTER_SEQ_NUM", unique = true, nullable = false, precision = 31, scale = 0)
	public BigDecimal getCpPscEncounterSeqNum() {
		return this.cpPscEncounterSeqNum;
	}

	public void setCpPscEncounterSeqNum(BigDecimal cpPscEncounterSeqNum) {
		this.cpPscEncounterSeqNum = cpPscEncounterSeqNum;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLIENT_PROFILE_PSC_SEQ_NUM", nullable = false)
	public ClientProfilePscSrc getClientProfilePscSrc() {
		return this.clientProfilePscSrc;
	}

	public void setClientProfilePscSrc(ClientProfilePscSrc clientProfilePscSrc) {
		this.clientProfilePscSrc = clientProfilePscSrc;
	}

	@Column(name = "COURT_CASE_NUM", length = 10)
	public String getCourtCaseNum() {
		return this.courtCaseNum;
	}

	public void setCourtCaseNum(String courtCaseNum) {
		this.courtCaseNum = courtCaseNum;
	}

}
