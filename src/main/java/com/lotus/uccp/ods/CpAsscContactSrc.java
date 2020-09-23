package com.lotus.uccp.ods;
// Generated May 31, 2018 4:53:55 PM by Hibernate Tools 5.2.6.Final

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CpAsscContactSrc generated by hbm2java
 */
import com.lotus.uccp.util.Constant; 
@Entity
@Table(schema = Constant.SCHEMANAME_ODS , name = "CP_ASSC_CONTACT_SRC")
public class CpAsscContactSrc implements java.io.Serializable {

	private BigDecimal cpAsscContactSeqNum;
	private ClientProfileAsscSrc clientProfileAsscSrc;
	private String firstName;
	private String lastName;
	private Date dob;
	private Short age;
	private String gender;
	private String sysId;
	private String relationToClent;
	private String ssn;

	public CpAsscContactSrc() {
	}

	public CpAsscContactSrc(BigDecimal cpAsscContactSeqNum) {
		this.cpAsscContactSeqNum = cpAsscContactSeqNum;
	}

	public CpAsscContactSrc(BigDecimal cpAsscContactSeqNum, ClientProfileAsscSrc clientProfileAsscSrc, String firstName,
			String lastName, Date dob, Short age, String gender, String sysId, String relationToClent, String ssn) {
		this.cpAsscContactSeqNum = cpAsscContactSeqNum;
		this.clientProfileAsscSrc = clientProfileAsscSrc;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.age = age;
		this.gender = gender;
		this.sysId = sysId;
		this.relationToClent = relationToClent;
		this.ssn = ssn;
	}

	@Id

	@Column(name = "CP_ASSC_CONTACT_SEQ_NUM", unique = true, nullable = false, precision = 31, scale = 0)
	public BigDecimal getCpAsscContactSeqNum() {
		return this.cpAsscContactSeqNum;
	}

	public void setCpAsscContactSeqNum(BigDecimal cpAsscContactSeqNum) {
		this.cpAsscContactSeqNum = cpAsscContactSeqNum;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLIENT_PROFILE_ASSC_SEQ_NUM")
	public ClientProfileAsscSrc getClientProfileAsscSrc() {
		return this.clientProfileAsscSrc;
	}

	public void setClientProfileAsscSrc(ClientProfileAsscSrc clientProfileAsscSrc) {
		this.clientProfileAsscSrc = clientProfileAsscSrc;
	}

	@Column(name = "FIRST_NAME", length = 75)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "LAST_NAME", length = 75)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DOB", length = 10)
	public Date getDob() {
		return this.dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	@Column(name = "AGE", precision = 3, scale = 0)
	public Short getAge() {
		return this.age;
	}

	public void setAge(Short age) {
		this.age = age;
	}

	@Column(name = "GENDER", length = 15)
	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name = "SYS_ID", length = 60)
	public String getSysId() {
		return this.sysId;
	}

	public void setSysId(String sysId) {
		this.sysId = sysId;
	}

	@Column(name = "RELATION_TO_CLENT", length = 50)
	public String getRelationToClent() {
		return this.relationToClent;
	}

	public void setRelationToClent(String relationToClent) {
		this.relationToClent = relationToClent;
	}

	@Column(name = "SSN", length = 11)
	public String getSsn() {
		return this.ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

}
