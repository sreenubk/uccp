package  com.lotus.uccp.vo;

import java.math.BigDecimal;
import java.util.Date;

public class ConstraintVO   {

	private BigDecimal attributeconstraintid;
	private String attributename;
	private String attributevalue;
	private String accessconstrainttype;
	private String createdby;
	private Date createdon;
	private String lastupdatedby;
	private Date lastupdatedon;
	private BigDecimal controlgroupid;
	private BigDecimal transactionid;
	

	
	public BigDecimal getTransactionid() {
		return transactionid;
	}

	public void setTransactionid(BigDecimal transactionid) {
		this.transactionid = transactionid;
	}

	public ConstraintVO() {
	}

	public ConstraintVO(BigDecimal attributeconstraintid, String attributename, String attributevalue,
			String accessconstrainttype) {
		this.attributeconstraintid = attributeconstraintid;
		this.attributename = attributename;
		this.attributevalue = attributevalue;
		this.accessconstrainttype = accessconstrainttype;
	}

	public ConstraintVO(BigDecimal attributeconstraintid, String attributename, String attributevalue,
			String accessconstrainttype, String createdby, Date createdon, String lastupdatedby, Date lastupdatedon
			) {
		this.attributeconstraintid = attributeconstraintid;
		this.attributename = attributename;
		this.attributevalue = attributevalue;
		this.accessconstrainttype = accessconstrainttype;
		this.createdby = createdby;
		this.createdon = createdon;
		this.lastupdatedby = lastupdatedby;
		this.lastupdatedon = lastupdatedon;
		
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


	
	public BigDecimal getControlgroupid() {
		return controlgroupid;
	}
	public void setControlgroupid(BigDecimal controlgroupid) {
		this.controlgroupid = controlgroupid;
	}

}
