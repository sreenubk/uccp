package  com.lotus.uccp.consentmgmt.service;

import java.util.List;

import com.lotus.uccp.authentication.model.OrganizationUnit;
import org.springframework.stereotype.Service;

import com.lotus.uccp.consentmgmt.dao.AuthorizationGroupDao;
import com.lotus.uccp.consentmgmt.dao.OrgRoleJobUserDao;
import com.lotus.uccp.consentmgmt.dao.OrganizationUnitDao;
import com.lotus.uccp.consentmgmt.dao.SecurityroleDao;
import com.lotus.uccp.consentmgmt.dao.UsersDao;
import com.lotus.uccp.authentication.model.AuthorizationGroup;
import com.lotus.uccp.authentication.model.OrgRoleJobUser;
import com.lotus.uccp.authentication.model.SecurityRole;
import com.lotus.uccp.authentication.model.Users;
import com.lotus.uccp.vo.AuthorizationGroupVO;

@Service("AuthServices")
public class AuthServices {
	
	public List<OrganizationUnit> getOrgList()
	{
		OrganizationUnitDao organisationunitdao = new OrganizationUnitDao();
		return organisationunitdao.getlist() ;
	}
	
	public List<OrgRoleJobUser> getlist()
	{
		OrgRoleJobUserDao cosdorgrolejobuserdao = new OrgRoleJobUserDao();
		return cosdorgrolejobuserdao.getlist() ;
	}
	
	public List<Users> getUserList()
	{
		UsersDao userdao = new UsersDao();
		List<Users> usersList = userdao.getlist();
		return usersList;
	}
	
	public List<SecurityRole> getSecRoleList()
	{
		SecurityroleDao securityroledao = new SecurityroleDao();
		List<SecurityRole> securityrolelist = securityroledao.getlist() ;
		return securityrolelist;
	}
	
	public String saveAuthGroup(AuthorizationGroupVO cosdauthorizationgroupvo)
	{
		AuthorizationGroupDao cosdauthorizationgroupdao = new AuthorizationGroupDao();
		AuthorizationGroup cosdauthorizationgroup = new AuthorizationGroup();
		
		//cosdauthorizationgroup.
		
		return cosdauthorizationgroupdao.save(cosdauthorizationgroup);
	}
	
	public String editAuthGroup(AuthorizationGroupVO cosdauthorizationgroupvo)
	{
		AuthorizationGroupDao cosdauthorizationgroupdao = new AuthorizationGroupDao();
		AuthorizationGroup cosdauthorizationgroup = new AuthorizationGroup();
		
		//cosdauthorizationgroup.
		
		return cosdauthorizationgroupdao.update(cosdauthorizationgroup);
	}
	
	
	public String deleteAuthGroup(long authID)
	{
		AuthorizationGroupDao cosdauthorizationgroupdao = new AuthorizationGroupDao();
		
		return cosdauthorizationgroupdao.delete(authID);
	}
	
	public List<AuthorizationGroup> getListAuthGroup()
	{
		AuthorizationGroupDao cosdauthorizationgroupdao = new AuthorizationGroupDao();
		
		List<AuthorizationGroup> cosdauthorizationgrouplist = cosdauthorizationgroupdao.getlist() ;
		return cosdauthorizationgrouplist ;
	}

}


