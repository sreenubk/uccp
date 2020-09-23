package com.lotus.uccp.vo;

public class ProgramaticDataInput {
	
		  private String SourceSys;
		  private String PersonID;
		  private String role;
		  private String org;
		  private String job;
		  private String userName;


		 // Getter Methods 

		  public String getSourceSys() {
		    return SourceSys;
		  }

		  public String getPersonID() {
		    return PersonID;
		  }

		  public String getRole() {
		    return role;
		  }

		  public String getOrg() {
		    return org;
		  }

		  public String getJob() {
		    return job;
		  }

		  public String getUserName() {
		    return userName;
		  }

		 // Setter Methods 

		  public void setSourceSys( String SourceSys ) {
		    this.SourceSys = SourceSys;
		  }

		  public void setPersonID( String PersonID ) {
		    this.PersonID = PersonID;
		  }

		  public void setRole( String role ) {
		    this.role = role;
		  }

		  public void setOrg( String org ) {
		    this.org = org;
		  }

		  public void setJob( String job ) {
		    this.job = job;
		  }

		  public void setUserName( String userName ) {
		    this.userName = userName;
		  }
		  
		  public String getObject()
		  {
			  String s =  " Role : " +  this.role + " Org : " + this.org + " Job : " + this.job + " SourceSystem :- " + this.SourceSys + " PersonID : -" + this.PersonID ;
			  return s ;
		  }
		
}
