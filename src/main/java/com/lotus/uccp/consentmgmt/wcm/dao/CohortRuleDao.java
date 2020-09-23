package com.lotus.uccp.consentmgmt.wcm.dao ;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.lotus.uccp.util.JDBCUtil;
import com.lotus.uccp.consentmgmt.wcm.dto.Cohort;
import com.lotus.uccp.consentmgmt.wcm.dto.CohortRule;
import com.lotus.uccp.wcm.vo.CohortRuleVO;
import com.lotus.uccp.wcm.vo.CohortRulesVO;
import com.lotus.uccp.wcm.vo.CohortVO;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CohortRuleDao {
	
	/*
	 * COHORTID
RULEID
RULESHORTDESC
RULEDESCRIPTION
RULEACTION
REASONCODE
ISENABLED
APPROVALREQUIRED
STARTDATE
ENDDATE
COHORTRULEID
	 */
	
	public CohortRulesVO searchCohortRule(long cohortId)
	{
		System.out.println(":: searchCohortRule ::" + cohortId);
		String schema = Constant.SCHEMA.trim() + ".";
		
		// select cohort.COHORTNAME, cohort.COHORTDESCRIPTION , corule.COHORTID ,corule.RULEID , corule.RULESHORTDESC , corule.RULEDESCRIPTION , corule.RULEACTION , corule.REASONCODE , corule.ISENABLED ,corule.APPROVALREQUIRED , corule.STARTDATE , corule.ENDDATE , corule.COHORTRULEID from WCM_COHORT cohort, WCM_COHORT_RULE corule where cohort.COHORTID = 50 and  cohort.COHORTID = corule.COHORTID 
		String sqlConstraint = "select cohort.COHORTNAME, cohort.COHORTDESCRIPTION , cohort.COHORTID ,corule.RULEID , corule.RULESHORTDESC , "
				+ " corule.RULEDESCRIPTION , corule.RULEACTION , corule.REASONCODE , corule.ISENABLED ,corule.APPROVALREQUIRED , corule.STARTDATE , "
				+ " corule.ENDDATE , corule.COHORTRULEID from "+schema +"WCM_COHORT cohort, "+ schema  +"WCM_COHORT_RULE corule where cohort.COHORTID = ? "
				+ "and  cohort.COHORTID = corule.COHORTID(+)  ";
		
		
		
		System.out.println(":: searchCohortRule Query ::" + sqlConstraint);
		
		Connection con = null ;
		PreparedStatement stmt =  null ;
		ResultSet resultCohortRule = null ; 
		CohortRulesVO cohortRules = new CohortRulesVO();
		CohortVO cohortvo = new CohortVO();
		ArrayList<CohortRuleVO> listCohortRules = new ArrayList<>() ;
		try
		{
			con = JDBCUtil.getConnection() ;
			
			cohortRules.setCohortvo(cohortvo);
			
			stmt = con.prepareStatement(sqlConstraint);
			
			stmt.setLong(1, cohortId);
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			resultCohortRule = stmt.executeQuery();
			
			while(resultCohortRule.next()) 
			{
				System.out.println("---" + resultCohortRule.getInt("COHORTID"));
				cohortvo =  new CohortVO(); 
				cohortvo.setCohortid(new BigDecimal(resultCohortRule.getInt("COHORTID")));
				cohortvo.setName(resultCohortRule.getString("COHORTNAME"));
				cohortvo.setCohortdescription(resultCohortRule.getString("COHORTDESCRIPTION"));
				
				CohortRuleVO cohortRule = new CohortRuleVO();
				
				//cohortRule.setCohortid(new BigDecimal(resultCohortRule.getInt("COHORTID")));
				
				cohortRule.setRuleID(resultCohortRule.getString("RULEID"));
				cohortRule.setRuleDescription(resultCohortRule.getString("RULEDESCRIPTION"));
				cohortRule.setRuleShortDesc(resultCohortRule.getString("RULESHORTDESC"));
				cohortRule.setReasonCode(resultCohortRule.getString("REASONCODE"));
				cohortRule.setRuleAction(resultCohortRule.getString("RULEACTION"));
				cohortRule.setIsEnabled(resultCohortRule.getString("ISENABLED"));
				cohortRule.setApprovalRequired(resultCohortRule.getString("APPROVALREQUIRED"));
				cohortRule.setCohortRuleID(new BigDecimal(resultCohortRule.getInt("COHORTRULEID")));
		
				if(resultCohortRule.getDate("STARTDATE") != null)
				{			   
					String startDate= formatter.format(new java.util.Date(resultCohortRule.getDate("STARTDATE").getTime())); 		
					cohortRule.setStartDate(startDate);
				}
				if(resultCohortRule.getDate("ENDDATE") != null)
				{			   
					String endDate= formatter.format(new java.util.Date(resultCohortRule.getDate("ENDDATE").getTime())); 		
					cohortRule.setEndDate(endDate);
				}
				
				listCohortRules.add(cohortRule);
			}	
			
			cohortRules.setCohortvo(cohortvo);
			cohortRules.setListCohortRules(listCohortRules);
				
		}
			
		
		catch(Exception ex)
		{
			System.out.println("Exception"  + ex);
		}
		finally
		{
			try
			{
				if(resultCohortRule!=null)
					resultCohortRule.close();
				
				if(stmt!=null)
					stmt.close();
				
				if(con!=null)
					con.close();
			}
			catch(Exception exsql)
			{
				
			}
		}
		
		return cohortRules ;
	}
	
	 public CohortRule save(CohortRule CohortRule) {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        try {
	        		
	            transaction = session.beginTransaction();
	            
	            session.save(CohortRule);
	            transaction.commit();
	        }catch (HibernateException e) {
	        	System.out.println("Exception ..mmm");
	            transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	            return CohortRule ;
	        }
     }
	
	 public  List<CohortRule> getlist() {
		 
		 	System.out.println(" ");
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        List<CohortRule> cosdpersonconsentlist = null ;
	        try {
	              
	            transaction = session.beginTransaction();
	            cosdpersonconsentlist = session.createQuery("from CohortRule ").list();
	            for (Iterator iterator = cosdpersonconsentlist.iterator(); iterator.hasNext();) {
	            	CohortRule cosdpersonconsent = (CohortRule) iterator.next();
	                
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
	
	 public CohortRule getById(BigDecimal id , String ruleID)
	 {
		 
		  Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction transaction = null;
	      CohortRule CohortRule = null ;	 
	      List<CohortRule> cohortrulelist = null ;
		  CohortDao cdao= new CohortDao();
	      try {		 
		 	         session.beginTransaction();		 	             
		 	          	 
		 	        Cohort cid = cdao.getById(id.longValue()) ;
		 	         Query query = session.createQuery("from CohortRule where  cohortid = :cohortid and ruleID = :ruleID ");
			         query.setParameter("cohortid", cid);
			         query.setParameter("ruleID",ruleID);
			         
			         cohortrulelist = query.list();
			          
			          if(cohortrulelist.isEmpty())
			        	  return CohortRule ;
			          
			          for (Iterator iterator = cohortrulelist.iterator(); iterator.hasNext();) {
			        	  CohortRule = (CohortRule) iterator.next();
			                
			            }
			          
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
		 	return CohortRule ;
	 }
	 
	
		 
	 public String delete(long id)
	 {
		 
		  Session session = HibernateUtil.getSessionFactory().openSession();
	     // Transaction transaction = null;
	      
		 	try {		 
		 	           session.beginTransaction();		 	             
		 	           CohortRule CohortRule =  (CohortRule) session.get(CohortRule.class, new BigDecimal(id));
		 
		 	           session.delete(CohortRule);
		 	          	 
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

	 public String update( CohortRule CohortRule ) {
		 
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        CohortRule old_CohortRule  = null ;
	        CohortRuleDao CohortRuledao = new CohortRuleDao();
	        
	        try {
	              
	            transaction = session.beginTransaction(); 
	            
	            //old_CohortRule = CohortRuledao.getById(CohortRule.getPersonconsentid().longValue());	            
	            //if(old_CohortRule == null)
	            //return "Consent Person - is not present" ;	            
	            //old_cosdauthorizationgroup.setJobid(cosdauthorizationgroup.getJobid());
	            
	            session.update(CohortRule);
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

/*CREATE TABLE CohortRule (
	    CohortRuleedID varchar2(30)  NOT NULL,
	    CohortRuleDescription varchar2(100)  NOT NULL,
	    CONSTRAINT CohortRule_pk PRIMARY KEY (CohortRuleedID)
	) ; */
