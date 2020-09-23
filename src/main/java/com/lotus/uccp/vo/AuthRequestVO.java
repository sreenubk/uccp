package  com.lotus.uccp.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.lotus.uccp.auth.util.HashMapCaseInsensitive;

public class AuthRequestVO {

	private String userName ;
	private String clientID ;
	private String srcSystem ;
	private BigDecimal transactionid;
	private String transName ;
	private BigDecimal constraintid;
	private String constraintName ;
	private String constraintValue ;
	private String privilegeRequested ;
	private String accessReason ;
	private String apiSwith ;
	private Date requestDt ;
	private String strrequestDt ;
	private HashMapCaseInsensitive  attributehm =  null ;
	
	public Map<String, String> getAttributehm() {
		return attributehm;
	}
	public void setAttributehm(HashMapCaseInsensitive  attributehm) {
		
		this.attributehm = attributehm;
	}
	private String minor ;
	public String getMinor() {
		return minor;
	}
	public void setMinor(String minor) {
		this.minor = minor;
	}
	public String getParolee() {
		return parolee;
	}
	public void setParolee(String parolee) {
		this.parolee = parolee;
	}
	private String parolee ;
	
	private long authID ;
	private String result ;
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public long getAuthID() {
		return authID;
	}
	public void setAuthID(long authID) {
		this.authID = authID;
	}
	public Date getRequestDt() {
		return requestDt;
	}
	public void setRequestDt(Date requestDt) {
		this.requestDt = requestDt;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getClientID() {
		return clientID;
	}
	public void setClientID(String clientID) {
		this.clientID = clientID;
	}
	public String getSrcSystem() {
		return srcSystem;
	}
	public void setSrcSystem(String srcSystem) {
		this.srcSystem = srcSystem;
	}
	public BigDecimal getTransactionid() {
		return transactionid;
	}
	public void setTransactionid(BigDecimal transactionid) {
		this.transactionid = transactionid;
	}
	public String getTransName() {
		return transName;
	}
	public void setTransName(String transName) {
		this.transName = transName;
	}
	public BigDecimal getConstraintid() {
		return constraintid;
	}
	public void setConstraintid(BigDecimal constraintid) {
		this.constraintid = constraintid;
	}
	public String getConstraintName() {
		return constraintName;
	}
	public void setConstraintName(String constraintName) {
		this.constraintName = constraintName;
	}
	public String getConstraintValue() {
		return constraintValue;
	}
	public void setConstraintValue(String constraintValue) {
		this.constraintValue = constraintValue;
	}
	public String getPrivilegeRequested() {
		return privilegeRequested;
	}
	public void setPrivilegeRequested(String privilegeRequested) {
		this.privilegeRequested = privilegeRequested;
	}
	public String getAccessReason() {
		return accessReason;
	}
	public void setAccessReason(String accessReason) {
		this.accessReason = accessReason;
	}
	public String getApiSwith() {
		return apiSwith;
	}
	public void setApiSwith(String apiSwith) {
		this.apiSwith = apiSwith;
	}
	
	public String getStrrequestDt() {
		return strrequestDt;
	}
	public void setStrrequestDt(String strrequestDt) {
		this.strrequestDt = strrequestDt;
	}
	
}
