package  com.lotus.uccp.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


public class ATTCosdabactransactionVO   {

	private BigDecimal transactionid;
	public BigDecimal getTransactionid() {
		return transactionid;
	}
	public void setTransactionid(BigDecimal transactionid) {
		this.transactionid = transactionid;
	}
	public String getTransactiontype() {
		return transactiontype;
	}
	public void setTransactiontype(String transactiontype) {
		this.transactiontype = transactiontype;
	}
	public String getTransactionname() {
		return transactionname;
	}
	public void setTransactionname(String transactionname) {
		this.transactionname = transactionname;
	}
	public String getDefaultaccess() {
		return defaultaccess;
	}
	public void setDefaultaccess(String defaultaccess) {
		this.defaultaccess = defaultaccess;
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
	public byte getVersionno() {
		return versionno;
	}
	public void setVersionno(byte versionno) {
		this.versionno = versionno;
	}
	private String transactiontype;
	private String transactionname;
	private String defaultaccess;
	private String createdby;
	private Date createdon;
	private String lastupdatedby;
	private Date lastupdatedon;
	private byte versionno;	
	private BigDecimal controlgroupid;
	
	public BigDecimal getControlgroupid() {
		return controlgroupid;
	}
	public void setControlgroupid(BigDecimal controlgroupid) {
		this.controlgroupid = controlgroupid;
	}
	
	List<ConstraintVO>  attributes ;
	//private CosdabacconstraintVO attributes ;
	
	public List<ConstraintVO> getCosdabacconstraintvo() {
		return attributes;
	}
	public void setCosdabacconstraintvo(List<ConstraintVO> cosdabacconstraintvo) {
		this.attributes = cosdabacconstraintvo;
	}
}
