package com.lotus.uccp.consentmgmt.service;




import java.util.Hashtable;
import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class SimpleLdapAuthentication
{
	/*
	 * URL-ldap://192.168.100.40:386(or 389)
	 * port 636
	 * Username: acsonldap
	 * Password: t=7Chohl&iHLFRUcH-si
	 * Base: OU=Users,OU=Department Computers and Users,DC=win,DC=root,DC=sonoma,DC=gov
     * UserDN: CN=acson ldap,OU=Service Accounts,OU=Resource IDs,DC=win,DC=root,DC=sonoma,DC=gov
	 */
	public static void main(String[] args)
	{
//		String username = "RKULKARN";
//		String password = "o5EZd2y4N7";
//		String username = "ACSONLDAP@win";
		String username = "acsonldap@win.root.sonoma.gov";
		//Example: username@domain, DOMAIN\\username
		//String username = "win\\acsonldap";
		String password = "$XeB3Okq^56UtDZr$272&JM0";
		//By changing this to cn=Ross Butler,ou=Users,dc=domain,dc=com it passed!
		String base = "OU=Users,OU=Department Computers and Users,DC=win,DC=root,DC=sonoma,DC=gov";
		//String dn = "uid=" + username + "," + base;
//		String dn = "CN=" + username + "," + base;
		String dn = "CN=acson ldap,OU=Service Accounts,OU=Resource IDs,DC=win,DC=root,DC=sonoma,DC=gov";
//		String ldapURL = "ldap://192.168.100.40:389";
		String ldapURL = "ldap://ldap.win.root.sonoma.gov:389";
//		String ldapURL = "ldap://win.root.sonoma.gov:389";
		//String ldapURL = "ldaps://192.168.100.40:636" ;
		// Setup environment for authenticating
		
		Hashtable<String, String> environment = 
			new Hashtable<String, String>();
		environment.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.ldap.LdapCtxFactory");
		environment.put(Context.PROVIDER_URL, ldapURL);
		environment.put(Context.SECURITY_AUTHENTICATION, "simple"); //"simple","none"
		environment.put(Context.SECURITY_PRINCIPAL, username);
		environment.put(Context.SECURITY_CREDENTIALS, password);

		try
		{
			DirContext authContext = 
				new InitialDirContext(environment);
			
			System.out.println("--Try block ---");
			// user is authenticated
			
		}
		catch (AuthenticationException ex)
		{
			System.out.println("--AuthenticationException---" + ex);
			ex.printStackTrace();
			// Authentication failed

		}
		catch (NamingException ex)
		{
			ex.printStackTrace();
		}
	}
}
