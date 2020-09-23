/* Licensed Materials - 
 Property of IBM 6949 - 67L 
 Copyright IBM Corp. 2017, 2018 All Rights Reserved */
package  com.lotus.uccp.consentmgmt.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lotus.uccp.util.HibernateUtil;
import com.lotus.uccp.util.JDBCUtil;
import com.lotus.uccp.authentication.model.AuthorizationGroup;
import com.lotus.uccp.authentication.model.AuthorizationPolicy;
import com.lotus.uccp.authentication.model.AuthorizationPolicySet;

public class AuthorizationPolicyDao {
	
	public void deleteAuthPoly(long id)
	{
		Connection con = null ;
		PreparedStatement stmt =  null ;
		
		try
		{
			con = JDBCUtil.getConnection_ConsentMgmt() ;
		
			stmt = con.prepareStatement("Delete from COSDAUTHORIZATIONPOLICY where AUTHORIZATIONGROUPID = " + id );	
			System.out.println("before Deleted ID - " + id);
			stmt.executeUpdate();
			System.out.println("Deleted ID - " + id);
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				stmt.close();
				con.close();
			}
			catch(Exception ex)
			{
				
			}
		}
		
	}
	public void save(AuthorizationPolicy authorizationPolicy) {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        try {
	              
	            transaction = session.beginTransaction();
	            
	            session.save(authorizationPolicy);
	            transaction.commit();
	        }catch (HibernateException e) {
	            transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
}
	
	public String saveWithAuthGroupPolicy(long authGroupId , long policySetID) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
        	AuthorizationPolicy authorizationPolicy = new AuthorizationPolicy();
        	
        	
    		AuthorizationGroupDao  authorizationGroupDao = new AuthorizationGroupDao();
    		AuthorizationPolicySetDao authorizationPolicysetdao = new AuthorizationPolicySetDao();
    		
            transaction = session.beginTransaction();
            
            AuthorizationPolicyDao authorizationPolicydao = new AuthorizationPolicyDao();
            List<AuthorizationPolicy> authorizationPolicylist = authorizationPolicydao.getlistByAuthGroup(authGroupId);
            
            if( !authorizationPolicylist.isEmpty())
            {   System.out.println("Group :: Auth Group Creation ..Aleready Exists " + authGroupId);
            	return "already" ;
            }
            
    		AuthorizationGroup authorizationGroup = authorizationGroupDao.getById(authGroupId) ;
    		AuthorizationPolicySet authorizationPolicyset = authorizationPolicysetdao.getById(policySetID);
    		
    		authorizationPolicy.setAuthorizationGroup(authorizationGroup);
    		authorizationPolicy.setAuthorizationPolicySet(authorizationPolicyset);
    		authorizationPolicy.setPolicyComments("1 Policy AuthGroup");
            
            session.save(authorizationPolicy);
            transaction.commit();
            System.out.println("Group :: Auth Group + Policy Set linked ..saved " + authGroupId);
        }catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
            return "Fail" ;
        } finally {
            session.close();
        }
        return "saved" ;
}
	 
	 
	 public  List<AuthorizationPolicy> getlist() {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        List<AuthorizationPolicy> authorizationPolicylist = null ;
	        try {
	              
	            transaction = session.beginTransaction();
	            authorizationPolicylist = session.createQuery("from AuthorizationPolicy").list();
	            for (Iterator iterator = authorizationPolicylist.iterator(); iterator.hasNext();) {
	            	AuthorizationPolicy authorizationPolicy = (AuthorizationPolicy) iterator.next();
	                System.out.println(" ");
	            }
	            transaction.commit();
	        } catch (HibernateException e) {
	            transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
	        
	        return authorizationPolicylist ;
	    }
	 
	 
	 public  List<AuthorizationPolicy> getlistByGroupName(String groupname) {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        List<AuthorizationPolicy> authorizationPolicylist = null ;
	        try {
	              
	            transaction = session.beginTransaction();
	            
	            Query query = session.createQuery("from AuthorizationPolicy where  controlgroupname = :grpName ");
	            query.setParameter("grpName", groupname);
	            
	            authorizationPolicylist = query.list();
	            //Cosdabaccontrolgrouplist = session.createQuery("from Cosdabaccontrolgroup where  controlgroupname = :code").list();
	            for (Iterator iterator = authorizationPolicylist.iterator(); iterator.hasNext();) {
	            	AuthorizationPolicy authorizationPolicy = (AuthorizationPolicy) iterator.next();
	                System.out.println("Name ");
	            }
	            transaction.commit();
	        } catch (HibernateException e) {
	            transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
	        
	        return authorizationPolicylist ;
	    }
	 
	 public  List<AuthorizationPolicy> getlistByAuthGroup(long authGrp) {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        List<AuthorizationPolicy> authorizationPolicylist = null ;
	        try {
	              
	            transaction = session.beginTransaction();
	            
	            AuthorizationGroupDao  authorizationGroupDao = new AuthorizationGroupDao();
	            AuthorizationGroup authorizationGroup = authorizationGroupDao.getById(authGrp) ;
	            
	            Query query = session.createQuery("from AuthorizationPolicy where  authorizationGroup = :grpName ");
	            query.setParameter("grpName", authorizationGroup);
	            
	            authorizationPolicylist = query.list();
	            //Cosdabaccontrolgrouplist = session.createQuery("from Cosdabaccontrolgroup where  controlgroupname = :code").list();
	            for (Iterator iterator = authorizationPolicylist.iterator(); iterator.hasNext();) {
	            	AuthorizationPolicy authorizationPolicy = (AuthorizationPolicy) iterator.next();
	                System.out.println("Name ");
	            }
	            transaction.commit();
	        } catch (HibernateException e) {
	            transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
	        
	        return authorizationPolicylist ;
	    }
	 
	 
	 public AuthorizationPolicy getById(long id)
	 {
		 
		  Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction transaction = null;
	      AuthorizationPolicy authorizationPolicy = null ;
	      
		 	try {		 
		 	          session.beginTransaction();		 	             
		 	         authorizationPolicy = (AuthorizationPolicy) session.get(AuthorizationPolicy.class, new BigDecimal(id));
		              if(authorizationPolicy != null)
		            	  System.out.println("Name " + authorizationPolicy.getPolicyComments());	 
		 	          session.getTransaction().commit();
		         }		 
		 	catch (HibernateException e) {		 
		 	            e.printStackTrace();		 
		 	            session.getTransaction().rollback();
		 
		 	    }
		 	return authorizationPolicy;
	 }
	 
	 public void delete(long id)
	 {		 
		  Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction transaction = null;
	      AuthorizationPolicy authorizationPolicy = null ;	      
		 	try {		 
		 	          session.beginTransaction();		 	             
		 	         authorizationPolicy = (AuthorizationPolicy) session.get(AuthorizationPolicy.class, new BigDecimal(id));
		              session.delete(authorizationPolicy);
		              if(authorizationPolicy != null)
		              {
		            	  System.out.println("Name " + authorizationPolicy);	 
		 	          }  	 
		 	          session.getTransaction().commit();
		         }		 
		 	catch (HibernateException e) {		 
		 	            e.printStackTrace();		 
		 	            session.getTransaction().rollback();
		 
		 	    }
		 	
	 }
	 
	 public String update(AuthorizationPolicy authorizationPolicy) {
		 
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        AuthorizationPolicy old_authorizationPolicy = null ;
	        AuthorizationPolicyDao authorizationPolicydao = new AuthorizationPolicyDao();
	        try {
	              
	            transaction = session.beginTransaction();
	            
	            old_authorizationPolicy = authorizationPolicydao.getById(authorizationPolicy.getAuthorizationpolicyid().longValue());
	            
	            if(old_authorizationPolicy == null)
	            return "authorizationpolicy is not present" ;
	            
	            //old_authorizationGroup.setJobid(authorizationGroup.getJobid());
	            session.update(old_authorizationPolicy);
	            transaction.commit();
	        }catch (HibernateException e) {
	            transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
	        return "Updated" ;
}


}
