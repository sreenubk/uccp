package  com.lotus.uccp.consentmgmt.dao;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lotus.uccp.util.HibernateUtil;
import com.lotus.uccp.authentication.model.ControlGroup;
import com.lotus.uccp.authentication.model.ControlObject;

public class ControlObjectDao {
	
	 public void save(ControlObject controlObject) {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        try {
	        		
	            transaction = session.beginTransaction();
	            
	            session.save(controlObject);
	            transaction.commit();
	        }catch (HibernateException e) {
	        	System.out.println("Exception ..mmm");
	            transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
    }
	 
	 
	 public  List<ControlObject> getlist() {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        List<ControlObject> controlObjectlist = null ;
	        try {
	              
	            transaction = session.beginTransaction();
	            controlObjectlist = session.createQuery("from ControlGroup").list();
	            for (Iterator iterator = controlObjectlist.iterator(); iterator.hasNext();) {
	            	ControlObject controlObject = (ControlObject) iterator.next();
	                System.out.println("Transaction ::" + controlObject.getTransaction());
	            }
	            transaction.commit();
	        } catch (HibernateException e) {
	            transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
	        
	        return controlObjectlist ;
	    }
	 
	 
	 
	 public ControlObject getById(long id)
	 {
		 
		  Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction transaction = null;
	      ControlObject controlObject = null ;	      
		 
	      try {		 
		 	          session.beginTransaction();		 	             
		 	          controlObject = (ControlObject) session.get(ControlObject.class, new BigDecimal(id));		 
		 	          System.out.println(controlObject.getControlGroup()+ " - " + controlObject.getTransaction());	 
		 	          session.getTransaction().commit();
		         }		 
		 	catch (HibernateException e) {		 
		 	            e.printStackTrace();		 
		 	            session.getTransaction().rollback();
		 
		 	    }
		 	return controlObject ;
	 }
	 
	 public ControlObject getByGrpId(long id)
	 {
		 
		  Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction transaction = null;
	      ControlObject controlObject = null ;	      
	      ControlGroup cosdabaccontrolgroup = null ;
	      List<ControlObject> controlObjectlist = null ;
	      
	      try {		 
		 	          session.beginTransaction();		 	 
		 	          // 1. Take cosdabaccontrolgroup
		 	          ControlGroupDao controlGroupDao = new ControlGroupDao();
		 	          cosdabaccontrolgroup = controlGroupDao.getById(id);
		 	          
		 	          Query query = session.createQuery("from ControlObject where  cosdabaccontrolgroup = :cosdabaccontrolgroupId ");
			          query.setParameter("cosdabaccontrolgroupId", cosdabaccontrolgroup);
			          controlObjectlist = query.list();
			          
			          if(controlObjectlist.isEmpty())
			        	  return controlObject ;
			          
			          for (Iterator iterator = controlObjectlist.iterator(); iterator.hasNext();) {
			        	  controlObject = (ControlObject) iterator.next();
			                
			            }
		 	         
			          System.out.println(controlObject.getControlGroup()+ " - " + controlObject.getTransaction());	 
		 	          session.getTransaction().commit();
		         }		 
		 	catch (HibernateException e) {		 
		 	            e.printStackTrace();		 
		 	            session.getTransaction().rollback();
		 
		 	    }
	      finally{
	    	  session.close();
	      }
		 	return controlObject ;
	 }
	 
	 public ControlObject getByTxnID(long txnid)
	 {
		 
		  Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction transaction = null;
	      ControlObject controlObject = null ;	      
	      com.lotus.uccp.authentication.model.Transaction cosdabactransaction = null ;
	      List<Transaction> cosdabactransactionlist = null ;
	      
	      try {		  
		 	          session.beginTransaction();		 	 
		 	          // 1. Take cosdabaccontrolgroup
		 	          TransactionDao cosdabactransactiondao = new TransactionDao();
		 	          cosdabactransaction = cosdabactransactiondao.getById(txnid);
		 	          
		 	          Query query = session.createQuery("from ControlObject where  cosdabactransaction = :txnID ");
			          query.setParameter("txnID", cosdabactransaction);
			          cosdabactransactionlist = query.list();
			          
			          for (Iterator iterator = cosdabactransactionlist.iterator(); iterator.hasNext();) {
			        	  controlObject = (ControlObject) iterator.next();
			                
			            }
		 	         
			          //System.out.println(controlObject.getControlGroup()+ " - " + controlObject.getTransaction());	 
		 	          session.getTransaction().commit();
		         }		 
		 	catch (HibernateException e) {		 
		 	            e.printStackTrace();		 
		 	            session.getTransaction().rollback();
		 
		 	    }
	      finally{
	    	  session.close();
	      }
		 	return controlObject ;
	 }
	 
	 public ControlObject getByTxnID(com.lotus.uccp.authentication.model.Transaction cosdabactransaction,ControlGroup controlgrp)
	 {
		 
		  Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction transaction = null;
	      ControlObject controlObject = null ;	      
	     
	      List<Transaction> cosdabactransactionlist = null ;
	      
	      try {		  
		 	          session.beginTransaction();		 	 
		 	          // 1. Take cosdabaccontrolgroup
		 	          
		 	        
		 	          
		 	          Query query = session.createQuery("from ControlObject where  cosdabactransaction = :txnID and cosdabaccontrolgroup =:grpID and cosdabacconstraint is null");
			          query.setParameter("txnID", cosdabactransaction);
			          query.setParameter("grpID", controlgrp);
			          cosdabactransactionlist = query.list();
			          
			          for (Iterator iterator = cosdabactransactionlist.iterator(); iterator.hasNext();) {
			        	  controlObject = (ControlObject) iterator.next();
			                
			            }
		 	         
			          //System.out.println(controlObject.getControlGroup()+ " - " + controlObject.getTransaction());	 
		 	          session.getTransaction().commit();
		         }		 
		 	catch (HibernateException e) {		 
		 	            e.printStackTrace();		 
		 	            session.getTransaction().rollback();
		 
		 	    }
	      finally{
	    	  session.close();
	      }
		 	return controlObject ;
	 }
		 
	 public void delete(long id)
	 {
		 
		  Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction transaction = null;
	      
		 	try {		 
		 	          session.beginTransaction();		 	             
		 	          ControlObject controlObject = (ControlObject) session.get(ControlObject.class, new BigDecimal(id));
		 
		 	         session.delete(controlObject);
		 	          System.out.println(controlObject.getControlGroup()+ " - " + controlObject.getTransaction());	 
		 	          session.getTransaction().commit();
		         }		 
		 	catch (HibernateException e) {		 
		 	            e.printStackTrace();		 
		 	            session.getTransaction().rollback();
		 
		 	    }
	 }

	 public void update(ControlObject controlObject) {
		 
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        
	        try {
	              
	            transaction = session.beginTransaction();
	            
	            session.update(controlObject);
	            transaction.commit();
	        }catch (HibernateException e) {
	            transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
  }

}
