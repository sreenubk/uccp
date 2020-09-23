package  com.lotus.uccp.consentmgmt.service;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotus.uccp.util.Constant;
import com.lotus.uccp.util.JDBCUtil;
import com.lotus.uccp.consentmgmt.dao.AuthRequestDao;
import com.lotus.uccp.consentmgmt.dto.AuthRequest;
import com.lotus.uccp.vo.ProgramaticDataInput;



public class ConsentMgmtService {
	
	public static final Logger logger = LoggerFactory.getLogger(ConsentMgmtService.class);
	private HashMap<String,String> hm = new HashMap();
	private String allowSection = "" ;
	
	public ConsentMgmtService()
	{
		this.hm.put("DEMOGRAPHICS", "ALLOW");
		this.hm.put("EXTENDED DEMOGRAPHICS", "ALLOW");
		this.hm.put("PUBLIC ASSISTANCE PROGRAMS", "DENY");
		this.hm.put("PHYSICAL HEALTH INFORMATION", "DENY");
		this.hm.put("MENTAL HEALTH INFORMATION", "DENY");
		this.hm.put("ALCOHOL AND DRUG HISTORY", "DENY");
		this.hm.put("JUSTICE DATA", "DENY");
	}
	
	public HashMap getConsentResult(ProgramaticDataInput pgmInput)
	{
		logger.info(" Method - getConsentResult() ");
		if(pgmInput.getJob().equalsIgnoreCase("Y"))
			pgmInput.setJob("IMDT PARTICIPANT");
		if(pgmInput.getJob().equalsIgnoreCase("N"))
			pgmInput.setJob("NOT AN IMDT PARTICIPANT");
		
		logger.info(" Input ProgramaticDataInput - " + pgmInput.getObject());
		AuthRequestDao authDao = new AuthRequestDao();
		AuthRequest authRequest = new AuthRequest();
		 
		if(pgmInput.getSourceSys().equalsIgnoreCase("wcm") || pgmInput.getSourceSys().equalsIgnoreCase("WCMCLIENTID"))
		{
			getConsentWCM(pgmInput);
		}
		else
		{
			getResultNonConsentRecords(pgmInput);
		}
		
		authRequest.setClientID(pgmInput.getPersonID());
		authRequest.setSrcSystem(pgmInput.getSourceSys());
		authRequest.setApiSwith("BOTH");
		authRequest.setRequestdt(new Date());
		authRequest.setResult(allowSection);
		authRequest.setUserName(pgmInput.getUserName());
		authRequest.setTransName("WCM-Full Record");
		authDao.save(authRequest);
		return this.hm ;
	}
	
	public HashMap getConsentWCM(ProgramaticDataInput pgmInput)
	{
		logger.info(" Method - getConsentWCM() ");
		
		Connection con = null ;
		ResultSet rs = null ;
		PreparedStatement ps = null ;
		try
		{
			Properties prop = loadPropertiesFile();
			String consentQuery = prop.getProperty("ConsentQuery");
			// 1 job name , 2. role name 3. org name
			con = JDBCUtil.getConnection_ConsentMgmt();
			ps = con.prepareStatement(consentQuery);  
			ps.setString(1, pgmInput.getJob());
			ps.setString(2, pgmInput.getRole());
			ps.setString(3, pgmInput.getOrg());
			logger.info("SQL --::" + consentQuery);
			rs=ps.executeQuery();
			String result = "" ;
			int i = 0 ;
			 while(rs.next())
			 {
				 
				 result = rs.getString("accessBy_policy").equalsIgnoreCase("YES") ? "ALLOW" : "DENY" ;
				 if(result.equalsIgnoreCase("ALLOW"))
				 {	
					 i++ ;
				     allowSection = allowSection +" "+ rs.getString("txnname").toUpperCase() +" , " ;
				 }
				 this.hm.put(rs.getString("txnname").toUpperCase(), result);
				 
				 logger.info("Key Name : -" + rs.getString("txnname") + "Key Value : -" + result);
				 
			 }
			 if(i >= 7)
				 allowSection = "All section Allow";
			 if(i == 0)
				 allowSection = "All section Deny";
			 if(i < 7)
				 allowSection =  "Allowed Section:: -"+ allowSection ;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		logger.info(" Method - Exit getConsentWCM() Size of MAP - " + this.hm.size());
		logger.info(" AllowSection - " + allowSection);
	  return this.hm ;	
	}
	
	public HashMap getResultNonConsentRecords(ProgramaticDataInput pgmInput)
	{
		logger.info(" Method - getResultNonConsentRecords() ");
		
		 HashMap hm = new HashMap();
		 
		 
		 Connection con = null ;
		 ResultSet rs = null ;
		 String sql = "select NOCONSENTRECORDID ,ORGNAME ,ROLENAME ,SUBJECTAREA ,PERMISSION from NO_CONSENT_RECORD where ORGNAME = ? and ROLENAME = ?";
		 StringTokenizer subjectArea = null ;
		 String subjectAreas = null ;
		 String permission = null ;
		try
		{
			 con = JDBCUtil.getConnection_ConsentMgmt();
			 PreparedStatement ps=con.prepareStatement(sql);  
			 ps.setString(1, pgmInput.getOrg());
			 ps.setString(2, pgmInput.getRole());
			 rs=ps.executeQuery(); 
			 int i = 0 ;
			 logger.info(" SQL - getResultNonConsentRecords() - ::" + sql);
			 while(rs.next())
			 {
				 subjectAreas = rs.getString("SUBJECTAREA");
				 String[] words = subjectAreas.split(",");
				 permission = rs.getString("PERMISSION");
				  i = 0 ;
				 while(words.length > i)
				 {
					 logger.info(" Subject Area :: -" + words[i].toUpperCase().trim());
					 this.hm.put(words[i].toUpperCase().trim(), rs.getString("PERMISSION").toUpperCase());
					 allowSection = allowSection +" "+words[i].toUpperCase().trim() ;
					 i++ ;
				 }
				 
				 logger.info("Map Updated with -::" + i + ":: values ");
				 
			 }
			 if(i >= 7)
				 allowSection = "All section Allow";
			 if(i == 0)
				 allowSection = "All section Deny"; 
			 if(i < 7)
				 allowSection =  "Allowed Section:: -"+ allowSection ;
		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		 logger.info("Exit from - getResultNonConsentRecords() :: Size of MAP - " + this.hm.size() );
		 logger.info(" AllowSection - " + allowSection);
		return this.hm ;
	}
	
	public static void main(String[] args) throws Exception {
		
		ConsentMgmtService consentService = new ConsentMgmtService();
		ProgramaticDataInput pgmInput = new ProgramaticDataInput();
		//
		pgmInput.setOrg("HUMAN SERVICES DEPARTMENT");
		pgmInput.setRole("CARE TEAM"); // 'IMDT PARTICIPANT'
		pgmInput.setJob("NOT AN IMDT PARTICIPANT");
		pgmInput.setSourceSys("Connect360");
		//consentService.getResultNonConsentRecords(pgmInput);
		consentService.getConsentResult(pgmInput);
	}
	
	public static Properties loadPropertiesFile() throws Exception
	{
		
		Properties prop = new Properties();
		ClassLoader classLoader = new Constant().getClass().getClassLoader();
		InputStream in =  classLoader.getResourceAsStream("Query.properties");
        //InputStream in = new FileInputStream("resources\\application.properties");
        prop.load(in);
        in.close();
		return prop;
	}

}
