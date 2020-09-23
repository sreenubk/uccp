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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lotus.uccp.util.Constant;

public class CohortRuleVO {

	BigDecimal cohortid ;
	String ruleID ;
	String ruleShortDesc ;
	String ruleDescription ;
	String ruleAction ;
	String reasonCode ;
	String isEnabled ;
	String approvalRequired ;
	String startDate ;
	String endDate ;
	BigDecimal cohortRuleID ;
	
		
	public BigDecimal getCohortid() {
		return cohortid;
	}
	public void setCohortid(BigDecimal cohortid) {
		this.cohortid = cohortid;
	}
	
	
	public String getRuleID() {
		return ruleID;
	}
	public void setRuleID(String ruleID) {
		this.ruleID = ruleID;
	}
	
	
	public String getRuleShortDesc() {
		return ruleShortDesc;
	}
	public void setRuleShortDesc(String ruleShortDesc) {
		this.ruleShortDesc = ruleShortDesc;
	}
	
	
	public String getRuleDescription() {
		return ruleDescription;
	}
	public void setRuleDescription(String ruleDescription) {
		this.ruleDescription = ruleDescription;
	}
	
	
	public String getRuleAction() {
		return ruleAction;
	}
	public void setRuleAction(String ruleAction) {
		this.ruleAction = ruleAction;
	}
	
	
	public String getReasonCode() {
		return reasonCode;
	}
	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}
	
	
	public String getIsEnabled() {
		return isEnabled;
	}
	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}
	
	
	public String getApprovalRequired() {
		return approvalRequired;
	}
	public void setApprovalRequired(String approvalRequired) {
		this.approvalRequired = approvalRequired;
	}
	

	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
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
