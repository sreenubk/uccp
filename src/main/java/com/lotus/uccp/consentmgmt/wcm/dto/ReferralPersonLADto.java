package com.lotus.uccp.consentmgmt.wcm.dto;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.lotus.uccp.util.Constant;

@Entity
@Table(name = "REF_PERSON_LA", schema = Constant.SCHEMA)
public class ReferralPersonLADto {

	private BigDecimal personID ;
	private String clientID ;
	private String customerName ;
	private String DOB ;
	private String gender ;
	private String relationship ;
	private String address ;
	private String memberID ;
	private String fName ;
	private String lName ;
		
	@Id
	@SequenceGenerator(schema = Constant.SCHEMA, name = "ReferralPersonSeq", sequenceName = "ReferralPersonSeq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ReferralPersonSeq")
	@Column(name = "personID", unique = true, nullable = false, scale = 0)
	public BigDecimal getPersonID() {
		return personID;
	}
	public void setPersonID(BigDecimal personID) {
		this.personID = personID;
	}
	
	@Column(name = "clientID", length = 10)
	public String getClientID() {
		return clientID;
	}
	public void setClientID(String clientID) {
		this.clientID = clientID;
	}
	
	@Column(name = "customerName", length = 50)
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	@Column(name = "DOB", length = 25)
	public String getDOB() {
		return DOB;
	}
	public void setDOB(String dOB) {
		DOB = dOB;
	}
	
	@Column(name = "gender", length = 10)
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Column(name = "relationship", length = 30)
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	
	@Column(name = "address", length = 70)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name = "memberID", length = 20)
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	
	@Column(name = "fName", length = 30)
	public String getFName() {
		return fName;
	}
	public void setFName(String fName) {
		this.fName = fName;
	}
	
	@Column(name = "lName", length = 30)
	public String getLName() {
		return lName;
	}
	public void setLName(String lName) {
		this.lName = lName;
	}		
	
}
