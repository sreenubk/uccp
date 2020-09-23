package  com.lotus.uccp.vo;

import java.math.BigDecimal;
import java.util.Date;


public class AuthorizationPolicySetVO   {

	private BigDecimal policysetid;
	public BigDecimal getPolicysetid() {
		return policysetid;
	}
	public void setPolicysetid(BigDecimal policysetid) {
		this.policysetid = policysetid;
	}
	public String getConsentstatus() {
		return consentstatus;
	}
	public void setConsentstatus(String consentstatus) {
		this.consentstatus = consentstatus;
	}
	public String getClassificationlevel() {
		return classificationlevel;
	}
	public void setClassificationlevel(String classificationlevel) {
		this.classificationlevel = classificationlevel;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public Date getCreatedon() {
		return createdon;
	}
	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}
	public String getLastupdatedby() {
		return lastupdatedby;
	}
	public void setLastupdatedby(String lastupdatedby) {
		this.lastupdatedby = lastupdatedby;
	}
	public Date getLastupdatedon() {
		return lastupdatedon;
	}
	public void setLastupdatedon(Date lastupdatedon) {
		this.lastupdatedon = lastupdatedon;
	}
	private String consentstatus;
	private String classificationlevel;
	private String comments;
	private String createdby;
	private Date createdon;
	private String lastupdatedby;
	private Date lastupdatedon;

	private String name;
	private String transactionType;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getTransactionType() {
		return transactionType;
	}
	
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}


}
