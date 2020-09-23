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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lotus.uccp.util.Constant;
@Entity
@Table(name = "WCM_COHORT", schema = Constant.SCHEMA)
public class Cohort {
	
	private BigDecimal cohortid;
	private String name ;
	private String cohortdescription;
	
	private Set<CohortRule> cohortRule = new HashSet<CohortRule>(0);
	
	
	public Cohort() {
	}

	public Cohort(BigDecimal cohortid, String cohortdescription, 
			Set<CohortRule> cohortRule
			) {
		this.cohortid = cohortid;
		this.cohortdescription = cohortdescription;
		this.cohortRule = cohortRule;
	}
	
	
	@Id	
	//@SequenceGenerator(schema = Constant.SCHEMA,name  = "cohortidseq", sequenceName = "cohortidseq", allocationSize=1 )
	@SequenceGenerator(schema = Constant.SCHEMA,name = "cohortidseq", sequenceName = "cohortidseq", allocationSize=1 )
	@GeneratedValue( strategy=GenerationType.SEQUENCE, generator="cohortidseq" )
	@Column(name = "COHORTID", unique = true, nullable = false, scale = 0)
		public BigDecimal getCohortid() {
		return cohortid;
	}
	public void setCohortid(BigDecimal cohortid) {
		this.cohortid = cohortid;
	}
	
	@Column(name = "COHORTDESCRIPTION", nullable = false, length = 100)
	public String getCohortdescription() {
		return cohortdescription;
	}
	public void setCohortdescription(String cohortdescription) {
		this.cohortdescription = cohortdescription;
	}
	
	@Column(name = "COHORTNAME", nullable = false, length = 50)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cohortid")
	public Set<CohortRule> getCohortRule() {
		return cohortRule;
	}

	public void setCohortRule(Set<CohortRule> cohortRule) {
		this.cohortRule = cohortRule;
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
