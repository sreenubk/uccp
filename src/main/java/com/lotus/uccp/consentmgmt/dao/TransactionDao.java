package  com.lotus.uccp.consentmgmt.dao;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lotus.uccp.util.HibernateUtil;
import com.lotus.uccp.authentication.model.ControlGroup;
import com.lotus.uccp.authentication.model.ControlObject;
import com.lotus.uccp.authentication.model.ConsentGroupTxn;
import com.lotus.uccp.authentication.model.ConsentGroup;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class TransactionDao {

	@PersistenceContext
	EntityManager entityManager;
	
	
	 public  String getlistByTxnName(String txnName ,long grpid) {
	 	return entityManager.createNativeQuery(String.format("select txn.TRANSACTIONID from Cosdabactransaction txn ,ConsentGroupTxn contxn where txn.TRANSACTIONID = contxn.TRANSACTIONID and contxn.CONSENTGROUPID = %s and txn.TRANSACTIONNAME = '%s'", grpid,txnName)).getResultStream().count() > 0? "duplicate" : "empty";
	    }
	 
	 public  String getlistByTxnName(String txnName ) {

		 return entityManager.createNativeQuery(String.format("select txn.TRANSACTIONID from Cosdabactransaction txn  where   txn.TRANSACTIONNAME = '%s",txnName)).getResultStream().count() > 0? "duplicate" : "empty";
	    }
	 
	 public  String getlistByTxnConsentID(long txnid ) {
	 	return entityManager.createNativeQuery(String.format("select TRANSACTIONID from cosdconsentgrouptxn where TRANSACTIONID = %s",txnid)).getResultStream().count() >0 ? "duplicate" : "empty";
	 }

	 public void save(long grpID , com.lotus.uccp.authentication.model.Transaction abactransaction) {
	 	entityManager.persist(abactransaction);
	 	ControlObjectDao cosdabaccontrolobjectdao = new ControlObjectDao() ;
	 	ControlObject cosdabaccontrolobject = new ControlObject();
	 	ControlGroupDao cosdabaccontrolgroupdao=  new ControlGroupDao() ;
	 	ControlGroup cosdabaccontrolGroup = cosdabaccontrolgroupdao.getById(grpID);
	 	cosdabaccontrolobject.setControlGroup(cosdabaccontrolGroup);
	 	cosdabaccontrolobject.setTransaction(abactransaction);
	 	cosdabaccontrolobjectdao.save(cosdabaccontrolobject);
	 }
	 
	 public com.lotus.uccp.authentication.model.Transaction saveWithConsent(long grpID , com.lotus.uccp.authentication.model.Transaction  abactransaction) {

		 entityManager.persist(abactransaction);
		 ConsentGroupDao cosdconsentgroupdao = new ConsentGroupDao();
		 ConsentGroup cosdconsentgroup = cosdconsentgroupdao.getById(grpID);
		 ConsentGroupTxn cosdconsentgrouptxn = new ConsentGroupTxn();
		 cosdconsentgrouptxn.setTransaction(abactransaction);
		 cosdconsentgrouptxn.setConsentGroup(cosdconsentgroup);
		 ConsentGroupTxnDao cosdconsentgrouptxndao = new ConsentGroupTxnDao();
		 cosdconsentgrouptxndao.save(cosdconsentgrouptxn);
		 return abactransaction ;
    }
	 
	 public com.lotus.uccp.authentication.model.Transaction saveExistingWithConsent(long grpID , long transactionID) {
		 
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        com.lotus.uccp.authentication.model.Transaction abactransaction = null ;
	        
	        try {
	              
	            transaction = session.beginTransaction();
	            TransactionDao abactransactiondao = new TransactionDao();
	            abactransaction = abactransactiondao.getById(transactionID);
	            //session.save(abactransaction);
	            transaction.commit();
	        }catch (HibernateException e) {
	            transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
	        
	        
	        ConsentGroupDao cosdconsentgroupdao = new ConsentGroupDao();
	        ConsentGroup cosdconsentgroup = cosdconsentgroupdao.getById(grpID);
	        
	        ConsentGroupTxn cosdconsentgrouptxn = new ConsentGroupTxn();
	        cosdconsentgrouptxn.setTransaction(abactransaction);
	        cosdconsentgrouptxn.setConsentGroup(cosdconsentgroup);
	        ConsentGroupTxnDao cosdconsentgrouptxndao = new ConsentGroupTxnDao();
	        cosdconsentgrouptxndao.save(cosdconsentgrouptxn);
	        System.out.println("Added .." );
	        return abactransaction ;
 }
	 
	 public com.lotus.uccp.authentication.model.Transaction saveExistingWithControl(long grpID , long transactionID) {
		 
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        com.lotus.uccp.authentication.model.Transaction abactransaction = null ;
	        
	        try {
	              
	            transaction = session.beginTransaction();
	            TransactionDao abactransactiondao = new TransactionDao();
	            abactransaction = abactransactiondao.getById(transactionID);
	            //session.save(abactransaction);
	            transaction.commit();
	        }catch (HibernateException e) {
	            transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
	        
	        
	        ControlObjectDao cosdabaccontrolobjectdao = new ControlObjectDao() ;
	        ControlObject cosdabaccontrolobject = new ControlObject();
	        ControlGroupDao cosdabaccontrolgroupdao=  new ControlGroupDao() ;
	        ControlGroup cosdabaccontrolGroup = cosdabaccontrolgroupdao.getById(grpID);
	        
	        cosdabaccontrolobject.setControlGroup(cosdabaccontrolGroup);
	        cosdabaccontrolobject.setTransaction(abactransaction);
	        cosdabaccontrolobjectdao.save(cosdabaccontrolobject);
	        
	        
	        System.out.println("...saveExistingWithControl .." );
	        return abactransaction ;
}
	 
	 
	 public  List<com.lotus.uccp.authentication.model.Transaction> getlist() {
	 	return entityManager.createQuery("select t from Transaction t").getResultList();
	    }
	 
	 
	 
	 public com.lotus.uccp.authentication.model.Transaction getById(long id) {
		 return entityManager.find(com.lotus.uccp.authentication.model.Transaction.class,id);
	 }
	 
	 public void update(com.lotus.uccp.authentication.model.Transaction abactransaction) {
	 	entityManager.createNativeQuery("update Transaction t", abactransaction.getClass());
	 }
	 
	 public String delete(long id) {
		 com.lotus.uccp.authentication.model.Transaction t = new com.lotus.uccp.authentication.model.Transaction();
		 t.setTransactionid(new BigDecimal(id));
		 entityManager.createQuery("delete t from Transaction", t.getClass());
		 return "Record deleted" ;
	 } 
	 
	 public String deleteFromGroup(long id) {

		  Session session = HibernateUtil.getSessionFactory().openSession();
	      com.lotus.uccp.consentmgmt.dto.Transaction abactransaction = null ;
	      
		 	try {		 
		 	          session.beginTransaction();		 	             
		 	          abactransaction = (com.lotus.uccp.consentmgmt.dto.Transaction) session.get(com.lotus.uccp.consentmgmt.dto.Transaction.class, new BigDecimal(id));
		 	          
		 	          /*Query query = session.createQuery("update ControlObject set abactransaction = :NewValue" +
								" where abactransaction = :oldValue");
		 	          query.setParameter("NewValue", null);
		 	          query.setParameter("oldValue", abactransaction);
		 	          int result = query.executeUpdate();*/
		 	        
		 	         session.delete(abactransaction);
		 	         System.out.println("deleted:: Name ::" + abactransaction.getTransactionname() + " - Type -" + abactransaction.getTransactiontype());	 
		 	         session.getTransaction().commit();
		         }		 
		 	catch (HibernateException e) {		 
		 	            e.printStackTrace();		 
		 	            session.getTransaction().rollback();
		 	           return "Error- while delete operation" ;
		 	    }	
		 	finally {
	            session.close();
	        }
		 	 	
		 	return "Record deleted" ;	
	 }
	 
	 
	 public String deleteConsentTxn(long id)
	 {		 
		  Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction transaction = null;
	      com.lotus.uccp.consentmgmt.dto.Transaction abactransaction = null ;
	      
		 	try {		 
		 	         session.beginTransaction();		 	             
		 	         abactransaction = (com.lotus.uccp.consentmgmt.dto.Transaction) session.get(com.lotus.uccp.consentmgmt.dto.Transaction.class, new BigDecimal(id));
		 	          
		 	         ConsentGroupTxnDao cosdconsentgrouptxndao = new ConsentGroupTxnDao();
		 	         cosdconsentgrouptxndao.deleteBytxnID(abactransaction.getTransactionid().longValue());
		 	         		 	        
		 	         session.delete(abactransaction);
		 	         System.out.println("deleted:: Name ::" + abactransaction.getTransactionname() + " - Type -" + abactransaction.getTransactiontype());	 
		 	         session.getTransaction().commit();
		         }		 
		 	catch (HibernateException e) {		 
		 	            e.printStackTrace();		 
		 	            session.getTransaction().rollback();
		 	           return "Error- while delete operation" ;
		 	    }	
		 	finally {
	            session.close();
	        }
		 	 	
		 	return "Record deleted" ;	
	 } 
	 
	 
	
	
	 
}	 
