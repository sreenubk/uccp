package com.lotus.uccp.consentmgmt.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Constraint implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal attributeconstraintid;
	private String attributename;
	private String attributevalue;
	private String accessconstrainttype;
	private String createdby;
	private Date createdon;
	private String lastupdatedby;
	private Date lastupdatedon;
	private Set<ControlObject> controlObjects = new HashSet<ControlObject>(0);

	public Constraint() {
		 
	}

	public Constraint(BigDecimal attributeconstraintid, String attributename, String attributevalue,
			String accessconstrainttype) {
		this.attributeconstraintid = attributeconstraintid;
		this.attributename = attributename;
		this.attributevalue = attributevalue;
		this.accessconstrainttype = accessconstrainttype;
	}

	public Constraint(BigDecimal attributeconstraintid, String attributename, String attributevalue,
			String accessconstrainttype, String createdby, Date createdon, String lastupdatedby, Date lastupdatedon,
			Set<ControlObject> controlObjects) {
		this.attributeconstraintid = attributeconstraintid;
		this.attributename = attributename;
		this.attributevalue = attributevalue;
		this.accessconstrainttype = accessconstrainttype;
		this.createdby = createdby;
		this.createdon = createdon;
		this.lastupdatedby = lastupdatedby;
		this.lastupdatedon = lastupdatedon;
		this.controlObjects = controlObjects;
	}

	
	public BigDecimal getAttributeconstraintid() {
		return this.attributeconstraintid;
	}

	public void setAttributeconstraintid(BigDecimal attributeconstraintid) {
		this.attributeconstraintid = attributeconstraintid;
	}

	public String getAttributename() {
		return this.attributename;
	}

	public void setAttributename(String attributename) {
		this.attributename = attributename;
	}

	public String getAttributevalue() {
		return this.attributevalue;
	}

	public void setAttributevalue(String attributevalue) {
		this.attributevalue = attributevalue;
	}

	public String getAccessconstrainttype() {
		return this.accessconstrainttype;
	}

	public void setAccessconstrainttype(String accessconstrainttype) {
		this.accessconstrainttype = accessconstrainttype;
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

	public Set<ControlObject> getControlObjects() {
		return this.controlObjects;
	}

	public void setControlObjects(Set<ControlObject> controlObjects) {
		this.controlObjects = controlObjects;
	}

}
