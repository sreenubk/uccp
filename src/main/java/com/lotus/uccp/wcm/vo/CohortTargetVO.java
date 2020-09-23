package com.lotus.uccp.wcm.vo ;
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

public class CohortTargetVO {

	BigDecimal cohortid ;
	BigDecimal targetedID ;
	BigDecimal targetcohortid ;	
	String name ;
	String cohortName ;
	String cohortDesc ;
	
	public String getCohortName() {
		return cohortName;
	}
	public void setCohortName(String cohortName) {
		this.cohortName = cohortName;
	}
	public String getCohortDesc() {
		return cohortDesc;
	}
	public void setCohortDesc(String cohortDesc) {
		this.cohortDesc = cohortDesc;
	}

	
	public BigDecimal getCohortid() {
		return cohortid;
	}
	public void setCohortid(BigDecimal cohortid) {
		this.cohortid = cohortid;
	}
	public BigDecimal getTargetedID() {
		return targetedID;
	}
	public void setTargetedID(BigDecimal targetedID) {
		this.targetedID = targetedID;
	}
	public BigDecimal getTargetcohortid() {
		return targetcohortid;
	}
	public void setTargetcohortid(BigDecimal targetcohortid) {
		this.targetcohortid = targetcohortid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
/*
 * COHORTID
TARGETCOHORTID
NAME
TARGETEDID
 * 
 */
