package  com.lotus.uccp.vo;

import java.util.List;

public class ViewPolicyAuthGroup {
	
	private List<AuthorizationGroupVO> authGroups ;
	private AuthorizationPolicySetVO policyset ;
	
    public List<AuthorizationGroupVO> getAuthGroups() {
		return authGroups;
	}
	public void setAuthGroups(List<AuthorizationGroupVO> authGroups) {
		this.authGroups = authGroups;
	}
	public AuthorizationPolicySetVO getPolicyset() {
		return policyset;
	}
	public void setPolicyset(AuthorizationPolicySetVO policyset) {
		this.policyset = policyset;
	}
	
       
}
