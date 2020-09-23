package com.lotus.uccp.consentmgmt.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lotus.uccp.consentmgmt.wcm.dao.AlertRequestDao;
import com.lotus.uccp.consentmgmt.wcm.dao.AlertStatusHistDao;
import com.lotus.uccp.consentmgmt.wcm.dao.ApiHistDao;
import com.lotus.uccp.consentmgmt.wcm.dao.ReferralStatusHistDao;
import com.lotus.uccp.consentmgmt.wcm.dao.RegistrationRequestDao;
import com.lotus.uccp.consentmgmt.wcm.dto.AlertRequest;
import com.lotus.uccp.consentmgmt.wcm.dto.AlertStatusHist;
import com.lotus.uccp.consentmgmt.wcm.dto.ApiHist;
import com.lotus.uccp.consentmgmt.wcm.dto.ReferralStatusHist;
import com.lotus.uccp.consentmgmt.wcm.dto.RegistrationRequest;
import com.lotus.uccp.wcm.vo.AlertRequestVO;
import com.lotus.uccp.wcm.vo.AlertStatusHistVO;
import com.lotus.uccp.wcm.vo.ApiHistVO;
import com.lotus.uccp.wcm.vo.CohortRuleVO;
import com.lotus.uccp.wcm.vo.CohortVO;
import com.lotus.uccp.wcm.vo.ReferralStatusHistVO;
import com.lotus.uccp.wcm.vo.RegistrationRequestVO;

@RestController
@RequestMapping("/TransactionController")
public class TransactionController {

	@RequestMapping(value = "/listAllRegRequest/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<List<RegistrationRequestVO>> listAllCohort() 
	{
		System.out.println(":: list All Registration Request()::");
		RegistrationRequestDao requestdao = new RegistrationRequestDao();
    	
		List<RegistrationRequest> requestlist = requestdao.getlist() ;
		RegistrationRequestVO vo = new RegistrationRequestVO();
		List<RegistrationRequestVO> requestvolist = new ArrayList<RegistrationRequestVO>();
		
		
		return new ResponseEntity<List<RegistrationRequestVO>>(requestvolist, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/listAllRegRequestWCohort/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<List<RegistrationRequestVO>> listAllRegRequestWCohort(@RequestBody CohortVO cohort) 
	{
		System.out.println(":: list All Reg RequestWCohort::");
		RegistrationRequestDao requestdao = new RegistrationRequestDao();
    	
		List<RegistrationRequest> requestlist = requestdao.getlistWithCohortID(cohort);
		RegistrationRequestVO vo = new RegistrationRequestVO();
		List<RegistrationRequestVO> requestvolist = new ArrayList<RegistrationRequestVO>();
		
		
		return new ResponseEntity<List<RegistrationRequestVO>>(requestvolist, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getRegReqlistWithStatus/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<List<RegistrationRequestVO>> getRegReqlistWithStatus(@RequestBody RegistrationRequestVO regvo) 
	{
		System.out.println(":: getRegReqlistWithStatus()::" + regvo.getStatus() );
		RegistrationRequestDao requestdao = new RegistrationRequestDao();
    	
		List<RegistrationRequest> requestlist = requestdao.getlistWithStatus(regvo.getStatus());
		RegistrationRequestVO vo = new RegistrationRequestVO();
		List<RegistrationRequestVO> requestvolist = new ArrayList<RegistrationRequestVO>();
		
		
		return new ResponseEntity<List<RegistrationRequestVO>>(requestvolist, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/saveRequest/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<RegistrationRequestVO> saveRequest(@RequestBody RegistrationRequestVO regrequest,HttpServletRequest request) 
	{	
		System.out.println(":: save Request()::");
		RegistrationRequestDao requestdao = new RegistrationRequestDao();
		RegistrationRequest req = new RegistrationRequest();
		req.setCreatedDate(new Date());
		req.setStatus("Staged");
		req = requestdao.save(req);
		if(regrequest == null)
		{
			return new ResponseEntity("Duplicate", HttpStatus.OK);
		}		
		
		RegistrationRequestVO voReg = new RegistrationRequestVO();
		
		return new ResponseEntity<RegistrationRequestVO>(voReg, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/postRequest/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<String> postRequest(@RequestBody CohortRuleVO cohortRule,HttpServletRequest request) 
	{	
		System.out.println(":: post Request()::");
		
		System.out.println(":: Rule ID::" + cohortRule.getRuleID());
		System.out.println(":: Cohort ID::" + cohortRule.getCohortid());
		
		return new ResponseEntity("sucess", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/updateRequest/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<String> updateRequest(@RequestBody RegistrationRequestVO regrequest,HttpServletRequest request)
	{	
		System.out.println(":: update Request()::");
		RegistrationRequestDao requestdao = new RegistrationRequestDao();
		
		RegistrationRequest req = new RegistrationRequest();
		
    	
		String result =  requestdao.update(req);
		if(regrequest == null)
		{
			return new ResponseEntity("Duplicate", HttpStatus.OK);
		}
		
		
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/deleteRequest/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<String> deleteRequest(@RequestBody RegistrationRequestVO regrequest,HttpServletRequest request)
	{	
		System.out.println(":: delete Request::");
		RegistrationRequest req = new RegistrationRequest() ;
		
		RegistrationRequestDao requestdao = new RegistrationRequestDao();
    	
		String result =  requestdao.delete(req.getReqReqtID().longValue());
		
		if(regrequest == null)
		{
			return new ResponseEntity("Duplicate", HttpStatus.OK);
		}
		
		
			return new ResponseEntity<String>(new String(result), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/listAllAltRequest/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<List<AlertRequestVO>> listAltRequest() 
	{
		System.out.println(":: list All Alert Request()::");
		AlertRequestDao requestdao = new AlertRequestDao();
    	
		List<AlertRequest> requestlist = requestdao.getlist() ;
		AlertRequestVO vo = new AlertRequestVO();
		List<AlertRequestVO> requestvolist = new ArrayList<AlertRequestVO>();
		 
		
		return new ResponseEntity<List<AlertRequestVO>>(requestvolist, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/listAllAlertRequestWCohort/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<List<AlertRequestVO>> listAllAlertRequestWCohort(@RequestBody CohortVO cohort) 
	{
		System.out.println(":: list All Alert RequestWCohort::");
		AlertRequestDao requestdao = new AlertRequestDao();
    	
		List<AlertRequest> requestlist = requestdao.getlistWithCohortID(cohort);
		AlertRequestVO vo = new AlertRequestVO();
		List<AlertRequestVO> requestvolist = new ArrayList<AlertRequestVO>();
		return new ResponseEntity<List<AlertRequestVO>>(requestvolist, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/saveAltRequest/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<AlertRequestVO> saveAltRequest(@RequestBody AlertRequest regrequest,HttpServletRequest request) 
	{	
		System.out.println(":: save Alt Request::");
		AlertRequestDao requestdao = new AlertRequestDao();
    	
		regrequest = requestdao.save(regrequest);
		if(regrequest == null)
		{
			return new ResponseEntity("Duplicate", HttpStatus.OK);
		}
		AlertRequestVO voAlt = new AlertRequestVO();
		
		return new ResponseEntity<AlertRequestVO>(voAlt, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/updateAltRequest/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<String> updateAltRequest(@RequestBody AlertRequest regrequest,HttpServletRequest request)
	{	
		System.out.println(":: update Alt Request()::");
		AlertRequestDao requestdao = new AlertRequestDao();
    	
		String result =  requestdao.update(regrequest);
		if(regrequest == null)
		{
			return new ResponseEntity("Duplicate", HttpStatus.OK);
		}
		
		
		return new ResponseEntity<String>(new String(result), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/deleteAltRequest/", method = RequestMethod.POST, produces = "application/json")			
		public ResponseEntity<String> deleteAltRequest(@RequestBody AlertRequest regrequest,HttpServletRequest request)
	{	
		System.out.println(":: delete Alt Request::");
		AlertRequestDao requestdao = new AlertRequestDao();
    	
		String result =  requestdao.delete(regrequest.getReqReqtID().longValue());
		
		if(regrequest == null)
		{
			return new ResponseEntity(new String("Duplicate"), HttpStatus.OK);
		}
		
		
		return new ResponseEntity<String>(new String(result), HttpStatus.OK);
	}
	
/*---------------Services for ReferralStatusHist - Start------------------*/
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/saveReferralStatusHist/", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<ReferralStatusHistVO> saveReferralStatusHist(@RequestBody ReferralStatusHistVO voObj, HttpServletRequest request) {
		
		System.out.println(":: saveReferralStatusHist ::");
		ReferralStatusHistDao referralStatusHistDao = new ReferralStatusHistDao();
		ReferralStatusHist referralStatusHist = new ReferralStatusHist();
		referralStatusHist.setStatusDate(new Date());
		if(referralStatusHist.getStatusComments() == null || referralStatusHist.getStatusComments().isEmpty()) {
			referralStatusHist.setStatusComments("APPROVED");
		}
		referralStatusHist.setStatus("PENDING INTERFACE");
		referralStatusHist.setStatusUser("C360 ADMIN");
		if(referralStatusHist.getRegReq() != null)
		{
			RegistrationRequestDao dao = new RegistrationRequestDao();
			RegistrationRequest registrationRequest = referralStatusHist.getRegReq() ;
			registrationRequest.setStatus("PENDING INTERFACE");
			registrationRequest.setComments("APPROVED");
			dao.update(registrationRequest);
			System.out.println(":: Updated :: Registration Request Table ::");
		}			
		
		referralStatusHist = referralStatusHistDao.save(referralStatusHist);		
		if (referralStatusHist == null) {
			return new ResponseEntity(new String("FAILED"), HttpStatus.OK);
		}

		System.out.println(":: saved ::");
		return new ResponseEntity(new String("Request Approved - Pending to send WCM Target"), HttpStatus.OK);
		//return new ResponseEntity<ReferralStatusHistVO>(voObj, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/saveAlertStatusHist/", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<AlertStatusHistVO> saveAlertStatusHist(@RequestBody AlertStatusHistVO voObj, HttpServletRequest request) {
		
		System.out.println(":: saveAlertStatusHist ::");
		AlertStatusHistDao alertStatusHistDao = new AlertStatusHistDao();

		AlertStatusHist alertStatusHist = new AlertStatusHist();
		alertStatusHist.setStatusDate(new Date());
		if(alertStatusHist.getStatusComments() == null || alertStatusHist.getStatusComments().isEmpty()) {
			alertStatusHist.setStatusComments("APPROVED");
		}
		alertStatusHist.setStatus("PENDING INTERFACE");
		alertStatusHist.setStatusUser("C360 ADMIN");
		if(alertStatusHist.getRegReq() != null)
		{
			AlertRequestDao alertRequestDao = new AlertRequestDao();
			AlertRequest alertRequest = alertStatusHist.getRegReq() ;
			alertRequest.setStatus("PENDING INTERFACE");
			alertRequest.setComments("APPROVED");
			alertRequestDao.update(alertRequest);
			System.out.println(":: Updated :: Alert Request Table ::");
		}			
		
		alertStatusHist = alertStatusHistDao.save(alertStatusHist);		
		if (alertStatusHist == null) {
			return new ResponseEntity(new String("FAILED"), HttpStatus.OK);
		}


		System.out.println(":: saved ::");
		return new ResponseEntity(new String("Request Approved - Pending to send WCM Target"), HttpStatus.OK);
		//return new ResponseEntity<ReferralStatusHistVO>(voObj, HttpStatus.OK);
	}
		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/listAllReferralStatusHist/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<List<ReferralStatusHistVO>> listAllReferralStatusHist() 
	{
		System.out.println(":: listAllReferralStatusHist() ::");
		ReferralStatusHistDao daoObj = new ReferralStatusHistDao();
    	
		List<ReferralStatusHist> list = daoObj.getlist() ;
		if (list == null) {
			return new ResponseEntity(new String("NO HISTORY"), HttpStatus.OK);
		}
		 
		ReferralStatusHistVO voObj = new ReferralStatusHistVO();
		List<ReferralStatusHistVO> voList = new ArrayList<ReferralStatusHistVO>();
		
		return new ResponseEntity<List<ReferralStatusHistVO>>(voList, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/listAllRefStatusHistByRegReqId/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<List<ReferralStatusHistVO>> listAllRefStatusHistByRegReqId(@RequestBody ReferralStatusHistVO voObj, HttpServletRequest request) 
	{
		System.out.println(":: listAllRefStatusHistByRegReqId() ::");
		ReferralStatusHistDao daoObj = new ReferralStatusHistDao();
    	
		List<ReferralStatusHist> list = daoObj.getlistWithRegReqID(voObj.getReqReqtID().longValue());		
		if (list == null) {
			return new ResponseEntity(new String("NO HISTORY"), HttpStatus.OK);
		}
		List<ReferralStatusHistVO> voList = new ArrayList<ReferralStatusHistVO>();
				
		return new ResponseEntity<List<ReferralStatusHistVO>>(voList, HttpStatus.OK);
	}
		
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/listAllSendHistByReqActionAndReqId/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<List<ApiHistVO>> listAllSendHistByReqActionAndReqId(@RequestBody ApiHistVO voObj, HttpServletRequest request) 
	{
		System.out.println(":: listAllSendHistByReqActionAndReqId() ::");
		ApiHistDao daoObj = new ApiHistDao();
    	
		List<ApiHist> list = daoObj.getlistWithReqActionAndRegID(voObj.getApiAction(), voObj.getRequestId().longValue());		
		if (list == null) {
			return new ResponseEntity(new String("NO HISTORY"), HttpStatus.OK);
		}
		List<ApiHistVO> voList = new ArrayList<ApiHistVO>();
				
		return new ResponseEntity<List<ApiHistVO>>(voList, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/listAllAlertStatusHistByAlertReqId/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<List<AlertStatusHistVO>> listAllAlertStatusHistByAlertReqId(@RequestBody AlertStatusHistVO voObj, HttpServletRequest request) 
	{
		System.out.println(":: listAllAlertStatusHistByAlertReqId() ::");
		AlertStatusHistDao daoObj = new AlertStatusHistDao();
    	
		List<AlertStatusHist> list = daoObj.getlistWithRegReqID(voObj.getReqReqtID().longValue());		
		if (list == null) {
			return new ResponseEntity(new String("NO HISTORY"), HttpStatus.OK);
		}
		List<AlertStatusHistVO> voList = new ArrayList<AlertStatusHistVO>();
				
		return new ResponseEntity<List<AlertStatusHistVO>>(voList, HttpStatus.OK);
	}

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/updateReferralStatusHist/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<String> updateReferralStatusHist(@RequestBody ReferralStatusHistVO voObj, HttpServletRequest request)
	{	
		System.out.println(":: updateReferralStatusHist() ::");
		ReferralStatusHistDao daoObj = new ReferralStatusHistDao();
		ReferralStatusHist obj = new ReferralStatusHist();
    	
		String result =  daoObj.update(obj);
		if(result == null)
		{
			return new ResponseEntity(new String("FAILED"), HttpStatus.OK);
		}
				
		return new ResponseEntity<String>(new String(result), HttpStatus.OK);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/deleteReferralStatusHist/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<String> deleteReferralStatusHist(@RequestBody ReferralStatusHistVO voObj, HttpServletRequest request)
	{	
		System.out.println(":: saveReferralStatusHist ::");
		ReferralStatusHistDao daoObj = new ReferralStatusHistDao();
		ReferralStatusHist referralStatusHist = new ReferralStatusHist();
		
		if(referralStatusHist.getRegReq() != null)
		{
			RegistrationRequestDao dao = new RegistrationRequestDao();
			RegistrationRequest RegistrationRequest = referralStatusHist.getRegReq() ;
			RegistrationRequest.setStatus("Rejected");
			dao.update(RegistrationRequest);
			System.out.println(":: Updated ..Request in Request table ::");
		}
			
		
		referralStatusHist = daoObj.save(referralStatusHist);		
		if (referralStatusHist == null) {
			return new ResponseEntity(new String("FAILED"), HttpStatus.OK);
		}

		System.out.println(":: saved ::");
		return new ResponseEntity(new String("Request Approved - Pending to send WCM Target"), HttpStatus.OK);
	
	}
	
	/*---------------Services for ReferralStatusHist - End------------------*/	
}
