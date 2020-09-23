package  com.lotus.uccp.vo;

import java.math.BigDecimal;
import java.util.Date;


public class ControlObjectVO   {
	         
	private BigDecimal controlobjectid;
	public BigDecimal getControlobjectid() {
		return controlobjectid;
	}

	public void setControlobjectid(BigDecimal controlobjectid) {
		this.controlobjectid = controlobjectid;
	}

	public BigDecimal getCosdabacconstraintiD() {
		return cosdabacconstraintiD;
	}

	public void setCosdabacconstraintiD(BigDecimal cosdabacconstraintiD) {
		this.cosdabacconstraintiD = cosdabacconstraintiD;
	}

	public BigDecimal getCosdabaccontrolgroupiD() {
		return cosdabaccontrolgroupiD;
	}

	public void setCosdabaccontrolgroupiD(BigDecimal cosdabaccontrolgroupiD) {
		this.cosdabaccontrolgroupiD = cosdabaccontrolgroupiD;
	}

	public BigDecimal getCosdabactransactioniD() {
		return cosdabactransactioniD;
	}

	public void setCosdabactransactioniD(BigDecimal cosdabactransactioniD) {
		this.cosdabactransactioniD = cosdabactransactioniD;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public Date getCreatedon() {
		return createdon;
	}

	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}

	public String getLastupdatedby() {
		return lastupdatedby;
	}

	public void setLastupdatedby(String lastupdatedby) {
		this.lastupdatedby = lastupdatedby;
	}

	public Date getLastupdatedon() {
		return lastupdatedon;
	}

	public void setLastupdatedon(Date lastupdatedon) {
		this.lastupdatedon = lastupdatedon;
	}

	private BigDecimal cosdabacconstraintiD;
	private BigDecimal cosdabaccontrolgroupiD;
	private BigDecimal cosdabactransactioniD;
	private String createdby;
	private Date createdon;
	private String lastupdatedby;
	private Date lastupdatedon;

	public ControlObjectVO() {
	}

	public ControlObjectVO(BigDecimal controlobjectid) {
		this.controlobjectid = controlobjectid;
	}

	

	
}
