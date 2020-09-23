package com.lotus.uccp.ods;
// Generated May 31, 2018 4:53:55 PM by Hibernate Tools 5.2.6.Final

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * CpPscProviderSrc generated by hbm2java
 */
import com.lotus.uccp.util.Constant; 
@Entity
@Table(schema = Constant.SCHEMANAME_ODS , name = "CP_PSC_PROVIDER_SRC")
public class CpPscProviderSrc implements java.io.Serializable {

	private BigDecimal cpPscProviderSeqNum;
	private ClientProfilePscSrc clientProfilePscSrc;
	private String svcProviderName;
	private String svcProviderType;

	public CpPscProviderSrc() {
	}

	public CpPscProviderSrc(BigDecimal cpPscProviderSeqNum, ClientProfilePscSrc clientProfilePscSrc) {
		this.cpPscProviderSeqNum = cpPscProviderSeqNum;
		this.clientProfilePscSrc = clientProfilePscSrc;
	}

	public CpPscProviderSrc(BigDecimal cpPscProviderSeqNum, ClientProfilePscSrc clientProfilePscSrc,
			String svcProviderName, String svcProviderType) {
		this.cpPscProviderSeqNum = cpPscProviderSeqNum;
		this.clientProfilePscSrc = clientProfilePscSrc;
		this.svcProviderName = svcProviderName;
		this.svcProviderType = svcProviderType;
	}

	@Id

	@Column(name = "CP_PSC_PROVIDER_SEQ_NUM", unique = true, nullable = false, precision = 31, scale = 0)
	public BigDecimal getCpPscProviderSeqNum() {
		return this.cpPscProviderSeqNum;
	}

	public void setCpPscProviderSeqNum(BigDecimal cpPscProviderSeqNum) {
		this.cpPscProviderSeqNum = cpPscProviderSeqNum;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLIENT_PROFILE_PSC_SEQ_NUM", nullable = false)
	public ClientProfilePscSrc getClientProfilePscSrc() {
		return this.clientProfilePscSrc;
	}

	public void setClientProfilePscSrc(ClientProfilePscSrc clientProfilePscSrc) {
		this.clientProfilePscSrc = clientProfilePscSrc;
	}

	@Column(name = "SVC_PROVIDER_NAME", length = 35)
	public String getSvcProviderName() {
		return this.svcProviderName;
	}

	public void setSvcProviderName(String svcProviderName) {
		this.svcProviderName = svcProviderName;
	}

	@Column(name = "SVC_PROVIDER_TYPE", length = 40)
	public String getSvcProviderType() {
		return this.svcProviderType;
	}

	public void setSvcProviderType(String svcProviderType) {
		this.svcProviderType = svcProviderType;
	}

}
