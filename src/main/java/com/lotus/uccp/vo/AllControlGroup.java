package  com.lotus.uccp.vo;

import java.util.List;


public class AllControlGroup {
	
	ControlGroupVO controlGroup ;	 
	public ControlGroupVO getControlGroup() {
		return controlGroup;
	}
	public void setControlGroup(ControlGroupVO controlGroup) {
		this.controlGroup = controlGroup;
	}
	public List<ATTCosdabactransactionVO> getTransaction() {
		return transaction;
	}
	public void setTransaction(List<ATTCosdabactransactionVO> transaction) {
		this.transaction = transaction;
	}
	List<ATTCosdabactransactionVO>  transaction ;
}
