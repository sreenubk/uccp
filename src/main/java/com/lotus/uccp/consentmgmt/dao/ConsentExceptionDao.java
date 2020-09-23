package  com.lotus.uccp.consentmgmt.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lotus.uccp.util.HibernateUtil;
import com.lotus.uccp.authentication.model.ConsentException;
import com.lotus.uccp.authentication.model.ConsentGroup;
import com.lotus.uccp.authentication.model.PersonConsent;
import com.lotus.uccp.vo.PersonConsentVO;

public class ConsentExceptionDao {
	
	
	public String insertList(PersonConsentVO personConsentvo ,String userName)
	{
		String consentList = personConsentvo.getConsentCatList() ; // categorylist
		
		System.out.println("::consentList ::" + consentList);
		
		if(consentList == null || consentList.trim().equalsIgnoreCase(""))
			return "consent List is empty "; 
		
		ConsentException consentExceptpion = new ConsentException();
    	ConsentExceptionDao consentExceptpiondao = new ConsentExceptionDao();
    	PersonConsentDao personConsentdao = new PersonConsentDao();
    	ConsentGroupDao consentGroupdao = new ConsentGroupDao() ;
    	
    	PersonConsent personConsent = personConsentdao.getById(personConsentvo.getPersonconsentid().longValue());
		int cnt = 0 ; 
		for (String consentCat: consentList.split(",")) {
			
			long consentGrpCat = Long.parseLong(consentCat);
			ConsentGroup consentGroup = consentGroupdao.getById(consentGrpCat);
			consentExceptpion.setConsentGroup(consentGroup);
	    	consentExceptpion.setPersonConsent(personConsent);
	    	consentExceptpion.setConsentstatus("Yes");
	    	
	    	consentExceptpion.setCreatedby(userName);
	    	consentExceptpion.setCreatedon(new Date());
	    	//consentExceptpion.setLastupdatedby(username);
	    	//consentExceptpion.setLastupdatedo(new Date());
	    	consentExceptpiondao.save(consentExceptpion);
	        System.out.println("::ConsentException insertLoop ::" + ++cnt + ": ::" + consentGrpCat);
	      }
		
		System.out.println("::success Exception insert::");
		return "success"; 
	}
	
	 public void save(ConsentException consentExceptpion) {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        try {
	        		
	            transaction = session.beginTransaction();
	            
	            session.save(consentExceptpion);
	            transaction.commit();
	        }catch (HibernateException e) {
	        	System.out.println("Exception ..mmm");
	            transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
}
	 
	 
	 public  List<ConsentException> getlist() {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        List<ConsentException> consentExceptpionlist = null ;
	        try {
	              
	            transaction = session.beginTransaction();
	            consentExceptpionlist = session.createQuery("from ConsentException").list();
	            for (Iterator iterator = consentExceptpionlist.iterator(); iterator.hasNext();) {
	            	ConsentException consentExceptpion = (ConsentException) iterator.next();
	                System.out.println("Transaction ::" + consentExceptpion.getConsentexceptionid());
	            }
	            transaction.commit();
	        } catch (HibernateException e) {
	            transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
	        
	        return consentExceptpionlist ;
	    }
	 
	 
	 
	 public ConsentException getById(long id)
	 {
		 
		  Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction transaction = null;
	      ConsentException consentExceptpion = null ;	      
		 
	      try {		 
		 	          session.beginTransaction();		 	             
		 	          consentExceptpion = (ConsentException) session.get(ConsentException.class, new BigDecimal(id));		 
		 	          	 
		 	          session.getTransaction().commit();
		         }		 
		 	catch (HibernateException e) {		 
		 	            e.printStackTrace();		 
		 	            session.getTransaction().rollback();
		 
		 	    }
		 	return consentExceptpion ;
	 }
	 
	
		 
	 public void delete(long id)
	 {
		 
		  Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction transaction = null;
	      
		 	try {		 
		 	           session.beginTransaction();		 	             
		 	           ConsentException consentExceptpion =  (ConsentException) session.get(ConsentException.class, new BigDecimal(id));
		 
		 	           session.delete(consentExceptpion);
		 	          	 
		 	          session.getTransaction().commit();
		         }		 
		 	catch (HibernateException e) {		 
		 	            e.printStackTrace();		 
		 	            session.getTransaction().rollback();
		 
		 	    }
	 }
	 
	 public void deleteByPersonConsent(long id)
	 { 
		 
		  Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction transaction = null;
	      
		 	try {		 
		 	           session.beginTransaction();	
		 	          PersonConsentDao personConsentdao = new PersonConsentDao();
		 	           PersonConsent personConsent = personConsentdao.getById(id);
		 	           Query qry = session.createQuery("delete from ConsentException conexcpt where conexcpt.personConsent=:personID");
		 	           qry.setParameter("personID",personConsent);
		 	          
		 	           int res = qry.executeUpdate();		 	          	 
		 	           session.getTransaction().commit();
		 	           
		 	           System.out.println("Deleted Exception...using PersonConsent ID:: " + id);
		         }		 
		 	catch (HibernateException e) {		 
		 	            e.printStackTrace();		 
		 	            session.getTransaction().rollback();
		 
		 	    }
	 }

	 public String update( ConsentException consentExceptpion ) {
		 
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        ConsentException old_consentExceptpion  = null ;
	        ConsentExceptionDao consentExceptpiondao = new ConsentExceptionDao();
	        try {
	              
	            transaction = session.beginTransaction();
	            
	            old_consentExceptpion = consentExceptpiondao.getById(consentExceptpion.getConsentexceptionid().longValue());
	            
	            if(old_consentExceptpion == null)
	            return "Consent Group - is not present" ;
	            
	            //old_cosdauthorizationgroup.setJobid(cosdauthorizationgroup.getJobid());
	            session.update(old_consentExceptpion);
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
