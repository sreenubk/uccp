package com.lotus.uccp.consentmgmt.dao ;
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
import com.lotus.uccp.consentmgmt.dto.Job;
import com.lotus.uccp.consentmgmt.wcm.dto.Cohort;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
public class JobDao {
	
	 public Job save(Job Job) {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        try {
	        		
	            transaction = session.beginTransaction();
	            
	            session.save(Job);
	            transaction.commit();
	        }catch (HibernateException e) {
	        	System.out.println("Exception ..mmm");
	            transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	            return Job ;
	        }
     }
	 
	 public  List<Job> getlistwithName(String name) {
		 
		 	System.out.println(" ");
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        List<Job> joblist = null ;
	        try {
	              
	            transaction = session.beginTransaction();
	            
	            
	            joblist = session.createQuery("from Job where jobname LIKE  '%" + name +"%'").list();
	            for (Iterator iterator = joblist.iterator(); iterator.hasNext();) {
	            	Job job = (Job) iterator.next();
	                System.out.println("::--Name :: -- " + job.getJobname());
	                
	            }
	            transaction.commit();
	        } catch (HibernateException e) {
	            transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
	        
	        return joblist ;
	    }
	
	 public  List<Job> getlist() {
		 
		 	System.out.println(" Job Dao List");
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        List<Job> cosdpersonconsentlist = null ;
	        try {
	              
	            transaction = session.beginTransaction();
	            cosdpersonconsentlist = session.createQuery("from Job ").list();
	            for (Iterator iterator = cosdpersonconsentlist.iterator(); iterator.hasNext();) {
	            	Job cosdpersonconsent = (Job) iterator.next();
	                
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
	
	 public Job getById(long id)
	 {
		 
		  Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction transaction = null;
	      Job Job = null ;	      
		 
	      try {		 
		 	          session.beginTransaction();		 	             
		 	          Job = (Job) session.get(Job.class, new BigDecimal(id));		 
		 	          	 
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
		 	return Job ;
	 }
	 
	
		 
	 public String delete(long id)
	 {
		 
		  Session session = HibernateUtil.getSessionFactory().openSession();
	     // Transaction transaction = null;
	      
		 	try {		 
		 	           session.beginTransaction();		 	             
		 	           Job Job =  (Job) session.get(Job.class, new BigDecimal(id));		 
		 	           session.delete(Job);		 	          	 
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

	 public String update( Job Job ) {
		 
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        Job old_Job  = null ;
	        JobDao Jobdao = new JobDao();
	        
	        try {
	              
	            transaction = session.beginTransaction(); 
	            
	            //old_Job = Jobdao.getById(Job.getPersonconsentid().longValue());	            
	            //if(old_Job == null)
	            //return "Consent Person - is not present" ;	            
	            //old_cosdauthorizationgroup.setJobid(cosdauthorizationgroup.getJobid());
	            
	            session.update(Job);
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

/*CREATE TABLE Job (
	    JobedID varchar2(30)  NOT NULL,
	    JobDescription varchar2(100)  NOT NULL,
	    CONSTRAINT Job_pk PRIMARY KEY (JobedID)
	) ; */
