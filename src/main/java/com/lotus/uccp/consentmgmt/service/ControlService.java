package  com.lotus.uccp.consentmgmt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lotus.uccp.consentmgmt.dao.ConstraintDao;
import com.lotus.uccp.consentmgmt.dao.ControlGroupDao;
import com.lotus.uccp.consentmgmt.dao.TransactionDao;
import com.lotus.uccp.authentication.model.Constraint;
import com.lotus.uccp.authentication.model.ControlGroup;
import com.lotus.uccp.authentication.model.Transaction;

@Service("ControlService")
public class ControlService {
	
	
	
	/*------------------------------  Constraint ----------------------------------------*/
	public List<Constraint> getListConstraint()
	{
		ConstraintDao cosdabactransactiondao = new ConstraintDao();
		List<Constraint> constraintlist = cosdabactransactiondao.getlist() ;
		return constraintlist ;
	}
	
	public void saveConstraint(long grpID , long txnid ,Constraint cosdabacconstraint)
	{
		ConstraintDao cosdabacconstraintdao = new ConstraintDao();
		cosdabacconstraintdao.save(grpID, txnid,cosdabacconstraint);
	}
	
	public String updateConstraint(Constraint cosdabacconstraint)
	{
		ConstraintDao cosdabacconstraintdao = new ConstraintDao();
		return cosdabacconstraintdao.update(cosdabacconstraint);
	}
	
	public String deleteConstraint(long id)
	{
		ConstraintDao cosdabacconstraintdao = new ConstraintDao();
		return cosdabacconstraintdao.delete(id);
	}
	
	public Constraint getConstraint(long id)
	{
		ConstraintDao cosdabacconstraintdao = new ConstraintDao();
		return cosdabacconstraintdao.getById(id);
	}
	
	/*------------------------------  Transaction ----------------------------------------*/
	public List<Transaction> getListCosdabacTxn()
	{
		TransactionDao cosdabactransactiondao = new TransactionDao();
		List<Transaction> cosdabactxngrouplist = cosdabactransactiondao.getlist() ;
		return cosdabactxngrouplist ;
	}
	
	public void saveCosdabacTxn(long grpID, Transaction cosdabactransaction)
	{
		TransactionDao cosdabactransactiondao = new TransactionDao();
		cosdabactransactiondao.save(grpID , cosdabactransaction);
	}
	
	public void updateCosdabacTxn(Transaction cosdabactransaction)
	{
		TransactionDao cosdabactransactiondao = new TransactionDao();
		cosdabactransactiondao.update(cosdabactransaction);
	}
	
	public String deleteCosdabacTxn(long id)
	{
		TransactionDao cosdabactransactiondao = new TransactionDao();
		return cosdabactransactiondao.delete(id);
	}
	
	public Transaction getTxn(long id)
	{
		TransactionDao cosdabactransactiondao = new TransactionDao();
		return cosdabactransactiondao.getById(id);
	}
	
	/*------------------------------  ControlGroup ----------------------------------------*/
	public List<ControlGroup> getListControlGroup()
	{ 
		ControlGroupDao cosdabaccontrolgroupdao = new ControlGroupDao();
		List<ControlGroup> ControlGrouplist = cosdabaccontrolgroupdao.getlist() ;
		return ControlGrouplist ;
	}
	
	public List<ControlGroup> getListcontrolgroupWithGrpName(String grpName)
	{
		ControlGroupDao cosdabaccontrolgroupdao = new ControlGroupDao();
		List<ControlGroup> ControlGrouplist = cosdabaccontrolgroupdao.getlistByGroupName(grpName) ;
		return ControlGrouplist ;
	}
	
	public ControlGroup getGrpwithID(long id)
	{
		ControlGroupDao cosdabaccontrolgroupdao = new ControlGroupDao();		
		ControlGroup cosdabaccontrolgroup = cosdabaccontrolgroupdao.getById(id);
		
		return cosdabaccontrolgroup ;
	}
	
	public void saveControlGroup(ControlGroup cosdabaccontrolgroup)
	{
		ControlGroupDao cosdabaccontrolgroupdao = new ControlGroupDao();
		cosdabaccontrolgroupdao.save(cosdabaccontrolgroup);
	}
	
	public String updateControlGroup(ControlGroup cosdabaccontrolgroup)
	{
		ControlGroupDao cosdabaccontrolgroupdao = new ControlGroupDao();
		return cosdabaccontrolgroupdao.update(cosdabaccontrolgroup);
	}
	
	public String deleteControlGroup(long id)
	{
		ControlGroupDao cosdabaccontrolgroupdao = new ControlGroupDao();
		return cosdabaccontrolgroupdao.delete(id);
	}

}
