package com.lotus.uccp.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lotus.uccp.authentication.model.ConsentException;
import com.lotus.uccp.authentication.model.ConsentGroup;
import com.lotus.uccp.authentication.model.ConsentGroupTxn;
import com.lotus.uccp.authentication.model.PersonConsent;
import com.lotus.uccp.authentication.model.PersonConsentHistory;
import com.lotus.uccp.authentication.model.ProxyPersonConsent;
import com.lotus.uccp.vo.ConsentExceptionVO;
import com.lotus.uccp.vo.ConsentGroupVO;
import com.lotus.uccp.vo.ConsentGroupTxnVO;
import com.lotus.uccp.vo.PersonConsentVO;
import com.lotus.uccp.vo.PersonConsentHistoryVO;
import com.lotus.uccp.vo.ProxyPersonConsentVO;


public class ConsentConverter {
	
	/*--- PersonConsentVO --*/
	public ProxyPersonConsentVO convertToVo(ProxyPersonConsent personConsent)
	{
		ProxyPersonConsentVO cosdproxypersonconsentvo = new ProxyPersonConsentVO() ;
		return cosdproxypersonconsentvo;
	}

	public List<ProxyPersonConsentVO> covertList(List<ProxyPersonConsent>  doObjs, ProxyPersonConsentVO cosdconsentgroupvo)
	{
		List<ProxyPersonConsentVO> voObjs = new ArrayList<ProxyPersonConsentVO>();		
		doObjs.forEach(doObj-> voObjs.add(convertToVo(doObj))); 
		
		return voObjs ;		
	}
	
	/*--- cosdpersonconsenthistoryvo --*/
	public PersonConsentHistoryVO convertToVo(PersonConsentHistory personConsentHistory)
	{
		PersonConsentHistoryVO cosdpersonconsenthistoryvo = new PersonConsentHistoryVO() ;
		return cosdpersonconsenthistoryvo;
	}

	public List<PersonConsentHistoryVO> covertList(List<PersonConsentHistory>  doObjs, PersonConsentHistoryVO cosdconsentgroupvo)
	{
		List<PersonConsentHistoryVO> voObjs = new ArrayList<PersonConsentHistoryVO>();		
		doObjs.forEach(doObj-> voObjs.add(convertToVo(doObj))); 
		
		return voObjs ;		
	}

	
	public ConsentGroupVO convertToVo(ConsentGroup consentGroup)
	{
		ConsentGroupVO cosdconsentgroupvo = new ConsentGroupVO() ;
		
		cosdconsentgroupvo.setConsentgroupid(consentGroup.getConsentgroupid());
		cosdconsentgroupvo.setName(consentGroup.getName());
		cosdconsentgroupvo.setDescription(consentGroup.getDescription());
		
		cosdconsentgroupvo.setCreatedby(consentGroup.getCreatedby());
		cosdconsentgroupvo.setCreatedon(consentGroup.getCreatedon());
		
		cosdconsentgroupvo.setLastupdatedby(consentGroup.getLastupdatedby());
		cosdconsentgroupvo.setLastupdatedon(consentGroup.getLastupdatedon());
		
		return cosdconsentgroupvo;
	}

	public List<ConsentGroupVO> covertList( List<ConsentGroup>  doObjs, ConsentGroupVO cosdconsentgroupvo)
	{
		List<ConsentGroupVO> voObjs = new ArrayList<ConsentGroupVO>();		
		doObjs.forEach(doObj-> voObjs.add(convertToVo(doObj))); 
		
		return voObjs ;		
	}
	
	/*--- PersonConsentVO--*/
	public PersonConsentVO convertToVo(PersonConsent cosdpersonconsent)
	{
		PersonConsentVO cosdpersonconsentvo = new PersonConsentVO() ;
		
		
		
		cosdpersonconsentvo.setPersonconsentid(cosdpersonconsent.getPersonconsentid());
		cosdpersonconsentvo.setAuthpersoncontactinfoone(cosdpersonconsent.getAuthpersoncontactinfoone());
		cosdpersonconsentvo.setAuthpersoncontactinfotwo(cosdpersonconsent.getAuthpersoncontactinfotwo());
		cosdpersonconsentvo.setAuthpersonnameone(cosdpersonconsent.getAuthpersonnameone());
		cosdpersonconsentvo.setAuthpersonnametwo(cosdpersonconsent.getAuthpersonnametwo());
		cosdpersonconsentvo.setAuthpersonrelone(cosdpersonconsent.getAuthpersonrelone());
		cosdpersonconsentvo.setAuthpersonreltwo(cosdpersonconsent.getAuthpersonreltwo());
		cosdpersonconsentvo.setConcernroleid(cosdpersonconsent.getConcernroleid());
		cosdpersonconsentvo.setContactaddressid(cosdpersonconsent.getContactaddressid());
		cosdpersonconsentvo.setContactphone(cosdpersonconsent.getContactphone());
		cosdpersonconsentvo.setCreatedon(cosdpersonconsent.getCreatedon());
		cosdpersonconsentvo.setCreatedby(cosdpersonconsent.getCreatedby());
		cosdpersonconsentvo.setLastupdatedby(cosdpersonconsent.getLastupdatedby());
		cosdpersonconsentvo.setLastupdatedon(cosdpersonconsent.getLastupdatedon());
		cosdpersonconsentvo.setThirdpartyfirstname(cosdpersonconsent.getThirdpartyfirstname());
		cosdpersonconsentvo.setThirdpartylastname(cosdpersonconsent.getThirdpartylastname());
		cosdpersonconsentvo.setThirdpartyotherrelationship(cosdpersonconsent.getThirdpartyotherrelationship());
		cosdpersonconsentvo.setThirdpartyrelationship(cosdpersonconsent.getThirdpartyrelationship());
		cosdpersonconsentvo.setFileid(cosdpersonconsent.getFileid());
		cosdpersonconsentvo.setAppSuite(cosdpersonconsent.getAppSuite());
		cosdpersonconsentvo.setCity(cosdpersonconsent.getCity()); 
		cosdpersonconsentvo.setStreet(cosdpersonconsent.getStreet());
		cosdpersonconsentvo.setZipCode(cosdpersonconsent.getZipCode());
		cosdpersonconsentvo.setState(cosdpersonconsent.getState());
		cosdpersonconsentvo.setCustomerName(cosdpersonconsent.getCustomerName());
		cosdpersonconsentvo.setConsentCatList(cosdpersonconsent.getConsentCatGrp());
		cosdpersonconsentvo.setClientid(cosdpersonconsent.getClientid());
		
		cosdpersonconsentvo.setIdentificationno(cosdpersonconsent.getIdentificationno());
		cosdpersonconsentvo.setIdentificationtype(cosdpersonconsent.getIdentificationtype());
		
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy"); 
		if(cosdpersonconsent.getStartdate() != null)
		{			  
			String startDate= formatter.format(cosdpersonconsent.getStartdate()); 		
			cosdpersonconsentvo.setStartdate(startDate);
		}
	    //SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
	    
		if(cosdpersonconsent.getEnddate() != null)
	    {
			String endDate= formatter.format(cosdpersonconsent.getEnddate()); 
			cosdpersonconsentvo.setEnddate(endDate);
			
			 boolean editNotAllowed = cosdpersonconsent.getEnddate().before(new Date()) ;
			 
			 if(editNotAllowed == true)
				 cosdpersonconsentvo.setIseditAllow(false);
	    }
	   
	     
		cosdpersonconsentvo.setLastupdatedby(cosdpersonconsent.getLastupdatedby());
		cosdpersonconsentvo.setCreatedby(cosdpersonconsent.getCreatedby());
		cosdpersonconsentvo.setLastupdatedon(new Date());
		cosdpersonconsentvo.setCreatedon(new Date());
		return cosdpersonconsentvo;
	}

	public List<PersonConsentVO> covertList( List<PersonConsent>  doObjs, PersonConsentVO cosdpersonconsentvo)
	{
		List<PersonConsentVO> voObjs = new ArrayList<PersonConsentVO>();		
		doObjs.forEach(doObj-> voObjs.add(convertToVo(doObj))); 
		
		return voObjs ;		
	}
	
	/*--- ConsentExceptionVO--*/
	public ConsentExceptionVO convertToVo(ConsentException cosdconsentexception)
	{
		ConsentExceptionVO consentExceptionVO = new ConsentExceptionVO() ;
		
		consentExceptionVO.setConsentexceptionid(cosdconsentexception.getConsentexceptionid());
		consentExceptionVO.setConsentstatus(cosdconsentexception.getConsentstatus());
		consentExceptionVO.setConsentGroup(cosdconsentexception.getConsentGroup().getConsentgroupid().longValue());
		consentExceptionVO.setPersonConsent(cosdconsentexception.getPersonConsent().getPersonconsentid().longValue());
		
		consentExceptionVO.setCreatedby(cosdconsentexception.getCreatedby());
		consentExceptionVO.setCreatedon(cosdconsentexception.getCreatedon());
		consentExceptionVO.setLastupdatedby(cosdconsentexception.getLastupdatedby());
		consentExceptionVO.setLastupdatedo(cosdconsentexception.getLastupdatedo());
		
		return consentExceptionVO;
	}

	public List<ConsentExceptionVO> covertList( List<ConsentException>  doObjs, ConsentExceptionVO cosdpersonconsentvo)
	{
		List<ConsentExceptionVO> voObjs = new ArrayList<ConsentExceptionVO>();		
		doObjs.forEach(doObj-> voObjs.add(convertToVo(doObj))); 
		
		return voObjs ;		
	}
	
	/*--- ConsentGroupTxnVO --*/
	public ConsentGroupTxnVO convertToVo(ConsentGroupTxn consentGroupTxn)
	{
		ConsentGroupTxnVO consentGroupTxnvo = new ConsentGroupTxnVO() ;
		
		consentGroupTxnvo.setConsentgrouptxnid(consentGroupTxn.getConsentgrouptxnid());
		consentGroupTxnvo.setConsentGroup(consentGroupTxn.getConsentGroup().getConsentgroupid().longValue());
		consentGroupTxnvo.setCreatedby(consentGroupTxn.getCreatedby());
		consentGroupTxnvo.setCreatedon(consentGroupTxn.getCreatedon());
		consentGroupTxnvo.setLastupdatedby(consentGroupTxn.getLastupdatedby());
		consentGroupTxnvo.setLastupdatedon(consentGroupTxn.getLastupdatedon());
		return consentGroupTxnvo;
	}

	public List<ConsentGroupTxnVO> covertList( List<ConsentGroupTxn>  doObjs, ConsentGroupTxnVO cosdpersonconsentvo)
	{
		List<ConsentGroupTxnVO> voObjs = new ArrayList<ConsentGroupTxnVO>();		
		doObjs.forEach(doObj-> voObjs.add(convertToVo(doObj))); 
		
		return voObjs ;		
	}
}
