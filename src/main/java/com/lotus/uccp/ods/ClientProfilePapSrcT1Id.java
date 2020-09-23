package com.lotus.uccp.ods;
// Generated May 31, 2018 4:53:55 PM by Hibernate Tools 5.2.6.Final

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ClientProfilePapSrcT1Id generated by hbm2java
 */
@Embeddable
public class ClientProfilePapSrcT1Id implements java.io.Serializable {

	private BigDecimal clientProfPapSeqNum;
	private BigDecimal clientId;
	private String caseId;

	public ClientProfilePapSrcT1Id() {
	}

	public ClientProfilePapSrcT1Id(BigDecimal clientProfPapSeqNum, BigDecimal clientId, String caseId) {
		this.clientProfPapSeqNum = clientProfPapSeqNum;
		this.clientId = clientId;
		this.caseId = caseId;
	}

	@Column(name = "CLIENT_PROF_PAP_SEQ_NUM", precision = 31, scale = 0)
	public BigDecimal getClientProfPapSeqNum() {
		return this.clientProfPapSeqNum;
	}

	public void setClientProfPapSeqNum(BigDecimal clientProfPapSeqNum) {
		this.clientProfPapSeqNum = clientProfPapSeqNum;
	}

	@Column(name = "CLIENT_ID", precision = 31, scale = 0)
	public BigDecimal getClientId() {
		return this.clientId;
	}

	public void setClientId(BigDecimal clientId) {
		this.clientId = clientId;
	}

	@Column(name = "CASE_ID", length = 50)
	public String getCaseId() {
		return this.caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ClientProfilePapSrcT1Id))
			return false;
		ClientProfilePapSrcT1Id castOther = (ClientProfilePapSrcT1Id) other;

		return ((this.getClientProfPapSeqNum() == castOther.getClientProfPapSeqNum())
				|| (this.getClientProfPapSeqNum() != null && castOther.getClientProfPapSeqNum() != null
						&& this.getClientProfPapSeqNum().equals(castOther.getClientProfPapSeqNum())))
				&& ((this.getClientId() == castOther.getClientId()) || (this.getClientId() != null
						&& castOther.getClientId() != null && this.getClientId().equals(castOther.getClientId())))
				&& ((this.getCaseId() == castOther.getCaseId()) || (this.getCaseId() != null
						&& castOther.getCaseId() != null && this.getCaseId().equals(castOther.getCaseId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getClientProfPapSeqNum() == null ? 0 : this.getClientProfPapSeqNum().hashCode());
		result = 37 * result + (getClientId() == null ? 0 : this.getClientId().hashCode());
		result = 37 * result + (getCaseId() == null ? 0 : this.getCaseId().hashCode());
		return result;
	}

}
