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

public class CohortVO {
	
	private BigDecimal cohortid;
	private String cohortdescription;
	private String name ;
	
	
	public CohortVO() {
	}

	
	
	public BigDecimal getCohortid() {
		return cohortid;
	}
	public void setCohortid(BigDecimal cohortid) {
		this.cohortid = cohortid;
	}
	
	
	public String getCohortdescription() {
		return cohortdescription;
	}
	public void setCohortdescription(String cohortdescription) {
		this.cohortdescription = cohortdescription;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

/*
 * CREATE TABLE WCM_COHORT (
    cohortID number(25,2)  NOT NULL,
    cohortDescription varchar2(100)  NOT NULL,
    CONSTRAINT COHORT_pk PRIMARY KEY (cohortID)
) ;
	-- Table: COHORT_RULE
	CREATE TABLE COHORT_RULE (
	    cohortID varchar2(30)  NULL,
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

	-- Table: COHORT_TARGET
	CREATE TABLE COHORT_TARGET (
	    cohortID varchar2(30)  NULL,
	    targetedID varchar2(30)  NULL,
	    targetCohortID varchar2(50)  NULL
	) ; */
