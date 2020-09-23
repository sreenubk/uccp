package com.lotus.uccp.consentmgmt.wcm.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lotus.uccp.util.Constant;

@Entity
@Table(name = "WCM_API_HIST", schema = Constant.SCHEMA)
public class ApiHist implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BigDecimal apiHistId;
	private BigDecimal targetID;
	private BigDecimal requestId;
	private String apiAction;
	private Date sendDate;
	private String sendStatus;
	private BigDecimal targetTransID;

	public ApiHist() {
	}

	public ApiHist(BigDecimal targetID, BigDecimal requestId, String apiAction, Date sendDate, String sendStatus,
			BigDecimal targetTransID) {
		super();
		this.targetID = targetID;
		this.requestId = requestId;
		this.apiAction = apiAction;
		this.sendDate = sendDate;
		this.sendStatus = sendStatus;
		this.targetTransID = targetTransID;
	}

	public ApiHist(BigDecimal apiHistId, BigDecimal targetID, BigDecimal requestId, String apiAction, Date sendDate,
			String sendStatus, BigDecimal targetTransID) {
		super();
		this.apiHistId = apiHistId;
		this.targetID = targetID;
		this.requestId = requestId;
		this.apiAction = apiAction;
		this.sendDate = sendDate;
		this.sendStatus = sendStatus;
		this.targetTransID = targetTransID;
	}

	@Id
	@SequenceGenerator(schema = Constant.SCHEMA, name = "SEQ_APIHISTID", sequenceName = "SEQ_APIHISTID", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_APIHISTID")
	@Column(name = "APIHISTID", unique = true, nullable = false, scale = 0)
	public BigDecimal getApiHistId() {
		return apiHistId;
	}

	public void setApiHistId(BigDecimal apiHistId) {
		this.apiHistId = apiHistId;
	}

	@Column(name = "TARGETID", nullable = false, length = 25)
	public BigDecimal getTargetID() {
		return targetID;
	}

	public void setTargetID(BigDecimal targetID) {
		this.targetID = targetID;
	}

	@Column(name = "REQUESTID", nullable = false, length = 25)
	public BigDecimal getRequestId() {
		return requestId;
	}

	public void setRequestId(BigDecimal requestId) {
		this.requestId = requestId;
	}

	@Column(name = "APIACTION", nullable = false, length = 50)
	public String getApiAction() {
		return apiAction;
	}

	public void setApiAction(String apiAction) {
		this.apiAction = apiAction;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "SENDDATE")
	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	@Column(name = "SENDSTATUS", nullable = false, length = 40)
	public String getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}

	@Column(name = "TARGETTRANSID", nullable = false, length = 20)
	public BigDecimal getTargetTransID() {
		return targetTransID;
	}

	public void setTargetTransID(BigDecimal targetTransID) {
		this.targetTransID = targetTransID;
	}

	@Override
	public String toString() {
		return "ApiHist [apiHistId=" + apiHistId + ", targetID=" + targetID + ", requestId=" + requestId
				+ ", apiAction=" + apiAction + ", sendDate=" + sendDate + ", sendStatus=" + sendStatus
				+ ", targetTransID=" + targetTransID + "]";
	}

}
