package  com.lotus.uccp.consentmgmt.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.lotus.uccp.util.HibernateUtil;
import com.lotus.uccp.util.Message;
import com.lotus.uccp.consentmgmt.dao.ControlGroupDao;
import com.lotus.uccp.consentmgmt.dao.TransactionDao;
import com.lotus.uccp.consentmgmt.dao.ConsentExceptionDao;
import com.lotus.uccp.consentmgmt.dao.ConsentGroupDao;
import com.lotus.uccp.consentmgmt.dao.ConsentGroupTxnDao;
import com.lotus.uccp.consentmgmt.dao.PersonConsentDao;
import com.lotus.uccp.consentmgmt.dao.PersonConsentHistoryDao;
import com.lotus.uccp.consentmgmt.dao.ReferralLADao;
import com.lotus.uccp.authentication.model.ConsentException;
import com.lotus.uccp.authentication.model.ConsentGroup;
import com.lotus.uccp.authentication.model.PersonConsent;
import com.lotus.uccp.authentication.model.PersonConsentHistory;
import com.lotus.uccp.authentication.model.ReferralLA;
import com.lotus.uccp.authentication.model.Transaction;
import com.lotus.uccp.util.ConsentConverter;
import com.lotus.uccp.util.Converters;
import com.lotus.uccp.vo.AllControlGroup;
import com.lotus.uccp.vo.ConstraintVO;
import com.lotus.uccp.vo.ControlGroupVO;
import com.lotus.uccp.vo.TransactionVO;
import com.lotus.uccp.vo.ConsentExceptionVO;
import com.lotus.uccp.vo.ConsentGroupVO;
import com.lotus.uccp.vo.PersonConsentVO;
import com.lotus.uccp.vo.ReferralLAVO;
import com.lotus.uccp.vo.ViewConsentGroup;

@RestController
@RequestMapping("/consent")
public class ConsentController {
	
	public static final Logger logger = LoggerFactory.getLogger(ControlController.class);
	
	@RequestMapping(value = "/viewConsentGrp/", method = RequestMethod.POST, produces = "application/json")			
	 public ResponseEntity<ViewConsentGroup> viewConsentGrp(@RequestBody ConsentGroupVO cosdconsentgroupvo) 
	 {	System.out.println(":: Grp ID ::" + cosdconsentgroupvo.getConsentgroupid().longValue());
		ConsentGroupDao cosdconsentgroupdao = new ConsentGroupDao();
		ViewConsentGroup vConsentGrp = cosdconsentgroupdao.getConsetGrp(cosdconsentgroupvo);
		return new ResponseEntity<ViewConsentGroup>(vConsentGrp, HttpStatus.OK);
	 }
	
	@RequestMapping(value = "/listConsentGroup/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<List<ConsentGroupVO>> listAllConstraint() 
	{	
		ConsentGroupDao cosdconsentgroupdao = new ConsentGroupDao();
		List<ConsentGroup> cosdconsentgrouplist = cosdconsentgroupdao.getlist() ;
		
		ConsentGroupVO cosdabacconstraintvo = new ConsentGroupVO();
		ConsentConverter conv = new ConsentConverter();
							
		
		if (cosdconsentgrouplist.isEmpty()) {
					return new ResponseEntity(HttpStatus.OK);
					// You many decide to return HttpStatus.NOT_FOUND
		}
		List<ConsentGroupVO> cosdconsentgroupVolist = conv.covertList(cosdconsentgrouplist, cosdabacconstraintvo);
		return new ResponseEntity<List<ConsentGroupVO>>(cosdconsentgroupVolist, HttpStatus.OK);
	}
	
	
	 @RequestMapping(value = "/listgrpwithgrpName/", method = RequestMethod.POST, produces = "application/json")			
	 public ResponseEntity<List<ConsentGroupVO>> listAllgrpWithName(@RequestBody ConsentGroupVO cosdconsentgroupvo) 
	 {		
	    	System.out.println(":: Search::" + cosdconsentgroupvo.getName());
	    	ConsentGroupDao cosdconsentgroupdao = new ConsentGroupDao();
			List<ConsentGroup> cosdconsentgrouplist = cosdconsentgroupdao.getlistByGroupName(cosdconsentgroupvo.getName().toUpperCase());
			
			ConsentConverter conv = new ConsentConverter();
			
			List<ConsentGroupVO> cosdabaccontrolgroupsVo = conv.covertList(cosdconsentgrouplist, cosdconsentgroupvo);
			System.out.println("::-- Cosdabaccontrolgroup --::" + cosdabaccontrolgroupsVo.size());
				
				if (cosdconsentgrouplist.isEmpty()) {
					return new ResponseEntity(new Message("NO Group Found."), HttpStatus.OK); 			
						
				}
				return new ResponseEntity<List<ConsentGroupVO>>(cosdabaccontrolgroupsVo, HttpStatus.OK);
	}


	@RequestMapping(value = "/saveConGrp/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<ConsentGroupVO> saveConGrp(@RequestBody ConsentGroupVO cosdconsentgroupvo,HttpServletRequest request) 
	{	
		ConsentGroupDao cosdconsentgroupdao = new ConsentGroupDao();
		ConsentGroup cosdconsentgroup = new ConsentGroup() ;
		String username = HibernateUtil.getUserName(request); // HttpServletRequest request
		//cosdconsentgroup.setConsentgroupid(cosdconsentgroupvo.getConsentgroupid());
		cosdconsentgroup.setName(cosdconsentgroupvo.getName().toUpperCase());
		cosdconsentgroup.setDescription(cosdconsentgroupvo.getDescription());
		
		cosdconsentgroup.setCreatedby(username);
		cosdconsentgroup.setCreatedon(new Date());
		
		cosdconsentgroup.setLastupdatedby(username);
		cosdconsentgroup.setLastupdatedon(new Date());
		
		
		//List<ConsentGroup> ConsentGrouplist  = cosdconsentgroupdao.getlistByGroupName(cosdconsentgroupvo.getName());
		
		//if(ConsentGrouplist == null || ConsentGrouplist.size() == 0)
		cosdconsentgroup = cosdconsentgroupdao.save(cosdconsentgroup);
		if(cosdconsentgroup == null)
		{
			return new ResponseEntity(new Message("Duplicate"), HttpStatus.OK);
		}
		
		ConsentConverter conv = new ConsentConverter();
		cosdconsentgroupvo = conv.convertToVo(cosdconsentgroup);	
		
		if (cosdconsentgroup == null) {
					return new ResponseEntity(HttpStatus.OK);
					// You many decide to return HttpStatus.NOT_FOUND
		}
		//List<ConsentGroupVO> cosdconsentgroupVolist = conv.covertList(cosdconsentgrouplist, cosdabacconstraintvo);
		return new ResponseEntity<ConsentGroupVO>(cosdconsentgroupvo, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/updateConGrp/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<ConsentGroupVO> updateConGrp(@RequestBody ConsentGroupVO cosdconsentgroupvo ,HttpServletRequest request) 
	{	
		ConsentGroupDao cosdconsentgroupdao = new ConsentGroupDao();
		ConsentGroup cosdconsentgroup = cosdconsentgroupdao.getById(cosdconsentgroupvo.getConsentgroupid().longValue()) ;
		
		if (cosdconsentgroup == null) {
			return new ResponseEntity(new Message("Not Found") ,HttpStatus.OK);
			// You many decide to return HttpStatus.NOT_FOUND
          }
		String username = HibernateUtil.getUserName(request); // HttpServletRequest request
		//cosdconsentgroup.setConsentgroupid(cosdconsentgroupvo.getConsentgroupid());
		cosdconsentgroup.setName(cosdconsentgroupvo.getName());
		cosdconsentgroup.setDescription(cosdconsentgroupvo.getDescription());
		
		cosdconsentgroup.setCreatedby(username);
		cosdconsentgroup.setCreatedon(new Date());
		
		cosdconsentgroup.setLastupdatedby(username);
		cosdconsentgroup.setLastupdatedon(new Date());
		
		String result = cosdconsentgroupdao.update(cosdconsentgroup);
		
		
		ConsentConverter conv = new ConsentConverter();
		cosdconsentgroupvo = conv.convertToVo(cosdconsentgroup);	
		
		
		//List<ConsentGroupVO> cosdconsentgroupVolist = conv.covertList(cosdconsentgrouplist, cosdabacconstraintvo);
		return new ResponseEntity<ConsentGroupVO>(cosdconsentgroupvo, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/delConGrp/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<?> delConGrp(@RequestBody ConsentGroupVO cosdconsentgroupvo) 
	{	
		ConsentGroupDao cosdconsentgroupdao = new ConsentGroupDao();
		ConsentGroup cosdconsentgroup = cosdconsentgroupdao.getById(cosdconsentgroupvo.getConsentgroupid().longValue()) ;
		
		if (cosdconsentgroup == null) {
			return new ResponseEntity(new Message("Not Found") ,HttpStatus.OK);
			// You many decide to return HttpStatus.NOT_FOUND
          }
		
		String result = cosdconsentgroupdao.delete(cosdconsentgroupvo.getConsentgroupid().longValue());
	
			
		ConsentConverter conv = new ConsentConverter();
		cosdconsentgroupvo = conv.convertToVo(cosdconsentgroup);	
		
		
		//List<ConsentGroupVO> cosdconsentgroupVolist = conv.covertList(cosdconsentgrouplist, cosdabacconstraintvo);
		return new ResponseEntity(new Message(result), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/saveConsTxn/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<TransactionVO> saveConsTxn(@RequestBody TransactionVO cosdabactransactionvo, HttpServletRequest request) 
	{	
		logger.info(":: Transactionname ::", cosdabactransactionvo.getTransactionname());
		
		Transaction cosdabactransaction = new Transaction();
		try
		{   String username = HibernateUtil.getUserName(request); // HttpServletRequest request
			cosdabactransaction.setTransactionname(cosdabactransactionvo.getTransactionname());
			cosdabactransaction.setTransactiontype(cosdabactransactionvo.getTransactiontype());
			cosdabactransaction.setDefaultaccess(cosdabactransactionvo.getDefaultaccess());
			cosdabactransaction.setLastupdatedby(username);
			cosdabactransaction.setCreatedby(username);
			cosdabactransaction.setLastupdatedon(new Date());
			cosdabactransaction.setCreatedon(new Date());
			
		    logger.info("before insert");
		    
		    TransactionDao cosdabactransactiondao = new TransactionDao() ;
		    if(cosdabactransactionvo.getControlgroupid() == null)
		    {
		    	return new ResponseEntity(new Message("Consent Group ID is null."), HttpStatus.OK);
		    }
		    
		    
		    
		    String result = cosdabactransactiondao.getlistByTxnName(cosdabactransactionvo.getTransactionname());// ,cosdabactransactionvo.getControlgroupid().longValue());
		    
		    if("empty".equalsIgnoreCase(result))
		    {
		    
		    	cosdabactransaction = cosdabactransactiondao.saveWithConsent(cosdabactransactionvo.getControlgroupid().longValue(), cosdabactransaction);
		    
		    	Converters conv = new Converters();
		    	TransactionVO cosdabactransactionVo = conv.convertToVo(cosdabactransaction);
		    	cosdabactransactionVo.setControlgroupid(cosdabactransactionvo.getControlgroupid());
		    	return new ResponseEntity(cosdabactransactionVo, HttpStatus.OK);
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
	
	@RequestMapping(value = "/updateConsTxn/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<TransactionVO> updateConsTxn(@RequestBody TransactionVO cosdabactransactionvo, HttpServletRequest request) 
	{	
		logger.info(":: Transactionname ::", cosdabactransactionvo.getTransactionname());
		
		Transaction cosdabactransaction = new Transaction();
		try
		{
			String username = HibernateUtil.getUserName(request); // HttpServletRequest request
			TransactionDao cosdabactransactiondao = new TransactionDao() ;
			cosdabactransaction = cosdabactransactiondao.getById(cosdabactransactionvo.getTransactionid().longValue());
			
			if(cosdabactransaction == null)
				return new ResponseEntity(new Message("Given Transactionid Record not found"), HttpStatus.OK);
			
			cosdabactransaction.setTransactionname(cosdabactransactionvo.getTransactionname());
			cosdabactransaction.setTransactiontype(cosdabactransactionvo.getTransactiontype());
			cosdabactransaction.setDefaultaccess(cosdabactransactionvo.getDefaultaccess());
			cosdabactransaction.setLastupdatedby(username);
			cosdabactransaction.setCreatedby(username);
			cosdabactransaction.setLastupdatedon(new Date());
			cosdabactransaction.setCreatedon(new Date());
			
		    logger.info("before insert");
		    
		    
		    cosdabactransactiondao.update(cosdabactransaction);
		    
		    Converters conv = new Converters();
		    cosdabactransactionvo = conv.convertToVo(cosdabactransaction);
		    return new ResponseEntity(cosdabactransactionvo, HttpStatus.OK);
		   
		  
		}
		catch(Exception ex)
		{    System.out.println("::-----$$$$$ ::" + ex);
			ex.printStackTrace();
			return new ResponseEntity(new Message("Fail"), HttpStatus.OK);
		}		
	}	
	
	@RequestMapping(value = "/deleteConsTxn/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<?> deleteConsTxn(@RequestBody TransactionVO cosdabactransactionvo) 
	{
		TransactionDao cosdabactransactiondao = new TransactionDao() ;
		try
		{
			cosdabactransactiondao.deleteConsentTxn(cosdabactransactionvo.getTransactionid().longValue());
		}
		catch(Exception ex)
		{
			return new ResponseEntity(new Message("Fail"), HttpStatus.OK);	
		}
		 
		 return new ResponseEntity(new Message("Deleted.."), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/existTrans/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<?> existConsTxn(@RequestBody TransactionVO cosdabactransactionvo) 
	{
		TransactionDao cosdabactransactiondao = new TransactionDao() ;
		//ConsentGrouptxnDao cosdconsentgrouptxndao = new ConsentGrouptxnDao();
		
		try
		{
			String result = cosdabactransactiondao.getlistByTxnConsentID(cosdabactransactionvo.getTransactionid().longValue()) ;
			
			if("duplicate".equalsIgnoreCase(result))
			{
				 return new ResponseEntity(new Message("duplicate"), HttpStatus.OK);
			}
			
			cosdabactransactiondao.saveExistingWithConsent(cosdabactransactionvo.getControlgroupid().longValue() ,cosdabactransactionvo.getTransactionid().longValue());
		}
		catch(Exception ex)
		{
			return new ResponseEntity(new Message("Fail"), HttpStatus.OK);	
		}
		 
		 return new ResponseEntity(new Message("success"), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/saveConsPerson/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<PersonConsentVO> saveConsPerson(@RequestBody PersonConsentVO cosdpersonconsentvo, HttpServletRequest request) 
	{	
		logger.info(":: Phone ::", cosdpersonconsentvo.getContactphone());
		System.out.println("Client ID --->"+ cosdpersonconsentvo.getClientid());
		
		PersonConsent cosdpersonconsent = new PersonConsent();
		try
		{
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy"); 
			
			String username = HibernateUtil.getUserName(request); // HttpServletRequest request
			cosdpersonconsent.setAuthpersoncontactinfoone(cosdpersonconsentvo.getAuthpersoncontactinfoone());
			cosdpersonconsent.setAuthpersoncontactinfotwo(cosdpersonconsentvo.getAuthpersoncontactinfotwo());
			cosdpersonconsent.setAuthpersonnameone(cosdpersonconsentvo.getAuthpersonnameone());
			cosdpersonconsent.setAuthpersonnametwo(cosdpersonconsentvo.getAuthpersonnametwo());
			cosdpersonconsent.setAuthpersonrelone(cosdpersonconsentvo.getAuthpersonrelone());
			cosdpersonconsent.setAuthpersonreltwo(cosdpersonconsentvo.getAuthpersonreltwo());
			cosdpersonconsent.setConcernroleid(cosdpersonconsentvo.getConcernroleid());
			cosdpersonconsent.setContactaddressid(cosdpersonconsentvo.getContactaddressid());
			cosdpersonconsent.setContactphone(cosdpersonconsentvo.getContactphone());
			cosdpersonconsent.setCreatedon(cosdpersonconsentvo.getCreatedon());
			cosdpersonconsent.setCreatedby(cosdpersonconsentvo.getCreatedby());
			cosdpersonconsent.setLastupdatedby(cosdpersonconsentvo.getLastupdatedby());
			cosdpersonconsent.setLastupdatedon(cosdpersonconsentvo.getLastupdatedon());
			cosdpersonconsent.setThirdpartyfirstname(cosdpersonconsentvo.getThirdpartyfirstname());
			cosdpersonconsent.setThirdpartylastname(cosdpersonconsentvo.getThirdpartylastname());
			cosdpersonconsent.setThirdpartyotherrelationship(cosdpersonconsentvo.getThirdpartyotherrelationship());
			cosdpersonconsent.setThirdpartyrelationship(cosdpersonconsentvo.getThirdpartyrelationship());
			cosdpersonconsent.setFileid(cosdpersonconsentvo.getFileid());
			cosdpersonconsent.setLastupdatedby(username);
			cosdpersonconsent.setCreatedby(username);
			cosdpersonconsent.setLastupdatedon(new Date());
			cosdpersonconsent.setCreatedon(new Date());
			cosdpersonconsent.setClientid(cosdpersonconsentvo.getClientid());
			String name = cosdpersonconsentvo.getCustomerName(); 
			System.out.println("name --->"+ cosdpersonconsentvo.getCustomerName());
			
			cosdpersonconsent.setCustomerName(cosdpersonconsentvo.getCustomerName().toUpperCase());
			cosdpersonconsent.setConsentCatGrp(cosdpersonconsentvo.getConsentUser());
			
			cosdpersonconsent.setConsentCatGrp(cosdpersonconsentvo.getConsentCatList());
			cosdpersonconsent.setCity(cosdpersonconsentvo.getCity());
			cosdpersonconsent.setStreet(cosdpersonconsentvo.getStreet());
			cosdpersonconsent.setAppSuite(cosdpersonconsentvo.getAppSuite());
			cosdpersonconsent.setZipCode(cosdpersonconsentvo.getZipCode());
			cosdpersonconsent.setState(cosdpersonconsentvo.getState());
			cosdpersonconsent.setHistroyRecord("NO");
			if(cosdpersonconsentvo.getStartdate() != null) 
			{
				Date startdate = formatter.parse(cosdpersonconsentvo.getStartdate());
				cosdpersonconsent.setStartdate(startdate);
			}
			if(cosdpersonconsentvo.getEnddate() != null)
			{
				Date enddate = formatter.parse(cosdpersonconsentvo.getEnddate());
				cosdpersonconsent.setEnddate(enddate);
			}
			
			cosdpersonconsent.setConsentCatGrp(cosdpersonconsentvo.getConsentCatList());
			cosdpersonconsent.setIdentificationno(cosdpersonconsentvo.getIdentificationno());
			cosdpersonconsent.setIdentificationtype(cosdpersonconsentvo.getIdentificationtype());
			cosdpersonconsent.setClientid(cosdpersonconsentvo.getClientid());
			
		    logger.info("before insert :: ClientID ::" + cosdpersonconsentvo.getClientid());
		    
		    PersonConsentDao cosdpersonconsentdao = new PersonConsentDao() ;
		  
		    String result = cosdpersonconsentdao.isDuplicate(cosdpersonconsent) ;
		    if(result.equalsIgnoreCase("duplicate"))
		    {
		    	logger.info("Duplicate Found");
		    	return new ResponseEntity(new Message("duplicate"), HttpStatus.OK);
		    }
		    
		    cosdpersonconsent = cosdpersonconsentdao.save(cosdpersonconsent) ;	    
		    
		    ConsentExceptionDao  cosdconsentexceptiondao = new ConsentExceptionDao();
		    
		    cosdpersonconsentvo.setPersonconsentid(cosdpersonconsent.getPersonconsentid());
		    cosdconsentexceptiondao.insertList(cosdpersonconsentvo , username) ;
		    
		    logger.info("before insert Client Reg");
		    //cosdpersonconsentdao.insertClientReg(cosdpersonconsent);
		    Converters conv = new Converters();
			//TransactionVO cosdabactransactionVo = conv.convertToVo(cosdpersonconsent);
		    
		    cosdpersonconsentdao.updateClientID(cosdpersonconsent ,name);
		    
		    return new ResponseEntity(cosdpersonconsentvo, HttpStatus.OK);
		   
		  
		}
		catch(Exception ex)
		{    System.out.println("::-----$$$$$ ::" + ex);
			ex.printStackTrace();
			return new ResponseEntity(new Message("Fail"), HttpStatus.OK);
		}		
	}
	
	@RequestMapping(value = "/listConsentPerson/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<List<PersonConsentVO>> listConsentPerson() 
	{	
		PersonConsentDao cosdpersonconsentdao = new PersonConsentDao();
		List<PersonConsent> cosdpersonconsentlist = cosdpersonconsentdao.getlist() ;
		
		PersonConsentVO cosdpersonconsentvo = new PersonConsentVO();
		ConsentConverter conv = new ConsentConverter();
							
		
		if (cosdpersonconsentlist.isEmpty()) {
					return new ResponseEntity(HttpStatus.OK);
					// You many decide to return HttpStatus.NOT_FOUND
		}
		List<PersonConsentVO> cosdconsentgroupVolist = conv.covertList(cosdpersonconsentlist, cosdpersonconsentvo);
		return new ResponseEntity<List<PersonConsentVO>>(cosdconsentgroupVolist, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/listConsentPersonByCriteria/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<List<PersonConsentVO>> listConsentPersonBySearch(@RequestBody PersonConsentVO cosdpersonconsentvo, HttpServletRequest request) 
	{	
		PersonConsentDao cosdpersonconsentdao = new PersonConsentDao();
		List<PersonConsent> cosdpersonconsentlist = cosdpersonconsentdao.getlistBySearch(cosdpersonconsentvo);
		
		//PersonConsentVO cosdpersonconsentvo = new PersonConsentVO();
		ConsentConverter conv = new ConsentConverter();
							
		
		if (cosdpersonconsentlist.isEmpty()) {
					return new ResponseEntity(HttpStatus.OK);
					// You many decide to return HttpStatus.NOT_FOUND
		}
		List<PersonConsentVO> cosdconsentgroupVolist = conv.covertList(cosdpersonconsentlist, cosdpersonconsentvo);
		return new ResponseEntity<List<PersonConsentVO>>(cosdconsentgroupVolist, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/editConsPerson/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<String> editConsPerson(@RequestBody PersonConsentVO cosdpersonconsentvo , HttpServletRequest request) 
	{	
		System.out.println("In Edit Method -Edit PersonConsentID(Histroy).." + cosdpersonconsentvo.getPersonconsentid());
		
		PersonConsent cosdpersonconsent = new PersonConsent();
		PersonConsentHistory cosdpersonconsenthistory = new PersonConsentHistory();
		PersonConsentDao cosdpersonconsentdao = new PersonConsentDao();
		PersonConsentHistoryDao cosdpersonconsenthistorydao = new PersonConsentHistoryDao();
		
		try
		{
			String username = HibernateUtil.getUserName(request); // HttpServletRequest request
			long oldpersonConsent = cosdpersonconsentvo.getPersonconsentid().longValue() ;
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy"); 
			cosdpersonconsent = cosdpersonconsentdao.getById(oldpersonConsent);
			
			cosdpersonconsent.setHistroyRecord("YES");
			cosdpersonconsentdao.update(cosdpersonconsent) ;
			
			cosdpersonconsenthistory.setPersonConsent(cosdpersonconsent);
			cosdpersonconsenthistory.setActiontype(cosdpersonconsentvo.getActionType());
			cosdpersonconsenthistory.setCreatedby(username);
			cosdpersonconsenthistory.setLastupdatedby(username);
			cosdpersonconsenthistory.setCreatedon(new Date());
			cosdpersonconsenthistory.setLastupdatedon(new Date());
			cosdpersonconsenthistory.setCancelreason(cosdpersonconsentvo.getReason());
			cosdpersonconsenthistorydao.save(cosdpersonconsenthistory);
			
			cosdpersonconsent.setClientid(cosdpersonconsent.getClientid());
			cosdpersonconsent.setAuthpersoncontactinfoone(cosdpersonconsentvo.getAuthpersoncontactinfoone());
			cosdpersonconsent.setAuthpersoncontactinfotwo(cosdpersonconsentvo.getAuthpersoncontactinfotwo());
			cosdpersonconsent.setAuthpersonnameone(cosdpersonconsentvo.getAuthpersonnameone());
			cosdpersonconsent.setAuthpersonnametwo(cosdpersonconsentvo.getAuthpersonnametwo());
			cosdpersonconsent.setAuthpersonrelone(cosdpersonconsentvo.getAuthpersonrelone());
			cosdpersonconsent.setAuthpersonreltwo(cosdpersonconsentvo.getAuthpersonreltwo());
			cosdpersonconsent.setConcernroleid(cosdpersonconsentvo.getConcernroleid());
			cosdpersonconsent.setContactaddressid(cosdpersonconsentvo.getContactaddressid());
			cosdpersonconsent.setContactphone(cosdpersonconsentvo.getContactphone());
			cosdpersonconsent.setCreatedon(cosdpersonconsentvo.getCreatedon());
			cosdpersonconsent.setCreatedby(cosdpersonconsentvo.getCreatedby());
			cosdpersonconsent.setLastupdatedby(cosdpersonconsentvo.getLastupdatedby());
			cosdpersonconsent.setLastupdatedon(cosdpersonconsentvo.getLastupdatedon());
			cosdpersonconsent.setThirdpartyfirstname(cosdpersonconsentvo.getThirdpartyfirstname());
			cosdpersonconsent.setThirdpartylastname(cosdpersonconsentvo.getThirdpartylastname());
			cosdpersonconsent.setThirdpartyotherrelationship(cosdpersonconsentvo.getThirdpartyotherrelationship());
			cosdpersonconsent.setThirdpartyrelationship(cosdpersonconsentvo.getThirdpartyrelationship());
			cosdpersonconsent.setFileid(cosdpersonconsentvo.getFileid());
			cosdpersonconsent.setLastupdatedby(username);
			cosdpersonconsent.setCreatedby(username);
			cosdpersonconsent.setLastupdatedon(new Date());
			cosdpersonconsent.setCreatedon(new Date());
			
			//cosdpersonconsent.setCustomerName(cosdpersonconsentvo.getCustomerName());
			cosdpersonconsent.setConsentCatGrp(cosdpersonconsentvo.getConsentUser());
			
			cosdpersonconsent.setConsentCatGrp(cosdpersonconsentvo.getConsentCatList());
			cosdpersonconsent.setCity(cosdpersonconsentvo.getCity());
			cosdpersonconsent.setStreet(cosdpersonconsentvo.getStreet());
			cosdpersonconsent.setAppSuite(cosdpersonconsentvo.getAppSuite());
			cosdpersonconsent.setZipCode(cosdpersonconsentvo.getZipCode());
			cosdpersonconsent.setState(cosdpersonconsentvo.getState());
			
			cosdpersonconsent.setIdentificationno(cosdpersonconsentvo.getIdentificationno());
			cosdpersonconsent.setIdentificationtype(cosdpersonconsentvo.getIdentificationtype());
			
			
			if(cosdpersonconsentvo.getStartdate() != null) 
			{
				System.out.println("Edit:: Start Date::" + cosdpersonconsentvo.getStartdate());
				Date startdate = formatter.parse(cosdpersonconsentvo.getStartdate());
				cosdpersonconsent.setStartdate(startdate);
			}
			if(cosdpersonconsentvo.getEnddate() != null)
			{
				System.out.println("Edit:: End Date::" + cosdpersonconsentvo.getEnddate());
				Date enddate = formatter.parse(cosdpersonconsentvo.getEnddate());
				cosdpersonconsent.setEnddate(enddate);
			}
			
			cosdpersonconsent.setConsentCatGrp(cosdpersonconsentvo.getConsentCatList());
			
			
		    logger.info("before insert");
		    
		   // PersonConsentDao cosdpersonconsentdao = new PersonConsentDao() ;
		     cosdpersonconsent.setHistroyRecord("NO");
		     cosdpersonconsentdao.save(cosdpersonconsent) ;
		     cosdpersonconsentvo.setPersonconsentid(cosdpersonconsent.getPersonconsentid());
		     
		     cosdpersonconsentdao.updateClientReg(cosdpersonconsent ,oldpersonConsent);
		    ConsentExceptionDao  cosdconsentexceptiondao = new ConsentExceptionDao();
		    
		    cosdconsentexceptiondao.deleteByPersonConsent(cosdpersonconsentvo.getPersonconsentid().longValue());
		    
		    logger.info("After delete old Exception ..");		    
		    cosdpersonconsentvo.setPersonconsentid(cosdpersonconsent.getPersonconsentid());
		    cosdconsentexceptiondao.insertList(cosdpersonconsentvo , username) ;
		    
		    //logger.info("before insert Client Reg");
		    //cosdpersonconsentdao.insertClientReg(cosdpersonconsent);
		    Converters conv = new Converters();
			//TransactionVO cosdabactransactionVo = conv.convertToVo(cosdpersonconsent);
		    System.out.println("In Edit Method -Creted New PersonConsentID.." + cosdpersonconsent.getPersonconsentid());
		    return new ResponseEntity(cosdpersonconsentvo, HttpStatus.OK);
		   
		  
		}
		catch(Exception ex)
		{    System.out.println("::-----$$$$$ ::" + ex);
			ex.printStackTrace();
			return new ResponseEntity(new Message("Fail"), HttpStatus.OK);
		}	
		
	}
	
	
	@RequestMapping(value = "/delConPerson/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<?> delConPerson(@RequestBody PersonConsentVO cosdpersonconsentvo) 
	{	
		PersonConsentDao cosdpersonconsentdao = new PersonConsentDao();
		PersonConsent cosdpersonconsent = cosdpersonconsentdao.getById(cosdpersonconsentvo.getPersonconsentid().longValue()) ;
		
		if (cosdpersonconsent == null) {
			return new ResponseEntity(new Message("Not Found") ,HttpStatus.OK);
			// You many decide to return HttpStatus.NOT_FOUND
          }
		
		String result = cosdpersonconsentdao.delete(cosdpersonconsentvo.getPersonconsentid().longValue());
	
		
		
		//List<ConsentGroupVO> cosdconsentgroupVolist = conv.covertList(cosdconsentgrouplist, cosdabacconstraintvo);
		return new ResponseEntity(new Message(result), HttpStatus.OK);
	}
	
	@PostMapping("/uploadFile")
	//@PostMapping("/uploadfile")
	public ResponseEntity<?> uploadFile(
            @RequestParam("file") MultipartFile uploadfile) {

		String unixpath= "/" ;
		String windowspath= "\\";
		String pathSep = unixpath ;
        logger.debug("Single file upload!");
        int id = 0 ;
       
        if (uploadfile.isEmpty()) {
            return new ResponseEntity("please select a file!", HttpStatus.OK);
        }

        try {
        	
        	String pattern = "dd-MM-yyyy" ;
        	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        	String date = simpleDateFormat.format(new Date());
        	System.out.println(date);
        	
        	File file = new File("Upload");
        	
        	double byets = file.length() ;
            double mb =  byets/(1024*1024);
            System.out.println(" File Size ::" + mb);
            
            if(mb > 1)
            {
            	 return new ResponseEntity("File Size is more than 1 MB .", HttpStatus.BAD_REQUEST);
            }
        	if (!file.exists()) {
                if (file.mkdir()) {
                    System.out.println("Directory is created!");
                } else {
                    System.out.println("Failed to create directory!");
                }
            }

            String dateFolder =  "Upload" + pathSep + date  ;
            
            File file1 = new File(dateFolder);
            if (!file1.exists()) {
                if (file1.mkdir()) {
                    System.out.println("Directory inside is created!");
                } else {
                    System.out.println("Failed inside to create directory!");
                }
            }
            
            
            
        	/*File convFile = new File( uploadfile.getOriginalFilename());
            uploadfile.transferTo(convFile);*/
            PersonConsentDao cosdpersonconsentdao = new PersonConsentDao();
            id = cosdpersonconsentdao.getFileID() + 1;
            String fpath = dateFolder + pathSep +id+"__" +uploadfile.getOriginalFilename() ;
            File convFile = new File(fpath);
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(uploadfile.getBytes());
            fos.close();
            
            cosdpersonconsentdao.FileUpload(convFile, fpath);
            //cosdpersonconsentdao.saveFromVO(cosdpersonconsentvo);
         

        }catch (IOException ex) {
        	System.out.println("IOException ::" + ex);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
        	System.out.println("Exception ::" + e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(new Integer(id), new HttpHeaders(), HttpStatus.OK);

    } // http://www.technicalkeeda.com/spring-tutorials/spring-4-mvc-download-file-from-server
	
	
	
	//@RequestMapping(value = "/pdf/{fileName:.+}", method = RequestMethod.GET, produces = "application/pdf")
	@RequestMapping(value = "/download/{id}", method = RequestMethod.GET, produces = "application/pdf")
	 public ResponseEntity<InputStreamResource> download(@PathVariable("id") String id) throws IOException {
	  String fileName = "poly.pdf" ;
	  //ClassPathResource pdfFile = new ClassPathResource("poly.pdf");
	  //Upload\13-11-2017\Consent_UseCases_20170908.pdf
	  PersonConsentDao cosdpersonconsentdao = new PersonConsentDao();    	
  	//String FilePath = cosdpersonconsentdao.FileDown(Integer.parseInt(id));
  	String FilePath = cosdpersonconsentdao.FileDown(Integer.parseInt(id));
  	System.out.println(" 1 File PAth - " + FilePath);
  	//FilePath = FilePath.replace('/', '\\');
  	System.out.println(" 2 File PAth - " + FilePath);
	  ClassPathResource pdfFile = new ClassPathResource(FilePath);
	  System.out.println("::1 ::" + pdfFile.getPath() + ":: ::" );
	  pdfFile.createRelative("C:\\Connect360");
	  System.out.println("::2 ::" + pdfFile.getDescription() + ":: ::" );
	 
	  
	  HttpHeaders headers = new HttpHeaders();
	  headers.setContentType(MediaType.parseMediaType("application/pdf"));
	  headers.add("Access-Control-Allow-Origin", "*");
	  headers.add("Access-Control-Allow-Methods", "GET, POST, PUT");
	  headers.add("Access-Control-Allow-Headers", "Content-Type");
	  headers.add("Content-Disposition", "filename=" + fileName);
	  headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
	  headers.add("Pragma", "no-cache");
	  headers.add("Expires", "0");
	  headers.setContentLength(pdfFile.contentLength());
	  InputStreamResource fileInputStream = null;
	  headers.setContentLength(pdfFile.contentLength());
	  ResponseEntity<InputStreamResource> response = new ResponseEntity<InputStreamResource>(
			    new InputStreamResource(pdfFile.getInputStream()), headers, HttpStatus.OK);
			  return response;
	  

	 }
	
    @RequestMapping(value = "/getdwn/{id}/", method = RequestMethod.GET)
    public StreamingResponseBody getSteamingFile(HttpServletResponse response ,@PathVariable("id") String id) throws IOException {
        // @PathVariable("id") String id
    	PersonConsentDao cosdpersonconsentdao = new PersonConsentDao();    	
    	//String FilePath = cosdpersonconsentdao.FileDown(Integer.parseInt(id));
    	String FilePath = cosdpersonconsentdao.FileDown(Integer.parseInt(id));
    	
    	System.out.println("FilePath -" + FilePath);
    		response.setContentType("application/zip");
    		response.setHeader("Content-Disposition", "attachment; filename=\"demo.pdf\"");
    		InputStream inputStream = new FileInputStream(new File(FilePath)); // Upload\13-11-2017\Consent_UseCases_20170908.pdf
    		return outputStream -> {
            int nRead;
            byte[] data = new byte[1024];
            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                System.out.println("Writing some bytes..");
                outputStream.write(data, 0, nRead);
            }
    		};
    	
    }
    
    @RequestMapping(value = "/saveConStatus/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<ConsentExceptionVO> saveConStatus(@RequestBody ConsentExceptionVO cosdconsentgroupvo,HttpServletRequest request) 
	{	
    	String username = HibernateUtil.getUserName(request); // HttpServletRequest request
    	ConsentException cosdconsentexception = new ConsentException();
    	ConsentExceptionDao cosdconsentexceptiondao = new ConsentExceptionDao();
    	PersonConsentDao cosdpersonconsentdao = new PersonConsentDao();
    	ConsentGroupDao cosdconsentgroupdao = new ConsentGroupDao() ;
    	
    	PersonConsent cosdpersonconsent = cosdpersonconsentdao.getById(cosdconsentgroupvo.getPersonConsent());    	
    	ConsentGroup cosdconsentgroup = cosdconsentgroupdao.getById(cosdconsentgroupvo.getConsentGroup());
    	
    	cosdconsentexception.setConsentGroup(cosdconsentgroup);
    	cosdconsentexception.setPersonConsent(cosdpersonconsent);
    	cosdconsentexception.setConsentstatus(cosdconsentgroupvo.getConsentstatus());
    	
    	cosdconsentexception.setCreatedby(username);
    	cosdconsentexception.setCreatedon(new Date());
    	//cosdconsentexception.setLastupdatedby(username);
    	//cosdconsentexception.setLastupdatedo(new Date());
    	cosdconsentexceptiondao.save(cosdconsentexception);
		//List<ConsentGroupVO> cosdconsentgroupVolist = conv.covertList(cosdconsentgrouplist, cosdabacconstraintvo);
		return new ResponseEntity<ConsentExceptionVO>(cosdconsentgroupvo, HttpStatus.OK);
	}
    
    @RequestMapping(value = "/listConsentStatus/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<List<ConsentExceptionVO>> listConsentStatus() 
	{	
    	ConsentExceptionDao cosdconsentexceptiondao = new ConsentExceptionDao();
		List<ConsentException> cosdconsentexceptionlist = cosdconsentexceptiondao.getlist() ;
		
		ConsentExceptionVO cosdconsentexceptionvo = new ConsentExceptionVO();
		ConsentConverter conv = new ConsentConverter();
							
		
		if (cosdconsentexceptionlist.isEmpty()) {
					return new ResponseEntity(HttpStatus.OK);
					// You many decide to return HttpStatus.NOT_FOUND
		}
		File file = new File("");
		new ResponseEntity(file ,HttpStatus.OK);
		List<ConsentExceptionVO> cosdconsentexceptionVolist = conv.covertList(cosdconsentexceptionlist, cosdconsentexceptionvo);
		return new ResponseEntity<List<ConsentExceptionVO>>(cosdconsentexceptionVolist, HttpStatus.OK);
	}
    
    @RequestMapping(value = "/getFile/{id}", method = RequestMethod.GET, produces = "application/pdf")
	public ResponseEntity<InputStreamResource> getFile(@PathVariable("id") String id) {
    	try
    	{
    	  PersonConsentDao cosdpersonconsentdao = new PersonConsentDao();    	
    	  	//String FilePath = cosdpersonconsentdao.FileDown(Integer.parseInt(id));
    	  	String FilePath = cosdpersonconsentdao.FileDown(Integer.parseInt(id));
    	  System.out.println("File Path -" + FilePath);

		File file = new File(FilePath);
		
		InputStream stream = new FileInputStream(file);
		
		System.out.println(" Stream -> " + stream.toString());
		System.out.println(" File Length -> " + file.length());
		
		InputStreamResource fileInputStream = new InputStreamResource(stream); 
	    
	    
	    System.out.println(" File Length -> " + fileInputStream.getFilename());
		 HttpHeaders headers = new HttpHeaders();
		  headers.setContentType(MediaType.parseMediaType("application/pdf"));
		  headers.add("Access-Control-Allow-Origin", "*");
		  headers.add("Access-Control-Allow-Methods", "GET, POST, PUT");
		  headers.add("Access-Control-Allow-Headers", "Content-Type");
		  headers.add("Content-Disposition", "filename=" + fileInputStream.getFilename());
		  headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		  headers.add("Pragma", "no-cache");
		  headers.add("Expires", "0");
		  headers.setContentLength(file.length());
		new ResponseEntity(fileInputStream ,headers, HttpStatus.OK);
		
    	}
    	catch(Exception ex)
    	{
    		System.out.println("Fail in getFile Service ..");
    		new ResponseEntity("Fail" ,HttpStatus.OK);
    	}
    	return new ResponseEntity(HttpStatus.OK);

	}
    
    @RequestMapping(value = "/getConsForm/{id}", method = RequestMethod.GET, produces = "application/pdf")
    public ResponseEntity<InputStreamResource> download2(@PathVariable("id") String id) throws IOException {
     //System.out.println("Calling Download:- " + fileName);
     //ClassPathResource pdfFile = new ClassPathResource("dwnload/" + "Cosdie_ClassDiagram1.pdf");
     
     PersonConsentDao cosdpersonconsentdao = new PersonConsentDao();    	
	  	//String FilePath = cosdpersonconsentdao.FileDown(Integer.parseInt(id));
	  	String FilePath = cosdpersonconsentdao.FileDown(Integer.parseInt(id));
	  System.out.println("File Path -" + FilePath);
	  
	  if( FilePath == null || FilePath.trim().equals(""))
		  new ResponseEntity("File Not Found" ,HttpStatus.OK);

	File file = new File(FilePath);
	
	if ( file == null || file.exists() == false )
		 new ResponseEntity("File Not Found" ,HttpStatus.OK);
	
	InputStream stream = new FileInputStream(file);
	
	System.out.println(" Stream -> " + stream.toString());
	System.out.println(" File Length -> " + file.length());
	
	InputStreamResource fileInputStream = new InputStreamResource(stream); 
	//fileInputStream.getInputStream()
	
     HttpHeaders headers = new HttpHeaders();
     headers.setContentType(MediaType.parseMediaType("application/pdf"));
     headers.add("Access-Control-Allow-Origin", "*");
     headers.add("Access-Control-Allow-Methods", "GET, POST, PUT");
     headers.add("Access-Control-Allow-Headers", "Content-Type");
     headers.add("Content-Disposition", "filename=ConsentForm.pdf");
     headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
     headers.add("Pragma", "no-cache");
     headers.add("Expires", "0");

     headers.setContentLength(file.length());
     ResponseEntity<InputStreamResource> response = new ResponseEntity<InputStreamResource>(
    		 new InputStreamResource(stream) , headers, HttpStatus.OK);
     return response;

    }
    
    @RequestMapping(value = "/getRefList/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<List<ReferralLAVO>> getRefnList() 
	{
		System.out.println(":: RefnList ::");		
		ReferralLADao refDao = new ReferralLADao();
		List<ReferralLA> referrallalist =  refDao.getlist();
		
		if(referrallalist.isEmpty())
		{
			//return new ResponseEntity(new Message("Referral List is Empty") , HttpStatus.OK) ;
		}
		
		Converters conv = new Converters();
		ReferralLAVO refla = new ReferralLAVO();
		
		List<ReferralLAVO> referrallavolist = conv.covertList(referrallalist, refla);
		System.out.println("::-- Referral LA --::" + referrallavolist.size());
			
			
	  return new ResponseEntity<List<ReferralLAVO>>(referrallavolist, HttpStatus.OK);
	}
}
