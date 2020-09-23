package  com.lotus.uccp.consentmgmt.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lotus.uccp.util.Converters;
import com.lotus.uccp.util.HibernateUtil;
import com.lotus.uccp.util.JDBCUtil;
import com.lotus.uccp.authentication.model.AuthorizationGroup;
import com.lotus.uccp.authentication.model.AuthorizationPolicySet;
import com.lotus.uccp.vo.AuthorizationGroupVO;
import com.lotus.uccp.vo.AuthorizationPolicySetVO;
import com.lotus.uccp.vo.ViewPolicyAuthGroup;

public class AuthorizationPolicySetDao {
	
	 public String  save(AuthorizationPolicySet authorizationPolicySet) {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        try {
	              
	            transaction = session.beginTransaction();
	            
	            session.save(authorizationPolicySet);
	            transaction.commit();
	        }catch(org.hibernate.exception.ConstraintViolationException cvex)
	        {
	        	System.out.println();
	        	return "Duplicate" ;
	        }
	        catch (HibernateException e) {
	            transaction.rollback();
	            e.printStackTrace();
	            return "Fail" ;
	        } finally {
	            session.close();
	        }
	        return "save";
}
	 
	 
	 public  List<AuthorizationPolicySet> getlist() {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        List<AuthorizationPolicySet> authorizationPolicySetlist = null ;
	        try {
	              
	            transaction = session.beginTransaction();
	            authorizationPolicySetlist = session.createQuery("from AuthorizationPolicySet").list();
	            for (Iterator iterator = authorizationPolicySetlist.iterator(); iterator.hasNext();) {
	            	AuthorizationPolicySet authorizationPolicySet = (AuthorizationPolicySet) iterator.next();
	                System.out.println(":: Unit ID ::" + authorizationPolicySet.getConsentstatus());
	            }
	            transaction.commit();
	        } catch (HibernateException e) {
	            transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
	        
	        return authorizationPolicySetlist ;
	    }
	 
	 
	 public  List<AuthorizationPolicySet> getlistByGroupName(String groupname) {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        List<AuthorizationPolicySet> authorizationPolicySetlist = null ;
	        try {
	              
	            transaction = session.beginTransaction();
	            
	            Query query = session.createQuery("from AuthorizationPolicySet where  name LIKE :grpName ");	           
	            query.setParameter("grpName","%" + groupname +"%");
	            
	            authorizationPolicySetlist = query.list();
	            //ControlGrouplist = session.createQuery("from ControlGroup where  controlgroupname = :code").list();
	            for (Iterator iterator = authorizationPolicySetlist.iterator(); iterator.hasNext();) {
	            	AuthorizationPolicySet authorizationPolicySet = (AuthorizationPolicySet) iterator.next();
	                System.out.println("Name " + authorizationPolicySet.getConsentstatus());
	            }
	            System.out.println("..Not Found ..??");
	            transaction.commit();
	        } catch (HibernateException e) {
	            transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
	        
	        return authorizationPolicySetlist ;
	    }
	 
	 
	 public AuthorizationPolicySet getById(long id)
	 {
		 
		  Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction transaction = null;
	      AuthorizationPolicySet authorizationPolicySet = null ;
	      
		 	try {		 
		 	          session.beginTransaction();		 	             
		 	         authorizationPolicySet = (AuthorizationPolicySet) session.get(AuthorizationPolicySet.class, new BigDecimal(id));
		              if(authorizationPolicySet != null)
		            	  System.out.println("Name " + authorizationPolicySet.getConsentstatus());	 
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
		 	return authorizationPolicySet;
	 }
	 
	 public String delete(long id)
	 {		 
		  Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction transaction = null;
	      AuthorizationPolicySet authorizationPolicySet = null ;	      
		 	try {		 
		 	          session.beginTransaction();		 	             
		 	         authorizationPolicySet = (AuthorizationPolicySet) session.get(AuthorizationPolicySet.class, new BigDecimal(id));
		              session.delete(authorizationPolicySet);
		              if(authorizationPolicySet != null)
		              {
		            	  System.out.println("Name " + authorizationPolicySet.getConsentstatus());	 
		 	          }  	 
		 	          session.getTransaction().commit();
		         }		 
		 	catch (HibernateException e) {		 
		 	            e.printStackTrace();		 
		 	            session.getTransaction().rollback();
		 	           return "Error while deletion" ;
		 
		 	    }
		 	
		 	return "deleted" ;
	 }
	 
	 public String update(AuthorizationPolicySet authorizationPolicySet) {
		 
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        AuthorizationPolicySet old_authorizationPolicySet = null ;
	        AuthorizationPolicySetDao authorizationPolicySetdao = new AuthorizationPolicySetDao();
	        try {
	              
	            transaction = session.beginTransaction();
	            
	            old_authorizationPolicySet = authorizationPolicySetdao.getById(authorizationPolicySet.getPolicysetid().longValue());
	            
	            if(old_authorizationPolicySet == null)
	            return "Group is not present" ;
	            
	            //old_authorizationGroup.setJobid(authorizationGroup.getJobid());
	            session.update(authorizationPolicySet);
	            transaction.commit();
	        }catch (HibernateException e) {
	            transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
	        return "Updated" ;
 }
	 
	 public ViewPolicyAuthGroup getViewPolicyAuthGroup(long policySet)
	 {
		 ViewPolicyAuthGroup viewpolicyauthgroup = new ViewPolicyAuthGroup();
		 
		 String viewGroupQ = " select authgrp.AUTHORIZATIONGROUPID as AUTHORIZATIONGROUPID, poliset.POLICYSETID , poliset.COSD_ABAC_SEC_CLASSIFICATION , "
		 		+ "poliset.COMMENTS , poliset.CONSENTSTATUS , poliset.CREATEDBY , poliset.CREATEDON , poliset.LASTUPDATEDBY , poliset.LASTUPDATEDON, "
		 		+ "poliset.TRANSACTIONTYPE, poliset.NAME , authgrp.AUTHORIZATIONGROUPID from COSDAUTHORIZATIONPOLICYSET poliset , "
		 		+ "COSDAUTHORIZATIONPOLICY poli , COSDAUTHORIZATIONGROUP authgrp "
				+" where poli.POLICYSETID = poliset.POLICYSETID and authgrp.AUTHORIZATIONGROUPID = poli.AUTHORIZATIONGROUPID and poliset.POLICYSETID = ? " ;
		 
		 Connection con = null ;
		 PreparedStatement stmt =  null ;
		 ResultSet viewAuthGRouprs = null ; 
		 
		 try
		 {
		
			 AuthorizationGroupDao  authorizationGroupDao = new AuthorizationGroupDao();
			 AuthorizationPolicySetVO policyset  = new AuthorizationPolicySetVO();			 
			 List<AuthorizationGroupVO> authGroups = new ArrayList<AuthorizationGroupVO>();
			
			 con = JDBCUtil.getConnection_ConsentMgmt() ;				
			 stmt = con.prepareStatement(viewGroupQ);				
			 stmt.setLong(1, policySet);				
			 viewAuthGRouprs = stmt.executeQuery();
			 
			 AuthorizationPolicySetDao authorizationPolicySetdao = new AuthorizationPolicySetDao();
			 AuthorizationPolicySet authorizationPolicySet = authorizationPolicySetdao.getById(policySet);
			 Converters conv = new Converters();
			
			 
			 
			 System.out.println("Method - getViewPolicyAuthGroup() :: viewGroupQ ::-" + viewGroupQ);
				
			 while(viewAuthGRouprs.next())
			 {
				 if(policyset.getPolicysetid() == null || policyset.getPolicysetid().longValue() < 1 )
				 {
					 policyset.setPolicysetid(new BigDecimal(viewAuthGRouprs.getInt("POLICYSETID")));				 
					 policyset.setClassificationlevel(viewAuthGRouprs.getString("COSD_ABAC_SEC_CLASSIFICATION"));
					 policyset.setComments(viewAuthGRouprs.getString("COMMENTS"));
					 policyset.setConsentstatus(viewAuthGRouprs.getString("CONSENTSTATUS"));
					 policyset.setCreatedby(viewAuthGRouprs.getString("CREATEDBY"));
					 if(viewAuthGRouprs.getDate("CREATEDON") != null)
					 policyset.setCreatedon(new java.util.Date(viewAuthGRouprs.getDate("CREATEDON").getTime()));
					 policyset.setLastupdatedby(viewAuthGRouprs.getString("LASTUPDATEDBY"));
					 if(viewAuthGRouprs.getDate("LASTUPDATEDON") != null)
					 policyset.setLastupdatedon(new java.util.Date(viewAuthGRouprs.getDate("LASTUPDATEDON").getTime()));
					 policyset.setTransactionType(viewAuthGRouprs.getString("TRANSACTIONTYPE"));
				     policyset.setName(viewAuthGRouprs.getString("NAME"));
			     }
				 
				 if( viewAuthGRouprs.getInt("AUTHORIZATIONGROUPID") > 0)
				 {
					 AuthorizationGroup authorizationGroup = authorizationGroupDao.getById(viewAuthGRouprs.getInt("AUTHORIZATIONGROUPID")) ;
					  conv = new Converters();
					 
					 authGroups.add(conv.convertToVo(authorizationGroup));
				 }
			 
		   } // try
			 policyset = conv.convertToVo(authorizationPolicySet) ;
			 viewpolicyauthgroup.setPolicyset(policyset);
			 viewpolicyauthgroup.setAuthGroups(authGroups);
			 
			 System.out.println("Before Try finish ..");
		 }
		 catch(Exception ex)
		 {
			 ex.printStackTrace();
		 }
		 finally
		 {
			 try
				{
					if(viewAuthGRouprs!=null)
						viewAuthGRouprs.close();
					
					if(stmt!=null)
						stmt.close();
					
					if(con!=null)
						con.close();
				}
				catch(Exception exsql)
				{
					
				}
			 
		 }
		 return viewpolicyauthgroup ;
	 }
	 
	

}
