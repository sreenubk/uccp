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
 * CpDemoVehicleSrc generated by hbm2java
 */
import com.lotus.uccp.util.Constant; 
@Entity
@Table(schema = Constant.SCHEMANAME_ODS , name = "CP_DEMO_VEHICLE_SRC")
public class CpDemoVehicleSrc implements java.io.Serializable {

	private BigDecimal cpDemoVehicleSeqNum;
	private ClientProfileDemoSrc clientProfileDemoSrc;
	private String vehicleType;
	private String vehicleModel;
	private String vehicleColor;
	private String licPlateNum;

	public CpDemoVehicleSrc() {
	}

	public CpDemoVehicleSrc(BigDecimal cpDemoVehicleSeqNum, ClientProfileDemoSrc clientProfileDemoSrc) {
		this.cpDemoVehicleSeqNum = cpDemoVehicleSeqNum;
		this.clientProfileDemoSrc = clientProfileDemoSrc;
	}

	public CpDemoVehicleSrc(BigDecimal cpDemoVehicleSeqNum, ClientProfileDemoSrc clientProfileDemoSrc,
			String vehicleType, String vehicleModel, String vehicleColor, String licPlateNum) {
		this.cpDemoVehicleSeqNum = cpDemoVehicleSeqNum;
		this.clientProfileDemoSrc = clientProfileDemoSrc;
		this.vehicleType = vehicleType;
		this.vehicleModel = vehicleModel;
		this.vehicleColor = vehicleColor;
		this.licPlateNum = licPlateNum;
	}

	@Id

	@Column(name = "CP_DEMO_VEHICLE_SEQ_NUM", unique = true, nullable = false, precision = 31, scale = 0)
	public BigDecimal getCpDemoVehicleSeqNum() {
		return this.cpDemoVehicleSeqNum;
	}

	public void setCpDemoVehicleSeqNum(BigDecimal cpDemoVehicleSeqNum) {
		this.cpDemoVehicleSeqNum = cpDemoVehicleSeqNum;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLIENT_PROFILE_DEMO_SEQ_NUM", nullable = false)
	public ClientProfileDemoSrc getClientProfileDemoSrc() {
		return this.clientProfileDemoSrc;
	}

	public void setClientProfileDemoSrc(ClientProfileDemoSrc clientProfileDemoSrc) {
		this.clientProfileDemoSrc = clientProfileDemoSrc;
	}

	@Column(name = "VEHICLE_TYPE", length = 200)
	public String getVehicleType() {
		return this.vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	@Column(name = "VEHICLE_MODEL", length = 50)
	public String getVehicleModel() {
		return this.vehicleModel;
	}

	public void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}

	@Column(name = "VEHICLE_COLOR", length = 50)
	public String getVehicleColor() {
		return this.vehicleColor;
	}

	public void setVehicleColor(String vehicleColor) {
		this.vehicleColor = vehicleColor;
	}

	@Column(name = "LIC_PLATE_NUM", length = 10)
	public String getLicPlateNum() {
		return this.licPlateNum;
	}

	public void setLicPlateNum(String licPlateNum) {
		this.licPlateNum = licPlateNum;
	}

}
