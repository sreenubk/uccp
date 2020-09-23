/* Licensed Materials - 
 Property of IBM 6949 - 67L 
 Copyright IBM Corp. 2017, 2018 All Rights Reserved */
package  com.lotus.uccp.consentmgmt.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lotus.uccp.util.HibernateUtil;
import com.lotus.uccp.util.JDBCUtil;
import com.lotus.uccp.util.ConsentConverter;
import com.lotus.uccp.authentication.model.ConsentGroup;
import com.lotus.uccp.vo.TransactionVO;
import com.lotus.uccp.vo.ConsentGroupVO;
import com.lotus.uccp.vo.ViewConsentGroup;

public class ConsentGroupDao {
	
	 public ConsentGroup save(ConsentGroup consentGroup) {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        try {
	        		
	            transaction = session.beginTransaction();
	            
	            session.save(consentGroup);
	            transaction.commit();
	            return consentGroup ;
	        }
	        catch(org.hibernate.exception.ConstraintViolationException sqlIntex)
	        {
	        	System.out.println("Duplication in Constraint");
	        	return null ;
	        }
	        catch (HibernateException e) {
	        	System.out.println("Exception ..mmm");
	            transaction.rollback();
	            e.printStackTrace();
	        } 
	       finally {
	            session.close();
	        }
	        
	        return consentGroup ;
}
	 
	 
	 public  List<ConsentGroup> getlist() {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        List<ConsentGroup> consentGrouplist = null ;
	        try {
	              
	            transaction = session.beginTransaction();
	            consentGrouplist = session.createQuery("from ConsentGroup").list();
	            for (Iterator iterator = consentGrouplist.iterator(); iterator.hasNext();) {
	            	ConsentGroup consentGroup = (ConsentGroup) iterator.next();
	                System.out.println("Transaction ::" + consentGroup.getConsentgroupid());
	            }
	            transaction.commit();
	        } catch (HibernateException e) {
	            transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
	        
	        return consentGrouplist ;
	    }
	 
	 
	 
	 public ConsentGroup getById(long id)
	 {
		 
		  Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction transaction = null;
	      ConsentGroup consentGroup = null ;	      
		 
	      try {		 
		 	          session.beginTransaction();		 	             
		 	          consentGroup = (ConsentGroup) session.get(ConsentGroup.class, new BigDecimal(id));		 
		 	          	 
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
		 	return consentGroup ;
	 }
	 
	
		 
	 public String  delete(long id)
	 {
		 
		  Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction transaction = null;
	      
		 	try {		 
		 	           session.beginTransaction();		 	             
		 	           ConsentGroup consentGroup =  (ConsentGroup) session.get(ConsentGroup.class, new BigDecimal(id));
		 	           
		 	          if(!consentGroup.getConsentGroupTxns().isEmpty())
		 	        	  return "child record found" ; 
		 
		 	           session.delete(consentGroup);
		 	          	 
		 	          session.getTransaction().commit();
		         }		 
		 	catch (HibernateException e) {		 
		 	            e.printStackTrace();		 
		 	            session.getTransaction().rollback();
		 
		 	    }
		 	return "deleted" ;
	 }

	 public String update( ConsentGroup consentGroup ) {
		 
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        ConsentGroup old_consentGroup  = null ;
	        ConsentGroupDao consentGroupdao = new ConsentGroupDao();
	        try {
	              
	            transaction = session.beginTransaction();
	            
	           /* old_consentGroup = consentGroupdao.getById(consentGroup.getConsentgroupid().longValue());
	            
	            if(old_consentGroup == null)
	            return "Consent Group - is not present" ;*/
	            
	            //old_cosdauthorizationgroup.setJobid(cosdauthorizationgroup.getJobid());
	            session.update(consentGroup);
	            transaction.commit();
	        }catch (HibernateException e) {
	            transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
	        return "Updated" ;
   }
	 
	 public  List<ConsentGroup> getlistByGroupName(String groupname) {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        List<ConsentGroup> consentGrouplist = null ;
	        try {
	        	
	        	
	              
	            transaction = session.beginTransaction();
	            
	            Query query = session.createQuery("from ConsentGroup where  name LIKE :grpName ");
	            query.setParameter("grpName","%" + groupname +"%");
	            
	            consentGrouplist = query.list();
	            //ControlGrouplist = session.createQuery("from ControlGroup where  controlgroupname = :code").list();
	            for (Iterator iterator = consentGrouplist.iterator(); iterator.hasNext();) {
	            	ConsentGroup consentGroup = (ConsentGroup) iterator.next();
	                System.out.println("Name " + consentGroup.getName());
	            }
	            transaction.commit();
	        } catch (HibernateException e) {
	            transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
	        
	        return consentGrouplist ;
	    }	 

	 public ViewConsentGroup getConsetGrp(ConsentGroupVO consentGroupvo)
	 {
		 ViewConsentGroup viewconsentgroup = new ViewConsentGroup();
		 
		 ConsentGroupDao consentGroupdao = new ConsentGroupDao();
		 ConsentGroup consentGroup = consentGroupdao.getById(consentGroupvo.getConsentgroupid().longValue());
			
		 String sqlTrans = "select * from COSDABACTRANSACTION trans ,COSDCONSENTGROUPTXN contxn where "
					+ "contxn.TRANSACTIONID = trans.TRANSACTIONID and contxn.CONSENTGROUPID = ? " ;
				
			List<TransactionVO> trans = new ArrayList<TransactionVO>();
			Connection con = null ;
			PreparedStatement stmt =  null ;
			ResultSet resultTrans = null ; 
			ResultSet resultAtt = null ;
			PreparedStatement attstmt =  null ;
			try 
			{
				con = JDBCUtil.getConnection_ConsentMgmt() ;
			
				stmt = con.prepareStatement(sqlTrans);
			
				stmt.setInt(1, consentGroupvo.getConsentgroupid().intValue()); 
			
				resultTrans = stmt.executeQuery();
			
				while(resultTrans.next())
				{
					TransactionVO attTrans = new TransactionVO();
					attTrans.setTransactionid(new BigDecimal(resultTrans.getInt("TRANSACTIONID")));
					attTrans.setCreatedby(resultTrans.getString("CREATEDBY"));
					attTrans.setCreatedon(new java.util.Date(resultTrans.getDate("CREATEDON").getTime()));
					attTrans.setDefaultaccess(resultTrans.getString("DEFAULTACCESS"));
					attTrans.setLastupdatedby(resultTrans.getString("LASTUPDATEDBY"));
					attTrans.setLastupdatedon(new java.util.Date(resultTrans.getDate("LASTUPDATEDON").getTime()));
					attTrans.setTransactionname(resultTrans.getString("TRANSACTIONNAME"));
					attTrans.setTransactiontype(resultTrans.getString("TRANSACTIONTYPE"));
					trans.add(attTrans);
				}
				
				ConsentConverter conv = new ConsentConverter();				
				viewconsentgroup.setConsentGroup(conv.convertToVo(consentGroup));
				viewconsentgroup.setTransList(trans);
			}
			catch(Exception ex)
			{
				try
				{
					if(resultTrans!=null)
					resultTrans.close();
					
					if(stmt!=null)
						stmt.close();
					
					if(con!=null)
						con.close();
				}
				catch(Exception exsql)
				{
					
				}
			}
		 
		 return viewconsentgroup ;
		 
	 }
}
