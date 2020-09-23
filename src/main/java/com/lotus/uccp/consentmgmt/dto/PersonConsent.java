package com.lotus.uccp.consentmgmt.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@SuppressWarnings("serial")
public class PersonConsent implements java.io.Serializable {

	private BigDecimal personconsentid;
	private BigDecimal fileid;
	private BigDecimal clientid;

	

	private BigDecimal concernroleid;
	private Date startdate;
	private Date enddate;
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
	
	private String consentCatGrp;
	private String histroyRecord;
	
	
	public BigDecimal getClientid() {
		return clientid;
	}

	public void setClientid(BigDecimal clientid) {
		this.clientid = clientid;
	}
	
	public String getHistroyRecord() {
		return histroyRecord;
	}

	public void setHistroyRecord(String histroyRecord) {
		this.histroyRecord = histroyRecord;
	}

	private String customerName;
	
	public String getConsentCatGrp() {
		return consentCatGrp;
	}

	public void setConsentCatGrp(String consentUser) {
		this.consentCatGrp = consentUser;
	}
	
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
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
	
	private String Street ;
	private String City ;
	private String AppSuite ;
	private String  State ;
	private String zipCode ;
	
	private Set<ConsentException> consentExceptions = new HashSet<ConsentException>(0);
	private Set<ProxyPersonConsent> proxyPersonConsents = new HashSet<ProxyPersonConsent>(0);
	private Set<PersonConsentHistory> personConsentHistories = new HashSet<PersonConsentHistory>(0);

	public PersonConsent() {
	}

	public PersonConsent(BigDecimal personconsentid, BigDecimal concernroleid) {
		this.personconsentid = personconsentid;
		this.concernroleid = concernroleid;
	}

	public PersonConsent(BigDecimal personconsentid, BigDecimal concernroleid, Date startdate, Date enddate,
			String thirdpartyrelationship, String thirdpartyfirstname, String thirdpartylastname,
			String thirdpartyotherrelationship, String identificationno, String identificationtype,
			String identificationother, BigDecimal contactaddressid, String contactphone, String authpersonnameone,
			String authpersonrelone, String authpersoncontactinfoone, String authpersonnametwo, String authpersonreltwo,
			String authpersoncontactinfotwo, String createdby, Date createdon, String lastupdatedby, Date lastupdatedon,
			Set<ConsentException> consentExceptions, Set<ProxyPersonConsent> proxyPersonConsents,
			Set<PersonConsentHistory> personConsentHistories) {
		this.personconsentid = personconsentid;
		this.concernroleid = concernroleid;
		this.startdate = startdate;
		this.enddate = enddate;
		this.thirdpartyrelationship = thirdpartyrelationship;
		this.thirdpartyfirstname = thirdpartyfirstname;
		this.thirdpartylastname = thirdpartylastname;
		this.thirdpartyotherrelationship = thirdpartyotherrelationship;
		this.identificationno = identificationno;
		this.identificationtype = identificationtype;
		this.identificationother = identificationother;
		this.contactaddressid = contactaddressid;
		this.contactphone = contactphone;
		this.authpersonnameone = authpersonnameone;
		this.authpersonrelone = authpersonrelone;
		this.authpersoncontactinfoone = authpersoncontactinfoone;
		this.authpersonnametwo = authpersonnametwo;
		this.authpersonreltwo = authpersonreltwo;
		this.authpersoncontactinfotwo = authpersoncontactinfotwo;
		this.createdby = createdby;
		this.createdon = createdon;
		this.lastupdatedby = lastupdatedby;
		this.lastupdatedon = lastupdatedon;
		this.consentExceptions = consentExceptions;
		this.proxyPersonConsents = proxyPersonConsents;
		this.personConsentHistories = personConsentHistories;
	}

	public BigDecimal getPersonconsentid() {
		return this.personconsentid;
	}

	public void setPersonconsentid(BigDecimal personconsentid) {
		this.personconsentid = personconsentid;
	}

	public BigDecimal getConcernroleid() {
		return this.concernroleid;
	}

	public void setConcernroleid(BigDecimal concernroleid) {
		this.concernroleid = concernroleid;
	}

	public Date getStartdate() {
		return this.startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return this.enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public String getThirdpartyrelationship() {
		return this.thirdpartyrelationship;
	}

	public void setThirdpartyrelationship(String thirdpartyrelationship) {
		this.thirdpartyrelationship = thirdpartyrelationship;
	}

	public String getThirdpartyfirstname() {
		return this.thirdpartyfirstname;
	}

	public void setThirdpartyfirstname(String thirdpartyfirstname) {
		this.thirdpartyfirstname = thirdpartyfirstname;
	}

	public String getThirdpartylastname() {
		return this.thirdpartylastname;
	}

	public void setThirdpartylastname(String thirdpartylastname) {
		this.thirdpartylastname = thirdpartylastname;
	}

	public String getThirdpartyotherrelationship() {
		return this.thirdpartyotherrelationship;
	}

	public void setThirdpartyotherrelationship(String thirdpartyotherrelationship) {
		this.thirdpartyotherrelationship = thirdpartyotherrelationship;
	}

	public String getIdentificationno() {
		return this.identificationno;
	}

	public void setIdentificationno(String identificationno) {
		this.identificationno = identificationno;
	}

	public String getIdentificationtype() {
		return this.identificationtype;
	}

	public void setIdentificationtype(String identificationtype) {
		this.identificationtype = identificationtype;
	}

	public String getIdentificationother() {
		return this.identificationother;
	}

	public void setIdentificationother(String identificationother) {
		this.identificationother = identificationother;
	}
	
	public BigDecimal getContactaddressid() {
		return this.contactaddressid;
	}

	public void setContactaddressid(BigDecimal contactaddressid) {
		this.contactaddressid = contactaddressid;
	}

	public String getContactphone() {
		return this.contactphone;
	}

	public void setContactphone(String contactphone) {
		this.contactphone = contactphone;
	}

	public String getAuthpersonnameone() {
		return this.authpersonnameone;
	}

	public void setAuthpersonnameone(String authpersonnameone) {
		this.authpersonnameone = authpersonnameone;
	}

	public String getAuthpersonrelone() {
		return this.authpersonrelone;
	}

	public void setAuthpersonrelone(String authpersonrelone) {
		this.authpersonrelone = authpersonrelone;
	}

	public String getAuthpersoncontactinfoone() {
		return this.authpersoncontactinfoone;
	}

	public void setAuthpersoncontactinfoone(String authpersoncontactinfoone) {
		this.authpersoncontactinfoone = authpersoncontactinfoone;
	}

	public String getAuthpersonnametwo() {
		return this.authpersonnametwo;
	}

	public void setAuthpersonnametwo(String authpersonnametwo) {
		this.authpersonnametwo = authpersonnametwo;
	}

	public String getAuthpersonreltwo() {
		return this.authpersonreltwo;
	}

	public void setAuthpersonreltwo(String authpersonreltwo) {
		this.authpersonreltwo = authpersonreltwo;
	}

	public String getAuthpersoncontactinfotwo() {
		return this.authpersoncontactinfotwo;
	}

	public void setAuthpersoncontactinfotwo(String authpersoncontactinfotwo) {
		this.authpersoncontactinfotwo = authpersoncontactinfotwo;
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
	
	public BigDecimal getFileid() {
		return fileid;
	}

	public void setFileid(BigDecimal fileid) {
		this.fileid = fileid;
	}

	public Date getLastupdatedon() {
		return this.lastupdatedon;
	}

	public void setLastupdatedon(Date lastupdatedon) {
		this.lastupdatedon = lastupdatedon;
	}

	public Set<ConsentException> getConsentExceptions() {
		return this.consentExceptions;
	}

	public void setConsentExceptions(Set<ConsentException> consentExceptions) {
		this.consentExceptions = consentExceptions;
	}

	public Set<ProxyPersonConsent> getProxyPersonConsents() {
		return this.proxyPersonConsents;
	}

	public void setProxyPersonConsents(Set<ProxyPersonConsent> proxyPersonConsents) {
		this.proxyPersonConsents = proxyPersonConsents;
	}

	public Set<PersonConsentHistory> getPersonConsenthistories() {
		return this.personConsentHistories;
	}

	public void setPersonConsenthistories(Set<PersonConsentHistory> personConsentHistories) {
		this.personConsentHistories = personConsentHistories;
	}

}
