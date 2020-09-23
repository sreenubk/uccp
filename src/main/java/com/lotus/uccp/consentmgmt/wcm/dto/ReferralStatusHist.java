package com.lotus.uccp.consentmgmt.wcm.dto ;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.lotus.uccp.util.Constant;

@SuppressWarnings("serial")
@Entity
@Table(name = "WCM_REFERRAL_STATUS_HIST", schema = Constant.SCHEMA)
public class ReferralStatusHist  implements java.io.Serializable {

	BigDecimal his_reqreqtid ;
	RegistrationRequest reqReqtID ;
	Date statusDate ;
	String status ; 
	String statusUser ;
	String statusComments ;
	
	public ReferralStatusHist() {
		
	}
		
	public ReferralStatusHist(BigDecimal his_reqreqtid, RegistrationRequest reqReqtID, Date statusDate, String status,
			String statusUser, String statusComments) {
		super();
		this.his_reqreqtid = his_reqreqtid;
		this.reqReqtID = reqReqtID;
		this.statusDate = statusDate;
		this.status = status;
		this.statusUser = statusUser;
		this.statusComments = statusComments;
	}

	@Id
	@SequenceGenerator(schema = Constant.SCHEMA,name = "REGREQTID_HISseq", sequenceName = "REGREQTID_HISseq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REGREQTID_HISseq")
	@Column(name = "his_reqreqtid", unique = true, nullable = false, scale = 0)
	public BigDecimal getHis_reqreqtid() {
		return his_reqreqtid;
	}

	public void setHis_reqreqtid(BigDecimal his_reqreqtid) {
		this.his_reqreqtid = his_reqreqtid;
	}	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reqReqtID")
	public RegistrationRequest getRegReq() {
		return reqReqtID;
	}
	
	public void setRegReq(RegistrationRequest reqReqtID) {
		this.reqReqtID = reqReqtID;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "statusDate", length = 13)
	public Date getStatusDate() {
		return statusDate;
	}
	
	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}
	
	@Column(name = "status", nullable = false, length = 30)
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "statusUser", nullable = false, length = 30)
	public String getStatusUser() {
		return statusUser;
	}
	
	public void setStatusUser(String statusUser) {
		this.statusUser = statusUser;
	}
	
	@Column(name = "statusComments", nullable = false, length = 100)
	public String getStatusComments() {
		return statusComments;
	}
	
	public void setStatusComments(String statusComments) {
		this.statusComments = statusComments;
	}
	
}

/*
-- Table: WCM_REFERRAL_STATUS_HIST
CREATE TABLE WCM_REFERRAL_STATUS_HIST (
	his_reqreqtid number(30,2) NOT NULL,
    reqReqtID number(20,2) NOT NULL,
    statusDate date  NOT NULL,
	status varchar2(40)  NOT NULL,
    statusUser varchar2(30)  NOT NULL,
    statusComments varchar2(100)  NOT NULL
) ;*/