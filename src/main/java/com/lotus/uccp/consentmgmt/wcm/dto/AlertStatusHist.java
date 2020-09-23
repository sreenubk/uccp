package com.lotus.uccp.consentmgmt.wcm.dto;

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


@Entity
@Table(name = "WCM_ALERT_STATUS_HIST", schema = Constant.SCHEMA)
public class AlertStatusHist implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BigDecimal his_reqreqtid;
	private AlertRequest reqReqtID;
	private Date statusDate;
	private String status;
	private String statusUser;
	private String statusComments;
	
	@Id
	@SequenceGenerator(schema = Constant.SCHEMA, name = "ALERTREQTID_HISseq", sequenceName = "ALERTREQTID_HISseq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ALERTREQTID_HISseq")
	@Column(name = "HIS_ALERTREQTID", unique = true, nullable = false, scale = 0)
	public BigDecimal getHis_reqreqtid() {
		return his_reqreqtid;
	}

	public void setHis_reqreqtid(BigDecimal his_reqreqtid) {
		this.his_reqreqtid = his_reqreqtid;
	}	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ALERTREQTID")
	public AlertRequest getRegReq() {
		return reqReqtID;
	}
	
	public void setRegReq(AlertRequest reqReqtID) {
		this.reqReqtID = reqReqtID;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "STATUSDATE")
	public Date getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}

	@Column(name = "STATUS", nullable = false, length = 40)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "STATUSUSER", nullable = false, length = 30)
	public String getStatusUser() {
		return statusUser;
	}

	public void setStatusUser(String statusUser) {
		this.statusUser = statusUser;
	}
	
	@Column(name = "STATUSCOMMENTS", nullable = false, length = 255)
	public String getStatusComments() {
		return statusComments;
	}

	public void setStatusComments(String statusComments) {
		this.statusComments = statusComments;
	}
	
}

/*
 * Oracle
CREATE TABLE WCM_ALERT_STATUS_HIST (
	his_alertReqtId number(30,2) NOT NULL,
    alertReqtId number(20,2) NOT NULL,
    statusDate date  NOT NULL,
	status varchar2(40)  NOT NULL,
    statusUser varchar2(30)  NOT NULL,
    statusComments varchar2(255)  NOT NULL
    ) 
    ALTER TABLE WCM_ALERT_STATUS_HIST ADD PRIMARY KEY (HIS_ALERTREQTID); 
    CREATE SEQUENCE ALERTREQTID_HISseq
  MINVALUE 1
  MAXVALUE 999999999999999999999999999
  START WITH 1
  INCREMENT BY 1
  CACHE 20;

*/

/*
 *DB2 
 * CREATE TABLE WCM_ALERT_STATUS_HIST (
    his_alertReqtId DECIMAL(30,2) NOT NULL,
    alertReqtId DECIMAL(20,2) NOT NULL,
    statusDate date  NOT NULL,
    status varchar(40)  NOT NULL,
    statusUser varchar(30)  NOT NULL,
    statusComments varchar(255)  NOT NULL
    )
ALTER TABLE WCM_ALERT_STATUS_HIST ADD PRIMARY KEY (HIS_ALERTREQTID); 

  
  CREATE SEQUENCE ALERTREQTID_HISseq
 START WITH 1
 INCREMENT BY 1
 NO MAXVALUE
 NO CYCLE
 CACHE 24;
 */


