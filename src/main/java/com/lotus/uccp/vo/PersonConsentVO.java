package  com.lotus.uccp.vo;

import java.math.BigDecimal;
import java.util.Date;


public class PersonConsentVO   {

	public BigDecimal getPersonconsentid() {
		return personconsentid;
	}

	public void setPersonconsentid(BigDecimal personconsentid) {
		this.personconsentid = personconsentid;
	}

	public BigDecimal getConcernroleid() {
		return concernroleid;
	}

	public void setConcernroleid(BigDecimal concernroleid) {
		this.concernroleid = concernroleid;
	}

	

	public String getThirdpartyrelationship() {
		return thirdpartyrelationship;
	}

	public void setThirdpartyrelationship(String thirdpartyrelationship) {
		this.thirdpartyrelationship = thirdpartyrelationship;
	}

	public String getThirdpartyfirstname() {
		return thirdpartyfirstname;
	}

	public void setThirdpartyfirstname(String thirdpartyfirstname) {
		this.thirdpartyfirstname = thirdpartyfirstname;
	}

	public String getThirdpartylastname() {
		return thirdpartylastname;
	}

	public void setThirdpartylastname(String thirdpartylastname) {
		this.thirdpartylastname = thirdpartylastname;
	}

	public String getThirdpartyotherrelationship() {
		return thirdpartyotherrelationship;
	}

	public void setThirdpartyotherrelationship(String thirdpartyotherrelationship) {
		this.thirdpartyotherrelationship = thirdpartyotherrelationship;
	}

	public String getIdentificationno() {
		return identificationno;
	}

	public void setIdentificationno(String identificationno) {
		this.identificationno = identificationno;
	}

	public String getIdentificationtype() {
		return identificationtype;
	}

	public void setIdentificationtype(String identificationtype) {
		this.identificationtype = identificationtype;
	}

	public String getIdentificationother() {
		return identificationother;
	}

	public void setIdentificationother(String identificationother) {
		this.identificationother = identificationother;
	}

	public BigDecimal getContactaddressid() {
		return contactaddressid;
	}

	public void setContactaddressid(BigDecimal contactaddressid) {
		this.contactaddressid = contactaddressid;
	}

	public String getContactphone() {
		return contactphone;
	}

	public void setContactphone(String contactphone) {
		this.contactphone = contactphone;
	}

	public String getAuthpersonnameone() {
		return authpersonnameone;
	}

	public void setAuthpersonnameone(String authpersonnameone) {
		this.authpersonnameone = authpersonnameone;
	}

	public String getAuthpersonrelone() {
		return authpersonrelone;
	}

	public void setAuthpersonrelone(String authpersonrelone) {
		this.authpersonrelone = authpersonrelone;
	}

	public String getAuthpersoncontactinfoone() {
		return authpersoncontactinfoone;
	}

	public void setAuthpersoncontactinfoone(String authpersoncontactinfoone) {
		this.authpersoncontactinfoone = authpersoncontactinfoone;
	}

	public String getAuthpersonnametwo() {
		return authpersonnametwo;
	}

	public void setAuthpersonnametwo(String authpersonnametwo) {
		this.authpersonnametwo = authpersonnametwo;
	}

	public String getAuthpersonreltwo() {
		return authpersonreltwo;
	}

	public void setAuthpersonreltwo(String authpersonreltwo) {
		this.authpersonreltwo = authpersonreltwo;
	}

	public String getAuthpersoncontactinfotwo() {
		return authpersoncontactinfotwo;
	}

	public void setAuthpersoncontactinfotwo(String authpersoncontactinfotwo) {
		this.authpersoncontactinfotwo = authpersoncontactinfotwo;
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

	private BigDecimal personconsentid;
	private BigDecimal clientid;
	public BigDecimal getClientid() {
		return clientid;
	}

	public void setClientid(BigDecimal clientid) {
		this.clientid = clientid;
	}

	private BigDecimal concernroleid;
	private BigDecimal fileid;
	private boolean iseditAllow = true;
	
	public boolean isIseditAllow() {
		return iseditAllow;
	}

	public void setIseditAllow(boolean iseditAllow) {
		this.iseditAllow = iseditAllow;
	}

	public BigDecimal getFileid() {
		return fileid;
	}

	public void setFileid(BigDecimal fileid) {
		this.fileid = fileid;
	}

	private String startdate;
	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	private String enddate;
	private String thirdpartyrelationship;
	private String thirdpartyfirstname;
	private String thirdpartylastname;
	private String thirdpartyotherrelationship;
	private String identificationno;
	private String identificationtype;
	private String identificationother;
	private BigDecimal contactaddressid;
	private String contactphone;
	private String authpersonnameone;
	private String authpersonrelone;
	private String authpersoncontactinfoone;
	private String authpersonnametwo;
	private String authpersonreltwo;
	private String authpersoncontactinfotwo;
	private String createdby;
	private Date createdon;
	private String lastupdatedby;
	private Date lastupdatedon;
	
	private String Street ;
	
	private String actionType ;
	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	private String reason ;
	
	public String getStreet() {
		return Street;
	}

	public void setStreet(String street) {
		Street = street;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getAppSuite() {
		return AppSuite;
	}

	public void setAppSuite(String appSuite) {
		AppSuite = appSuite;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getConsentCatList() {
		return consentCatList;
	}

	public void setConsentCatList(String consentCatList) {
		this.consentCatList = consentCatList;
	}

	private String City ;
	private String AppSuite ;
	private String  State ;
	private String zipCode ;
	private String consentCatList ;	
    private String consentUser;
	private String customerName;
	
	
	public String getConsentUser() {
		return consentUser;
	}

	public void setConsentUser(String consentUser) {
		this.consentUser = consentUser;
	}
	
	
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
}
