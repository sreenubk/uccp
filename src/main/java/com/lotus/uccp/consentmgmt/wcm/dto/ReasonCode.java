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
@Table(name = "WCM_REASON_CODE", schema = Constant.SCHEMA)
public class ReasonCode {

	String reasonCode ;
	String reasonDesc ;
	
	private Set<CohortRule> cohortRule = new HashSet<CohortRule>(0);
	
	@Id
	@Column(name = "reasonCode", unique = true, nullable = false, scale = 0)
	public String getReasonCode() {
		return reasonCode;
	}
	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}
	
	@Column(name = "REASONDESC", nullable = false, length = 30)
	public String getReasonDesc() {
		return reasonDesc;
	}
	public void setReasonDesc(String reasonDesc) {
		this.reasonDesc = reasonDesc;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "reasonCode")
	public Set<CohortRule> getCohortRule() {
		return cohortRule;
	}

	public void setCohortRule(Set<CohortRule> cohortRule) {
		this.cohortRule = cohortRule;
	}
	
}
/*
 * -- Table: REASON_CODE
CREATE TABLE REASON_CODE (
    reasonCode varchar2(40)  NOT NULL,
    reasonDesc varchar2(100)  NOT NULL
) ;
*/
