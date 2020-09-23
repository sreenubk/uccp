package com.lotus.uccp.consentmgmt.dto;

import java.math.BigDecimal;
import java.util.Date;
import com.lotus.uccp.authentication.model.ControlGroup;


@SuppressWarnings("serial")
public class ControlObject implements java.io.Serializable {
	         
	private BigDecimal controlobjectid;
	private Constraint constraint;
	private ControlGroup controlGroup;
	private Transaction transaction;
	private String createdby;
	private Date createdon;
	private String lastupdatedby;
	private Date lastupdatedon;

	public ControlObject() {
	}

	public ControlObject(BigDecimal controlobjectid) {
		this.controlobjectid = controlobjectid;
	}

	public ControlObject(BigDecimal controlobjectid, Constraint constraint,
			ControlGroup controlGroup, Transaction transaction, String createdby,
			Date createdon, String lastupdatedby, Date lastupdatedon) {
		this.controlobjectid = controlobjectid;
		this.constraint = constraint;
		this.controlGroup = controlGroup;
		this.transaction = transaction;
		this.createdby = createdby;
		this.createdon = createdon;
		this.lastupdatedby = lastupdatedby;
		this.lastupdatedon = lastupdatedon;
	}

	public BigDecimal getControlobjectid() {
		return this.controlobjectid;
	}

	public void setControlobjectid(BigDecimal controlobjectid) {
		this.controlobjectid = controlobjectid;
	}

	public Constraint getConstraint() {
		return this.constraint;
	}

	public void setConstraint(Constraint constraint) {
		this.constraint = constraint;
	}

	public ControlGroup getControlGroup() {
		return this.controlGroup;
	}

	public void setControlGroup(ControlGroup controlGroup) {
		this.controlGroup = controlGroup;
	}

	public Transaction getTransaction() {
		return this.transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public String getCreatedby() {
		return this.createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public Date getCreatedon() {
		return this.createdon;
	}

	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}

	public String getLastupdatedby() {
		return this.lastupdatedby;
	}

	public void setLastupdatedby(String lastupdatedby) {
		this.lastupdatedby = lastupdatedby;
	}

	public Date getLastupdatedon() {
		return this.lastupdatedon;
	}

	public void setLastupdatedon(Date lastupdatedon) {
		this.lastupdatedon = lastupdatedon;
	}

}
