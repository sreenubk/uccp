package com.lotus.uccp.authentication.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lotus.uccp.util.Constant;

/**
* ControlGroup generated by hbm2java
*/
@Entity
@Table(name = "controlgroup", schema = Constant.SCHEMA )
public class ControlGroup implements java.io.Serializable {

	private BigDecimal controlgroupid;
	private String controlgroupname;
	private String createdby;
	private Date createdon;
	private String lastupdatedby;
	private Date lastupdatedon;
	
	private Set<ControlObject> controlObjects = new HashSet<ControlObject>(0);
	private Set<AuthorizationGroup> AuthorizationGroups = new HashSet<AuthorizationGroup>(0);

	public ControlGroup() {
	}

	public ControlGroup(BigDecimal controlgroupid, String controlgroupname) {
		this.controlgroupid = controlgroupid;
		this.controlgroupname = controlgroupname;
	}

	public ControlGroup(BigDecimal controlgroupid, String controlgroupname, String createdby, Date createdon,
			String lastupdatedby, Date lastupdatedon , Set<ControlObject> controlObjects,
			Set<AuthorizationGroup> AuthorizationGroups ) {
		this.controlgroupid = controlgroupid;
		this.controlgroupname = controlgroupname;
		this.createdby = createdby;
		this.createdon = createdon;
		this.lastupdatedby = lastupdatedby;
		this.lastupdatedon = lastupdatedon;
		this.controlObjects = controlObjects;
		this.AuthorizationGroups = AuthorizationGroups;
	}

	
	@Id
	@SequenceGenerator(schema = Constant.SCHEMA ,name  = "controlGrpSeq", sequenceName = "controlGrpSeq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="controlGrpSeq")
	@Column(name = "controlgroupid", unique = true, nullable = false, scale = 0)
	public BigDecimal getControlgroupid() {
		return this.controlgroupid;
	}

	public void setControlgroupid(BigDecimal controlgroupid) {
		this.controlgroupid = controlgroupid;
	}

	@Column(name = "controlgroupname", nullable = false, length = 30)
	public String getControlgroupname() {
		return this.controlgroupname;
	}

	public void setControlgroupname(String controlgroupname) {
		this.controlgroupname = controlgroupname;
	}

	@Column(name = "createdby", length = 64)
	public String getCreatedby() {
		return this.createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "createdon", length = 13)
	public Date getCreatedon() {
		return this.createdon;
	}

	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}

	@Column(name = "lastupdatedby", length = 64)
	public String getLastupdatedby() {
		return this.lastupdatedby;
	}

	public void setLastupdatedby(String lastupdatedby) {
		this.lastupdatedby = lastupdatedby;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "lastupdatedon", length = 13)
	public Date getLastupdatedon() {
		return this.lastupdatedon;
	}

	public void setLastupdatedon(Date lastupdatedon) {
		this.lastupdatedon = lastupdatedon;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "controlGroup")
	public Set<ControlObject> getControlObjects() {
		return this.controlObjects;
	}

	public void setControlObjects(Set<ControlObject> controlObjects) {
		this.controlObjects = controlObjects;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "controlGroup")
	public Set<AuthorizationGroup> getAuthorizationGroups() {
		return this.AuthorizationGroups;
	}

	public void setAuthorizationGroups(Set<AuthorizationGroup> AuthorizationGroups) {
		this.AuthorizationGroups = AuthorizationGroups;
	}

}
