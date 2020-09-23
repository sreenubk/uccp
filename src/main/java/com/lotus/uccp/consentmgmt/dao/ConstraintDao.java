package  com.lotus.uccp.consentmgmt.dao;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
//import org.hibernate.Transaction;

import com.lotus.uccp.util.HibernateUtil;
import com.lotus.uccp.authentication.model.Constraint;
import com.lotus.uccp.authentication.model.ControlGroup;
import com.lotus.uccp.authentication.model.ControlObject;
import com.lotus.uccp.authentication.model.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ConstraintDao {

	@PersistenceContext
	EntityManager entityManager;
		
	
	public void save(long grpId ,long txnid ,Constraint constraint) {

		entityManager.persist(constraint);

	       // updateControlObject(grpId ,constraint);
	        
	       Transaction abacTransaction = null ;
	        TransactionDao abacTransactiondao = new TransactionDao();
	        abacTransaction = abacTransactiondao.getById(txnid);
	          
	         ControlGroup controlgrp = null ;
	         ControlGroupDao controlgrpdao = new ControlGroupDao();
	         controlgrp = controlgrpdao.getById(grpId);
	        
	         ControlObjectDao controlObjectDao = new ControlObjectDao() ; 
	         ControlObject controlObject = controlObjectDao.getByTxnID(abacTransaction, controlgrp);
	         
	        if(controlObject != null && controlObject.getConstraint() == null)
	        {
	        	controlObject.setConstraint(constraint);
	        	 controlObjectDao.update(controlObject);
	        }
	        else
	        {
	        	controlObject = new ControlObject();
	        	controlObject.setControlGroup(controlgrp);
	        	controlObject.setTransaction(abacTransaction);
	        	controlObject.setConstraint(constraint);
	        	controlObjectDao.save(controlObject);
	        }
    }
	 
	 
	 public  List<Constraint> getlist() {
		return entityManager.createQuery("select c from Constraint c", 	Constraint.class).getResultList();
	    }
	 
	 
	 
	 public Constraint getById(long id)
	 {
	 	return entityManager.createQuery("select c from Constraint c where attributeconstraintid=:id", Constraint.class).setParameter("id", BigDecimal.class).getSingleResult();
	 }
	 
	

	 public String delete(long id)
	 {
	 	Constraint constraint = new Constraint();
	 	constraint.setAttributeconstraintid(new BigDecimal(id));

		 entityManager.createQuery("delete c from Constraint", constraint.getClass());
		return "Record Deleted";
	 }
	 
//	 public String deleteFromControlGroup(long id) {
//		entityManager.createQuery("delete cg ControlGroug cg where ")
//
//		  Session session = HibernateUtil.getSessionFactory().openSession();
//	      Transaction transaction = null;
//	      Constraint constraint = null ;
//
//		 	try {
//		 	          session.beginTransaction();
//		 	          constraint = (Constraint) session.get(Constraint.class, new BigDecimal(id));
//
//		 	         /* Query query = session.createQuery("update ControlObject set constraint = :NewValue" +
//								" where constraint = :oldValue");
//		 	          query.setParameter("NewValue", null);
//		 	          query.setParameter("oldValue", constraint);
//		 	          int result = query.executeUpdate();*/
//
//		 	          session.delete(constraint);
//		 	          System.out.println(constraint.getAttributename() + " - " + constraint.getAttributevalue());
//		 	          session.getTransaction().commit();
//
//		 	         return "Record deleted" ;
//		         }
//		 	catch (HibernateException e) {
//		 	            e.printStackTrace();
//		 	            session.getTransaction().rollback();
//		 	           return "Error- while delete operation" ;
//
//		 	    }
//		 	finally
//		 	{
//		 		session.close();
//		 	}
//
//	 }
	  public String update( Constraint constraint) {
		entityManager.createQuery("update c from Constraint c", constraint.getClass());
		return "Updated" ;
      }
		 
		 

}
