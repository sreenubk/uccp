package  com.lotus.uccp.consentmgmt.dto;

import java.math.BigDecimal;

import com.lotus.uccp.util.Constant;

@SuppressWarnings("serial")
public class Job implements java.io.Serializable {

	private BigDecimal jobid;
	
	public BigDecimal getJobid() {
		return jobid;
	}
	
	
	public void setJobid(BigDecimal jobid) {
		this.jobid = jobid;
	}
	
	public String getJobname() {
		return jobname;
	}
	
		
	
	public void setJobname(String jobname) {
		this.jobname = jobname.toUpperCase();
	}
	
	public String getJobdesc() {
		return jobdesc;
	}
	
	
	public void setJobdesc(String jobdesc) {
		this.jobdesc = jobdesc;
	}
	private String jobname;
	private String jobdesc;
	
}
