package  com.lotus.uccp.vo;

import java.util.Date;
public class C360SavedResultsIdVO   {

	private int id;
	private String referralnum;
	private String username;
	private String inactiveflag;
	private Date saveddate;
	private String personfname;
	private String personlname;
	private String personaddress;
	private String sourcesys;
	private String sourcesyspersonid;
	private String usercomments;

	public C360SavedResultsIdVO() {
	}

	public C360SavedResultsIdVO(int id, String referralnum, String username) {
		this.id = id;
		this.referralnum = referralnum;
		this.username = username;
	}

	public C360SavedResultsIdVO(int id, String referralnum, String username, String inactiveflag, Date saveddate,
			String personfname, String personlname, String personaddress, String sourcesys, String sourcesyspersonid,
			String usercomments) {
		this.id = id;
		this.referralnum = referralnum;
		this.username = username;
		this.inactiveflag = inactiveflag;
		this.saveddate = saveddate;
		this.personfname = personfname;
		this.personlname = personlname;
		this.personaddress = personaddress;
		this.sourcesys = sourcesys;
		this.sourcesyspersonid = sourcesyspersonid;
		this.usercomments = usercomments;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReferralnum() {
		return this.referralnum;
	}

	public void setReferralnum(String referralnum) {
		this.referralnum = referralnum;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getInactiveflag() {
		return this.inactiveflag;
	}

	public void setInactiveflag(String inactiveflag) {
		this.inactiveflag = inactiveflag;
	}

	public Date getSaveddate() {
		return this.saveddate;
	}

	public void setSaveddate(Date saveddate) {
		this.saveddate = saveddate;
	}

	public String getPersonfname() {
		return this.personfname;
	}

	public void setPersonfname(String personfname) {
		this.personfname = personfname;
	}

	public String getPersonlname() {
		return this.personlname;
	}

	public void setPersonlname(String personlname) {
		this.personlname = personlname;
	}

	public String getPersonaddress() {
		return this.personaddress;
	}

	public void setPersonaddress(String personaddress) {
		this.personaddress = personaddress;
	}

	public String getSourcesys() {
		return this.sourcesys;
	}

	public void setSourcesys(String sourcesys) {
		this.sourcesys = sourcesys;
	}

	public String getSourcesyspersonid() {
		return this.sourcesyspersonid;
	}

	public void setSourcesyspersonid(String sourcesyspersonid) {
		this.sourcesyspersonid = sourcesyspersonid;
	}

	public String getUsercomments() {
		return this.usercomments;
	}

	public void setUsercomments(String usercomments) {
		this.usercomments = usercomments;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof C360SavedResultsIdVO))
			return false;
		C360SavedResultsIdVO castOther = (C360SavedResultsIdVO) other;

		return (this.getId() == castOther.getId())
				&& ((this.getReferralnum() == castOther.getReferralnum())
						|| (this.getReferralnum() != null && castOther.getReferralnum() != null
								&& this.getReferralnum().equals(castOther.getReferralnum())))
				&& ((this.getUsername() == castOther.getUsername()) || (this.getUsername() != null
						&& castOther.getUsername() != null && this.getUsername().equals(castOther.getUsername())))
				&& ((this.getInactiveflag() == castOther.getInactiveflag())
						|| (this.getInactiveflag() != null && castOther.getInactiveflag() != null
								&& this.getInactiveflag().equals(castOther.getInactiveflag())))
				&& ((this.getSaveddate() == castOther.getSaveddate()) || (this.getSaveddate() != null
						&& castOther.getSaveddate() != null && this.getSaveddate().equals(castOther.getSaveddate())))
				&& ((this.getPersonfname() == castOther.getPersonfname())
						|| (this.getPersonfname() != null && castOther.getPersonfname() != null
								&& this.getPersonfname().equals(castOther.getPersonfname())))
				&& ((this.getPersonlname() == castOther.getPersonlname())
						|| (this.getPersonlname() != null && castOther.getPersonlname() != null
								&& this.getPersonlname().equals(castOther.getPersonlname())))
				&& ((this.getPersonaddress() == castOther.getPersonaddress())
						|| (this.getPersonaddress() != null && castOther.getPersonaddress() != null
								&& this.getPersonaddress().equals(castOther.getPersonaddress())))
				&& ((this.getSourcesys() == castOther.getSourcesys()) || (this.getSourcesys() != null
						&& castOther.getSourcesys() != null && this.getSourcesys().equals(castOther.getSourcesys())))
				&& ((this.getSourcesyspersonid() == castOther.getSourcesyspersonid())
						|| (this.getSourcesyspersonid() != null && castOther.getSourcesyspersonid() != null
								&& this.getSourcesyspersonid().equals(castOther.getSourcesyspersonid())))
				&& ((this.getUsercomments() == castOther.getUsercomments())
						|| (this.getUsercomments() != null && castOther.getUsercomments() != null
								&& this.getUsercomments().equals(castOther.getUsercomments())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getId();
		result = 37 * result + (getReferralnum() == null ? 0 : this.getReferralnum().hashCode());
		result = 37 * result + (getUsername() == null ? 0 : this.getUsername().hashCode());
		result = 37 * result + (getInactiveflag() == null ? 0 : this.getInactiveflag().hashCode());
		result = 37 * result + (getSaveddate() == null ? 0 : this.getSaveddate().hashCode());
		result = 37 * result + (getPersonfname() == null ? 0 : this.getPersonfname().hashCode());
		result = 37 * result + (getPersonlname() == null ? 0 : this.getPersonlname().hashCode());
		result = 37 * result + (getPersonaddress() == null ? 0 : this.getPersonaddress().hashCode());
		result = 37 * result + (getSourcesys() == null ? 0 : this.getSourcesys().hashCode());
		result = 37 * result + (getSourcesyspersonid() == null ? 0 : this.getSourcesyspersonid().hashCode());
		result = 37 * result + (getUsercomments() == null ? 0 : this.getUsercomments().hashCode());
		return result;
	}

}
