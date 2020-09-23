package  com.lotus.uccp.vo;



public class UserAuthorityVO   {

	private int userId;
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public long getAuthorityid() {
		return authorityid;
	}

	public void setAuthorityid(long authorityid) {
		this.authorityid = authorityid;
	}

	public long getUsrId() {
		return usrId;
	}

	public void setUsrId(long usrId) {
		this.usrId = usrId;
	}

	private long authorityid;
	private long usrId;

	public UserAuthorityVO() {
	}

	public UserAuthorityVO(long usrId) {
		this.usrId = usrId;
	}

	

	
}
