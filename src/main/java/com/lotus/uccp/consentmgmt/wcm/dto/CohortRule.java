package com.lotus.uccp.consentmgmt.wcm.dto ;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lotus.uccp.util.Constant;
@Entity
@Table(name = "WCM_COHORT_RULE", schema = Constant.SCHEMA )
public class CohortRule {

	Cohort cohortid ;
	String ruleID ;
	String ruleShortDesc ;
	String ruleDescription ;
	String ruleAction ;
	ReasonCode reasonCode ;
	String isEnabled ;
	String approvalRequired ;
	Date startDate ;
	Date endDate ;
	BigDecimal cohortRuleID ;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cohortid")	
	public Cohort getCohortid() {
		return cohortid;
	}
	public void setCohortid(Cohort cohortid) {
		this.cohortid = cohortid;
	}
	
	@Column(name = "ruleID", nullable = false, length = 30)
	public String getRuleID() {
		return ruleID;
	}
	public void setRuleID(String ruleID) {
		this.ruleID = ruleID;
	}
	
	@Column(name = "ruleShortDesc", nullable = false, length = 100)
	public String getRuleShortDesc() {
		return ruleShortDesc;
	}
	public void setRuleShortDesc(String ruleShortDesc) {
		this.ruleShortDesc = ruleShortDesc;
	}
	
	@Column(name = "ruleDescription", nullable = false, length = 100)
	public String getRuleDescription() {
		return ruleDescription;
	}
	public void setRuleDescription(String ruleDescription) {
		this.ruleDescription = ruleDescription;
	}
	
	@Column(name = "ruleAction", nullable = false, length = 100)
	public String getRuleAction() {
		return ruleAction;
	}
	public void setRuleAction(String ruleAction) {
		this.ruleAction = ruleAction;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reasonCode")	
	public ReasonCode getReasonCode() {
		return reasonCode;
	}
	public void setReasonCode(ReasonCode reasonCode) {
		this.reasonCode = reasonCode;
	}
	
	@Column(name = "isEnabled", nullable = false, length = 40)
	public String getIsEnabled() {
		return isEnabled;
	}
	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}
	
	@Column(name = "approvalRequired", nullable = false, length = 40)
	public String getApprovalRequired() {
		return approvalRequired;
	}
	public void setApprovalRequired(String approvalRequired) {
		this.approvalRequired = approvalRequired;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "startDate", length = 13)
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "endDate", length = 13)
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	@Id
	@SequenceGenerator(schema = Constant.SCHEMA ,name  = "cohortRuleidseq", sequenceName = "cohortRuleidseq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="cohortRuleidseq")
	@Column(name = "cohortRuleID", unique = true, nullable = false, scale = 0)
	public BigDecimal getCohortRuleID() {
		return cohortRuleID;
	}
	public void setCohortRuleID(BigDecimal cohortRuleID) {
		this.cohortRuleID = cohortRuleID;
	}
	
	
}

/*
 CREATE TABLE WCM_COHORT_RULE (
    cohortID number(25,2)  NULL,
    ruleID varchar2(30)  NULL,
    ruleShortDesc varchar2(100)  NULL,
    ruleDescription varchar2(100)  NOT NULL,
    ruleAction varchar2(40)  NOT NULL,
    reasonCode varchar2(40)  NOT NULL,
    isEnabled varchar2(20)  NOT NULL,
    approvalRequired varchar2(40)  NOT NULL,
    startDate date  NOT NULL,
    endDate date  NOT NULL,
    cohortRuleID number(19,2)  NOT NULL,
    CONSTRAINT COHORT_RULE_pk PRIMARY KEY (cohortRuleID)
) ;
*/
