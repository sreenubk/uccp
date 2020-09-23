package  com.lotus.uccp.consentmgmt.dao;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lotus.uccp.util.HibernateUtil;
import com.lotus.uccp.authentication.model.ReferralLA;


public class ReferralLADao {
	
	
	
	 

	 public void save( ReferralLA referralla) {
		 
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        
	        try {
	              
	            transaction = session.beginTransaction();
	            
	            session.save(referralla);
	            transaction.commit();
	        }catch (HibernateException e) {
	            transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
	        	              
       }
	 
	 
	 public  List<ReferralLA> getlist() {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        List<ReferralLA> referralLAList = null ;
	        try {
	              
	            transaction = session.beginTransaction();
	            referralLAList = session.createQuery("from ReferralLA ").list();
	            for (Iterator iterator = referralLAList.iterator(); iterator.hasNext();) {
	            	ReferralLA referralla = (ReferralLA) iterator.next();
	                System.out.println("Customer Name " + referralla.getCustomer_name());
	            }
	            transaction.commit();
	        } catch (HibernateException e) {
	            transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
	        
	        return referralLAList ;
	    }
	 
	 
	 
	 public ReferralLA getById(long id)
	 {
		 
		  Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction transaction = null;
	      ReferralLA referralla = null ;
	      
		 	try {		 
		 	          session.beginTransaction();		 	             
		 	         referralla = (ReferralLA) session.get(ReferralLA.class, new BigDecimal(id));
		 
		 	          System.out.println("::Cust Name ::" + referralla.getCustomer_name() + " - Other Info -" + referralla.getOther_info());	 
		 	          session.getTransaction().commit();
		         }		 
		 	catch (HibernateException e) {		 
		 	            e.printStackTrace();		 
		 	            session.getTransaction().rollback();
		 
		 	    }
		 	return referralla ;
	 }
	 
	 public void update(ReferralLA referralla) {
		 
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        
	        try {
	              
	            transaction = session.beginTransaction();
	            
	            session.update(referralla);
	            transaction.commit();
	        }catch (HibernateException e) {
	            transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
    }
	 
	 public String delete(long id)
	 {		 
		  Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction transaction = null;
	      ReferralLA referralla = null ;
	      ReferralLADao referralladao = new ReferralLADao();
	      
		 	try {		 
		 			
		 		
		 			  session.beginTransaction();		 	             
		 			 referralla = (ReferralLA) session.get(ReferralLA.class, new BigDecimal(id));
		 	          
		 	      
		 	        
		 	         session.delete(referralla);
		 	        	 
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
	 
	
	 
	
	
	 
}	 
