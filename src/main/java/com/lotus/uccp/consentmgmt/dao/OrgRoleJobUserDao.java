package  com.lotus.uccp.consentmgmt.dao;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lotus.uccp.util.HibernateUtil;
import com.lotus.uccp.authentication.model.OrgRoleJobUser;



public class OrgRoleJobUserDao {
	
	 public void save(OrgRoleJobUser cosdorgrolejobuser) {
		 
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	      
	        try {
	              
	        		transaction = session.beginTransaction();	            
	        		session.save(cosdorgrolejobuser);
	        		transaction.commit();
	        		
	        }catch (HibernateException e) {
	            transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
    }
	 
	
	 public OrgRoleJobUser getById(long id)
	 {
		 
		  Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction transaction = null;
	      OrgRoleJobUser cosdorgrolejobuser = null ;
	      
		 	try {		 
		 	          session.beginTransaction();		 	             
		 	          
		 	          cosdorgrolejobuser = (OrgRoleJobUser) session.get(OrgRoleJobUser.class, new BigDecimal(id));
		              if(cosdorgrolejobuser != null)		 	         
		              System.out.println(cosdorgrolejobuser.getRolename() + " - " + cosdorgrolejobuser.getUsername());	 
		 	          session.getTransaction().commit();
		         }		 
		 	catch (HibernateException e) {		 
		 	            e.printStackTrace();		 
		 	            session.getTransaction().rollback();
		 
		 	    }
		 		finally {
	            session.close();
	        }
		 	return cosdorgrolejobuser;
	 }
	 
	 
	 public  List<OrgRoleJobUser> getlist() {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        List<OrgRoleJobUser> cosdorgrolejobuserlist = null ;
	        try {
	              
	            transaction = session.beginTransaction();
	            cosdorgrolejobuserlist = session.createQuery("from Cosdorgrolejobuser").list();
	            for (Iterator iterator = cosdorgrolejobuserlist.iterator(); iterator.hasNext();) {
	            	OrgRoleJobUser cosdabaccontrolgroup = (OrgRoleJobUser) iterator.next();
	                System.out.println("Name " + cosdabaccontrolgroup);
	            }
	            transaction.commit();
	        } catch (HibernateException e) {
	            transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
	        
	        return cosdorgrolejobuserlist ;
	    }
	 
	 
	
	 
	 public String delete(long id)
	 {
		 
		  OrgRoleJobUser cosdorgrolejobuser = null ;
		  
		  
		  Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction transaction = null;
	   	      
		 	try {		 
		 	          session.beginTransaction();		 	             
		 	          cosdorgrolejobuser = (OrgRoleJobUser) session.get(OrgRoleJobUser.class, new BigDecimal(id));             
		              session.delete(cosdorgrolejobuser);     
		              System.out.println( cosdorgrolejobuser.getRolename() + " - " + cosdorgrolejobuser.getUsername());	 
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
	 
	 public String update(  OrgRoleJobUser cosdorgrolejobuser ) {
		 
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        OrgRoleJobUser old_cosdorgrolejobuser = null ;
	        OrgRoleJobUserDao cosdorgrolejobuserdao = new OrgRoleJobUserDao();
	        try {
	              
	            transaction = session.beginTransaction();
	            
	            old_cosdorgrolejobuser = cosdorgrolejobuserdao.getById(cosdorgrolejobuser.getOrganisationunitid().longValue());
	            
	            if(old_cosdorgrolejobuser == null)
	            return "Group is not present" ;
	            
	            old_cosdorgrolejobuser.setUsername(cosdorgrolejobuser.getUsername());
	            session.update(old_cosdorgrolejobuser);
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
