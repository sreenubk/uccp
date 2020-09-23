package  com.lotus.uccp.vo;

import java.math.BigDecimal;


public class JobVO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BigDecimal jobid;
	public BigDecimal getJobid() {
		return jobid;
	}
	
	
	public void setJobid(BigDecimal jobid) {
		this.jobid = jobid;
	}
	public String getJobName() {
		return jobName;
	}
	
		

	public void setJobName(String jobname) {
		this.jobName = jobname.toUpperCase();
	}
	public String getJobdesc() {
		return jobdesc;
	}
	
	
	public void setJobdesc(String jobdesc) {
		this.jobdesc = jobdesc;
	}
	private String jobName;
	private String jobdesc;
	
}
