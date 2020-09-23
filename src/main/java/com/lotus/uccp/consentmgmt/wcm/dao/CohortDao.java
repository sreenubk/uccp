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
import com.lotus.uccp.consentmgmt.wcm.dto.Cohort;
import com.lotus.uccp.consentmgmt.wcm.dto.RegistrationRequest;
import com.lotus.uccp.wcm.vo.CohortVO;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CohortDao {
	
	 public Cohort save(Cohort Cohort) {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        try {
	        		
	            transaction = session.beginTransaction();
	            
	            session.save(Cohort);
	            transaction.commit();
	        }catch (HibernateException e) {
	        	System.out.println("Exception ..mmm");
	            transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	            return Cohort ;
	        }
     }
	
	 public  List<Cohort> getlist() {
		 
		 	System.out.println(" ");
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        List<Cohort> cosdpersonconsentlist = null ;
	        try {
	              
	            transaction = session.beginTransaction();
	            cosdpersonconsentlist = session.createQuery("from Cohort ").list();
	            for (Iterator iterator = cosdpersonconsentlist.iterator(); iterator.hasNext();) {
	            	Cohort cosdpersonconsent = (Cohort) iterator.next();
	                
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
	 
	 public  List<Cohort> getlistwithName(String name) {
		 
		 	System.out.println(" ");
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        List<Cohort> cosdpersonconsentlist = null ;
	        try {
	              
	            transaction = session.beginTransaction();
	            
	            
	            cosdpersonconsentlist = session.createQuery("from Cohort where name LIKE  '%" + name +"%'").list();
	            for (Iterator iterator = cosdpersonconsentlist.iterator(); iterator.hasNext();) {
	            	Cohort cosdpersonconsent = (Cohort) iterator.next();
	                System.out.println("::--Name :: -- " + cosdpersonconsent.getName());
	                
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
	
	 public Cohort getById(long id)
	 {
		 
		  Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction transaction = null;
	      Cohort Cohort = null ;	      
		 
	      try {		 
		 	          session.beginTransaction();		 	             
		 	          Cohort = (Cohort) session.get(Cohort.class, new BigDecimal(id));		 
		 	          	 
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
		 	return Cohort ;
	 }
	 
	 public  Cohort getlistWithCohortID(CohortVO cohort ) {
		 
		 	System.out.println(" -- getlistWithCohortID() ---" + cohort.getCohortid());
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        List<Cohort> cosdpersonconsentlist = null ;
	        Cohort cohortvo = new Cohort();
	        try {
	              
	            transaction = session.beginTransaction();
	           // Cohort cohortvo = new Cohort();
	            Query query = session.createQuery("from Cohort  where cohortid = :ID");
	            query.setBigDecimal("ID",cohort.getCohortid());
	            cosdpersonconsentlist = query.list();
	            for (Iterator iterator = cosdpersonconsentlist.iterator(); iterator.hasNext();) {
	            	cohortvo = (Cohort) iterator.next();
	                
	            }
	            transaction.commit();
	        } catch (HibernateException e) {
	            transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
	        
	        return cohortvo ;
	    }
	 
	
		 
	 public String delete(long id)
	 {
		 
		  Session session = HibernateUtil.getSessionFactory().openSession();
	     // Transaction transaction = null;
	      
		 	try {		 
		 	           session.beginTransaction();		 	             
		 	           Cohort Cohort =  (Cohort) session.get(Cohort.class, new BigDecimal(id));
		 
		 	           session.delete(Cohort);
		 	          	 
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

	 public String update( Cohort Cohort ) {
		 
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        Cohort old_Cohort  = null ;
	        CohortDao Cohortdao = new CohortDao();
	        
	        try {
	              
	            transaction = session.beginTransaction(); 
	            
	            //old_Cohort = Cohortdao.getById(Cohort.getPersonconsentid().longValue());	            
	            //if(old_Cohort == null)
	            //return "Consent Person - is not present" ;	            
	            //old_cosdauthorizationgroup.setJobid(cosdauthorizationgroup.getJobid());
	            
	            session.update(Cohort);
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

/*CREATE TABLE Cohort (
	    CohortedID varchar2(30)  NOT NULL,
	    CohortDescription varchar2(100)  NOT NULL,
	    CONSTRAINT Cohort_pk PRIMARY KEY (CohortedID)
	) ; */
