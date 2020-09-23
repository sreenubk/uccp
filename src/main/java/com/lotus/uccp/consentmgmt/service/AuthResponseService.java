package  com.lotus.uccp.consentmgmt.service;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.text.SimpleDateFormat;  
import org.springframework.stereotype.Service;

import com.lotus.uccp.util.HibernateUtil;
import com.lotus.uccp.util.JDBCUtil;
import com.lotus.uccp.consentmgmt.dao.AuthRequestDao;
import com.lotus.uccp.consentmgmt.dto.AuthRequest;
import com.lotus.uccp.vo.AuthRequestVO;
import com.lotus.uccp.vo.AuthResponseVO;

@Service("AuthResponseService")
public class AuthResponseService {
	
	public AuthResponseVO getAuthService(AuthRequestVO authrequestvo)
	{
		AuthResponseVO  authresponsevo = new AuthResponseVO() ;
		//System.out.println("Minor :: -- " + authrequestvo.getMinor() + "Parolee" + authrequestvo.getParolee() + "Client ID -" + authrequestvo.getClientID());
		
		try
		{
		//AuthResponseVO  authresponsevo = new AuthResponseVO() ;
			
			// Default Setting 
			if(authrequestvo.getApiSwith() == null)
				authrequestvo.setApiSwith("Both");
			/*if(authrequestvo.getMinor() == null)
				authrequestvo.setMinor("No");
			if(authrequestvo.getParolee() == null)
				authrequestvo.setParolee("No");*/
		
		authrequestvo.setApiSwith(authrequestvo.getApiSwith().toUpperCase());
		if(authrequestvo.getParolee() != null && "Yes".equalsIgnoreCase(authrequestvo.getParolee().trim()))
		{   
			System.out.println(":: Parolee Case:: Allowed");
			authresponsevo.setResponse("Allow");
			authrequestvo.setAccessReason("Parolee: Consent/Policy not required");
		}
		else if(authrequestvo.getApiSwith().equalsIgnoreCase("Consent"))
		{
			 
			authresponsevo = useConsentData(authrequestvo);
		}
		else if(authrequestvo.getApiSwith().equalsIgnoreCase("Policy"))			
		{   		
			authresponsevo = usePolicyData(authrequestvo);
			
		}
		else if(authrequestvo.getApiSwith().equalsIgnoreCase("Both") || authrequestvo.getApiSwith().equals(null) || authrequestvo.getApiSwith().equals(""))	
		{
			authresponsevo = useConsentData(authrequestvo) ;
			if(authresponsevo.getResponse().equalsIgnoreCase("deny"))
			authresponsevo = usePolicyData(authrequestvo);
		}
		else if(authrequestvo.getApiSwith().equalsIgnoreCase("BTG"))
		{   
			System.out.println(":: Break the Glass ::");
			authresponsevo.setResponse("Allow");
		}
		
		AuthRequest  authrequest = Convert(authrequestvo) ;

		authrequest.setResult(authresponsevo.getResponse());
		AuthRequestDao authrequestdao = new AuthRequestDao();
		authrequestdao.save(authrequest);
		}		
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			//authresponsevo.setResponse("Deny");
		}
		return authresponsevo ;
	}
	
	public AuthResponseVO useConsentData(AuthRequestVO authrequestvo)
	{
		AuthResponseVO  authresponsevo = new AuthResponseVO() ;
		
		
		String result = getResultConsentData(authrequestvo);
		authresponsevo.setResponse(result);
		authresponsevo.setUserName(authrequestvo.getUserName());
		return authresponsevo ;
	}

	public AuthResponseVO usePolicyData(AuthRequestVO authrequestvo)
	{
		AuthResponseVO  authresponsevo = new AuthResponseVO() ;
		
		
		String result = getResultPolicyData(authrequestvo);
		authresponsevo.setResponse(result);
				
		
		return authresponsevo ;
	}
	
	private String getResultPolicyData(AuthRequestVO authrequestvo)
	{
		Connection con = null ;
		PreparedStatement stmt =  null ;
		ResultSet policyDataRS = null ; 
		PreparedStatement stmtAll =  null ;
		ResultSet allDataRS = null ; 
		String result = null ;
		
	/*	String policyQuery =" Select ag.AUTHORIZATIONGROUPID as authGRP,ctxn.TRANSACTIONID as txnid ,ctxn.TRANSACTIONNAME as txnname ,"
				+ " aps.CONSENTSTATUS as cstatus , "
				 + " catt.ATTRIBUTECONSTRAINTID as id ,catt.ACCESSCONSTRAINTTYPE as contype "
				+ " from COSDAUTHORIZATIONPOLICY agp , COSDAUTHORIZATIONGROUP ag , COSDAUTHORIZATIONPOLICYSET aps , COSDABACCONSTRAINT catt ,  "
				+ " COSDABACCONTROLOBJECT co ,COSDABACTRANSACTION ctxn  "
				+ " where   ctxn.TRANSACTIONID = co.TRANSACTIONID "
				+ " and co.ATTRIBUTECONSTRAINTID =  catt.ATTRIBUTECONSTRAINTID(+) "
				+ " and co.CONTROLGROUPID = ag.CONTROLGROUPID  "
				+ " and agp.AUTHORIZATIONGROUPID in "
				+ " ( select AUTHORIZATIONGROUPID from COSDAUTHORIZATIONGROUP authgrp , COSDORGROLEJOBUSER job where  "
				+ " authgrp.ROLENAME = job.ROLELEVEL "
				+" and authgrp.ORGANISATIONUNITID =job.ORGANISATIONUNITID "
				+" and authgrp.JOBID = job.JOBID "
				+" and job.USERNAME = '" + authrequestvo.getUserName()  + "') "
				+" and agp.AUTHORIZATIONGROUPID = ag.AUTHORIZATIONGROUPID "
				+" and agp.POLICYSETID = aps.POLICYSETID and ctxn.TRANSACTIONNAME = '"+ authrequestvo.getTransName() + "'" ;
		*/
		
		try
		{
			Map map = authrequestvo.getAttributehm() ; // "Name"
			boolean attribute = false ;
			String minorval = null ; 
			String paroleeval = null ;
			
			if(map != null) 
			{  
				attribute = true ;
				//minorval = (String)map.get("Minor") == null ? null : ((String)map.get("Minor")).toUpperCase() ;
			    //paroleeval = (String)map.get("Parolee") == null ? null : ((String)map.get("Parolee")).toUpperCase();
				//System.out.println(":: Minor :: -- " + map.get("Minor") + ":: Parolee ::-- " + map.get("Parolee") + ":: attribute ::" + attribute);
			}
			
			Properties prop = loadPropertiesFile();
			String alljobQuery = prop.getProperty("AllQuery");
			con = JDBCUtil.getConnection_ConsentMgmt() ;
			stmtAll = con.prepareStatement(alljobQuery);
			allDataRS = stmtAll.executeQuery();
			allDataRS.next();
			int jobid = allDataRS.getInt("JOBID");
			int orgUnitid = allDataRS.getInt("ORGANISATIONUNITID");		
			
			String policyQuery = prop.getProperty("policyQuery");
			
			System.out.println(":: policyQuery Q::" + policyQuery + ":: Username::"+authrequestvo.getUserName() +"::TransName ::" + authrequestvo.getTransName());
			//con = JDBCUtil.getConnection_ConsentMgmt() ;
			
			stmt = con.prepareStatement(policyQuery);
			
			stmt.setInt(1, jobid);
			stmt.setInt(2, orgUnitid);
			stmt.setString(3, authrequestvo.getUserName());
			stmt.setString(4, authrequestvo.getTransName());
			
			policyDataRS = stmt.executeQuery();
			
			while(policyDataRS.next())
			{
				System.out.println("::Consent Status ::" + policyDataRS.getString("cstatus") + ":: ATTRIBUTE Content::" + policyDataRS.getString("contype"));
				if(policyDataRS.getString("cstatus").equalsIgnoreCase("NO"))
				{  
					result = "Deny" ;
					break ;
				}
				
				/*if("Allow".equalsIgnoreCase(policyDataRS.getString("contype")))
				{
					System.out.println("Found..");
					result = "Allow" ;
					break ;
				}*/
				
				if(policyDataRS.getString("TXN_DEFAULTACCESS").equalsIgnoreCase("Allow"))
				{
					System.out.println("Allow Block:: ATTRIBUTENAME ::" + policyDataRS.getString("ATTRIBUTENAME"));
					String mapValue = (String)map.get(policyDataRS.getString("ATTRIBUTENAME")) ;
					String maintainedValue  = policyDataRS.getString("ATTRIBUTEVALUE") ;
					System.out.println("Allow Block:: RequestOBJ value ::" + mapValue + ":: maintainedValue::" + maintainedValue + "::Decision ::" + policyDataRS.getString("contype") );
					
					result = "Allow" ;
					if (mapValue != null && maintainedValue !=null && mapValue.equalsIgnoreCase(maintainedValue))
					{
						if("Deny".equalsIgnoreCase(policyDataRS.getString("contype")))
						{
							System.out.println("Found..Deny");
							result = "Deny" ;
							break ;
						}
					}
				}
				
				if(policyDataRS.getString("TXN_DEFAULTACCESS").equalsIgnoreCase("Deny"))
				{   
					System.out.println("Deny Block:: ATTRIBUTENAME ::" + policyDataRS.getString("ATTRIBUTENAME"));
					String mapValue = null ;
					String maintainedValue  = null ;
					if(map != null)
					 mapValue = (String)map.get(policyDataRS.getString("ATTRIBUTENAME")) ;
					 maintainedValue  = policyDataRS.getString("ATTRIBUTEVALUE") ;
					System.out.println("Deny Block:: RequestOBJ value ::" + mapValue + ":: maintainedValue::" + maintainedValue + "::Decision ::" + policyDataRS.getString("contype") );
					
					result = "Deny" ;
					if (mapValue != null && maintainedValue !=null && mapValue.equalsIgnoreCase(maintainedValue))
					{
						if("Allow".equalsIgnoreCase(policyDataRS.getString("contype")))
						{
							System.out.println("Found..Allow");
							result = "Allow" ;
							break ;
						}
					}
				}
				
			}
			
			if ( ! "Allow".equalsIgnoreCase(result))
				result  = "Deny";
			
			System.out.println(":: End :: result"  + result );
		}
		catch(SQLException sqlex)
		{
			sqlex.printStackTrace();
			System.out.println(":: ::" + sqlex);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println(":: ::" + ex);
		}
		finally
		{   try
			{   if(stmtAll!= null)
				stmtAll.close();
				if(allDataRS != null)
					allDataRS.close();
				if(policyDataRS != null)
				policyDataRS.close();
				if(stmt != null)
				stmt.close();
				if(con != null)
				con.close();
			}
		   catch(SQLException ex)
		    {
			
		    } 
		finally {
			 stmtAll =  null ;
			 allDataRS = null ; 
			policyDataRS = null ;
			stmt = null ;
			con = null ;
		}
		      
		}
		
		return result ;
	}
	
	private String getResultConsentData(AuthRequestVO authrequestvo)
	{
		Connection con = null ;
		PreparedStatement stmt =  null ;
		ResultSet consentDataRS = null ; 
		String result = null ;
		
		String consentQuery = "select txn.TRANSACTIONID  ,cnexp.CONSENTSTATUS as cstatus "
				+ "from cosdabactransaction txn  , cosdconsentgrouptxn cntxn ,cosdconsentgroup cngrp , cosdconsentexception cnexp , cosdpersonconsent cnperson " 
				+ " where txn.TRANSACTIONID = cntxn.TRANSACTIONID and cntxn.CONSENTGROUPID = cngrp.CONSENTGROUPID and cnexp.CONSENTGROUPID = cngrp.CONSENTGROUPID and " 
				+ " cnexp.PERSONCONSENTID = cnperson.PERSONCONSENTID and txn.TRANSACTIONNAME = ? and  cnperson.CLIENTID = ?  " 
				+ " and sysdate >= nvl(cnperson.STARTDATE,sysdate-100) and sysdate <= nvl( cnperson.ENDDATE , sysdate+100) and cnperson.HISTORYRECORD = 'NO' ";
		
		try
		{
			
			System.out.println(":: consentQuery Q::" + consentQuery);
			System.out.println(":: Client ID::" + authrequestvo.getClientID());
			System.out.println(":: Src System::" + authrequestvo.getSrcSystem());
			System.out.println(":: Trans Name::" + authrequestvo.getTransName());
			//System.out.println(":: Trans Name::" + authrequestvo.getUserName());
			
			con = JDBCUtil.getConnection_ConsentMgmt() ;
			
			if( authrequestvo.getClientID() == null|| authrequestvo.getClientID().trim().equals("") || authrequestvo.getSrcSystem() == null || 
					authrequestvo.getSrcSystem().trim().equals("") || authrequestvo.getTransName() == null || authrequestvo.getTransName().trim().equals(""))
			{
				System.out.println(":: Values are missing :: so Deny");
				result  = "Deny";
				return result ;
			} 
			
			stmt = con.prepareStatement(consentQuery);
			
			stmt.setString(1, authrequestvo.getTransName());
			stmt.setInt(2,Integer.parseInt(authrequestvo.getClientID()));
			//stmt.setString(3, authrequestvo.getSrcSystem());
			//stmt.setString(4, authrequestvo.getUserName());
			consentDataRS = stmt.executeQuery();
			
			while(consentDataRS.next())
			{
				if(consentDataRS.getString("cstatus").equalsIgnoreCase("YES"))
				{  
					result = "Allow" ;
					break ;
				}
				
				/*if("Allow".equalsIgnoreCase(consentDataRS.getString("contype")))
				{
					result = "Allow" ;
					break ;
				}*/
					
				
			}
			
			if ( ! "Allow".equalsIgnoreCase(result))
				result  = "Deny";
			
			System.out.println(":: End ::" );
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
			System.out.println(":: ::" + ex);
		}
		finally
		{   try
			{   if(consentDataRS != null)
				consentDataRS.close();
				if(stmt != null)
				stmt.close();
				if(con != null)
				con.close();
			}
		   catch(SQLException ex)
		    {
			   ex.printStackTrace();
			   System.out.println(":: ::" + ex);
		    } 
		finally {
			consentDataRS = null ;
			stmt = null ;
			con = null ;
		}
		      
		}
		
		return result ;
	}
	
	public AuthRequest Convert(AuthRequestVO authrequestvo)
	{
		AuthRequest authrequest = new AuthRequest();
		
		authrequest.setAccessReason(authrequestvo.getAccessReason());
		authrequest.setApiSwith(authrequestvo.getApiSwith());		
		authrequest.setClientID(authrequestvo.getClientID());
		authrequest.setConstraintid(authrequestvo.getConstraintid());
		authrequest.setConstraintName(authrequestvo.getConstraintName());
		authrequest.setConstraintValue(authrequestvo.getConstraintValue());
		authrequest.setPrivilegeRequested(authrequestvo.getPrivilegeRequested());
		authrequest.setRequestdt(new Date());
		authrequest.setSrcSystem(authrequestvo.getSrcSystem());
		authrequest.setTransactionid(authrequestvo.getTransactionid());
		authrequest.setTransName(authrequestvo.getTransName());
		authrequest.setUserName(authrequestvo.getUserName());
		System.out.println(":: Reason -" + authrequest.getAccessReason() );
		return authrequest;
	}
	
	public List<AuthRequestVO> getReportAll()
	{
		List<AuthRequestVO> authReqList = new ArrayList<AuthRequestVO>() ;
		Connection con = null ;
		PreparedStatement stmt =  null ;
		ResultSet auditData = null ; 
		String auditReportQuery = " select AUDITID , USERNAME ,CLIENTID ,SRCSYSTEM ,TRANSNAME , APISWITCH , REQUESTDT , RESULT , ACCESSREASON  from CONNECT360_AUDIT order by REQUESTDT desc ,USERNAME";
		
        try
        {
        	con = JDBCUtil.getConnection_ConsentMgmt() ;
			
			stmt = con.prepareStatement(auditReportQuery);
			
			auditData = stmt.executeQuery();
			
			while(auditData.next())
			{
				AuthRequestVO authrequest = new AuthRequestVO();
				
				authrequest.setAuthID(auditData.getLong("AUDITID"));
				authrequest.setApiSwith(auditData.getString("APISWITCH"));		
				authrequest.setClientID(auditData.getString("CLIENTID"));
			/*	authrequest.setConstraintid(authrequestvo.getConstraintid());
				authrequest.setConstraintName(authrequestvo.getConstraintName());
				authrequest.setConstraintValue(authrequestvo.getConstraintValue());
				authrequest.setPrivilegeRequested(authrequestvo.getPrivilegeRequested());
				//authrequest.setTransactionid(authrequestvo.getTransactionid());
				//authrequest.setRequestdt(new java.util.Date(auditData.getDate("CREATEDON").getTime())); */
				if(auditData.getDate("REQUESTDT") != null)
				{
					authrequest.setRequestDt(new java.util.Date(auditData.getDate("REQUESTDT").getTime()));
					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
				    String strDate= formatter.format(authrequest.getRequestDt());  
				    authrequest.setStrrequestDt(strDate);
				    System.out.println("::ReqDt ::" + authrequest.getStrrequestDt());
				}
				 authrequest.setSrcSystem(auditData.getString("SRCSYSTEM"));
				authrequest.setAccessReason(auditData.getString("ACCESSREASON"));
				
				authrequest.setTransName(auditData.getString("TRANSNAME"));
				authrequest.setUserName(auditData.getString("USERNAME"));
				authrequest.setResult(auditData.getString("RESULT"));
				authReqList.add(authrequest);
			}
			
		
        	
        }
        catch(SQLException sqlex)
        {
        	sqlex.printStackTrace();
        }
        finally
        {
        	 try
 			{   if(auditData != null)
 				auditData.close();
 				if(stmt != null)
 				stmt.close();
 				if(con != null)
 				con.close();
 			}
 		   catch(SQLException ex)
 		    {
 			   ex.printStackTrace();
 			   System.out.println(":: ::" + ex);
 		    } 
 		finally {
 			auditData = null ;
 			stmt = null ;
 			con = null ;
 		}
        }
		
		return authReqList;
	}
	
	
	public List<AuthRequestVO> getReportWithCriteriaSearch(AuthRequestVO authrequestvo)
	{
		List<AuthRequestVO> authReqList = new ArrayList<AuthRequestVO>() ;
		Connection con = null ;
		PreparedStatement stmt =  null ;
		ResultSet auditData = null ;  // order by REQUESTDT desc ,USERNAME
		String auditReportQuery = " select AUDITID , USERNAME ,CLIENTID ,SRCSYSTEM ,TRANSNAME , APISWITCH , REQUESTDT , RESULT , ACCESSREASON from CONNECT360_AUDIT ";
		
		String WhereClause = " " ;
		boolean isuserFound = false ;
		if (authrequestvo.getUserName() != null && !authrequestvo.getUserName().trim().equals(""))
		{
			WhereClause = " Where USERNAME = '" + authrequestvo.getUserName() + "'  " ;
			isuserFound = true ;
		}
		
		if (authrequestvo.getSrcSystem() != null && !authrequestvo.getSrcSystem().trim().equals("") )
		{
			if(isuserFound)
			WhereClause = WhereClause +  " And  SRCSYSTEM = '" + authrequestvo.getSrcSystem() + "'  " ;
			else 
			WhereClause = " Where SRCSYSTEM = '" + authrequestvo.getSrcSystem() + "'  " ;
			
			isuserFound = true ;
		}
		
		if (authrequestvo.getApiSwith() != null && !authrequestvo.getApiSwith().trim().equals("") )
		{
			if(isuserFound)
			WhereClause = WhereClause +  " And  APISWITCH = '" + authrequestvo.getApiSwith().toUpperCase() + "'  " ;
			else 
			WhereClause = " Where APISWITCH = '" + authrequestvo.getApiSwith().toUpperCase() + "'  " ;
			
			isuserFound = true ;
		}
		auditReportQuery = auditReportQuery + WhereClause + "  order by REQUESTDT desc ,USERNAME " ;
		
		System.out.println(":: auditReportQuery ::" + auditReportQuery);
        try
        {
        	con = JDBCUtil.getConnection_ConsentMgmt() ;
			
			stmt = con.prepareStatement(auditReportQuery);
			
			auditData = stmt.executeQuery();
			
			while(auditData.next())
			{
				AuthRequestVO authrequest = new AuthRequestVO();
				
				authrequest.setAuthID(auditData.getLong("AUDITID"));
				authrequest.setApiSwith(auditData.getString("APISWITCH"));		
				authrequest.setClientID(auditData.getString("CLIENTID"));
			/*	authrequest.setConstraintid(authrequestvo.getConstraintid());
				authrequest.setConstraintName(authrequestvo.getConstraintName());
				authrequest.setConstraintValue(authrequestvo.getConstraintValue());
				authrequest.setPrivilegeRequested(authrequestvo.getPrivilegeRequested());
				//authrequest.setTransactionid(authrequestvo.getTransactionid());
				//authrequest.setRequestdt(new java.util.Date(auditData.getDate("CREATEDON").getTime())); */
				if(auditData.getDate("REQUESTDT") != null)
				{
					authrequest.setRequestDt(new java.util.Date(auditData.getDate("REQUESTDT").getTime()));
					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
				    String strDate= formatter.format(authrequest.getRequestDt());  
				    authrequest.setStrrequestDt(strDate);
				}
				authrequest.setSrcSystem(auditData.getString("SRCSYSTEM"));
				authrequest.setAccessReason(auditData.getString("ACCESSREASON"));
				authrequest.setTransName(auditData.getString("TRANSNAME"));
				authrequest.setUserName(auditData.getString("USERNAME"));
				authrequest.setResult(auditData.getString("RESULT"));
				authReqList.add(authrequest);
			}
			
		
        	
        }
        catch(SQLException sqlex)
        {
        	sqlex.printStackTrace();
        }
        finally
        {
        	 try
 			{   if(auditData != null)
 				auditData.close();
 				if(stmt != null)
 				stmt.close();
 				if(con != null)
 				con.close();
 			}
 		   catch(SQLException ex)
 		    {
 			   ex.printStackTrace();
 			   System.out.println(":: ::" + ex);
 		    } 
 		finally {
 			
 			auditData = null ;
 			stmt = null ;
 			con = null ;
 		}
        }
		
		return authReqList;
	}
	
	public static Properties loadPropertiesFile() throws Exception
	{
		
		Properties prop = new Properties();
		ClassLoader classLoader = new HibernateUtil().getClass().getClassLoader();
		InputStream in =  classLoader.getResourceAsStream("Query.properties");
        //InputStream in = new FileInputStream("resources\\application.properties");
        prop.load(in);
        in.close();
		return prop;
	}
}
/*
 * Select ctxn.TRANSACTIONID ,ctxn.TRANSACTIONNAME ,aps.CONSENTSTATUS ,catt.ATTRIBUTECONSTRAINTID ,catt.ACCESSCONSTRAINTTYPE from COSDAUTHORIZATIONPOLICY agp , COSDAUTHORIZATIONGROUP ag , COSDAUTHORIZATIONPOLICYSET aps , COSDABACCONSTRAINT catt , COSDABACCONTROLOBJECT co ,COSDABACTRANSACTION ctxn
where 
ctxn.TRANSACTIONID = co.TRANSACTIONID and catt.ATTRIBUTECONSTRAINTID = co.ATTRIBUTECONSTRAINTID and co.CONTROLGROUPID = ag.CONTROLGROUPID and ag.AUTHORIZATIONGROUPID = agp.AUTHORIZATIONGROUPID
and agp.POLICYSETID = aps.POLICYSETID and ctxn.TRANSACTIONNAME = 'hghg'
 */

/*
 * Select ctxn.TRANSACTIONID ,ctxn.TRANSACTIONNAME ,aps.CONSENTSTATUS ,catt.ATTRIBUTECONSTRAINTID ,catt.ACCESSCONSTRAINTTYPE from COSDAUTHORIZATIONPOLICY agp , COSDAUTHORIZATIONGROUP ag , COSDAUTHORIZATIONPOLICYSET aps , COSDABACCONSTRAINT catt , COSDABACCONTROLOBJECT co ,COSDABACTRANSACTION ctxn
where 
ctxn.TRANSACTIONID = co.TRANSACTIONID and catt.ATTRIBUTECONSTRAINTID = co.ATTRIBUTECONSTRAINTID and co.CONTROLGROUPID = ag.CONTROLGROUPID and ag.AUTHORIZATIONGROUPID = agp.AUTHORIZATIONGROUPID
and agp.POLICYSETID = aps.POLICYSETID and ctxn.TRANSACTIONNAME in('hghg' , 'tue_tn') */

/*
 * select txn.TRANSACTIONID  from cosdabactransaction txn  , cosdconsentgrouptxn cntxn ,cosdconsentgroup cngrp , cosdconsentexception cnexp , cosdpersonconsent cnperson 
where txn.TRANSACTIONID = cntxn.TRANSACTIONID and cntxn.CONSENTGROUPID = cngrp.CONSENTGROUPID and cnexp.CONSENTGROUPID = cngrp.CONSENTGROUPID and 
cnexp.PERSONCONSENTID = cnperson.PERSONCONSENTID */

