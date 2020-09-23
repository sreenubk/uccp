package com.lotus.uccp.authentication.model;

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
@Table(name = "Referral_LA", schema = Constant.SCHEMA)
public class ReferralLA {
	
	private BigDecimal referralID ;
	private String clientID ;
	private String customer_name ;
	private String Other_info ;
	private String Referral_Type ;
	private String fname ;
	
	private String lname ;
	private String memberID;
	private String srccode;
	private String cvwName;
	
	private String minor;
	
	private String parolee;
	
	private String transName ;
	
	@Column(name = "transName", length = 20)
	public String getTransName() {
		return transName;
	}
	public void setTransName(String transName) {
		this.transName = transName;
	}
	@Id
	@SequenceGenerator(schema = Constant.SCHEMA,name  = "ReferralSeq", sequenceName = "ReferralSeq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ReferralSeq")
	@Column(name = "referralID", unique = true, nullable = false, scale = 0)	
	public BigDecimal getReferralID() {
		return referralID;
	}
	public void setReferralID(BigDecimal referralID) {
		this.referralID = referralID;
	}
	
	@Column(name = "clientID", length = 10)
	public String getClientID() {
		return clientID;
	}
	public void setClientID(String clientID) {
		this.clientID = clientID;
	}
	
	@Column(name = "customer_name", length = 50)
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	
	@Column(name = "Other_info", length = 50)
	public String getOther_info() {
		return Other_info;
	}
	public void setOther_info(String other_info) {
		Other_info = other_info;
	}
	
	@Column(name = "Referral_Type", length = 50)
	public String getReferral_Type() {
		return Referral_Type;
	}
	public void setReferral_Type(String referral_Type) {
		Referral_Type = referral_Type;
	}
	
	@Column(name = "fname", length = 20)
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	
	@Column(name = "lname", length = 20)
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	@Column(name = "memberID", length = 20)
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	
	@Column(name = "srccode", length = 20)
	public String getSrccode() {
		return srccode;
	}
	public void setSrccode(String srccode) {
		this.srccode = srccode;
	}
	
	@Column(name = "cvwName", length = 20)
	public String getCvwName() {
		return cvwName;
	}
	public void setCvwName(String cvwName) {
		this.cvwName = cvwName;
	}
	
	@Column(name = "minor", length = 20)
	public String getMinor() {
		return minor;
	}
	public void setMinor(String minor) {
		this.minor = minor;
	}
	
	@Column(name = "parolee", length = 20)
	public String getParolee() {
		return parolee;
	}
	public void setParolee(String parolee) {
		this.parolee = parolee;
	}

}
