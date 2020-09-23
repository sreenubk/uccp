package  com.lotus.uccp.consentmgmt.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.lotus.uccp.consentmgmt.dao.TransactionDao;
import com.lotus.uccp.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.lotus.uccp.util.HibernateUtil;
import com.lotus.uccp.util.Message;
import com.lotus.uccp.consentmgmt.dao.ControlGroupDao;
import com.lotus.uccp.consentmgmt.dao.ControlObjectDao;
import com.lotus.uccp.authentication.model.Transaction;
import com.lotus.uccp.authentication.model.Constraint;
import com.lotus.uccp.authentication.model.ControlGroup;
import com.lotus.uccp.authentication.model.ControlObject;
import com.lotus.uccp.consentmgmt.model.User;
import com.lotus.uccp.consentmgmt.service.ControlService;
import com.lotus.uccp.consentmgmt.util.CustomErrorType;
import com.lotus.uccp.util.Converters;
import com.lotus.uccp.vo.AllControlGroup;
import com.lotus.uccp.vo.ConstraintVO;
import com.lotus.uccp.vo.ControlGroupVO;
import com.lotus.uccp.vo.TransactionVO;

@RestController
@RequestMapping("/control")
public class ControlController {

	public static final Logger logger = LoggerFactory.getLogger(ControlController.class);
	
	@Autowired	
	ControlService controlservice ;
	
		// ------------------- Constraint  ---------------------------------------/
	   
		@RequestMapping(value = "/getConstraint/", method = RequestMethod.POST, produces = "application/json")			
		public ResponseEntity<ConstraintVO> getConstraint(@RequestBody ControlGroupVO cosdabaccontrolgroupvo) 
		{				
			//List<Constraint> cosdabacconstraintList = controlservice.getListConstraint();
			
			ControlObjectDao cosdabaccontrolobjectdao = new ControlObjectDao();
			ControlObject cosdabaccontrolobject = null ;//cosdabaccontrolobjectdao.getByGrpId(cosdabaccontrolgroupvo.getControlgroupid().longValue()) ;
			Constraint cosdabacconstraint = cosdabaccontrolobject.getConstraint() ;
			
			if( cosdabacconstraint == null)
				return new ResponseEntity(new Message("Constraint not found For Control Group -" + cosdabaccontrolgroupvo.getControlgroupid().longValue()), HttpStatus.OK);
			
			Converters conv = new Converters();
			
			ConstraintVO cosdabacconstraintvo = conv.convertToVo(cosdabacconstraint) ;	 
			
			
			
			return new ResponseEntity<ConstraintVO>(cosdabacconstraintvo, HttpStatus.OK);
		}
	
		@RequestMapping(value = "/listAllConstraint/", method = RequestMethod.POST, produces = "application/json")			
		public ResponseEntity<List<ConstraintVO>> listAllConstraint() 
		{				
			List<Constraint> cosdabacconstraintList = controlservice.getListConstraint();
			
			ConstraintVO cosdabacconstraintvo = new ConstraintVO();
			Converters conv = new Converters();
								
			
			if (cosdabacconstraintList.isEmpty()) {
						return new ResponseEntity(HttpStatus.OK);
						// You many decide to return HttpStatus.NOT_FOUND
			}
			List<ConstraintVO> cosdabacconstraintVoList = conv.covertList(cosdabacconstraintList, cosdabacconstraintvo);
			return new ResponseEntity<List<ConstraintVO>>(cosdabacconstraintVoList, HttpStatus.OK);
		}

		@RequestMapping(value = "/delConstraint/", method = RequestMethod.POST)
		public ResponseEntity<?> delConstraint(@RequestBody ConstraintVO cosdabacconstraintvo) {
			logger.info("Fetching & Deleting User with id {} ", cosdabacconstraintvo.getAttributeconstraintid());
	        long del = Long.parseLong(cosdabacconstraintvo.getAttributeconstraintid().toString());
			 String result = controlservice.deleteConstraint(del);
			System.out.println(":: Result ::" + result);
			return new ResponseEntity(new Message(result), HttpStatus.OK);
		}
		
		@RequestMapping(value = "/updateConstraint/", method = RequestMethod.POST)
		public ResponseEntity<?> updateConstraint(@RequestBody ConstraintVO cosdabacconstraintvo , HttpServletRequest request)
		{	
			logger.info(":: constraint type ::", cosdabacconstraintvo.getAccessconstrainttype());
			Constraint cosdabacconstraint = new Constraint();
			
			try
			{
				String username = HibernateUtil.getUserName(request); // HttpServletRequest request
				cosdabacconstraint.setAttributeconstraintid(cosdabacconstraintvo.getAttributeconstraintid());
				cosdabacconstraint.setAccessconstrainttype(cosdabacconstraintvo.getAccessconstrainttype());
				cosdabacconstraint.setAttributevalue(cosdabacconstraintvo.getAttributevalue());
				cosdabacconstraint.setAttributename(cosdabacconstraintvo.getAttributename());
				cosdabacconstraint.setCreatedby(username);
				cosdabacconstraint.setCreatedon(new Date());
				cosdabacconstraint.setLastupdatedby(username);
				
				
				controlservice.updateConstraint(cosdabacconstraint);
		    	Converters conv = new Converters();
		    	cosdabacconstraintvo = conv.convertToVo(cosdabacconstraint) ;
		    	return new ResponseEntity(cosdabacconstraintvo, HttpStatus.OK);
			
			}
			catch(Exception ex)
			{    System.out.println("::-----$$$$$ ::" + ex);
				ex.printStackTrace();
				return new ResponseEntity(new Message("Unable to Update Record."), HttpStatus.OK);
			}
			
			
		}	
		
		@RequestMapping(value = "/saveConstraint/", method = RequestMethod.POST)
		public ResponseEntity<?> saveConstraint(@RequestBody ConstraintVO cosdabacconstraintvo, HttpServletRequest request) {
			logger.info(":: constraint type ::", cosdabacconstraintvo.getAccessconstrainttype());
			Constraint cosdabacconstraint = new Constraint();
			try
			{
				String username = HibernateUtil.getUserName(request); // HttpServletRequest request
				//cosdabacconstraint.setAttributeconstraintid(cosdabacconstraintvo.getAttributeconstraintid());
				cosdabacconstraint.setAccessconstrainttype(cosdabacconstraintvo.getAccessconstrainttype());
				cosdabacconstraint.setAttributevalue(cosdabacconstraintvo.getAttributevalue());
				cosdabacconstraint.setAttributename(cosdabacconstraintvo.getAttributename());
				cosdabacconstraint.setCreatedby(username);
				cosdabacconstraint.setCreatedon(cosdabacconstraintvo.getCreatedon());
				cosdabacconstraint.setLastupdatedby(username);
				
				
				controlservice.saveConstraint(cosdabacconstraintvo.getControlgroupid().longValue(),cosdabacconstraintvo.getTransactionid().longValue(),cosdabacconstraint);
				cosdabacconstraintvo.setAttributeconstraintid(cosdabacconstraint.getAttributeconstraintid());
		    	Converters conv = new Converters();
		    	cosdabacconstraintvo = conv.convertToVo(cosdabacconstraint) ;
		    	return new ResponseEntity(cosdabacconstraintvo, HttpStatus.OK);
				
			   
			}
			catch(Exception ex)
			{    System.out.println("::-----$$$$$ ::" + ex);
				ex.printStackTrace();
				return new ResponseEntity(new Message("Fail"), HttpStatus.OK);
			}
		}
	// ------------------- End Constraint ---------------------------------------/
		
		
	// ------------------- TRANSACTION  TXN ----------------------------------------/
		
		@RequestMapping(value = "/getTxn/", method = RequestMethod.POST, produces = "application/json")			
		public ResponseEntity<TransactionVO> getTxn(@RequestBody ControlGroupVO cosdabaccontrolgroupvo) 
		{				
			//List<Constraint> cosdabacconstraintList = controlservice.getListConstraint();
			System.out.println(":: Group ID ::"+ cosdabaccontrolgroupvo.getControlgroupid().longValue());
			 
			ControlObjectDao cosdabaccontrolobjectdao = new ControlObjectDao();
			ControlObject cosdabaccontrolobject = null ; //cosdabaccontrolobjectdao.getByGrpId(cosdabaccontrolgroupvo.getControlgroupid().longValue()) ;
			Transaction cosdabactransaction = cosdabaccontrolobject.getTransaction();
			
			if( cosdabactransaction == null)
				return new ResponseEntity(new Message("Transaction not found For Control Group -" + cosdabaccontrolgroupvo.getControlgroupid().longValue()), HttpStatus.OK);
			
			Converters conv = new Converters();
			
			TransactionVO cosdabactransactionvo = conv.convertToVo(cosdabactransaction) ;	 
			
			
			
			return new ResponseEntity<TransactionVO>(cosdabactransactionvo, HttpStatus.OK);
		}	
	@RequestMapping(value = "/listAlltxn/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<List<TransactionVO>> listAlltxn() 
	{				
		List<Transaction> cosdabactxngrouplist = controlservice.getListCosdabacTxn();
		//System.out.println("::-- ControlGroup --::" + cosdabaccontrolgroups.size());
		TransactionVO cosdabactransaction = new TransactionVO();
		Converters conv = new Converters();
		
		List<TransactionVO> cosdabaccontrolgroupsVolist = conv.covertList(cosdabactxngrouplist, cosdabactransaction);
		
		
		if (cosdabaccontrolgroupsVolist.isEmpty()) {
					return new ResponseEntity(HttpStatus.NO_CONTENT);
					// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<TransactionVO>>(cosdabaccontrolgroupsVolist, HttpStatus.OK);
	}

	@RequestMapping(value = "/deleteTxn/", method = RequestMethod.POST)	
	public ResponseEntity<?> deleteTxn(@RequestBody TransactionVO cosdabactransactionvo) {
		logger.info("Fetching & Deleting User with id {}", cosdabactransactionvo.getTransactionid().toString());
        long del = Long.parseLong(cosdabactransactionvo.getTransactionid().toString());
		 String result = controlservice.deleteCosdabacTxn(del);
		System.out.println(":: Result ::" + result);
		return new ResponseEntity(new Message(result), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/updateTxn/", method = RequestMethod.POST)
	public ResponseEntity<?> updateTxn(@RequestBody TransactionVO cosdabactransactionvo , HttpServletRequest request )
	{	
		logger.info(":: Transactionname ::", cosdabactransactionvo.getTransactionname());
	
		Transaction cosdabactransaction = new Transaction();
		try
		{
			String username = HibernateUtil.getUserName(request); // HttpServletRequest request
			cosdabactransaction.setTransactionid(cosdabactransactionvo.getTransactionid());
			cosdabactransaction.setTransactionname(cosdabactransactionvo.getTransactionname());
			cosdabactransaction.setTransactiontype(cosdabactransactionvo.getTransactiontype());
			cosdabactransaction.setDefaultaccess(cosdabactransactionvo.getDefaultaccess());
			cosdabactransaction.setLastupdatedby(username);		
			cosdabactransaction.setLastupdatedon(new Date());
		
		
			logger.info("before insert");
			controlservice.updateCosdabacTxn(cosdabactransaction);
			Converters conv = new Converters();
			TransactionVO cosdabactransactionVo = conv.convertToVo(cosdabactransaction);
			return new ResponseEntity(cosdabactransactionVo, HttpStatus.OK);  
		}	
		catch(Exception ex)
		{    System.out.println("::-----$$$$$ ::" + ex);
			ex.printStackTrace();
			return new ResponseEntity(new Message("Fail"), HttpStatus.OK);
		}	
				
	}	
	
	@RequestMapping(value = "/saveTxn/", method = RequestMethod.POST)	
	public ResponseEntity<?> saveTxn(@RequestBody TransactionVO cosdabactransactionvo ,HttpServletRequest request) {
		logger.info(":: Transactionname ::", cosdabactransactionvo.getTransactionname());
		
		Transaction cosdabactransaction = new Transaction();
		try
		{
			String username = HibernateUtil.getUserName(request); // HttpServletRequest request
			cosdabactransaction.setTransactionname(cosdabactransactionvo.getTransactionname());
			cosdabactransaction.setTransactiontype(cosdabactransactionvo.getTransactiontype());
			cosdabactransaction.setDefaultaccess(cosdabactransactionvo.getDefaultaccess());
			cosdabactransaction.setLastupdatedby(username);
			cosdabactransaction.setCreatedby(username);
			cosdabactransaction.setLastupdatedon(new Date());
			cosdabactransaction.setCreatedon(new Date());
			
		    logger.info("before insert");
		    
		    TransactionDao cosdabactransactiondao = new TransactionDao();
		    String result = "empty" ; //cosdabactransactiondao.getlistByTxnName(cosdabactransactionvo.getTransactionname());
		    
		    if("empty".equalsIgnoreCase(result))
		    {
		    	controlservice.saveCosdabacTxn( cosdabactransactionvo.getControlgroupid().longValue(), cosdabactransaction);
		    	cosdabactransactionvo.setTransactionid(cosdabactransaction.getTransactionid());
		    	logger.info(":: Transactionid ::" + cosdabactransaction.getTransactionid());
		    	Converters conv = new Converters();
		    	TransactionVO cosdabactransactionVo = conv.convertToVo(cosdabactransaction);
		    	return new ResponseEntity(cosdabactransactionvo, HttpStatus.OK);
		    }
		    else
		    {
		    	return new ResponseEntity(new Message("Duplicate"), HttpStatus.OK);
		    }
		   
		  
		}
		catch(Exception ex)
		{    System.out.println("::-----$$$$$ ::" + ex);
			ex.printStackTrace();
			return new ResponseEntity(new Message("Fail"), HttpStatus.OK);
		}		
		
	}
	
	@RequestMapping(value = "/existTrans/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<?> existConsTxn(@RequestBody TransactionVO cosdabactransactionvo) 
	{
		TransactionDao cosdabactransactiondao = new TransactionDao() ;
		//CosdconsentgrouptxnDao cosdconsentgrouptxndao = new CosdconsentgrouptxnDao();
		 
		try
		{
			/*String result = cosdabactransactiondao.getlistByTxnConsentID(cosdabactransactionvo.getTransactionid().longValue()) ;
			
			if("duplicate".equalsIgnoreCase(result))
			{
				 return new ResponseEntity(new Message("duplicate"), HttpStatus.OK);
			}*/
			
			cosdabactransactiondao.saveExistingWithControl(cosdabactransactionvo.getControlgroupid().longValue() ,cosdabactransactionvo.getTransactionid().longValue());
		}
		catch(Exception ex)
		{
			return new ResponseEntity(new Message("Fail"), HttpStatus.OK);	
		}
		 
		 return new ResponseEntity(new Message("success"), HttpStatus.OK);
	}
	// ------------------- End Txn ---------------------------------------/
	
	// -------------------Retrieve All ControlGroup---------------------------------------------/
	@RequestMapping(value = "/listgrp/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<List<ControlGroupVO>> listAllgrp() 
	{				
		List<ControlGroup> cosdabaccontrolgroups = controlservice.getListControlGroup();
		ControlGroupVO cosdabaccontrolgroupvo = new ControlGroupVO();
		Converters conv = new Converters();
		
		List<ControlGroupVO> cosdabaccontrolgroupsVo = conv.covertList(cosdabaccontrolgroups, cosdabaccontrolgroupvo);
		System.out.println("::-- ControlGroup --::" + cosdabaccontrolgroupsVo.size());
			
			if (cosdabaccontrolgroupsVo.isEmpty()) {
				return new ResponseEntity(new Message("NO Group Found."), HttpStatus.OK); 			
					
			}
			return new ResponseEntity<List<ControlGroupVO>>(cosdabaccontrolgroupsVo, HttpStatus.OK);
	}
	    
	 @RequestMapping(value = "/listgrpwithgrpName/", method = RequestMethod.POST, produces = "application/json")			
	 public ResponseEntity<List<ControlGroupVO>> listAllgrpWithName(@RequestBody ControlGroupVO cosdabaccontrolgroupvo ,HttpServletRequest request) 
	 {		
		 	String username = HttpUtil.getUserName(request); // HttpServletRequest request
	    	System.out.println(":: ::" + cosdabaccontrolgroupvo.getControlgroupname());
			List<ControlGroup> cosdabaccontrolgroups = controlservice.getListcontrolgroupWithGrpName(cosdabaccontrolgroupvo.getControlgroupname().toUpperCase());
			
			Converters conv = new Converters();
			
			List<ControlGroupVO> cosdabaccontrolgroupsVo = conv.covertList(cosdabaccontrolgroups, cosdabaccontrolgroupvo);
			System.out.println("::-- ControlGroup --::" + cosdabaccontrolgroupsVo.size());
				
				if (cosdabaccontrolgroupsVo.isEmpty()) {
					return new ResponseEntity(new Message("NO Group Found."), HttpStatus.OK); 			
						
				}
				return new ResponseEntity<List<ControlGroupVO>>(cosdabaccontrolgroupsVo, HttpStatus.OK);
	}

		@RequestMapping(value = "/delControlGrp/", method = RequestMethod.POST)
		public ResponseEntity<?> deleteControlGrp(@RequestBody ControlGroupVO cosdabaccontrolgroupvo) {
			logger.info("Fetching & Deleting User with id {}", cosdabaccontrolgroupvo.getControlgroupid());
            long del = Long.parseLong(cosdabaccontrolgroupvo.getControlgroupid().toString());
			 String result = controlservice.deleteControlGroup(del);
			System.out.println(":: REsult ::" + result);
			return new ResponseEntity(new Message(result), HttpStatus.OK);
		}
		
		@RequestMapping(value = "/updateControlGrp/", method = RequestMethod.POST)
		public ResponseEntity<?> updateControlGrp(@RequestBody ControlGroupVO controlGrpVo ,HttpServletRequest request)
		{	logger.info("Group Name ::", controlGrpVo.getControlgroupname() + ":: ID ::" + controlGrpVo.getControlgroupid());
			ControlGroup cosdabaccontrolgroup = new ControlGroup();
			try
			{   String username = HibernateUtil.getUserName(request); // HttpServletRequest request
				cosdabaccontrolgroup.setControlgroupid(controlGrpVo.getControlgroupid());
				cosdabaccontrolgroup.setControlgroupname(controlGrpVo.getControlgroupname().toUpperCase());
				cosdabaccontrolgroup.setCreatedby(username);
				cosdabaccontrolgroup.setCreatedon(new Date());
				cosdabaccontrolgroup.setLastupdatedby(username);
				cosdabaccontrolgroup.setLastupdatedon(new Date());
								
				String result = controlservice.updateControlGroup(cosdabaccontrolgroup);
			
				return new ResponseEntity(new Message(result), HttpStatus.OK);
			
			}
			catch(Exception ex)
			{    System.out.println("::-----$$$$$ ::" + ex);
				ex.printStackTrace();
				return new ResponseEntity(new Message("Unable to Update Record."), HttpStatus.OK);
			}
			
			
		}	
		
		@RequestMapping(value = "/saveControlGrp/", method = RequestMethod.POST)
		public ResponseEntity<?> saveControlGrp(@RequestBody ControlGroupVO controlGrpVo ,HttpServletRequest request) {
			logger.info(":: Group Name ::", controlGrpVo.getControlgroupname());
			ControlGroup cosdabaccontrolgroup = new ControlGroup();
			try
			{
				String username = HttpUtil.getUserName(request);
				
				cosdabaccontrolgroup.setControlgroupname(controlGrpVo.getControlgroupname().toUpperCase());
				cosdabaccontrolgroup.setCreatedby(username);
				cosdabaccontrolgroup.setCreatedon(new Date());
				cosdabaccontrolgroup.setLastupdatedby(username);
				cosdabaccontrolgroup.setLastupdatedon(new Date());
			    logger.info("before insert");
			    
			    List<ControlGroup> cosdabaccontrolgroups = controlservice.getListcontrolgroupWithGrpName(cosdabaccontrolgroup.getControlgroupname());
			    
			    if(cosdabaccontrolgroups == null || cosdabaccontrolgroups.size() == 0)
			    { 
			    	controlservice.saveControlGroup(cosdabaccontrolgroup);
			    	Converters conv = new Converters();
			    	ControlGroupVO cosdabaccontrolgroupvo = conv.convertToVo(cosdabaccontrolgroup) ;
			    	return new ResponseEntity(cosdabaccontrolgroupvo, HttpStatus.OK);
			    }
			    else
			    {
			    	return new ResponseEntity(new Message("Duplicate"), HttpStatus.OK);
			    }
			}
			catch(Exception ex)
			{    System.out.println("::-----$$$$$ ::" + ex);
				ex.printStackTrace();
				return new ResponseEntity(new Message("Fail"), HttpStatus.OK);
			}
			
			
		}
		
		@RequestMapping(value = "/getgrpWithGrpID/", method = RequestMethod.POST, produces = "application/json")			
		 public ResponseEntity<ControlGroupVO> getgrpWithGrpID(@RequestBody ControlGroupVO cosdabaccontrolgroupvo ,HttpServletRequest request) 
		 {		
			    HttpSession session = request.getSession(false) ;
			    
			    if(session == null)
			    {
			    	System.out.println("Session is null");
			    }
			    else 
			    {
			    	System.out.println("Session is Found");
			    }
			     System.out.println(":: Get details of Control Group Id ::" + cosdabaccontrolgroupvo.getControlgroupid());
		    	ControlGroup cosdabaccontrolgroup = controlservice.getGrpwithID(cosdabaccontrolgroupvo.getControlgroupid().longValue());
				
				Converters conv = new Converters();
		    	ControlGroupVO vogroup = conv.convertToVo(cosdabaccontrolgroup) ;
				
					
					if (vogroup == null) {
						return new ResponseEntity(new Message("NO Group Found."), HttpStatus.OK); 			
							
					}
					return new ResponseEntity<ControlGroupVO>(vogroup, HttpStatus.OK);
		}
		
		@RequestMapping(value = "/SearchAllControl/", method = RequestMethod.POST, produces = "application/json")			
		 public ResponseEntity<AllControlGroup> SearchAllControl(@RequestBody ControlGroupVO cosdabaccontrolgroupvo) 
		 {	ControlGroupDao cosdabaccontrolgroupdao = new ControlGroupDao();
		 	AllControlGroup allcontrolgroup = cosdabaccontrolgroupdao.searchControlData(cosdabaccontrolgroupvo);
			return new ResponseEntity<AllControlGroup>(allcontrolgroup, HttpStatus.OK);
		 }
		
		@RequestMapping(value = "/listAllConstraintWithTxn/{txnName}/", method = RequestMethod.GET, produces = "application/json")			
		public ResponseEntity<List<ConstraintVO>> listAllConstraintWithTxn(@PathVariable("txnName") String txn) 
		{				
			System.out.println("-- listAllConstraintWithTxn --" + txn);
			
			ControlGroupDao cosdabaccontrolgroupdao = new ControlGroupDao();
			List<ConstraintVO> cosdabacconstraintVoList = cosdabaccontrolgroupdao.searchAttributeData(txn);
			ConstraintVO cosdabacconstraintvo = new ConstraintVO();
			Converters conv = new Converters();
								
			
			if (cosdabacconstraintVoList.isEmpty()) {
						return new ResponseEntity(HttpStatus.OK);
						// You many decide to return HttpStatus.NOT_FOUND
			}
			//List<ConstraintVO> cosdabacconstraintVoList = conv.covertList(cosdabacconstraintList, cosdabacconstraintvo);
			return new ResponseEntity<List<ConstraintVO>>(cosdabacconstraintVoList, HttpStatus.OK);
		}

}