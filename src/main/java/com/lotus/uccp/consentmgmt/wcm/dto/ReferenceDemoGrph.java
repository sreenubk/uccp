package com.lotus.uccp.consentmgmt.wcm.dto ;

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
@Table(name = "Reference_DemoGrph", schema = Constant.SCHEMA)
public class ReferenceDemoGrph {
	
	/*demoGrphID number(19,2) not null ,
	  CUSTOMER_NAME varchar2(50 char) ,
	  Address varchar2(50 char) ,
	  dob varchar2(20 char) ,
	  gender varchar2(5 char),
	  MEMBERID VARCHAR2(20 char) ,
	  Phone VARCHAR2(20 char) ,
	  SSN VARCHAR2(20 char) ,
	  DL VARCHAR2(20 char) ,
	  RelationShip VARCHAR2(30 char) ,
	  SrcSystem1 VARCHAR2(30 char) ,
	  SrcSystem2 VARCHAR2(30 char) ,
	  SrcSystem3 VARCHAR2(30 char) ,*/
	
	private BigDecimal demoGrphID ;	
	private String customer_name ;
	private String dob ;
	private String address ;
	
	private String gender ;
	private String MEMBERID ;
	private String Phone ;
	private String SSN ;
	private String DL ;
	private String RelationShip ;
	private String SrcSystem1 ;
	private String SrcSystem2 ;
	private String SrcSystem3 ;
	
	@Id
	@SequenceGenerator(schema = Constant.SCHEMA,name  = "demoGrphIDSeq", sequenceName = "demoGrphIDSeq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="demoGrphIDSeq")
	@Column(name = "demoGrphID", unique = true, nullable = false, scale = 0)	
	public BigDecimal getDemoGrphID() {
		return demoGrphID;
	}
	public void setDemoGrphID(BigDecimal demoGrphID) {
		this.demoGrphID = demoGrphID;
	}
	
	@Column(name = "Address", length = 50)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name = "CUSTOMER_NAME", length = 50)
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	
	@Column(name = "dob", length = 20)
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	
	@Column(name = "gender", length = 10)
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Column(name = "MEMBERID", length = 20)
	public String getMEMBERID() {
		return MEMBERID;
	}
	public void setMEMBERID(String mEMBERID) {
		MEMBERID = mEMBERID;
	}
	
	@Column(name = "Phone", length = 20)
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	
	@Column(name = "SSN", length = 20)
	public String getSSN() {
		return SSN;
	}
	public void setSSN(String sSN) {
		SSN = sSN;
	}
	
	@Column(name = "DL", length = 20)
	public String getDL() {
		return DL;
	}
	public void setDL(String dL) {
		DL = dL;
	}
	@Column(name = "RelationShip", length = 30)
	public String getRelationShip() {
		return RelationShip;
	}
	public void setRelationShip(String relationShip) {
		RelationShip = relationShip;
	}
	@Column(name = "SrcSystem1", length = 30)
	public String getSrcSystem1() {
		return SrcSystem1;
	}
	public void setSrcSystem1(String srcSystem1) {
		SrcSystem1 = srcSystem1;
	}
	@Column(name = "SrcSystem2", length = 30)
	public String getSrcSystem2() {
		return SrcSystem2;
	}
	public void setSrcSystem2(String srcSystem2) {
		SrcSystem2 = srcSystem2;
	}
	@Column(name = "SrcSystem3", length = 30)
	public String getSrcSystem3() {
		return SrcSystem3;
	}
	public void setSrcSystem3(String srcSystem3) {
		SrcSystem3 = srcSystem3;
	}
	
	

}
