package com.lotus.uccp.wcm.vo;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.lotus.uccp.util.Constant;

@SuppressWarnings("serial")
public class AlertStatusHistVO implements java.io.Serializable {

	BigDecimal his_reqreqtid;
	BigDecimal reqReqtID;
	String statusDate;
	String status;
	String statusUser;
	String statusComments;

	public BigDecimal getHis_reqreqtid() {
		return his_reqreqtid;
	}

	public void setHis_reqreqtid(BigDecimal his_reqreqtid) {
		this.his_reqreqtid = his_reqreqtid;
	}

	public BigDecimal getReqReqtID() {
		return reqReqtID;
	}

	public void setReqReqtID(BigDecimal reqReqtID) {
		this.reqReqtID = reqReqtID;
	}

	public String getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(String statusDate) {
		this.statusDate = statusDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusUser() {
		return statusUser;
	}

	public void setStatusUser(String statusUser) {
		this.statusUser = statusUser;
	}

	public String getStatusComments() {
		return statusComments;
	}

	public void setStatusComments(String statusComments) {
		this.statusComments = statusComments;
	}

}

/*
 * -- Table: WCM_ALERT_STATUS_HIST CREATE TABLE WCM_ALERT_STATUS_HIST (
 * his_reqreqtid number(30,2) NOT NULL, reqReqtID number(20,2) NOT NULL,
 * statusDate date NOT NULL, status varchar2(40) NOT NULL, statusUser
 * varchar2(30) NOT NULL, statusComments varchar2(100) NOT NULL ) ;
 */