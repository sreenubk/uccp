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
import com.lotus.uccp.consentmgmt.wcm.dto.CohortTarget;
import com.lotus.uccp.consentmgmt.wcm.dto.Target;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CohortTargetDao {
	
	public CohortTarget save(CohortTarget CohortTarget) {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        try {
	        		
	            transaction = session.beginTransaction();	            
	            session.save(CohortTarget);
	            transaction.commit();
	            
	        }catch (HibernateException e) {
	        	System.out.println("Exception ..mmm");
	            transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	            return CohortTarget ;
	        }
     }
	
	 public  List<CohortTarget> getlistTarget(long id) {
		 
		 	System.out.println(" ");
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        List<CohortTarget> cosdpersonconsentlist = null ;
	        try {
	        	Target target = new Target();
	        	TargetDao dao = new TargetDao();
	        	target = dao.getById(id);
	            transaction = session.beginTransaction();
	            
	            Query query = session.createQuery("from CohortTarget where  targetedID = :targetName ");
	            query.setParameter("targetName",target);	            
	            cosdpersonconsentlist = query.list();
	            //System.out.println(":: Size ::-- " + cosdpersonconsentlist.size());
	            for (Iterator iterator = cosdpersonconsentlist.iterator(); iterator.hasNext();) {
	            	CohortTarget cohorttarget = (CohortTarget) iterator.next();
	                System.out.println(":: Name ::" + cohorttarget.getName());
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
	 public  List<CohortTarget> getlist() {
		 
		 	System.out.println(" ");
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        List<CohortTarget> cosdpersonconsentlist = null ;
	        try {
	              
	            transaction = session.beginTransaction();
	            cosdpersonconsentlist = session.createQuery("from CohortTarget ").list();
	            for (Iterator iterator = cosdpersonconsentlist.iterator(); iterator.hasNext();) {
	            	CohortTarget cosdpersonconsent = (CohortTarget) iterator.next();
	                
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
	
	 public CohortTarget getById(long id)
	 {
		 
		  Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction transaction = null;
	      CohortTarget CohortTarget = null ;	      
		 
	      try {		 
		 	          session.beginTransaction();		 	             
		 	          CohortTarget = (CohortTarget) session.get(CohortTarget.class, new BigDecimal(id));		 
		 	          	 
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
		 	return CohortTarget ;
	 }
	 
	
		 
	 public String delete(long id)
	 {
		 
		  Session session = HibernateUtil.getSessionFactory().openSession();
	     // Transaction transaction = null;
	      
		 	try {		 
		 	           session.beginTransaction();		 	             
		 	           CohortTarget CohortTarget =  (CohortTarget) session.get(CohortTarget.class, new BigDecimal(id));
		 
		 	           session.delete(CohortTarget);
		 	          	 
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

	 public String update( CohortTarget CohortTarget ) {
		 
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        CohortTarget old_CohortTarget  = null ;
	        CohortTargetDao CohortTargetdao = new CohortTargetDao();
	        
	        try {
	              
	            transaction = session.beginTransaction(); 
	            
	            //old_CohortTarget = CohortTargetdao.getById(CohortTarget.getPersonconsentid().longValue());	            
	            //if(old_CohortTarget == null)
	            //return "Consent Person - is not present" ;	            
	            //old_cosdauthorizationgroup.setJobid(cosdauthorizationgroup.getJobid());
	            
	            session.update(CohortTarget);
	            transaction.commit();
	        }catch (HibernateException e) {
	            transaction.rollback();
	            e.printStackTrace();
	            return "fail" ;
	        } finally {
	            session.close();
	        }
	        return "Updated" ;
   }
	

}

/*CREATE TABLE CohortTarget (
	    CohortTargetedID varchar2(30)  NOT NULL,
	    CohortTargetDescription varchar2(100)  NOT NULL,
	    CONSTRAINT CohortTarget_pk PRIMARY KEY (CohortTargetedID)
	) ; */
