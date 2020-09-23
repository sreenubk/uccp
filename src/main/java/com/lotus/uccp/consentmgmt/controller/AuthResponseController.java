/*Licensed Materials - 
 Property of IBM
6949 - 67L
  Copyright IBM Corp. 2017, 2018 All Rights Reserved
*/

/* Licensed Materials - 
 Property of IBM 6949 - 67L 
 Copyright IBM Corp. 2017, 2018 All Rights Reserved */
package  com.lotus.uccp.consentmgmt.controller;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lotus.uccp.consentmgmt.service.AuthResponseService;
import com.lotus.uccp.vo.AuthRequestVO;
import com.lotus.uccp.vo.AuthResponseVO;


@RestController
@RequestMapping("/authRes")
public class AuthResponseController {
	
	public static final Logger logger = LoggerFactory.getLogger(AuthResponseController.class);
	
	@Autowired	
	AuthResponseService authresponseservice ;

	@RequestMapping(value = "/getAuthData/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<AuthRequestVO> getAuthData() 
	{ 
		System.out.println(":::-- :: getAuthData() ---:::" );
		AuthRequestVO authrequestvo = new AuthRequestVO();
		
		return new ResponseEntity<AuthRequestVO>(authrequestvo, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getAuthRespData/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<AuthResponseVO> getAuthRespData(@RequestBody AuthRequestVO authrequestvo) 
	{
		AuthResponseVO authrestvo = new AuthResponseVO();		
		authrestvo = authresponseservice.getAuthService(authrequestvo) ;
		return new ResponseEntity<AuthResponseVO>(authrestvo, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/AuthServiceReportAll/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<List<AuthRequestVO>> AuthServiceReportAll() 
	{	
	    	// select AUDITID , USERNAME ,CLIENTID ,SRCSYSTEM ,TRANSNAME ,APISWITCH ,REQUESTDT ,RESULT from CONNECT360_AUDIT order by REQUESTDT desc ,USERNAME
			
			List<AuthRequestVO> authReqList = authresponseservice.getReportAll() ;// cosdconsentexceptiondao.getlist() ;
			
			return new ResponseEntity<List<AuthRequestVO>>(authReqList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/criteriaSearchReport/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<List<AuthRequestVO>> criteriaSearchReport(@RequestBody AuthRequestVO authrequestvo) 
	{	
	    	// select AUDITID , USERNAME ,CLIENTID ,SRCSYSTEM ,TRANSNAME ,APISWITCH ,REQUESTDT ,RESULT from CONNECT360_AUDIT order by REQUESTDT desc ,USERNAME
			
			List<AuthRequestVO> authReqList = authresponseservice.getReportWithCriteriaSearch(authrequestvo) ;// cosdconsentexceptiondao.getlist() ;
			
			return new ResponseEntity<List<AuthRequestVO>>(authReqList, HttpStatus.OK);
	}
	
	
}
