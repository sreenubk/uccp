package  com.lotus.uccp.consentmgmt.dao;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import com.lotus.uccp.authentication.model.OrganizationUnit;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lotus.uccp.authentication.model.AuthorizationGroup;
import com.lotus.uccp.authentication.model.ControlGroup;
import com.lotus.uccp.consentmgmt.dto.Job;
import com.lotus.uccp.authentication.model.SecurityRole;
import com.lotus.uccp.authentication.model.Users;
import com.lotus.uccp.util.HibernateUtil;
import com.lotus.uccp.vo.AuthorizationGroupVO;


public class AuthorizationGroupDao {
	
	
	public  List<AuthorizationGroup> search(AuthorizationGroupVO authorizationGroupVO) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<AuthorizationGroup> cosdauthorizationgrouplist = null ;
        ControlGroupDao controlGroupDao = new ControlGroupDao();
        ControlGroup controlGroup = null ;
        OrganizationUnitDao organizationUnitdao = new OrganizationUnitDao();
		OrganizationUnit organizationUnit = null ;
		SecurityroleDao securityroledao = new SecurityroleDao();
		SecurityRole sRole = null ;
		
        try {
        	
        	String whereClause = " " ;
        	String and = " " ;
        	boolean andEnable = false ;
            
        	boolean isgrpName = false ;
            boolean isorgUnitID = false ;
            boolean issecurityrole = false ;
            boolean isJobjID = false ;
            
        	long grpID = authorizationGroupVO.getControlGroup();
        	
        	if(grpID > 0)
        	{
        		controlGroup = controlGroupDao.getById(grpID);
        		whereClause = whereClause + " controlGroup = :grpName " ; 
        		andEnable = true ;
        		isgrpName = true ;
        	}
        	
    		 		
    		long orgUnitID = authorizationGroupVO.getOrganizationUnit();
    		if(orgUnitID > 0)
    		{
    			organizationUnit = organizationUnitdao.getById(orgUnitID);
    			and = andEnable ? " And " : " " ;
    			whereClause = whereClause + and  + " organizationUnit = :orgUnit  " ;
    			andEnable = true ;
    			isorgUnitID = true ;
    		}
        	
        	String securityrole = authorizationGroupVO.getSecurityRole() ;
        	if( securityrole != null && !securityrole.trim().equals("0"))
        	{
        		sRole = securityroledao.getById(securityrole);
        		and = andEnable ? " And " : " " ;
        		whereClause = whereClause + and + " securityrole = :secRole  " ;
        		andEnable = true ;
        		issecurityrole = true ;
        	}
        	
        	BigDecimal jobid = authorizationGroupVO.getJobId() ;
        	String jobName = null ;
        	if (jobid != null && jobid.intValue() > 0)
        	{
        		and = andEnable ? " And " : " " ;       
        		JobDao jobdao = new JobDao();
            	Job jobdto = jobdao.getById(jobid.longValue());
            	 jobName = jobdto.getJobname();
        		whereClause = whereClause + and + " jobName = :jID  " ;
        		isJobjID = true ;
        		andEnable = true ;
        	}
        	
        	String wherestr = andEnable ? " Where " : " " ; 
        	whereClause = wherestr + whereClause ;
    		
    		System.out.println(":: :: grpID" + grpID + ":: orgUnitID ::" + orgUnitID + ":: securityrole ::" + securityrole + ":: jobName ::" + jobName);
    		System.out.println("::**--> " + whereClause );
    		
    		/*CosdorgrolejobuserDao cosdorgrolejobuserdao = new CosdorgrolejobuserDao();
    		Cosdorgrolejobuser cosdorgrolejobuser =  cosdorgrolejobuserdao.getById(jobid);		
      */  	
            transaction = session.beginTransaction();
            
            /*Query query = session.createQuery("from AuthorizationGroup where  controlGroup = :grpName and "
            		+ " organizationUnit = :orgUnit and securityrole = :secRole and jobid = :jID");*/
            Query query = session.createQuery("from AuthorizationGroup " + whereClause );
            if(isgrpName)
            query.setParameter("grpName",controlGroup);
            if(isorgUnitID)
            query.setParameter("orgUnit",organizationUnit);
            if(issecurityrole)
            query.setParameter("secRole", sRole);
            if(isJobjID)
            query.setParameter("jID", jobName);
            
            cosdauthorizationgrouplist = query.list();
            //ControlGrouplist = session.createQuery("from ControlGroup where  controlgroupname = :code").list();
            for (Iterator iterator = cosdauthorizationgrouplist.iterator(); iterator.hasNext();) {
            	AuthorizationGroup cosdauthorizationgroup = (AuthorizationGroup) iterator.next();
                System.out.println("Authorization ID -" + cosdauthorizationgroup.getAuthorizationgroupid());
            }
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return cosdauthorizationgrouplist ;
    }
 
	
	 public String save(AuthorizationGroup cosdauthorizationgroup) {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        try {
	              
	            transaction = session.beginTransaction();
	            
	            session.save(cosdauthorizationgroup);
	            transaction.commit();
	            return "saved" ;
	        }
	        catch(org.hibernate.exception.ConstraintViolationException sqlIntex)
	        {
	        	System.out.println("Duplication in Constraint");
	        	return "duplicate" ;
	        }
	        catch (HibernateException e) {
	            transaction.rollback();
	            e.printStackTrace();
	            return "fail" ;
	        } finally {
	            session.close();
	        }
	        
	        
 }
	 
	 
	 public  List<AuthorizationGroup> getlist() {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        List<AuthorizationGroup> cosdauthorizationgrouplist = null ;
	        try {
	              
	            transaction = session.beginTransaction();
	            cosdauthorizationgrouplist = session.createQuery("from AuthorizationGroup").list();
	            for (Iterator iterator = cosdauthorizationgrouplist.iterator(); iterator.hasNext();) {
	            	AuthorizationGroup cosdauthorizationgroup = (AuthorizationGroup) iterator.next();
	                System.out.println(":: Unit ID ::" + cosdauthorizationgroup.getAuthorizationgroupid());
	            }
	            transaction.commit();
	        } catch (HibernateException e) {
	            transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
	        
	        return cosdauthorizationgrouplist ;
	    }
	 
	 
	 public  List<AuthorizationGroup> getlistByGroupName(String groupname) {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        List<AuthorizationGroup> controlGrouplist = null ;
	        try {
	              
	            transaction = session.beginTransaction();
	            
	            Query query = session.createQuery("from AuthorizationGroup where  authGroupName = :grpName ");
	            query.setParameter("grpName", groupname);
	            
	            controlGrouplist = query.list();
	            //ControlGrouplist = session.createQuery("from ControlGroup where  controlgroupname = :code").list();
	            for (Iterator iterator = controlGrouplist.iterator(); iterator.hasNext();) {
	            	AuthorizationGroup controlGroup = (AuthorizationGroup) iterator.next();
	                System.out.println("Name ::" + controlGroup.getAuthGroupName());
	            }
	            transaction.commit();
	        } catch (HibernateException e) {
	            transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
	        
	        return controlGrouplist ;
	    }
	 
	 
	 public AuthorizationGroup getById(long id)
	 {
		 
		  Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction transaction = null;
	      AuthorizationGroup cosdauthorizationgroup = null ;
	      
		 	try {		 
		 	          session.beginTransaction();		 	             
		 	         cosdauthorizationgroup = (AuthorizationGroup) session.get(AuthorizationGroup.class, new BigDecimal(id));
		              if(cosdauthorizationgroup != null)
		 	         // System.out.println(" Group Id -"+ cosdauthorizationgroup.getAuthorizationgroupid() + " - Job id - " + cosdauthorizationgroup.getJobid());	 
		 	          session.getTransaction().commit();
		         }		 
		 	catch (HibernateException e) {		 
		 	            e.printStackTrace();		 
		 	            session.getTransaction().rollback();
		 
		 	    }
		 	finally
		 	{
		 		session.close();
		 	}
		 	return cosdauthorizationgroup;
	 }
	 
	 public String  delete(long id)
	 {		 
		  Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction transaction = null;
	      AuthorizationGroup cosdauthorizationgroup = null ;	      
		 	try {		 
		 	          session.beginTransaction();		 	             
		 	         cosdauthorizationgroup = (AuthorizationGroup) session.get(AuthorizationGroup.class, new BigDecimal(id));
		 	         
		 	        AuthorizationPolicyDao cosdauthorizationpolicydao = new AuthorizationPolicyDao();
		 	        
		 	        cosdauthorizationpolicydao.deleteAuthPoly(id);
		 	        System.out.println("Child Record deleted ..if present");
		 	          
		 	         if(cosdauthorizationgroup == null)
		 	         {
		 	        	 return "Not Found" ;
		 	         }
		 	         
		              session.delete(cosdauthorizationgroup);
		              if(cosdauthorizationgroup != null)
		              {
			 	         // System.out.println(" Group Id -"+ cosdauthorizationgroup.getAuthorizationgroupid() + " - Job id - " + cosdauthorizationgroup.getJobid());	 
		 	          }  	 
		 	          session.getTransaction().commit();
		 	          return "Deleted" ;
		         }		 
		 	catch (HibernateException e) {		 
		 	            e.printStackTrace();		 
		 	            session.getTransaction().rollback();
		              return "Error - While deletion" ;
		 	    }
		 	finally
		 	{
		 		session.close();
		 	}
		 	
	 }
	 
	 public String update( AuthorizationGroup cosdauthorizationgroup) {
		 
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        AuthorizationGroup old_cosdauthorizationgroup = null ;
	        AuthorizationGroupDao cosdauthorizationgroupdao = new AuthorizationGroupDao();
	        try {
	              
	            transaction = session.beginTransaction();
	            
	            old_cosdauthorizationgroup = cosdauthorizationgroupdao.getById(cosdauthorizationgroup.getAuthorizationgroupid().longValue());
	            
	            if(old_cosdauthorizationgroup == null)
	            return "Group is not present" ;
	            
	            //old_cosdauthorizationgroup.setJobid(cosdauthorizationgroup.getJobid());
	            session.update(cosdauthorizationgroup);
	            transaction.commit();
	        }catch (HibernateException e) {
	            transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
	        return "Updated" ;
}
	 
	
	 
	 
	 public  List<AuthorizationGroup> searchBeforeSave(AuthorizationGroupVO authorizationGroupVO) {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        List<AuthorizationGroup> cosdauthorizationgrouplist = null ;
	        ControlGroupDao controlGroupDao = new ControlGroupDao();
	        ControlGroup controlGroup = null ;
	        OrganizationUnitDao organizationUnitdao = new OrganizationUnitDao();
			OrganizationUnit organizationUnit = null ;
			SecurityroleDao securityroledao = new SecurityroleDao();
			SecurityRole sRole = null ;
			
	        try {
	        	
	        	String whereClause = " " ;
	        	String and = " " ;
	        	boolean andEnable = false ;
	            
	        	boolean isgrpName = false ;
	            boolean isorgUnitID = false ;
	            boolean issecurityrole = false ;
	            boolean isJobjID = false ;
	            
	        	long grpID = authorizationGroupVO.getControlGroup();
	        	
	        	if(grpID > 0)
	        	{
	        		controlGroup = controlGroupDao.getById(grpID);
	        		whereClause = whereClause + " controlGroup = :grpName " ; 
	        		andEnable = true ;
	        		isgrpName = true ;
	        	}
	        	
	    		 		
	    		long orgUnitID = authorizationGroupVO.getOrganizationUnit();
	    		
	    			organizationUnit = orgUnitID < 1? null : organizationUnitdao.getById(orgUnitID);
	    			and = andEnable ? " And " : " " ;
	    			whereClause = whereClause + and  + " organizationUnit = :orgUnit  " ;
	    			andEnable = true ;
	    			isorgUnitID = true ;
	    		
	        	
	        	String securityrole = authorizationGroupVO.getSecurityRole() ;
	        	
	        		sRole = securityrole == null ? null : (SecurityRole)securityroledao.getById(securityrole);
	        		and = andEnable ? " And " : " " ;
	        		whereClause = whereClause + and + " securityrole = :secRole  " ;
	        		andEnable = true ;
	        		issecurityrole = true ;
	        	
	        	
	        	BigDecimal jobid = authorizationGroupVO.getJobId() ;
	        	if (jobid != null)
	        	{
	        		and = andEnable ? " And " : " " ;        	
	        		whereClause = whereClause + and + " jobid = :jID  " ;
	        		isJobjID = true ;
	        		andEnable = true ;
	        	}
	        	
	        	String user = authorizationGroupVO.getUsers() ;
	        	UsersDao  userdao = new UsersDao();
	        	Users Users = user == null ? null :userdao.getById(user);
	        	whereClause = whereClause + and + " Users = :users  " ;
	        	boolean isUser = true ;
	        	
	        	String wherestr = andEnable ? " Where " : " " ; 
	        	whereClause = wherestr + whereClause ;
	    		
	        	
	    		
	    		System.out.println("::**--> " + whereClause );
	    		
	    		/*CosdorgrolejobuserDao cosdorgrolejobuserdao = new CosdorgrolejobuserDao();
	    		Cosdorgrolejobuser cosdorgrolejobuser =  cosdorgrolejobuserdao.getById(jobid);		
	      */  	
	            transaction = session.beginTransaction();
	            
	            Query query = session.createQuery("from AuthorizationGroup where  controlGroup = :grpName and "
	            		+ " organizationUnit = :orgUnit and securityrole = :secRole and jobid = :jID and Users = :users ");
	          //  Query query = session.createQuery("from AuthorizationGroup " + whereClause );
	            if(isgrpName)
	            query.setParameter("grpName",controlGroup);
	            if(isorgUnitID)
	            query.setParameter("orgUnit",organizationUnit);
	            if(issecurityrole)
	            query.setParameter("secRole", sRole);
	            if(isJobjID)
	            query.setParameter("jID", jobid);
	            if(isUser)
		         query.setParameter("users", Users);
	            
	            cosdauthorizationgrouplist = query.list();
	            //ControlGrouplist = session.createQuery("from ControlGroup where  controlgroupname = :code").list();
	            for (Iterator iterator = cosdauthorizationgrouplist.iterator(); iterator.hasNext();) {
	            	AuthorizationGroup cosdauthorizationgroup = (AuthorizationGroup) iterator.next();
	                System.out.println("Authorization ID " + cosdauthorizationgroup.getAuthorizationgroupid());
	            }
	            transaction.commit();
	        } catch (HibernateException e) {
	            transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
	        
	        return cosdauthorizationgrouplist ;
	    }

}
