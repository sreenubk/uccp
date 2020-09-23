package com.lotus.uccp.wcm.vo ;
import java.math.BigDecimal;


public class TargetVO {
	
	BigDecimal targetedID ;
	String targetName ;
	String targetAddress ;
	String targetAltAddress ;
	String targetDescription ;
	
	

	public BigDecimal getTargetedID() {
		return targetedID;
	}
	public void setTargetedID(BigDecimal targetedID) {
		this.targetedID = targetedID;
	}
	
	
	public String getTargetName() {
		return targetName;
	}
	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}
	
	
	public String getTargetAddress() {
		return targetAddress;
	}
	public void setTargetAddress(String targetAddress) {
		this.targetAddress = targetAddress;
	}
	
	
	public String getTargetAltAddress() {
		return targetAltAddress;
	}
	public void setTargetAltAddress(String targetAltAddress) {
		this.targetAltAddress = targetAltAddress;
	}
	
	
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
