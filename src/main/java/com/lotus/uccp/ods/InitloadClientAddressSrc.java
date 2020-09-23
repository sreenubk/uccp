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
 * InitloadClientAddressSrc generated by hbm2java
 */
import com.lotus.uccp.util.Constant; 
@Entity
@Table(schema = Constant.SCHEMANAME_ODS , name = "INITLOAD_CLIENT_ADDRESS_SRC")
public class InitloadClientAddressSrc implements java.io.Serializable {

	private BigDecimal clientAddrSeqNum;
	private InitloadClientSrc initloadClientSrc;
	private String addrType;
	private String addrLine1;
	private String addrLine2;
	private String city;
	private String state;
	private String zip;

	public InitloadClientAddressSrc() {
	}

	public InitloadClientAddressSrc(BigDecimal clientAddrSeqNum, InitloadClientSrc initloadClientSrc, String addrType) {
		this.clientAddrSeqNum = clientAddrSeqNum;
		this.initloadClientSrc = initloadClientSrc;
		this.addrType = addrType;
	}

	public InitloadClientAddressSrc(BigDecimal clientAddrSeqNum, InitloadClientSrc initloadClientSrc, String addrType,
			String addrLine1, String addrLine2, String city, String state, String zip) {
		this.clientAddrSeqNum = clientAddrSeqNum;
		this.initloadClientSrc = initloadClientSrc;
		this.addrType = addrType;
		this.addrLine1 = addrLine1;
		this.addrLine2 = addrLine2;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}

	@Id

	@Column(name = "CLIENT_ADDR_SEQ_NUM", unique = true, nullable = false, precision = 31, scale = 0)
	public BigDecimal getClientAddrSeqNum() {
		return this.clientAddrSeqNum;
	}

	public void setClientAddrSeqNum(BigDecimal clientAddrSeqNum) {
		this.clientAddrSeqNum = clientAddrSeqNum;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLIENT_TRANSACTION_SEQ_NUM", nullable = false)
	public InitloadClientSrc getInitloadClientSrc() {
		return this.initloadClientSrc;
	}

	public void setInitloadClientSrc(InitloadClientSrc initloadClientSrc) {
		this.initloadClientSrc = initloadClientSrc;
	}

	@Column(name = "ADDR_TYPE", nullable = false, length = 60)
	public String getAddrType() {
		return this.addrType;
	}

	public void setAddrType(String addrType) {
		this.addrType = addrType;
	}

	@Column(name = "ADDR_LINE_1", length = 256)
	public String getAddrLine1() {
		return this.addrLine1;
	}

	public void setAddrLine1(String addrLine1) {
		this.addrLine1 = addrLine1;
	}

	@Column(name = "ADDR_LINE_2", length = 256)
	public String getAddrLine2() {
		return this.addrLine2;
	}

	public void setAddrLine2(String addrLine2) {
		this.addrLine2 = addrLine2;
	}

	@Column(name = "CITY", length = 30)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "STATE", length = 2)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "ZIP", length = 10)
	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

}
