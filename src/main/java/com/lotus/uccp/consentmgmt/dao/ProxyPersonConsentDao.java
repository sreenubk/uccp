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
import com.lotus.uccp.consentmgmt.dto.ProxyPersonConsent;

public class ProxyPersonConsentDao { 
	
	 public void save(ProxyPersonConsent personConsent) {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        try {
	        		
	            transaction = session.beginTransaction();
	            
	            session.save(personConsent);
	            transaction.commit();
	        }catch (HibernateException e) {
	        	System.out.println("Exception ..mmm");
	            transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
}
	 
	 
	 public  List<ProxyPersonConsent> getlist() {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        List<ProxyPersonConsent> personConsentlist = null ;
	        try {
	              
	            transaction = session.beginTransaction();
	            personConsentlist = session.createQuery("from ProxyPersonConsent").list();
	            for (Iterator iterator = personConsentlist.iterator(); iterator.hasNext();) {
	            	ProxyPersonConsent personConsent = (ProxyPersonConsent) iterator.next();
	                System.out.println("Transaction ::" + personConsent.getProxyconsentid());
	            }
	            transaction.commit();
	        } catch (HibernateException e) {
	            transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
	        
	        return personConsentlist ;
	    }
	 
	 
	 
	 public ProxyPersonConsent getById(long id)
	 {
		 
		  Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction transaction = null;
	      ProxyPersonConsent personConsent = null ;	      
		 
	      try {		 
		 	          session.beginTransaction();		 	             
		 	          personConsent = (ProxyPersonConsent) session.get(ProxyPersonConsent.class, new BigDecimal(id));		 
		 	          	 
		 	          session.getTransaction().commit();
		         }		 
		 	catch (HibernateException e) {		 
		 	            e.printStackTrace();		 
		 	            session.getTransaction().rollback();
		 
		 	    }
		 	return personConsent ;
	 }
	 
	
		 
	 public void delete(long id)
	 {
		 
		  Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction transaction = null;
	      
		 	try {		 
		 	           session.beginTransaction();		 	             
		 	           ProxyPersonConsent personConsent =  (ProxyPersonConsent) session.get(ProxyPersonConsent.class, new BigDecimal(id));
		 
		 	           session.delete(personConsent);
		 	          	 
		 	          session.getTransaction().commit();
		         }		 
		 	catch (HibernateException e) {		 
		 	            e.printStackTrace();		 
		 	            session.getTransaction().rollback();
		 
		 	    }
	 }

	 public String update( ProxyPersonConsent personConsent ) {
		 
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        ProxyPersonConsent old_personConsent  = null ;
	        ProxyPersonConsentDao personConsentdao = new ProxyPersonConsentDao();
	        try {
	              
	            transaction = session.beginTransaction();
	            
	            old_personConsent = personConsentdao.getById(personConsent.getProxyconsentid().longValue());
	            
	            if(old_personConsent == null)
	            return "proxypersonconsent - is not present" ;
	            
	            //old_cosdauthorizationgroup.setJobid(cosdauthorizationgroup.getJobid());
	            session.update(old_personConsent);
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
