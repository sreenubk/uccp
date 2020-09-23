package com.lotus.uccp.wcm.vo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;




public class ReferralLAVO {
	
	private BigDecimal referralID ;
	private String clientID ;
	private String customerName ;
	private String customer_name ;
	
private String minor;
	
	private String parolee;
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	private String Other_info ;
	private String Referral_Type ;
	private String fname ;
	private String lname ;
	private String memberID;
	private String srccode;
	private String cvwName;
	private String bdate;
	
	private String transName ;
	
	
	public String getTransName() {
		return transName;
	}
	public void setTransName(String transName) {
		this.transName = transName;
	}
	
	public String getBdate() {
		return bdate;
	}
	public void setBdate(String bdate) {
		this.bdate = bdate;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public String getSrccode() {
		return srccode;
	}
	public void setSrccode(String srccode) {
		this.srccode = srccode;
	}
	public String getCvwName() {
		return cvwName;
	}
	public void setCvwName(String cvwName) {
		this.cvwName = cvwName;
	}
	
	
	public BigDecimal getReferralID() {
		return referralID;
	}
	public void setReferralID(BigDecimal referralID) {
		this.referralID = referralID;
	}
	
	
	public String getClientID() {
		return clientID;
	}
	public void setClientID(String clientID) {
		this.clientID = clientID;
	}
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
		this.customer_name = customerName ;
	}
	
	
	public String getOther_info() {
		return Other_info;
	}
	public void setOther_info(String other_info) {
		Other_info = other_info;
	}
	
	
	public String getReferral_Type() {
		return Referral_Type;
	}
	public void setReferral_Type(String referral_Type) {
		Referral_Type = referral_Type;
	}
	
	
	public String getMinor() {
		return minor;
	}
	public void setMinor(String minor) {
		this.minor = minor;
	}
	
	
	public String getParolee() {
		return parolee;
	}
	public void setParolee(String parolee) {
		this.parolee = parolee;
	}

}
