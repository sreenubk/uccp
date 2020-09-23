package com.lotus.uccp.wcm.vo;
import java.util.ArrayList;



public class CohortRulesVO {

	private CohortVO cohortvo ;
	
	public CohortVO getCohortvo() {
		return cohortvo;
	}

	public void setCohortvo(CohortVO cohortvo) {
		this.cohortvo = cohortvo;
	}

	private ArrayList<CohortRuleVO> listCohortRules = new ArrayList<>() ;

	public ArrayList<CohortRuleVO> getListCohortRules() {
		return listCohortRules;
	}

	public void setListCohortRules(ArrayList<CohortRuleVO> listCohortRules) {
		this.listCohortRules = listCohortRules;
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
