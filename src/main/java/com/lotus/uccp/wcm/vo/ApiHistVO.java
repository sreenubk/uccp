package com.lotus.uccp.wcm.vo;

import java.math.BigDecimal;

public class ApiHistVO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BigDecimal apiHistId;
	private BigDecimal targetID;
	private BigDecimal requestId;
	private String apiAction;
	private String sendDate;
	private String sendStatus;
	private BigDecimal targetTransID;

	public BigDecimal getApiHistId() {
		return apiHistId;
	}

	public void setApiHistId(BigDecimal apiHistId) {
		this.apiHistId = apiHistId;
	}

	public BigDecimal getTargetID() {
		return targetID;
	}

	public void setTargetID(BigDecimal targetID) {
		this.targetID = targetID;
	}

	public BigDecimal getRequestId() {
		return requestId;
	}

	public void setRequestId(BigDecimal requestId) {
		this.requestId = requestId;
	}

	public String getApiAction() {
		return apiAction;
	}

	public void setApiAction(String apiAction) {
		this.apiAction = apiAction;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public String getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}

	public BigDecimal getTargetTransID() {
		return targetTransID;
	}

	public void setTargetTransID(BigDecimal targetTransID) {
		this.targetTransID = targetTransID;
	}

}
