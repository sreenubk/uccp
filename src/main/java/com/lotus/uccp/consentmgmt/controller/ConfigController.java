package  com.lotus.uccp.consentmgmt.controller;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lotus.uccp.util.Message;
import com.lotus.uccp.util.WCMConverter;
import com.lotus.uccp.consentmgmt.wcm.dao.CohortDao;
import com.lotus.uccp.consentmgmt.wcm.dao.CohortRuleDao;
import com.lotus.uccp.consentmgmt.wcm.dao.CohortTargetDao;
import com.lotus.uccp.consentmgmt.wcm.dao.ReasonCodeDao;
import com.lotus.uccp.consentmgmt.wcm.dao.TargetDao;
import com.lotus.uccp.consentmgmt.wcm.dto.Cohort;
import com.lotus.uccp.consentmgmt.wcm.dto.CohortRule;
import com.lotus.uccp.consentmgmt.wcm.dto.CohortTarget;
import com.lotus.uccp.consentmgmt.wcm.dto.ReasonCode;
import com.lotus.uccp.consentmgmt.wcm.dto.Target;
import com.lotus.uccp.vo.ControlGroupVO;
import com.lotus.uccp.wcm.vo.CohortRuleVO;
import com.lotus.uccp.wcm.vo.CohortRulesVO;
import com.lotus.uccp.wcm.vo.CohortTargetVO;
import com.lotus.uccp.wcm.vo.CohortVO;
import com.lotus.uccp.wcm.vo.ReasonCodeVO;
import com.lotus.uccp.wcm.vo.TargetVO;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/ConfigController")
public class ConfigController {

	@RequestMapping(value = "/listAllCohort/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<List<CohortVO>> listAllCohort() 
	{
		System.out.println(":: listAllCohort()::");
    	CohortDao cohortdao = new CohortDao();
    	CohortVO cohortvo = new CohortVO();
		List<Cohort> cohortlist = cohortdao.getlist() ;
		
		WCMConverter conv = new WCMConverter();
		List<CohortVO> cohortvolist = conv.covertList(cohortlist, cohortvo);
		
		return new ResponseEntity<List<CohortVO>>(cohortvolist, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getCohortwithID/", method = RequestMethod.POST, produces = "application/json")			
	 public ResponseEntity<CohortVO> getCohortwithID(@RequestBody CohortVO cohortvo ,HttpServletRequest request) 
	 {		
		System.out.println(":: getCohortwithID ::" + cohortvo.getCohortid().longValue() );
		    HttpSession session = request.getSession(false) ;
		    CohortDao cohortdao = new CohortDao();
		    if(session == null)
		    {
		    	System.out.println("Session is null");
		    }
		    else 
		    {
		    	System.out.println("Session is Found");
		    }
		    
		    Cohort cohort = cohortdao.getById(cohortvo.getCohortid().longValue());
		    //Cohort cohort = cohortdao.getlistWithCohortID(cohortvo);
		    		
		    System.out.println(":: ::"+ cohort );
		    if(cohort == null)
		    {
		    	 System.out.println(":: it is null cohort::");
		    }
			
	    	WCMConverter conv = new WCMConverter();
		     CohortVO vogroup = conv.convertToVo(cohort) ;
			
				
				if (vogroup == null) {
					return new ResponseEntity(new Message("NO Group Found."), HttpStatus.OK); 			
						
				}
		return new ResponseEntity<CohortVO>(vogroup, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/listAllCohortWithName/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<?> listAllCohortWithName(@RequestBody CohortVO cohortvo,HttpServletRequest request) 
	{
		System.out.println(":: listAllCohortWithName()::" + cohortvo.getName());
    	CohortDao cohortdao = new CohortDao();
    	
    	if(cohortvo.getName() == null)
    		return new ResponseEntity<Message>(new Message("Duplicate"), HttpStatus.OK);
		String name = cohortvo.getName().toUpperCase() ;
    	
		List<Cohort> cohortlist = cohortdao.getlistwithName(name);
		
		WCMConverter conv = new WCMConverter();
		List<CohortVO> cohortvolist = conv.covertList(cohortlist, cohortvo);
		
		return new ResponseEntity<List<CohortVO>>(cohortvolist, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/saveCohort/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<CohortVO> saveCohort(@RequestBody CohortVO cohortvo,HttpServletRequest request) 
	{	
		CohortDao cohortdao = new CohortDao();
		Cohort cohort = new Cohort() ;
		cohort.setName(cohortvo.getName().toUpperCase());
		cohort.setCohortdescription(cohortvo.getCohortdescription());
		
			
		
		cohort = cohortdao.save(cohort);
		if(cohort == null)
		{
			return new ResponseEntity(new Message("Duplicate"), HttpStatus.OK);
		}
		
		WCMConverter conv = new WCMConverter();
		cohortvo = conv.convertToVo(cohort);	
		
		
		//List<CosdconsentgroupVO> cosdconsentgroupVolist = conv.covertList(cosdconsentgrouplist, cosdabacconstraintvo);
		return new ResponseEntity<CohortVO>(cohortvo, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/updateCohort/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<Message> updateCohort(@RequestBody CohortVO cohortvo,HttpServletRequest request) 
	{	
		CohortDao cohortdao = new CohortDao();
		Cohort cohort = new Cohort() ;
		cohort.setName(cohortvo.getName());
		cohort.setCohortdescription(cohortvo.getCohortdescription());
		cohort.setCohortid(cohortvo.getCohortid());
			
		
		String result = cohortdao.update(cohort);
		System.out.println("Update Cohort result::" + result);
		if(cohort == null)
		{
			return new ResponseEntity(new Message("Fail"), HttpStatus.OK);
		}
		
		WCMConverter conv = new WCMConverter();
		cohortvo = conv.convertToVo(cohort);	
		
		
		//List<CosdconsentgroupVO> cosdconsentgroupVolist = conv.covertList(cosdconsentgrouplist, cosdabacconstraintvo);
		return new ResponseEntity<Message>(new Message(result), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/deleteCohort/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<Message> deleteCohort(@RequestBody CohortVO cohortvo,HttpServletRequest request) 
	{	
		CohortDao cohortdao = new CohortDao();
		
		String result = cohortdao.delete(cohortvo.getCohortid().longValue());
		
			
		
		
		//List<CosdconsentgroupVO> cosdconsentgroupVolist = conv.covertList(cosdconsentgrouplist, cosdabacconstraintvo);
		return new ResponseEntity<Message>(new Message("Duplicate"), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/searchWithCohortID/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<CohortRulesVO> searchCohort(@RequestBody CohortVO cohortvo,HttpServletRequest request) 
	{	
		CohortRuleDao cohortdao = new CohortRuleDao();
		System.out.println(":: searchWithCohortID()::" + cohortvo.getCohortid().longValue() );
		CohortRulesVO cohortRules = cohortdao.searchCohortRule(cohortvo.getCohortid().longValue());
						
		
		//List<CosdconsentgroupVO> cosdcCohortRulesVOonsentgroupVolist = conv.covertList(cosdconsentgrouplist, cosdabacconstraintvo);
		return new ResponseEntity<CohortRulesVO>(cohortRules, HttpStatus.OK);
	}
	
	/* ======================================= CohortRule  ================================================ */
	
	@RequestMapping(value = "/listAllCohortRule/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<List<CohortRule>> listAllCohortRule() 
	{
		System.out.println(":: listAllCohortRule()::");
    	CohortRuleDao cohortRuledao = new CohortRuleDao();
    	
		List<CohortRule> cohortRulelist = cohortRuledao.getlist() ;
		
		
		return new ResponseEntity<List<CohortRule>>(cohortRulelist, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/saveCohortRule/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<CohortRuleVO> saveCohortRule(@RequestBody CohortRuleVO cohortrulevo,HttpServletRequest request) 
	{	
		CohortRuleDao cohortRuledao = new CohortRuleDao();
		WCMConverter wconv = new WCMConverter();
		CohortRule cohortrule = wconv.convertTo(cohortrulevo);
		cohortRuledao.save(cohortrule);
		cohortrulevo = wconv.convertToVo(cohortrule);
		return new ResponseEntity<CohortRuleVO>(cohortrulevo, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getRuleDetailswithID/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<CohortRuleVO> getRuleDetailswithID(@RequestBody CohortRuleVO cohortrulevo) 
	{
		
    	
    	System.out.println("-- :: getRuleDetailswithID():: Rule ID--::" + cohortrulevo.getRuleID() + ":: Cohortid ::" + cohortrulevo.getCohortid());
    	CohortRuleDao cohortRuledao = new CohortRuleDao();
    	CohortRule crule = new CohortRule();
    	crule = cohortRuledao.getById(cohortrulevo.getCohortid(),cohortrulevo.getRuleID());
    	WCMConverter wconv = new WCMConverter();
    	CohortRuleVO vo = wconv.convertToVo(crule);
		return new ResponseEntity<CohortRuleVO>(vo, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/updateCohortRule/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<Message> updateCohortRule(@RequestBody CohortRuleVO cohortrulevo,HttpServletRequest request) 
	{	
		CohortRuleDao cohortRuledao = new CohortRuleDao();
		WCMConverter wconv = new WCMConverter();
		CohortRule cohortrule = wconv.convertTo(cohortrulevo);
		String result = cohortRuledao.update(cohortrule);
		cohortrulevo = wconv.convertToVo(cohortrule);		
		
		return new ResponseEntity<Message>(new Message(result), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/deleteCohortRule/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<Message> deleteCohortRule(@RequestBody CohortRule cohortrule,HttpServletRequest request) 
	{	
		CohortRuleDao cohortRuledao = new CohortRuleDao();
		
		String result = cohortRuledao.delete(cohortrule.getCohortRuleID().longValue());
		
		return new ResponseEntity<Message>(new Message(result), HttpStatus.OK);
	}

	/* ======================================= Target  ================================================ */
	
	@RequestMapping(value = "/listAllTarget/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<List<TargetVO>> listAllTarget() 
	{
		System.out.println(":: listAllTarget()::");
		TargetDao targetdao = new TargetDao();
		TargetVO targetVo = new TargetVO();
		List<Target> targetList = targetdao.getlist() ;
		List<TargetVO> targetVOList = new ArrayList<TargetVO>();
		
		WCMConverter wconv = new WCMConverter();
		targetVOList = wconv.covertList(targetList, targetVo);
		return new ResponseEntity<List<TargetVO>>(targetVOList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getTarget/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<TargetVO> getTarget(@RequestBody Target target,HttpServletRequest request) 
	{	
		TargetDao targetdao = new TargetDao();
		Target targetsave = targetdao.getById(target.getTargetedID().longValue());
		TargetVO targetVo = new TargetVO();
		WCMConverter wconv = new WCMConverter();
		targetVo = wconv.convertToVo(targetsave);	
		return new ResponseEntity<TargetVO>(targetVo, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/saveTarget/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<TargetVO> saveTarget(@RequestBody Target target,HttpServletRequest request) 
	{	
		TargetDao targetdao = new TargetDao();
		Target targetsave = targetdao.save(target);
		TargetVO targetVo = new TargetVO();
		WCMConverter wconv = new WCMConverter();
		targetVo = wconv.convertToVo(targetsave);	
		return new ResponseEntity<TargetVO>(targetVo, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/updateTarget/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<Message> updateTarget(@RequestBody Target target,HttpServletRequest request) 
	{	
		TargetDao targetdao = new TargetDao();
					
		String result = targetdao.update(target);
		
		return new ResponseEntity<Message>(new Message(result), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/deleteTarget/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<Message> deleteTarget(@RequestBody Target target,HttpServletRequest request) 
	{	
		TargetDao targetDao = new TargetDao();
		
		String result = targetDao.delete(target.getTargetedID().longValue());
		
		return new ResponseEntity<Message>(new Message(result), HttpStatus.OK);
	}
	
  /*======================================= Cohort Target  =============================================== */
	
	@RequestMapping(value = "/listCohortTarget/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<List<CohortTargetVO>> listCohortTarget() 
	{
		System.out.println(":: listCohortTarget()::");
		CohortTargetDao cohorttargetdao = new CohortTargetDao();
		CohortTargetVO targetVo = new CohortTargetVO();
		List<CohortTarget> targetList = cohorttargetdao.getlist() ;
		
		List<CohortTargetVO> targetVOList = new ArrayList<CohortTargetVO>();
		
		WCMConverter wconv = new WCMConverter();
		targetVOList = wconv.covertList(targetList, targetVo);
		return new ResponseEntity<List<CohortTargetVO>>(targetVOList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getlistTarget/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<List<CohortTargetVO>> getlistTarget(@RequestBody TargetVO targetvo) 
	{
		System.out.println(":: getlistTarget()::");
		CohortTargetDao cohorttargetdao = new CohortTargetDao();
		CohortTargetVO targetVo = new CohortTargetVO();
		List<CohortTarget> targetList = cohorttargetdao.getlistTarget(targetvo.getTargetedID().longValue());
		
		List<CohortTargetVO> targetVOList = new ArrayList<CohortTargetVO>();
		
		WCMConverter wconv = new WCMConverter();
		targetVOList = wconv.covertList(targetList, targetVo);
		return new ResponseEntity<List<CohortTargetVO>>(targetVOList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/listCohortTargetWithTargetID/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<List<CohortTargetVO>> listCohortTargetWithTargetID() 
	{
		System.out.println(":: listCohortTargetWithTargetID()::");
		CohortTargetDao cohorttargetdao = new CohortTargetDao();
		CohortTargetVO targetVo = new CohortTargetVO();
		List<CohortTarget> targetList = cohorttargetdao.getlist() ;
		
		List<CohortTargetVO> targetVOList = new ArrayList<CohortTargetVO>();
		
		WCMConverter wconv = new WCMConverter();
		targetVOList = wconv.covertList(targetList, targetVo);
		return new ResponseEntity<List<CohortTargetVO>>(targetVOList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/saveCohortTarget/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<CohortTargetVO> saveCohortTarget(@RequestBody CohortTargetVO cohtargetvo,HttpServletRequest request) 
	{	
		CohortTargetDao cohotdao = new CohortTargetDao();
		CohortTarget cohorttarget = new CohortTarget();
		Target t1 = new Target();
		TargetDao targetDao = new TargetDao();
		CohortDao cohortDao = new CohortDao();
		
		t1 = targetDao.getById(cohtargetvo.getTargetedID().longValue());
		Cohort cohort = cohortDao.getById(cohtargetvo.getCohortid().longValue());
		cohorttarget.setCohortid(cohort);
		cohorttarget.setTargetedID(t1);
		cohorttarget.setName(cohtargetvo.getName().toUpperCase());
		
		cohorttarget = cohotdao.save(cohorttarget);
		
		WCMConverter wconv = new WCMConverter();
		cohtargetvo = wconv.convertToVo(cohorttarget);	
		return new ResponseEntity<CohortTargetVO>(cohtargetvo, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/updateCohortTarget/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<Message> updateCohortTarget(@RequestBody CohortTargetVO cohtargetvo,HttpServletRequest request) 
	{	
		System.out.println(":: cohort ID -" + cohtargetvo.getCohortid().longValue());
		System.out.println(":: Target ID -" + cohtargetvo.getTargetedID().longValue());
		
		CohortTargetDao cohotdao = new CohortTargetDao();
		CohortTarget cohorttarget = new CohortTarget();
		Target t1 = new Target();
		TargetDao targetDao = new TargetDao();
		CohortDao cohortDao = new CohortDao();
		System.out.println("1");
		t1 = targetDao.getById(cohtargetvo.getTargetedID().longValue());
		Cohort cohort = cohortDao.getById(cohtargetvo.getCohortid().longValue());
		cohorttarget.setCohortid(cohort);
		cohorttarget.setTargetedID(t1);
		cohorttarget.setName(cohtargetvo.getName().toUpperCase());
		cohorttarget.setTargetcohortid(cohtargetvo.getTargetcohortid());
		System.out.println("1.1");
		String result = cohotdao.update(cohorttarget);
		
		WCMConverter wconv = new WCMConverter();
		cohtargetvo = wconv.convertToVo(cohorttarget);	
		return new ResponseEntity<Message>(new Message(result), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/deleteCohortTarget/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<Message> deleteCohortTarget(@RequestBody CohortTargetVO cohtargetvo,HttpServletRequest request) 
	{	
		CohortTargetDao cohorttargetdao = new CohortTargetDao();
		
		String result = cohorttargetdao.delete(cohtargetvo.getTargetcohortid().longValue());
		
		return new ResponseEntity<Message>(new Message(result), HttpStatus.OK);
	}
	/* ------------- */
	@RequestMapping(value = "/listReasonCode/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<List<ReasonCodeVO>> listReasonCode() 
	{
		System.out.println(":: listReasonCode()::");
		ReasonCodeVO reasonvo = new ReasonCodeVO();
		ReasonCode reasonCode = new ReasonCode();
		ReasonCodeDao dao = new ReasonCodeDao();
		List<ReasonCode> ReasonCodeList = dao.getlist();
		WCMConverter wconv = new WCMConverter();
		List<ReasonCodeVO> ReasonCodeVoList = wconv.covertList(ReasonCodeList, reasonvo);
		return new ResponseEntity<List<ReasonCodeVO>>(ReasonCodeVoList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getReasonCode/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<ReasonCodeVO> getReasonCode(@RequestBody ReasonCodeVO ReasonCodeVO,HttpServletRequest request) 
	{	
		
		ReasonCode reasonCode = new ReasonCode();
		ReasonCodeDao dao = new ReasonCodeDao();
		WCMConverter wconv = new WCMConverter();
		reasonCode= wconv.convertTo(ReasonCodeVO);
		reasonCode = dao.getById(ReasonCodeVO.getReasonCode());
		ReasonCodeVO = wconv.convertToVo(reasonCode);
		return new ResponseEntity<ReasonCodeVO>(ReasonCodeVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/saveReasonCode/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<ReasonCodeVO> saveReasonCode(@RequestBody ReasonCodeVO ReasonCodeVO,HttpServletRequest request) 
	{	
		ReasonCodeVO reasonvo = new ReasonCodeVO();
		ReasonCode reasonCode = new ReasonCode();
		ReasonCodeDao dao = new ReasonCodeDao();
		WCMConverter wconv = new WCMConverter();
		reasonCode= wconv.convertTo(ReasonCodeVO);
		dao.save(reasonCode);
		return new ResponseEntity<ReasonCodeVO>(ReasonCodeVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/updateReasonCode/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<Message> updateReasonCode(@RequestBody ReasonCodeVO ReasonCodeVO,HttpServletRequest request) 
	{	
		ReasonCodeVO reasonvo = new ReasonCodeVO();
		ReasonCode reasonCode = new ReasonCode();
		ReasonCodeDao dao = new ReasonCodeDao();
		WCMConverter wconv = new WCMConverter();
		reasonCode= wconv.convertTo(ReasonCodeVO);
		String result = dao.update(reasonCode);
		return new ResponseEntity<Message>(new Message(result), HttpStatus.OK);
	}
	@RequestMapping(value = "/deleteReasonCode/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<Message> deleteReasonCode(@RequestBody ReasonCodeVO ReasonCodeVO,HttpServletRequest request) 
	{	
		ReasonCodeVO reasonvo = new ReasonCodeVO();
		ReasonCode reasonCode = new ReasonCode();
		ReasonCodeDao dao = new ReasonCodeDao();
		WCMConverter wconv = new WCMConverter(); //bea42
		reasonCode= wconv.convertTo(ReasonCodeVO);
		String result = dao.delete(ReasonCodeVO.getReasonCode());//(reasonCode);
		return new ResponseEntity<Message>(new Message(result), HttpStatus.OK);
	}
}
