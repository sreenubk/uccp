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
@Table(name = "WCM_TARGET", schema = Constant.SCHEMA)
public class Target {
	
	BigDecimal targetedID ;
	String targetName ;
	String targetAddress ;
	String targetAltAddress ;
	String targetDescription ;
	
	private Set<CohortTarget> cohorttarget = new HashSet<CohortTarget>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "targetedID")
	public Set<CohortTarget> getCohorttarget() {
		return cohorttarget;
	}
	public void setCohorttarget(Set<CohortTarget> cohorttarget) {
		this.cohorttarget = cohorttarget;
	}
	
	@Id
	@SequenceGenerator(schema = Constant.SCHEMA,name  = "targetedidseq", sequenceName = "targetedidseq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="targetedidseq")
	@Column(name = "targetedID", unique = true, nullable = false, scale = 0)	
	public BigDecimal getTargetedID() {
		return targetedID;
	}
	public void setTargetedID(BigDecimal targetedID) {
		this.targetedID = targetedID;
	}
	
	@Column(name = "targetedName", nullable = false, length = 30)
	public String getTargetName() {
		return targetName;
	}
	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}
	
	@Column(name = "targetAddress", nullable = false, length = 50)
	public String getTargetAddress() {
		return targetAddress;
	}
	public void setTargetAddress(String targetAddress) {
		this.targetAddress = targetAddress;
	}
	
	@Column(name = "targetAltAddress", nullable = false, length = 50)
	public String getTargetAltAddress() {
		return targetAltAddress;
	}
	public void setTargetAltAddress(String targetAltAddress) {
		this.targetAltAddress = targetAltAddress;
	}
	
	@Column(name = "targetDescription", nullable = false, length = 100)
	public String getTargetDescription() {
		return targetDescription;
	}
	public void setTargetDescription(String targetDescription) {
		this.targetDescription = targetDescription;
	}
	
	
	/**/
	

}

/*CREATE TABLE WCM_TARGET (
	    targetedID number  NOT NULL,
		targetedName varchar2(30)  NOT NULL,
		targetAddress varchar2(50)  NOT NULL,
		targetAltAddress varchar2(50)  NOT NULL,
	    targetDescription varchar2(100)  NOT NULL,
	    CONSTRAINT TARGET_pk PRIMARY KEY (targetedID)
	) ; */
