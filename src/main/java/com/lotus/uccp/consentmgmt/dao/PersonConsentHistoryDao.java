/* Licensed Materials - 
 Property of IBM 6949 - 67L 
 Copyright IBM Corp. 2017, 2018 All Rights Reserved */
package  com.lotus.uccp.consentmgmt.dao;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lotus.uccp.util.HibernateUtil;
import com.lotus.uccp.authentication.model.PersonConsentHistory;

public class PersonConsentHistoryDao { 

	
	 public void save(PersonConsentHistory personConsentHistory) {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        try {
	        		
	            transaction = session.beginTransaction();
	            
	            session.save(personConsentHistory);
	            transaction.commit();
	        }catch (HibernateException e) {
	        	System.out.println("Exception ..mmm");
	            transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
}
	 
	 
	 public  List<PersonConsentHistory> getlist() {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        List<PersonConsentHistory> personConsentHistorylist = null ;
	        try {
	              
	            transaction = session.beginTransaction();
	            personConsentHistorylist = session.createQuery("from PersonConsentHistory").list();
	            for (Iterator iterator = personConsentHistorylist.iterator(); iterator.hasNext();) {
	            	PersonConsentHistory personConsentHistory = (PersonConsentHistory) iterator.next();
	                System.out.println("Transaction ::" + personConsentHistory.getConsenthistoryid());
	            }
	            transaction.commit();
	        } catch (HibernateException e) {
	            transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
	        
	        return personConsentHistorylist ;
	    }
	 
	 
	 
	 public PersonConsentHistory getById(long id)
	 {
		 
		  Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction transaction = null;
	      PersonConsentHistory personConsentHistory = null ;	      
		 
	      try {		 
		 	          session.beginTransaction();		 	             
		 	          personConsentHistory = (PersonConsentHistory) session.get(PersonConsentHistory.class, new BigDecimal(id));		 
		 	          	 
		 	          session.getTransaction().commit();
		         }		 
		 	catch (HibernateException e) {		 
		 	            e.printStackTrace();		 
		 	            session.getTransaction().rollback();
		 
		 	    }
		 	return personConsentHistory ;
	 }
	 
	
		 
	 public void delete(long id)
	 {
		 
		  Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction transaction = null;
	      
		 	try {		 
		 	           session.beginTransaction();		 	             
		 	           PersonConsentHistory personConsentHistory =  (PersonConsentHistory) session.get(PersonConsentHistory.class, new BigDecimal(id));
		 
		 	           session.delete(personConsentHistory);
		 	          	 
		 	          session.getTransaction().commit();
		         }		 
		 	catch (HibernateException e) {		 
		 	            e.printStackTrace();		 
		 	            session.getTransaction().rollback();
		 
		 	    }
	 }

	 public String update( PersonConsentHistory personConsentHistory ) {
		 
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        PersonConsentHistory old_personConsentHistory  = null ;
	        PersonConsentHistoryDao personConsentHistorydao = new PersonConsentHistoryDao();
	        try {
	              
	            transaction = session.beginTransaction();
	            
	            old_personConsentHistory = personConsentHistorydao.getById(personConsentHistory.getConsenthistoryid().longValue());
	            
	            if(old_personConsentHistory == null)
	            return "Consent Group - is not present" ;
	            
	            //old_cosdauthorizationgroup.setJobid(cosdauthorizationgroup.getJobid());
	            session.update(old_personConsentHistory);
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
