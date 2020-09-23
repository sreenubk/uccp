package com.lotus.uccp.consentmgmt.wcm.dao ;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lotus.uccp.util.Constant;
import com.lotus.uccp.util.HibernateUtil;
import com.lotus.uccp.consentmgmt.wcm.dto.ReasonCode;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ReasonCodeDao {
	
	 public ReasonCode save(ReasonCode ReasonCode) {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        try {
	        		
	            transaction = session.beginTransaction();
	            
	            session.save(ReasonCode);
	            transaction.commit();
	        }catch (HibernateException e) {
	        	System.out.println("Exception ..mmm");
	            transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	            return ReasonCode ;
	        }
     }
	
	 public  List<ReasonCode> getlist() {
		 
		 	System.out.println(" ");
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        List<ReasonCode> cosdpersonconsentlist = null ;
	        try {
	              
	            transaction = session.beginTransaction();
	            cosdpersonconsentlist = session.createQuery("from ReasonCode ").list();
	            for (Iterator iterator = cosdpersonconsentlist.iterator(); iterator.hasNext();) {
	            	ReasonCode cosdpersonconsent = (ReasonCode) iterator.next();
	                
	            }
	            transaction.commit();
	        } catch (HibernateException e) {
	            transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
	        
	        return cosdpersonconsentlist ;
	    }
	
	 public ReasonCode getById(String id)
	 {
		 
		  Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction transaction = null;
	      ReasonCode ReasonCode = null ;	      
		 
	      try {		 
		 	          session.beginTransaction();		 	             
		 	          ReasonCode = (ReasonCode) session.get(ReasonCode.class, id);		 
		 	          	 
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
		 	return ReasonCode ;
	 }
	 
	
		 
	 public String delete(String id)
	 {
		 
		  Session session = HibernateUtil.getSessionFactory().openSession();
	     // Transaction transaction = null;
	      
		 	try {		 
		 	           session.beginTransaction();		 	             
		 	           ReasonCode ReasonCode =  (ReasonCode) session.get(ReasonCode.class, id);
		 
		 	           session.delete(ReasonCode);
		 	          	 
		 	          session.getTransaction().commit();
		         }		 
		 	catch (HibernateException e) {		 
		 	            e.printStackTrace();		 
		 	            session.getTransaction().rollback();
		 	           return "Error - in deletion" ;
		 
		 	    }
		 	finally
		 	{
		 		session.close();
		 	}
		 	
		 	return "deleted" ;
	 }

	 public String update( ReasonCode ReasonCode ) {
		 
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        ReasonCode old_ReasonCode  = null ;
	        ReasonCodeDao ReasonCodedao = new ReasonCodeDao();
	        
	        try {
	              
	            transaction = session.beginTransaction(); 
	            
	            //old_ReasonCode = ReasonCodedao.getById(ReasonCode.getPersonconsentid().longValue());	            
	            //if(old_ReasonCode == null)
	            //return "Consent Person - is not present" ;	            
	            //old_cosdauthorizationgroup.setJobid(cosdauthorizationgroup.getJobid());
	            
	            session.update(ReasonCode);
	            transaction.commit();
	        }catch (HibernateException e) {
	            transaction.rollback();
	            e.printStackTrace();
	            return "fail"; 
	        } finally {
	            session.close();
	        }
	        return "Updated" ;
   }
	

}

/*CREATE TABLE ReasonCode (
	    ReasonCodeedID varchar2(30)  NOT NULL,
	    ReasonCodeDescription varchar2(100)  NOT NULL,
	    CONSTRAINT ReasonCode_pk PRIMARY KEY (ReasonCodeedID)
	) ; */
