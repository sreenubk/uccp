package  com.lotus.uccp.vo;

import java.util.Date;


public class SecurityroleVO   {

	private String rolename;
	private String orgname;
	private String jobname;
	private String rolelevel;	
	private Date lastwritten;
	
	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	public String getRolename() {
		return rolename;
	}


	public void setRolename(String rolename) {
		this.rolename = rolename;
	}


	public Date getLastwritten() {
		return lastwritten;
	}


	public void setLastwritten(Date lastwritten) {
		this.lastwritten = lastwritten;
	}

	public String getJobname() {
		return jobname;
	}

	public void setJobname(String jobname) {
		this.jobname = jobname;
	}

	public String getRolelevel() {
		return rolelevel;
	}

	public void setRolelevel(String rolelevel) {
		this.rolelevel = rolelevel;
	}
	

	public SecurityroleVO() {
	}

	

}
