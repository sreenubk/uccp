package  com.lotus.uccp.vo;

import java.math.BigDecimal;
import java.util.Date;


public class ControlGroupVO   {

	private BigDecimal controlgroupid;
	private String controlgroupname;
	private String createdby;
	private Date createdon;
	private String lastupdatedby;
	private Date lastupdatedon;
	
	
	public ControlGroupVO() {
	}

	public ControlGroupVO(BigDecimal controlgroupid, String controlgroupname) {
		this.controlgroupid = controlgroupid;
		this.controlgroupname = controlgroupname;
	}

	public ControlGroupVO(BigDecimal controlgroupid, String controlgroupname, String createdby, Date createdon,
			String lastupdatedby, Date lastupdatedon /*, Set<Cosdabaccontrolobject> cosdabaccontrolobjects,
			Set<Cosdauthorizationgroup> cosdauthorizationgroups */) {
		this.controlgroupid = controlgroupid;
		this.controlgroupname = controlgroupname;
		this.createdby = createdby;
		this.createdon = createdon;
		this.lastupdatedby = lastupdatedby;
		this.lastupdatedon = lastupdatedon;
	}

	public BigDecimal getControlgroupid() {
		return this.controlgroupid;
	}

	public void setControlgroupid(BigDecimal controlgroupid) {
		this.controlgroupid = controlgroupid;
	}

	public String getControlgroupname() {
		return this.controlgroupname;
	}

	public void setControlgroupname(String controlgroupname) {
		this.controlgroupname = controlgroupname;
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
