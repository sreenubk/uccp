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

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lotus.uccp.util.Constant;
import com.lotus.uccp.util.HibernateUtil;
import com.lotus.uccp.consentmgmt.wcm.dto.RegistrationRequest;
import com.lotus.uccp.wcm.vo.CohortVO;


public class RegistrationRequestDao implements java.io.Serializable {
	
	public RegistrationRequest save(RegistrationRequest RegistrationRequest) {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        try {
	        		
	            transaction = session.beginTransaction();
	            
	            session.save(RegistrationRequest);
	            transaction.commit();
	        }catch (HibernateException e) {
	        	System.out.println("Exception ..mmm");
	            transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	            return RegistrationRequest ;
	        }
  }
	
	 public  List<RegistrationRequest> getlist() {
		 
		 	System.out.println(" -- getlist() ---");
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        List<RegistrationRequest> cosdpersonconsentlist = null ;
	        try {
	              
	            transaction = session.beginTransaction();
	            cosdpersonconsentlist = session.createQuery(" from RegistrationRequest request ORDER BY request.createdDate ").list();
	            for (Iterator iterator = cosdpersonconsentlist.iterator(); iterator.hasNext();) {
	            	RegistrationRequest cosdpersonconsent = (RegistrationRequest) iterator.next();
	                
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
	 
	 public  List<RegistrationRequest> getlistWithCohortID(CohortVO cohort ) {
		 
		 	System.out.println(" -- getlistWithCohortID() ---" + cohort.getCohortid());
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        List<RegistrationRequest> cosdpersonconsentlist = null ;
	        try {
	              
	            transaction = session.beginTransaction();
	            
	            Query query = session.createQuery("from RegistrationRequest request where cohortID = :ID");
	            query.setBigDecimal("ID",cohort.getCohortid());
	            cosdpersonconsentlist = query.list();
	            for (Iterator iterator = cosdpersonconsentlist.iterator(); iterator.hasNext();) {
	            	RegistrationRequest cosdpersonconsent = (RegistrationRequest) iterator.next();
	                
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
	 
	 public  List<RegistrationRequest> getlistWithStatus(String  status ) {
		 
		 	//System.out.println(" -- getlistWithCohortID() ---" + cohort.getCohortid());
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        List<RegistrationRequest> cosdpersonconsentlist = null ;
	        try {
	              
	            transaction = session.beginTransaction();
	            
	            Query query = session.createQuery("from RegistrationRequest request where status = :STS");
	            query.setParameter("STS",status);
	            cosdpersonconsentlist = query.list();
	            for (Iterator iterator = cosdpersonconsentlist.iterator(); iterator.hasNext();) {
	            	RegistrationRequest cosdpersonconsent = (RegistrationRequest) iterator.next();
	                
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
	
	 public RegistrationRequest getById(long id)
	 {
		 
		  Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction transaction = null;
	      RegistrationRequest RegistrationRequest = null ;	      
		 
	      try {		 
		 	          session.beginTransaction();		 	             
		 	          RegistrationRequest = (RegistrationRequest) session.get(RegistrationRequest.class, new BigDecimal(id));		 
		 	          	 
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
		 	return RegistrationRequest ;
	 }
	 
	
		 
	 public String delete(long id)
	 {
		 
		  Session session = HibernateUtil.getSessionFactory().openSession();
	     // Transaction transaction = null;
	      
		 	try {		 
		 	           session.beginTransaction();		 	             
		 	           RegistrationRequest RegistrationRequest =  (RegistrationRequest) session.get(RegistrationRequest.class, new BigDecimal(id));
		 
		 	           session.delete(RegistrationRequest);
		 	          	 
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

	 public String update( RegistrationRequest RegistrationRequest ) {
		 
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        RegistrationRequest old_RegistrationRequest  = null ;
	        RegistrationRequestDao RegistrationRequestdao = new RegistrationRequestDao();
	        
	        try {
	              
	            transaction = session.beginTransaction(); 
	            
	            //old_RegistrationRequest = RegistrationRequestdao.getById(RegistrationRequest.getPersonconsentid().longValue());	            
	            //if(old_RegistrationRequest == null)
	            //return "Consent Person - is not present" ;	            
	            //old_cosdauthorizationgroup.setJobid(cosdauthorizationgroup.getJobid());
	            
	            session.update(RegistrationRequest);
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


/*-- Table: Registration_request
CREATE TABLE Registration_request (
    reqReqtID number(25,2)  NOT NULL,
    cohortID number(25,2)  NOT NULL,
    ruleID number(19,2)  NOT NULL,
    reasoncode varchar2(40)  NOT NULL,
    createdDate date  NOT NULL,
    createdByUser varchar2(40)  NOT NULL,
    sourceSys varchar2(40)  NOT NULL,
    sourceSysID varchar2(40)  NOT NULL,
    fName varchar2(30)  NOT NULL,
    lName varchar2(40)  NOT NULL,
    status varchar2(40)  NOT NULL,
    comments varchar2(100)  NOT NULL,
    CONSTRAINT Registration_request_pk PRIMARY KEY (reqReqtID)
) ; */