package  com.lotus.uccp.consentmgmt.dao;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lotus.uccp.util.HibernateUtil;
import com.lotus.uccp.authentication.model.ConsentGroupTxn;

public class ConsentGroupTxnDao {
	
	
	 public void save(ConsentGroupTxn consentGroupTxn) {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        try {
	        		
	            transaction = session.beginTransaction();
	            
	            session.save(consentGroupTxn);
	            transaction.commit();
	        }catch (HibernateException e) {
	        	System.out.println("Exception ..mmm");
	            transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
 }
	 
	 
	 public  List<ConsentGroupTxn> getlist() {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        List<ConsentGroupTxn> consentGroupTxnlist = null ;
	        try {
	              
	            transaction = session.beginTransaction();
	            consentGroupTxnlist = session.createQuery("from ConsentGroupTxn").list();
	            for (Iterator iterator = consentGroupTxnlist.iterator(); iterator.hasNext();) {
	            	ConsentGroupTxn consentGroupTxn = (ConsentGroupTxn) iterator.next();
	                System.out.println("Transaction ::" + consentGroupTxn.getConsentgrouptxnid());
	            }
	            transaction.commit();
	        } catch (HibernateException e) {
	            transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
	        
	        return consentGroupTxnlist ;
	    }
	 
	 
	 
	 public ConsentGroupTxn getById(long id)
	 {
		 
		  Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction transaction = null;
	      ConsentGroupTxn consentGroupTxn = null ;	      
		 
	      try {		 
		 	          session.beginTransaction();		 	             
		 	          consentGroupTxn = (ConsentGroupTxn) session.get(ConsentGroupTxn.class, new BigDecimal(id));		 
		 	          	 
		 	          session.getTransaction().commit();
		         }		 
		 	catch (HibernateException e) {		 
		 	            e.printStackTrace();		 
		 	            session.getTransaction().rollback();
		 
		 	    }
		 	return consentGroupTxn ;
	 }
	 
	
		 
	 public void delete(long id)
	 {
		 
		  Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction transaction = null;
	      
		 	try {		 
		 	           session.beginTransaction();		 	             
		 	           ConsentGroupTxn consentGroupTxn =  (ConsentGroupTxn) session.get(ConsentGroupTxn.class, new BigDecimal(id));
		 
		 	           session.delete(consentGroupTxn);
		 	          	 
		 	          session.getTransaction().commit();
		         }		 
		 	catch (HibernateException e) {		 
		 	            e.printStackTrace();		 
		 	            session.getTransaction().rollback();
		 
		 	    }
	 }
	 
	 public String deleteBytxnID(long id)
	 {
		 
		  Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction transaction = null;
	      Transaction cosdabactransaction = null ;
	      
		 	try {		 
		 	         session.beginTransaction();		 	             
		 	         cosdabactransaction = (Transaction) session.get(Transaction.class, new BigDecimal(id));
		 	          
		 	          Query query = session.createQuery("Delete ConsentGroupTxn conttxn where  conttxn.cosdabactransaction = :NewValue" );
		 	          query.setParameter("NewValue", cosdabactransaction);
		 	         
		 	          int result = query.executeUpdate();
		 	          
		 	          session.getTransaction().commit();
		 	          System.out.println("Record Deleted ..");
		 	          
		 	         return "Record deleted" ;
		         }		 
		 	catch (HibernateException e) {		 
		 	            e.printStackTrace();		 
		 	            session.getTransaction().rollback();
		 	           return "Error- while delete operation" ;
		 
		 	    }
		 	finally
		 	{
		 		session.close();	
		 	}
	 }

	 public String update( ConsentGroupTxn consentGroupTxn ) {
		 
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        ConsentGroupTxn old_consentGroupTxn  = null ;
	        ConsentGroupTxnDao consentGroupTxndao = new ConsentGroupTxnDao();
	        try {
	              
	            transaction = session.beginTransaction();
	            
	            old_consentGroupTxn = consentGroupTxndao.getById(consentGroupTxn.getConsentgrouptxnid().longValue());
	            
	            if(old_consentGroupTxn == null)
	            return "Consent Group - is not present" ;
	            
	            //old_cosdauthorizationgroup.setJobid(cosdauthorizationgroup.getJobid());
	            session.update(old_consentGroupTxn);
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
