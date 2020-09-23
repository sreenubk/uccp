package  com.lotus.uccp.consentmgmt.dao;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lotus.uccp.util.HibernateUtil;
import com.lotus.uccp.consentmgmt.dto.AuthRequest;

public class AuthRequestDao {
	
	 public void save(AuthRequest AuthRequest) {
		 
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	      
	        try {
	              
	        		transaction = session.beginTransaction();	            
	        		session.save(AuthRequest);
	        		transaction.commit();
	        		System.out.println("saved in Audit Table -" + AuthRequest.getAuditid() );
	        		
	        }catch (HibernateException e) {
	            transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
 }
	 
	
	 public AuthRequest getById(long id)
	 {
		 
		  Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction transaction = null;
	      AuthRequest AuthRequest = null ;
	      
		 	try {		 
		 	          session.beginTransaction();		 	             
		 	          
		 	          AuthRequest = (AuthRequest) session.get(AuthRequest.class, new BigDecimal(id));
		              if(AuthRequest != null)		 	         
		              System.out.println(AuthRequest.getApiSwith() + " - " + AuthRequest.getClientID());	 
		 	         
		              session.getTransaction().commit();
		         }		 
		 	catch (HibernateException e) {		 
		 	            e.printStackTrace();		 
		 	            session.getTransaction().rollback();
		 
		 	    }
		 		finally {
	            session.close();
	        }
		 	return AuthRequest;
	 }
	 
	 
	 public  List<AuthRequest> getlist() {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        List<AuthRequest> cosdorgrolejobuserlist = null ;
	        try {
	              
	            transaction = session.beginTransaction();
	            cosdorgrolejobuserlist = session.createQuery("from AuthRequest").list();
	            for (Iterator iterator = cosdorgrolejobuserlist.iterator(); iterator.hasNext();) {
	            	AuthRequest cosdabaccontrolgroup = (AuthRequest) iterator.next();
	                System.out.println("Name " + cosdabaccontrolgroup);
	            }
	            transaction.commit();
	        } catch (HibernateException e) {
	            transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
	        
	        return cosdorgrolejobuserlist ;
	    }
	 
	 
	
	 
	 public String delete(long id)
	 {
		 
		  AuthRequest AuthRequest = null ;
		  
		  
		  Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction transaction = null;
	   	      
		 	try {		 
		 	          session.beginTransaction();		 	             
		 	          AuthRequest = (AuthRequest) session.get(AuthRequest.class, new BigDecimal(id));             
		              session.delete(AuthRequest);     
		             	 
		 	          session.getTransaction().commit();
		         }		 
		 	catch (HibernateException e) {		 
		 	            e.printStackTrace();		 
		 	            session.getTransaction().rollback();
		 	           return "Error- while delete operation" ;
		 
		 	    }
		 	 finally {
	            session.close();
	        }
		 	return "Record deleted" ;
	 }
	 
	 public String update(  AuthRequest AuthRequest ) {
		 
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        AuthRequest old_authrequest = null ;
	        AuthRequestDao authrequestdao = new AuthRequestDao();
	        try {
	              
	            transaction = session.beginTransaction();
	            
	            old_authrequest = authrequestdao.getById(AuthRequest.getAuditid().longValue());
	            
	            if(old_authrequest == null)
	            return "Group is not present" ;
	            
	           
	            session.update(AuthRequest);
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
