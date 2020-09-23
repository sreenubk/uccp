package com.lotus.uccp.consentmgmt.service;

import java.io.InputStream;
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import com.lotus.uccp.util.JDBCUtil;
import com.lotus.uccp.consentmgmt.dao.UsersDao;
import com.lotus.uccp.consentmgmt.dto.User;
import com.lotus.uccp.authentication.model.Users;
import com.lotus.uccp.vo.UsersVO;

public class LdapService {
	
	public String getldapAuthticate(UsersVO usersvo) throws Exception
	{
		if(loadPropertiesFile().getProperty("ldap_enabled").equalsIgnoreCase("NO"))
		{ 
			System.out.println("LDAP is OFF - So Not verified");
			return "sucess";
		}
		
		LdapService lservice = new LdapService();
		
		if("fail".equalsIgnoreCase(lservice.checkInLocalDB(usersvo.getUsername())))
		{
			return "fail";
		}
		
		String userName = null ;
		if(usersvo.getUsername() != null)
		userName = usersvo.getUsername();
		
		String password = usersvo.getPassword() ;
		String ldapUrl = loadPropertiesFile().getProperty("ldapURL");
		String dn = "CN="+ userName+" " +loadPropertiesFile().getProperty("ldapUserDN");
		String authenticationType = loadPropertiesFile().getProperty("authentication_type");
		
		
		Hashtable<String, String> environment = 
			new Hashtable<String, String>();
		environment.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.ldap.LdapCtxFactory");
		environment.put(Context.PROVIDER_URL, ldapUrl);
		environment.put(Context.SECURITY_AUTHENTICATION, authenticationType); //"simple","none"
		environment.put(Context.SECURITY_PRINCIPAL, userName);
		environment.put(Context.SECURITY_CREDENTIALS, password);

		try
		{
			DirContext authContext = 
				new InitialDirContext(environment);
			System.out.println("--sucess--");
			return "sucess";
			// user is authenticated
			
		}
		catch (AuthenticationException ex)
		{
			System.out.println("--AuthenticationException---" + ex);
			ex.printStackTrace();
			return "fail";

		}
		catch (NamingException ex)
		{
			ex.printStackTrace();
			return "fail";
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return "fail";
		}
		
	}
	
	public String checkInLocalDB(String username)
	{
		username = username.toLowerCase();
		
		String username1 = username.replace("@win.root.sonoma.gov", "");
		String username2 = username.replace("win\\", "");
		
		UsersDao dao = new UsersDao();
		Users user = dao.getById(username1);
				
		
		if(user !=null)
		{
			System.out.println(":: :: Found in @win :: ");
			return "sucess";
		}
		
		user = dao.getById(username2);
		
		if(user !=null)
		{
			System.out.println(":: :: Found in win\\ :: ");
			return "sucess";
		}
		
		System.out.println(":: :: Not Found in localDB :: ");
		return "fail";
	}
	
	public String checkPrivilege(String username) 
	{
		try
		{
		username = username.toLowerCase();
		String allowedRole = loadPropertiesFile().getProperty("ALLOWEDROLE");
		
		String username1 = username.replace("@win.root.sonoma.gov", "");
		String username2 = username.replace("win\\", "");
		
		UsersDao dao = new UsersDao();
		Users user = dao.getById(username1);
				
		
		if(user !=null && user.getRolename().equalsIgnoreCase(allowedRole))
		{
			System.out.println(":: :: allowed by Role :: ");
			return "allow";
		}
		
		user = dao.getById(username2);
		
		if(user !=null && user.getRolename().equalsIgnoreCase(allowedRole))
		{
			System.out.println(":: :: allowed by Role :: ");
			return "allow";
		}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		System.out.println(":: :: Not Found in localDB :: ");
		return "deny";
	}
	
	public static Properties loadPropertiesFile() throws Exception
	{
		
		Properties prop = new Properties();
		ClassLoader classLoader = new JDBCUtil().getClass().getClassLoader();
		InputStream in =  classLoader.getResourceAsStream("ldap.properties");
        //InputStream in = new FileInputStream("resources\\application.properties");
        prop.load(in);
        in.close();
		return prop;
	}

	public static void main(String[] args)
	{
		String s = "AcsoNldap@win.root.sonoma.gov";
		String s1 = "win\\acsonldap";
		String s4 = "winsaf\\acsonldap";
	
		LdapService lservice = new LdapService();
		//System.out.println("::-- ::" +lservice.checkInLocalDB(s));
		//System.out.println("::-- ::" +lservice.checkInLocalDB(s1));
		System.out.println("::-- ::" +lservice.checkInLocalDB(s));
	
	}
}
