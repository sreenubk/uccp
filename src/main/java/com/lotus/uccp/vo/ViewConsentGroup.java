package  com.lotus.uccp.vo;

import java.util.List;



public class ViewConsentGroup {
	
	private ConsentGroupVO consentGroup ;
	
	public ConsentGroupVO getConsentGroup() {
		return consentGroup;
	}

	public void setConsentGroup(ConsentGroupVO consentGroup) {
		this.consentGroup = consentGroup;
	}

	public List<TransactionVO> getTransList() {
		return transList;
	}

	public void setTransList(List<TransactionVO> transList) {
		this.transList = transList;
	}

	private List<TransactionVO> transList ;
	

}
