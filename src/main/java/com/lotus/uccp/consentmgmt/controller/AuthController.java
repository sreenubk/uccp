package  com.lotus.uccp.consentmgmt.controller;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lotus.uccp.util.HibernateUtil;
import com.lotus.uccp.util.Message;
import com.lotus.uccp.consentmgmt.dao.ControlGroupDao;
import com.lotus.uccp.consentmgmt.dao.OrganizationUnitDao;
import com.lotus.uccp.consentmgmt.dao.SecurityroleDao;
import com.lotus.uccp.consentmgmt.dao.AuthorizationGroupDao;
import com.lotus.uccp.consentmgmt.dao.AuthorizationPolicyDao;
import com.lotus.uccp.consentmgmt.dao.AuthorizationPolicySetDao;
import com.lotus.uccp.consentmgmt.dao.UsersDao;
import com.lotus.uccp.authentication.model.AuthorizationGroup;
import com.lotus.uccp.authentication.model.AuthorizationPolicySet;
import com.lotus.uccp.authentication.model.ControlGroup;
import com.lotus.uccp.authentication.model.OrgRoleJobUser;
import com.lotus.uccp.authentication.model.OrganizationUnit;
import com.lotus.uccp.authentication.model.SecurityRole;
import com.lotus.uccp.authentication.model.Users;
import com.lotus.uccp.consentmgmt.service.AuthServices;
import com.lotus.uccp.util.Converters;
import com.lotus.uccp.vo.AuthGroupPolicyVO;
import com.lotus.uccp.vo.AuthorizationGroupVO;
import com.lotus.uccp.vo.AuthorizationPolicySetVO;
import com.lotus.uccp.vo.OrgRoleJobUserVO;
import com.lotus.uccp.vo.OrganizationUnitVO;
import com.lotus.uccp.vo.SecurityroleVO;
import com.lotus.uccp.vo.UsersVO;
import com.lotus.uccp.vo.ViewPolicyAuthGroup;

// /auth/getListOrg/
// /auth/getJobList/

@RestController
@RequestMapping("/auth")
public class AuthController {
	
public static final Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	@Autowired	
	AuthServices authServices ;

	@RequestMapping(value = "/getListOrg/", method = RequestMethod.GET, produces = "application/json")			
	public ResponseEntity<List<OrganizationUnitVO>> getOrgList() 
	{
		System.out.println(":: Organisation List ::");
		logger.info(":: Orgainization List ::");
		OrganizationUnitVO organisationunitvo = new OrganizationUnitVO();
		List<com.lotus.uccp.authentication.model.OrganizationUnit> organisationunitlist = authServices.getOrgList() ;
		
		if(organisationunitlist.isEmpty())
		{
			return new ResponseEntity(new Message("OrganizationUnit is Empty") , HttpStatus.OK) ;
		}
		
		Converters conv = new Converters();
		
		List<OrganizationUnitVO> organisationunitvolist = conv.covertList(organisationunitlist, organisationunitvo);
		System.out.println("::-- ControlUnit --::" + organisationunitvolist.size());
			
			
	  return new ResponseEntity<List<OrganizationUnitVO>>(organisationunitvolist, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getJobList/", method = RequestMethod.GET, produces = "application/json")			
	public ResponseEntity<List<OrgRoleJobUserVO>> getJobList() 
	{
		System.out.println(":: Organisation List ::");
		OrgRoleJobUserVO cosdorgrolejobuservo = new OrgRoleJobUserVO();
		List<OrgRoleJobUser> cosdorgrolejobuserlist = authServices.getlist() ;
		
		if(cosdorgrolejobuserlist.isEmpty())
		{
			return new ResponseEntity(new Message("Job List empty") , HttpStatus.OK) ;
		}
		
		Converters conv = new Converters();
		
		List<OrgRoleJobUserVO> cosdorgrolejobuservolist = conv.covertList(cosdorgrolejobuserlist, cosdorgrolejobuservo);
		System.out.println("::-- ControlUnit --::" + cosdorgrolejobuservolist.size());
			
			
	 
		return new ResponseEntity<List<OrgRoleJobUserVO>>(cosdorgrolejobuservolist, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getUserList/", method = RequestMethod.GET, produces = "application/json")			
	public ResponseEntity<List<UsersVO>> getUserList() 
	{
		System.out.println(":: Organisation List ::");
		UsersVO usersvo = new UsersVO();
		List<Users> userslist = authServices.getUserList() ;
		
		if(userslist.isEmpty())
		{
			return new ResponseEntity(new Message("User List empty") , HttpStatus.OK) ;
		}
		
		Converters conv = new Converters();
		
		List<UsersVO> usersvolist = conv.covertList(userslist, usersvo);
		System.out.println("::-- ControlUnit --::" + usersvolist.size());
			
			
	 
		return new ResponseEntity<List<UsersVO>>(usersvolist, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getSRoleList/", method = RequestMethod.GET, produces = "application/json")			
	public ResponseEntity<List<SecurityroleVO>> getSRoleList() 
	{
		System.out.println(":: Organisation List ::");
		SecurityroleVO securityrolevo = new SecurityroleVO();
		List<SecurityRole> secRoleList = authServices.getSecRoleList();
		
		if(secRoleList.isEmpty())
		{
			return new ResponseEntity(new Message("Security Role List empty") , HttpStatus.OK) ;
		}
		
		Converters conv = new Converters();
		
		List<SecurityroleVO> usersvolist = conv.covertList(secRoleList, securityrolevo);
		System.out.println("::-- ControlUnit --::" + usersvolist.size());
			
			 
		return new ResponseEntity<List<SecurityroleVO>>(usersvolist, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getListAuthGroup/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<List<AuthorizationGroupVO>> ListAuthGroup() 
	{				
		List<AuthorizationGroup> cosdauthorizationgrouplist = authServices.getListAuthGroup() ;
		
		AuthorizationGroupVO cosdauthorizationgroupvo = new AuthorizationGroupVO();
		Converters conv = new Converters();
							
		
		if (cosdauthorizationgrouplist.isEmpty()) {
					return new ResponseEntity(HttpStatus.OK);
					// You many decide to return HttpStatus.NOT_FOUND
		}
		
		List<AuthorizationGroupVO> cosdabacconstraintVoList = conv.covertList(cosdauthorizationgrouplist, cosdauthorizationgroupvo);
		return new ResponseEntity<List<AuthorizationGroupVO>>(cosdabacconstraintVoList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/searchAuthGroup/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<List<AuthorizationGroupVO>> searchAuthGroup(@RequestBody AuthorizationGroupVO cosdauthorizationgroupvo) 
	{				
		//List<AuthorizationGroup> cosdauthorizationgrouplist = authServices.getListAuthGroup() ;
		AuthorizationGroupDao cosdauthorizationgroupdao = new AuthorizationGroupDao();
		List<AuthorizationGroup> cosdauthorizationgrouplist = cosdauthorizationgroupdao.search(cosdauthorizationgroupvo);
		//List<AuthorizationGroup> cosdauthorizationgrouplist = cosdauthorizationgroupdao.searchBeforeSave(cosdauthorizationgroupvo);
		
		//AuthorizationGroupVO cosdauthorizationgroupvo = new AuthorizationGroupVO();
		Converters conv = new Converters();
							
		
		if (cosdauthorizationgrouplist.isEmpty()) {
					return new ResponseEntity(HttpStatus.OK);
					// You many decide to return HttpStatus.NOT_FOUND 
		}
		
		List<AuthorizationGroupVO> cosdabacconstraintVoList = conv.covertList(cosdauthorizationgrouplist, cosdauthorizationgroupvo);
		return new ResponseEntity<List<AuthorizationGroupVO>>(cosdabacconstraintVoList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/updateAuthGroup/", method = RequestMethod.POST)
	public ResponseEntity<?> updateAuthGroup(@RequestBody AuthorizationGroupVO cosdauthorizationgroupvo, HttpServletRequest request) 
	{
		long cntGrp = cosdauthorizationgroupvo.getControlGroup();
		long orgUnit = cosdauthorizationgroupvo.getOrganizationUnit() ;
		String secRole =  cosdauthorizationgroupvo.getSecurityRole();
		String user = cosdauthorizationgroupvo.getUsers() ;
		System.out.println("**updateAuthGroup Method - " + cntGrp +":org unit -:" + orgUnit +":secRole:" + secRole +":User:" + user );
		System.out.println("Auth ID For Update : " + cosdauthorizationgroupvo.getAuthorizationGroupId().longValue());
		AuthorizationGroupDao cosdauthorizationgroupdao = new AuthorizationGroupDao();
		AuthorizationGroup cosdauthorizationgroup = cosdauthorizationgroupdao.getById(cosdauthorizationgroupvo.getAuthorizationGroupId().longValue());
		
		if(cosdauthorizationgroup == null )
		{
			return new ResponseEntity(new Message("Auth Group ID not found  -" + cosdauthorizationgroupvo.getAuthorizationGroupId().longValue()), HttpStatus.OK);
		}
		
		ControlGroupDao cosdabaccontrolgroupdao = new ControlGroupDao();
		ControlGroup cosdabaccontrolgroup = cosdabaccontrolgroupdao.getById(cntGrp);
		
		String username = HibernateUtil.getUserName(request); // HttpServletRequest request
		OrganizationUnitDao organisationunitdao = new OrganizationUnitDao();
		com.lotus.uccp.authentication.model.OrganizationUnit organisationunit = organisationunitdao.getById(orgUnit);
		
		
		SecurityroleDao securityroledao = new SecurityroleDao();
		SecurityRole securityrole = securityroledao.getById(secRole);
		
		
		UsersDao usersdao = new UsersDao();
		Users users = null ;
		if(user != null)
		 users = usersdao.getById(user);
				
		cosdauthorizationgroup.setControlGroup(cosdabaccontrolgroup);
		cosdauthorizationgroup.setOrganizationUnit(organisationunit);
		cosdauthorizationgroup.setSecurityRole(securityrole);
		cosdauthorizationgroup.setUsers(users);
		cosdauthorizationgroup.setJobName(cosdauthorizationgroupvo.getJobName());
		//cosdauthorizationgroup.setAuthGroupName(cosdauthorizationgroupvo.getAuthGroupName());
		cosdauthorizationgroup.setLastupdatedby(username);
		cosdauthorizationgroup.setCreatedby(username);
		cosdauthorizationgroup.setCreatedon(new Date());
		cosdauthorizationgroup.setLastupdatedon(new Date());
		
		cosdauthorizationgroupdao.update(cosdauthorizationgroup);
		Converters conv = new Converters();
		cosdauthorizationgroupvo = conv.convertToVo(cosdauthorizationgroup) ;
		return new ResponseEntity<AuthorizationGroupVO>(cosdauthorizationgroupvo, HttpStatus.OK);

	}
	
	
	@RequestMapping(value = "/saveAuthGroup/", method = RequestMethod.POST)
	public ResponseEntity<?> saveAuthGroup(@RequestBody AuthorizationGroupVO cosdauthorizationgroupvo,HttpServletRequest request) 
	{
		System.out.println("Auth GroupName -" + cosdauthorizationgroupvo.getAuthGroupName());
		
		
		String username = HibernateUtil.getUserName(request); // HttpServletRequest request
		long cntGrp = cosdauthorizationgroupvo.getControlGroup() ;
		long orgUnit = cosdauthorizationgroupvo.getOrganizationUnit() ;
		String secRole =  cosdauthorizationgroupvo.getSecurityRole() ;
		String user = cosdauthorizationgroupvo.getUsers() ;
		AuthorizationGroupDao cosdauthorizationgroupdao = new AuthorizationGroupDao();
		AuthorizationGroup cosdauthorizationgroup = new AuthorizationGroup();
		
		
		List<AuthorizationGroup> cosdauthorizationgrouplist = cosdauthorizationgroupdao.getlistByGroupName(cosdauthorizationgroupvo.getAuthGroupName().toUpperCase());
	    
	    
	    if(cosdauthorizationgrouplist != null && !cosdauthorizationgrouplist.isEmpty())
	    {
	    	return new ResponseEntity(new Message("Duplicate"), HttpStatus.OK);
	    }
		
		
		
		
		ControlGroupDao cosdabaccontrolgroupdao = new ControlGroupDao();
		ControlGroup cosdabaccontrolgroup = cosdabaccontrolgroupdao.getById(cntGrp);
		
		
		OrganizationUnitDao organisationunitdao = new OrganizationUnitDao();
		OrganizationUnit organisationunit = orgUnit < 1 ? null : organisationunitdao.getById(orgUnit);
		
		
		SecurityroleDao securityroledao = new SecurityroleDao();
		SecurityRole securityrole = secRole == null ? null :securityroledao.getById(secRole);
		
		
		UsersDao usersdao = new UsersDao();
		Users users = user == null ? null :usersdao.getById(user);
				
		cosdauthorizationgroup.setControlGroup(cosdabaccontrolgroup);
		cosdauthorizationgroup.setOrganizationUnit(organisationunit);
		cosdauthorizationgroup.setSecurityRole(securityrole);
		cosdauthorizationgroup.setUsers(users);
		cosdauthorizationgroup.setJobName(cosdauthorizationgroupvo.getJobName());
		cosdauthorizationgroup.setAuthGroupName(cosdauthorizationgroupvo.getAuthGroupName().toUpperCase());
		cosdauthorizationgroup.setLastupdatedby(username);
		cosdauthorizationgroup.setCreatedby(username);
		cosdauthorizationgroup.setCreatedon(new Date());
		cosdauthorizationgroup.setLastupdatedon(new Date());
		
		System.out.println("in Save Job ID -" + cosdauthorizationgroupvo.getJobId());
		System.out.println("in save securityrole -" + securityrole);
		System.out.println("in save Users -" + users);
		
		String result = cosdauthorizationgroupdao.save(cosdauthorizationgroup);
		
		if("duplicate".equalsIgnoreCase(result))
		{
			return new ResponseEntity(new Message("Duplicate"), HttpStatus.OK);
		}
		
		if("fail".equalsIgnoreCase(result))
		{
			return new ResponseEntity(new Message("Duplicate"), HttpStatus.OK);
		}
	
		Converters conv = new Converters();
		cosdauthorizationgroupvo = conv.convertToVo(cosdauthorizationgroup) ;
		return new ResponseEntity<AuthorizationGroupVO>(cosdauthorizationgroupvo, HttpStatus.OK);

	}

	@RequestMapping(value = "/deleteAuthGroup/", method = RequestMethod.POST)
	public ResponseEntity<?> deleteAuthGroup(@RequestBody AuthorizationGroupVO cosdauthorizationgroupvo) 
	{
		
		AuthorizationGroupDao cosdauthorizationgroupdao = new AuthorizationGroupDao();
		AuthorizationGroup cosdauthorizationgroup = cosdauthorizationgroupdao.getById(cosdauthorizationgroupvo.getAuthorizationGroupId().longValue());
		
		if(cosdauthorizationgroup == null )
		{
			return new ResponseEntity(new Message("Auth Group ID not found  -" + cosdauthorizationgroupvo.getAuthorizationGroupId().longValue()), HttpStatus.OK);
		}
		
		String result = cosdauthorizationgroupdao.delete(cosdauthorizationgroupvo.getAuthorizationGroupId().longValue()) ;
		
		return new ResponseEntity(new Message(result), HttpStatus.OK);
	}
	
	/* ----------------------------- */
	
	@RequestMapping(value = "/getListPolySet/", method = RequestMethod.POST, produces = "application/json")			
	public ResponseEntity<List<AuthorizationPolicySetVO>> getListPolySet() 
	{				
		AuthorizationPolicySetDao cosdauthorizationpolicysetdao = new AuthorizationPolicySetDao(); 
		List<AuthorizationPolicySet> cosdauthorizationpolicysetList = cosdauthorizationpolicysetdao.getlist() ;
		
		AuthorizationPolicySetVO cosdauthorizationpolicysetvo = new AuthorizationPolicySetVO();
		Converters conv = new Converters();
							
		
		if (cosdauthorizationpolicysetList.isEmpty()) {
					return new ResponseEntity(HttpStatus.OK);
					// You many decide to return HttpStatus.NOT_FOUND
		}
		
		List<AuthorizationPolicySetVO> cosdauthorizationpolicysetvolist = conv.covertList(cosdauthorizationpolicysetList, cosdauthorizationpolicysetvo);
		return new ResponseEntity<List<AuthorizationPolicySetVO>>(cosdauthorizationpolicysetvolist, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/savePolySet/", method = RequestMethod.POST)
	public ResponseEntity<?> savePolySet(@RequestBody AuthorizationPolicySetVO cosdauthorizationpolicysetvo ,HttpServletRequest request) {
		logger.info(":: Consentstatus ::", cosdauthorizationpolicysetvo.getConsentstatus());
		AuthorizationPolicySet cosdauthorizationpolicyset = new AuthorizationPolicySet();
		AuthorizationPolicySetDao cosdauthorizationpolicysetdao = new AuthorizationPolicySetDao();
		try
		{
			String username = HibernateUtil.getUserName(request); // HttpServletRequest request
			cosdauthorizationpolicyset.setClassificationlevel(cosdauthorizationpolicysetvo.getClassificationlevel());
			cosdauthorizationpolicyset.setComments(cosdauthorizationpolicysetvo.getComments());
			cosdauthorizationpolicyset.setConsentstatus(cosdauthorizationpolicysetvo.getConsentstatus());
			//cosdauthorizationpolicyset.setCreatedby(cosdauthorizationpolicysetvo.getCreatedby());
			cosdauthorizationpolicyset.setCreatedby(username);
			cosdauthorizationpolicyset.setCreatedon(new Date());
			cosdauthorizationpolicyset.setLastupdatedby(username);
			cosdauthorizationpolicyset.setLastupdatedon(new Date()); //(new Date());
			cosdauthorizationpolicyset.setTransactionType(cosdauthorizationpolicysetvo.getTransactionType());
			cosdauthorizationpolicyset.setName(cosdauthorizationpolicysetvo.getName().toUpperCase());
			
			//cosdauthorizationpolicyset.setPolicysetid(cosdauthorizationpolicysetvo.getPolicysetid());
			
		    logger.info("before insert");
		    
		    
		    
		    	String result = cosdauthorizationpolicysetdao.save(cosdauthorizationpolicyset);
		    	
		    	Converters conv = new Converters();
		    	AuthorizationPolicySetVO cosdabaccontrolgroupvo = conv.convertToVo(cosdauthorizationpolicyset) ;
		    	return new ResponseEntity(cosdabaccontrolgroupvo, HttpStatus.OK);
		 		    	
		    
		}
		catch(Exception ex)
		{    System.out.println("::-----$$$$$ ::" + ex);
			ex.printStackTrace();
			return new ResponseEntity(new Message("Fail"), HttpStatus.OK);
		}
		
		
	}
	
	@RequestMapping(value = "/updatePolySet/", method = RequestMethod.POST)
	public ResponseEntity<?> updatePolySet(@RequestBody AuthorizationPolicySetVO cosdauthorizationpolicysetvo , HttpServletRequest request)
	{	
		logger.info(":: Consentstatus ::", cosdauthorizationpolicysetvo.getConsentstatus());
		AuthorizationPolicySet cosdauthorizationpolicyset = new AuthorizationPolicySet();
		AuthorizationPolicySetDao cosdauthorizationpolicysetdao = new AuthorizationPolicySetDao();
		
		try
		{
			String username = HibernateUtil.getUserName(request); // HttpServletRequest request
			cosdauthorizationpolicyset.setClassificationlevel(cosdauthorizationpolicysetvo.getClassificationlevel());
			cosdauthorizationpolicyset.setComments(cosdauthorizationpolicysetvo.getComments());
			cosdauthorizationpolicyset.setConsentstatus(cosdauthorizationpolicysetvo.getConsentstatus());
			cosdauthorizationpolicyset.setCreatedby(username);
			cosdauthorizationpolicyset.setLastupdatedby(username);
			cosdauthorizationpolicyset.setPolicysetid(cosdauthorizationpolicysetvo.getPolicysetid());
			cosdauthorizationpolicyset.setTransactionType(cosdauthorizationpolicysetvo.getTransactionType());
			cosdauthorizationpolicyset.setName(cosdauthorizationpolicysetvo.getName());
			
						
			cosdauthorizationpolicysetdao.update(cosdauthorizationpolicyset);
	    	
			Converters conv = new Converters();
			cosdauthorizationpolicysetvo = conv.convertToVo(cosdauthorizationpolicyset) ;
	    	return new ResponseEntity(cosdauthorizationpolicysetvo, HttpStatus.OK);
		
		}
		catch(Exception ex)
		{    System.out.println("::-----$$$$$ ::" + ex);
			ex.printStackTrace();
			return new ResponseEntity(new Message("Unable to Update Record."), HttpStatus.OK);
		}
		
		
	}	
	
	@RequestMapping(value = "/deletePolySet/", method = RequestMethod.POST)
	public ResponseEntity<?> deletePolySet(@RequestBody AuthorizationPolicySetVO cosdauthorizationpolicysetvo) 
	{
		
		AuthorizationPolicySetDao cosdauthorizationpolicysetdao = new AuthorizationPolicySetDao();
		AuthorizationPolicySet cosdauthorizationpolicyset = cosdauthorizationpolicysetdao.getById(cosdauthorizationpolicysetvo.getPolicysetid().longValue());
		
		if(cosdauthorizationpolicyset == null )
		{
			return new ResponseEntity(new Message("Policy Set ID not found  -" + cosdauthorizationpolicysetvo.getPolicysetid().longValue()), HttpStatus.OK);
		}
		
		String result = cosdauthorizationpolicysetdao.delete(cosdauthorizationpolicyset.getPolicysetid().longValue());
		
		return new ResponseEntity(new Message(result), HttpStatus.OK);
	}
	
	 @RequestMapping(value = "/listgrpwithgrpName/", method = RequestMethod.POST, produces = "application/json")			
	 public ResponseEntity<List<AuthorizationPolicySetVO>> listAllgrpWithName(@RequestBody AuthorizationPolicySetVO cosdauthorizationpolicysetvo) 
	 {		
	    	System.out.println(":: Search::" + cosdauthorizationpolicysetvo.getName());
	    	AuthorizationPolicySetDao cosdauthorizationpolicysetdao = new AuthorizationPolicySetDao();
			List<AuthorizationPolicySet> cosdconsentgrouplist = cosdauthorizationpolicysetdao.getlistByGroupName(cosdauthorizationpolicysetvo.getName().toUpperCase());
			
			Converters conv = new Converters();
			
			List<AuthorizationPolicySetVO> cosdabaccontrolgroupsVo = conv.covertList(cosdconsentgrouplist, cosdauthorizationpolicysetvo);
			System.out.println("::-- ControlUnit --::" + cosdabaccontrolgroupsVo.size());
				
				if (cosdconsentgrouplist.isEmpty()) {
					return new ResponseEntity(new Message("NO Group Found."), HttpStatus.OK); 			
						
				}
				return new ResponseEntity<List<AuthorizationPolicySetVO>>(cosdabaccontrolgroupsVo, HttpStatus.OK);
	}
	 
	 @RequestMapping(value = "/saveAuthGroupPolicy/", method = RequestMethod.POST)
	  public ResponseEntity<?> saveAuthGroupPolicy(@RequestBody AuthGroupPolicyVO authgrouppolicyvo) 
	 {  
		 AuthorizationPolicyDao cosdauthorizationpolicydao = new AuthorizationPolicyDao();
	     String result = cosdauthorizationpolicydao.saveWithAuthGroupPolicy(authgrouppolicyvo.getAuthGroupId() ,authgrouppolicyvo.getPolicySetID() );
	     
	    if("already".equalsIgnoreCase(result))
	     return new ResponseEntity(new Message("This Auth Grp is already in other PolicySet"), HttpStatus.OK);
	     
	     if("saved".equalsIgnoreCase(result))
	     return new ResponseEntity(authgrouppolicyvo, HttpStatus.OK);
	     else
	     return new ResponseEntity(new Message("Failed to save"), HttpStatus.OK); 
	 }

	 @RequestMapping(value = "/viewAuthPolicy/", method = RequestMethod.POST, produces = "application/json")			
	 public ResponseEntity<ViewPolicyAuthGroup> viewAuthPolicy(@RequestBody AuthorizationPolicySetVO policyset) 
	 {	
		 AuthorizationPolicySetDao cosdauthorizationpolicysetdao = new AuthorizationPolicySetDao();
		 ViewPolicyAuthGroup  viewpolicyauthgroupvo = cosdauthorizationpolicysetdao.getViewPolicyAuthGroup(policyset.getPolicysetid().longValue());
		 
		 return new ResponseEntity(viewpolicyauthgroupvo, HttpStatus.OK);
		 
	 }
}
