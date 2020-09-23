package com.lotus.uccp.consentmgmt.service;

public class Test {
	
	public static void main(String[] args)
	{
		String s = "acsonldap@win.root.sonoma.gov";
		String s1 = "win\\acsonldap";
		
		String sarray[] = null ;
		if(s.contains("@win.root.sonoma.gov"))
		{
			 sarray = s.split("@");
			 System.out.println("1");
		}
		
		if(s1.contains("Win\\"))
		{
			// sarray = s.split("@");
			 System.out.println("2");
		}
	
		
		
	}

}
