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
@Table(name = "WCM_COHORT_TARGET", schema = Constant.SCHEMA)
public class CohortTarget {

	Cohort cohortid ;
	Target targetedID ;
	BigDecimal targetcohortid ;
	
	String name ;
	
	@Column(name = "NAME", nullable = false, length = 30)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cohortid")	
	public Cohort getCohortid() {
		return cohortid;
	}
	public void setCohortid(Cohort cohortid) {
		this.cohortid = cohortid;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "targetedID")	
	public Target getTargetedID() {
		return targetedID;
	}
	public void setTargetedID(Target targetedID) {
		this.targetedID = targetedID;
	}

	@Id
	@SequenceGenerator(schema = Constant.SCHEMA,name  = "targetcohortidseq", sequenceName = "targetcohortidseq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="targetcohortidseq")
	@Column(name = "targetcohortid", unique = true, nullable = false, scale = 0)	
	public BigDecimal getTargetcohortid() {
		return targetcohortid;
	}
	public void setTargetcohortid(BigDecimal targetcohortid) {
		this.targetcohortid = targetcohortid;
	}
	
}
/*
 * COHORTID
TARGETCOHORTID
NAME
TARGETEDID
 * 
 */
