package  com.lotus.uccp.consentmgmt.dao;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.lotus.uccp.util.HibernateUtil;
import com.lotus.uccp.util.JDBCUtil;
import com.lotus.uccp.authentication.model.PersonConsent;
import com.lotus.uccp.util.Converters;
import com.lotus.uccp.vo.PersonConsentVO;

public class PersonConsentDao { 
	
	public void saveFromVO(PersonConsentVO  cosdpersonconsentvo)
	{
		
		PersonConsent cosdpersonconsent = new PersonConsent();
		try
		{
			cosdpersonconsent.setAuthpersoncontactinfoone(cosdpersonconsentvo.getAuthpersoncontactinfoone());
			cosdpersonconsent.setAuthpersoncontactinfotwo(cosdpersonconsentvo.getAuthpersoncontactinfotwo());
			cosdpersonconsent.setAuthpersonnameone(cosdpersonconsentvo.getAuthpersonnameone());
			cosdpersonconsent.setAuthpersonnametwo(cosdpersonconsentvo.getAuthpersonnametwo());
			cosdpersonconsent.setAuthpersonrelone(cosdpersonconsentvo.getAuthpersonrelone());
			cosdpersonconsent.setAuthpersonreltwo(cosdpersonconsentvo.getAuthpersonreltwo());
			cosdpersonconsent.setConcernroleid(cosdpersonconsentvo.getConcernroleid());
			cosdpersonconsent.setContactaddressid(cosdpersonconsentvo.getContactaddressid());
			cosdpersonconsent.setContactphone(cosdpersonconsentvo.getContactphone());
			cosdpersonconsent.setCreatedon(cosdpersonconsentvo.getCreatedon());
			cosdpersonconsent.setCreatedby(cosdpersonconsentvo.getCreatedby());
			cosdpersonconsent.setLastupdatedby(cosdpersonconsentvo.getLastupdatedby());
			cosdpersonconsent.setLastupdatedon(cosdpersonconsentvo.getLastupdatedon());
			cosdpersonconsent.setThirdpartyfirstname(cosdpersonconsentvo.getThirdpartyfirstname());
			cosdpersonconsent.setThirdpartylastname(cosdpersonconsentvo.getThirdpartylastname());
			cosdpersonconsent.setThirdpartyotherrelationship(cosdpersonconsentvo.getThirdpartyotherrelationship());
			cosdpersonconsent.setThirdpartyrelationship(cosdpersonconsentvo.getThirdpartyrelationship());
			
			cosdpersonconsent.setLastupdatedby("UMA");
			cosdpersonconsent.setCreatedby("UMA");
			cosdpersonconsent.setLastupdatedon(new java.util.Date());
			cosdpersonconsent.setCreatedon(new java.util.Date());
			
		   // logger.info("before insert");
		    
		    PersonConsentDao cosdpersonconsentdao = new PersonConsentDao() ;
		  
		    cosdpersonconsent = cosdpersonconsentdao.save(cosdpersonconsent) ;
		    
		    Converters conv = new Converters();
			//CosdabactransactionVO cosdabactransactionVo = conv.convertToVo(cosdpersonconsent);
		    cosdpersonconsentvo.setPersonconsentid(cosdpersonconsent.getPersonconsentid());
		   
		   
		  
		}
		catch(Exception ex)
		{    System.out.println("::-----$$$$$ ::" + ex);
			ex.printStackTrace();
			//return new ResponseEntity(new Message("Fail"), HttpStatus.OK);
		}	
	}
	
	 public PersonConsent save(PersonConsent cosdpersonconsent) {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        try {
	        		
	            transaction = session.beginTransaction();
	            
	            session.save(cosdpersonconsent);
	            transaction.commit();
	        }catch (HibernateException e) {
	        	System.out.println("Exception ..mmm");
	            transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	            return cosdpersonconsent ;
	        }
}
	 
	 
	 public  List<PersonConsent> getlist() {
		 
		 	System.out.println(" ");
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        List<PersonConsent> cosdpersonconsentlist = null ;
	        try {
	              
	            transaction = session.beginTransaction();
	            cosdpersonconsentlist = session.createQuery("from PersonConsent where histroyRecord = 'NO' ").list();
	            for (Iterator iterator = cosdpersonconsentlist.iterator(); iterator.hasNext();) {
	            	PersonConsent cosdpersonconsent = (PersonConsent) iterator.next();
	                System.out.println("Transaction ::" + cosdpersonconsent.getPersonconsentid());
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
	 
	 
	 
	 public  List<PersonConsent> getHistorylist() {
		 
		 	System.out.println(" ");
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        List<PersonConsent> cosdpersonconsentlist = null ;
	        try {
	              
	            transaction = session.beginTransaction();
	            cosdpersonconsentlist = session.createQuery("from PersonConsent where histroyRecord = 'YES' ").list();
	            for (Iterator iterator = cosdpersonconsentlist.iterator(); iterator.hasNext();) {
	            	PersonConsent cosdpersonconsent = (PersonConsent) iterator.next();
	                System.out.println("Transaction ::" + cosdpersonconsent.getPersonconsentid());
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
	 
	 public String isDuplicate(PersonConsent cosdpersonconsent)
	 {   
		 String result = "nofound" ;
		 PersonConsentDao cosdpersonconsentdao = new PersonConsentDao();
		 //PersonConsentVO cosdpersonconsentvo = new PersonConsentVO();
		
		 Query qry = null ;
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 Transaction transaction = null;
		 try 
		 {
			 transaction = session.beginTransaction();
			 qry = session.createQuery("from PersonConsent where histroyRecord = 'NO'  and customerName LIKE :customerName  and clientid = :clientid") ;	            
			 qry.setParameter("customerName","%" + cosdpersonconsent.getCustomerName() +"%");
			 qry.setParameter("clientid", cosdpersonconsent.getClientid() );
			 System.out.println("isDuplicate-- " + cosdpersonconsent.getCustomerName() + ":: Clientid::" + cosdpersonconsent.getClientid());
		
			List<PersonConsent> cosdpersonconsentlist =  qry.list();
		 
		 if(cosdpersonconsentlist != null)
		 { 
			 if (cosdpersonconsentlist.size() == 0)
				 result = "nofound" ;
			 if (cosdpersonconsentlist.size() > 0)
				 result = "duplicate" ;
		 }
		 }
		 catch (HibernateException e) {
	            transaction.rollback();
	            e.printStackTrace();
	        }
	        catch (Exception ex) {
	            transaction.rollback();
	            ex.printStackTrace();
	        } 
	        finally {
	            session.close();
	        }
		 
		 return result;
	 }
	 
	 public  List<PersonConsent> getlistBySearch(PersonConsentVO cosdpersonconsentvo) {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        List<PersonConsent> cosdpersonconsentlist = new ArrayList<PersonConsent> ();;
	        try {
	        	SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
	            transaction = session.beginTransaction();
	            String custName = null ;
	            if(cosdpersonconsentvo.getCustomerName() != null && !cosdpersonconsentvo.getCustomerName().trim().equals(""))
	            custName = cosdpersonconsentvo.getCustomerName().toUpperCase() ;
	            java.util.Date startdate = null ;
	            java.util.Date enddate  = null ;
	          
	            if(cosdpersonconsentvo.getStartdate() != null && !cosdpersonconsentvo.getStartdate().trim().equals("")) 
				{
					startdate = formatter.parse(cosdpersonconsentvo.getStartdate());
					//cosdpersonconsent.setStartdate(startdate);
				}
				if(cosdpersonconsentvo.getEnddate() != null && !cosdpersonconsentvo.getEnddate().trim().equals(""))
				{
					enddate = formatter.parse(cosdpersonconsentvo.getEnddate());
					//cosdpersonconsent.setEnddate(enddate);
				}
				Query qry = null ;
				
				System.out.println("CustName  ::" + custName + " EndDate ::" + enddate + " startdate :: " + startdate);
				
				if(enddate !=null && startdate != null && custName != null)
	            {
					qry = session.createQuery("from PersonConsent where histroyRecord = 'NO'  and customerName LIKE :customerName and startdate >= :startdate and enddate <= :enddate ") ;
					qry.setParameter("customerName","%" + custName +"%");
					//qry.setParameter("customerName", custName);
					qry.setParameter("startdate", startdate);
					qry.setParameter("enddate", enddate);
					System.out.println("Block 1 :: startdate , enddate , customerName ");
	            }
				
				if(qry ==null && startdate != null && custName != null)
	            {
					qry = session.createQuery("from PersonConsent where histroyRecord = 'NO'  and customerName LIKE :customerName and startdate >= :startdate ") ;	            
					qry.setParameter("customerName","%" + custName +"%");
					qry.setParameter("startdate", startdate);
					System.out.println("Block 2 :: where startdate , customerName ");
	            }
	            
				if(qry ==null && enddate != null && custName != null)
	            {
					qry = session.createQuery("from PersonConsent where histroyRecord = 'NO'  and customerName LIKE :customerName and enddate <= :enddate ") ;	            
					qry.setParameter("customerName","%" + custName +"%");				
					qry.setParameter("enddate", enddate);
					System.out.println("Block 3 :: where enddate , customerName ");
	            }
				
				if(qry ==null && enddate != null && startdate != null)
	            {
					qry = session.createQuery("from PersonConsent where histroyRecord = 'NO'  and startdate >= :startdate and enddate <= :enddate ") ;	            
					qry.setParameter("startdate", startdate);
					qry.setParameter("enddate", enddate);
					System.out.println("Block 3.1 :: where enddate , startdate ");
	            }
				
				if(qry ==null &&  custName != null)
	            {
					qry = session.createQuery("from PersonConsent where histroyRecord = 'NO'  and customerName LIKE :customerName  ") ;	            
					qry.setParameter("customerName","%" + custName +"%");
					System.out.println("Block 4 :: only customerName ");
					
	            }
				
				if(qry ==null &&  enddate != null)
	            {
					qry = session.createQuery("from PersonConsent where enddate <= :enddate  ") ;	            
					qry.setParameter("enddate", enddate);
					System.out.println("Block 4.1 :: only enddate ");
					
	            }
				
				if(qry ==null &&  startdate != null)
	            {
					qry = session.createQuery("from PersonConsent where startdate >= :startdate ") ;	            
					qry.setParameter("startdate", startdate);
					System.out.println("Block 4.2 :: only startdate ");
					
	            }
				
				if(qry ==null )
	            {
					qry = session.createQuery("from PersonConsent ") ;
					System.out.println("Block 5 :: All where conditions are off ");
					
	            }
				
	            cosdpersonconsentlist = qry.list();
	            for (Iterator iterator = cosdpersonconsentlist.iterator(); iterator.hasNext();) {
	            	PersonConsent cosdpersonconsent = (PersonConsent) iterator.next();
	                System.out.println("Transaction ::" + cosdpersonconsent.getPersonconsentid());
	            }
	            transaction.commit();
	        } catch (HibernateException e) {
	            transaction.rollback();
	            e.printStackTrace();
	        }
	        catch (Exception ex) {
	            transaction.rollback();
	            ex.printStackTrace();
	        } 
	        finally {
	            session.close();
	        }
	        
	        return cosdpersonconsentlist ;
	    }
	 
	 
	 
	 public PersonConsent getById(long id)
	 {
		 
		  Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction transaction = null;
	      PersonConsent cosdpersonconsent = null ;	      
		 
	      try {		 
		 	          session.beginTransaction();		 	             
		 	          cosdpersonconsent = (PersonConsent) session.get(PersonConsent.class, new BigDecimal(id));		 
		 	          	 
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
		 	return cosdpersonconsent ;
	 }
	 
	
		 
	 public String delete(long id)
	 {
		 
		  Session session = HibernateUtil.getSessionFactory().openSession();
	     // Transaction transaction = null;
	      
		 	try {		 
		 	           session.beginTransaction();		 	             
		 	           PersonConsent cosdpersonconsent =  (PersonConsent) session.get(PersonConsent.class, new BigDecimal(id));
		 
		 	           session.delete(cosdpersonconsent);
		 	          	 
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

	 public String update( PersonConsent cosdpersonconsent ) {
		 
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        PersonConsent old_cosdpersonconsent  = null ;
	        PersonConsentDao cosdpersonconsentdao = new PersonConsentDao();
	        
	        try {
	              
	            transaction = session.beginTransaction(); 
	            
	            //old_cosdpersonconsent = cosdpersonconsentdao.getById(cosdpersonconsent.getPersonconsentid().longValue());	            
	            //if(old_cosdpersonconsent == null)
	            //return "Consent Person - is not present" ;	            
	            //old_cosdauthorizationgroup.setJobid(cosdauthorizationgroup.getJobid());
	            
	            session.update(cosdpersonconsent);
	            transaction.commit();
	        }catch (HibernateException e) {
	            transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
	        return "Updated" ;
   }
	 
	 public int getFileID()
	 {
		 PreparedStatement ps = null ;
		 Connection con = null ;
		 ResultSet resultTrans = null ;
		 int lng = 0 ;
		 try
		 {
			 con = JDBCUtil.getConnection_ConsentMgmt();
			 
			 ps = con.prepareStatement(  
					 "select max(fileid) as fileid from PERSONCONSENTFILE "); 
			 resultTrans = ps.executeQuery();
			 resultTrans.next() ;
			 lng = resultTrans.getInt("fileid");
			 
			 return lng ;
		 }
		 catch(SQLException ex)
		 {
			 ex.printStackTrace();
			 System.out.println("exception ex" + ex);
			 return lng ;
		 }
		 finally
		 {
			 try
			 {
				 if(resultTrans != null )
				 resultTrans.close();
				 if(ps != null)
				 ps.close();
				 if(con != null)
				 con.close();
			 }
			 catch(Exception ex)
			 {
				 
			 }
		 }
		 
	 }
	 
	 public void FileUpload(File f ,String fpath)
	 {
		 PreparedStatement ps = null ;
		 Connection con = null ;
		 ResultSet resultTrans = null ;
		 long lng = 0 ;
		 try
		 {
			 con = JDBCUtil.getConnection_ConsentMgmt();
			 
			 
			 
			 ps = con.prepareStatement(  
					 "insert into PersonConsentFile values(consentFileSeq.nextval,?,?,?)"); 
			 
			 //File f=new File("C:\\Connect360\\PolySet.txt");  // 
			 FileReader fr=new FileReader(f);  
			 java.util.Date udt = new java.util.Date();          
			 //ps.setInt(1,101);  
			 ps.setCharacterStream(1,fr,(int)f.length()); 
			 ps.setDate(2,new Date(udt.getTime()));
			 ps.setString(3,fpath);
			 int i=ps.executeUpdate();
			 
			
			 
			 System.out.println("-- Uploaded File --" + lng);
			 System.out.println("-- File path--" + fpath);
		 }
		 catch(Exception ex)
		 {
			 ex.printStackTrace();
			 System.out.println("exception ex" + ex);
			
		 }
		 finally
		 {
			 try
			 {
				 if(resultTrans != null )
				 resultTrans.close();
				 if(ps != null)
				 ps.close();
				 if(con != null)
				 con.close();
			 }
			 catch(Exception ex)
			 {
				 
			 }
		 }
		 
		
	 }
	 
	 public String FileDown(int id)
	 {
		 String filePath = null ;
		 Connection con = null ;
		 ResultSet rs = null ;
		 try
		 {
			 con = JDBCUtil.getConnection_ConsentMgmt();
			 PreparedStatement ps=con.prepareStatement("select FILEDATA ,FILEPATH from PersonConsentFile where FILEID = (Select FILEID from COSDPERSONCONSENT where PERSONCONSENTID = ? ) ");  
			 ps.setInt(1, id);
			 rs=ps.executeQuery();  
			 while(rs.next())
			 {
				 filePath = rs.getString("FILEPATH"); 
				 System.out.println("File ID :"+ id + "File Path ::-" + filePath);
			 }
			 /*Clob c= rs.getClob("FILEDATA");  
			 rs.close();
			 rs = null ;
			 Reader r=c.getCharacterStream();              
			 System.out.println("2nd line");           
			 FileWriter fw=new FileWriter("\\retrivefile.txt");  
			               
			 int i;  
			 while((i=r.read())!=-1)  
			 fw.write((char)i);  
			 
			 System.out.println("3rd line");
			 
			 fw.close();  
			 con.close();  */
		 }
		 catch(Exception ex)
		 {
			 ex.printStackTrace();
			 System.out.println("exception ex" + ex);
		 }
		 
		 return filePath ;
	 }
	 
	 public String insertClientReg(PersonConsent cosdpersonconsent) {
		 
		 PreparedStatement ps = null ;
		 Connection con = null ;
			        // PersonConsent cosdpersonconsent
	        try {
	        	
	        	 con = JDBCUtil.getConnection_ConsentMgmt();
				 
				 
				 
				 ps = con.prepareStatement(  
						 "insert into CLIENT_REGISTRY (CLIENTID , PERSONCONSENTID , SOURCESYS) values ( clientIDSeq.nextval , ? ,'LA')"); 
				 ps.setLong(1,cosdpersonconsent.getPersonconsentid().longValue());
				 int i = ps.executeUpdate();
				 
				 System.out.println(".. Added into CLIENT_REGISTRY ..");
				 
				 return "Success" ;
	           
	        }
	        catch(Exception ex)
			 {
				 ex.printStackTrace();
				 System.out.println("exception ex" + ex);
				 return "fail" ;
				
			 }
			 finally
			 {
				 try
				 {
					
					 if(ps != null)
					 ps.close();
					 if(con != null)
					 con.close();
				 }
				 catch(Exception ex)
				 {
					 
				 }
			 }
   } // inserClinetReg

	 
	 
public String updateClientReg(PersonConsent cosdpersonconsent ,long oldpersonConsent) {
		 
		 PreparedStatement ps = null ;
		 Connection con = null ;
			        // PersonConsent cosdpersonconsent
	        try {
	        	
	        	 con = JDBCUtil.getConnection_ConsentMgmt();
				 
				 
				 
				 ps = con.prepareStatement(  
						 "update CLIENT_REGISTRY set PERSONCONSENTID = ? where PERSONCONSENTID = ?"); 
				 ps.setLong(1,cosdpersonconsent.getPersonconsentid().longValue());
				 ps.setLong(2,oldpersonConsent);
				 int i = ps.executeUpdate();
				 
				 System.out.println(".. updated into CLIENT_REGISTRY ..");
				 
				 return "Success" ;
	           
	        }
	        catch(Exception ex)
			 {
				 ex.printStackTrace();
				 System.out.println("exception ex" + ex);
				 return "fail" ;
				
			 }
			 finally
			 {
				 try
				 {
					
					 if(ps != null)
					 ps.close();
					 if(con != null)
					 con.close();
				 }
				 catch(Exception ex)
				 {
					 
				 }
			 }
   } // inserClinetReg	 


public String updateClientID(PersonConsent cosdpersonconsent ,String name) {
	 
	 PreparedStatement ps = null ;
	 Connection con = null ;
	 ResultSet rs = null ;
		        // PersonConsent cosdpersonconsent
       try {
       	
       	 con = JDBCUtil.getConnection_ConsentMgmt();
			 
			 int clientID = 0 ;
			 // Michael Cohen
			 ps = con.prepareStatement(  
					 "select CLIENTID from REFERRAL_LA  where CUSTOMER_NAME = ?"); 
			 ps.setString(1,name);
			
			 rs = ps.executeQuery();
			 
			 if(rs.next())
				 clientID = rs.getInt("CLIENTID") ;
			 
			 System.out.println("Client ID -" + clientID + "- name -" +name);
			 
			 cosdpersonconsent.setClientid(new BigDecimal(clientID));
			 PersonConsentDao dao = new PersonConsentDao();
			 dao.update(cosdpersonconsent);
			 
			 return "Success" ;
          
       }
       catch(Exception ex)
		 {
			 ex.printStackTrace();
			 System.out.println("exception ex" + ex);
			 return "fail" ;
			
		 }
		 finally
		 {
			 try
			 {
				
				 if(ps != null)
				 ps.close();
				 if(con != null)
				 con.close();
			 }
			 catch(Exception ex)
			 {
				 
			 }
		 }
} // inserClinetReg
}
