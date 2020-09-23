package  com.lotus.uccp.consentmgmt.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.lotus.uccp.authentication.model.OrganizationUnit;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lotus.uccp.util.HibernateUtil;
import com.lotus.uccp.util.JDBCUtil;
import com.lotus.uccp.authentication.model.ControlGroup;
import com.lotus.uccp.authentication.model.ControlObject;
import com.lotus.uccp.util.Converters;
import com.lotus.uccp.vo.ATTCosdabactransactionVO;
import com.lotus.uccp.vo.AllControlGroup;
import com.lotus.uccp.vo.ConstraintVO;
import com.lotus.uccp.vo.ControlGroupVO;
import com.lotus.uccp.vo.TransactionVO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SuppressWarnings("JpaQlInspection")
@Repository
public class ControlGroupDao {

	@PersistenceContext
	EntityManager entityManager;
	
	
	public List<ConstraintVO> searchAttributeData(String transName)
	{
		AllControlGroup allcontrolgroup = new AllControlGroup();
		
		ControlGroupDao controlGroupDao = new ControlGroupDao();
		//ControlGroup cosdabaccontrolgroup = controlGroupDao.getById(controlGroupVO.getControlgroupid().longValue());
		Converters conv = new Converters();
		
		//allcontrolgroup.setControlGroup(conv.convertToVo(cosdabaccontrolgroup));
		String sqlTrans = "select DISTINCT cobj.CONTROLGROUPID as grpid , trans.TRANSACTIONID , trans.CREATEDBY , trans.CREATEDON ,trans.DEFAULTACCESS , trans.LASTUPDATEDBY , trans.LASTUPDATEDON , trans.TRANSACTIONNAME , trans.TRANSACTIONTYPE from COSDABACTRANSACTION trans ,COSDABACCONTROLOBJECT cobj where "
				+ " cobj.TRANSACTIONID = trans.TRANSACTIONID and   trans.TRANSACTIONNAME =?" ;
		
		String sqlConstraint = "select * from COSDABACCONSTRAINT const ,COSDABACCONTROLOBJECT cobj where "
				+ "cobj.attributeconstraintid = const.attributeconstraintid and cobj.CONTROLGROUPID = ? and cobj.transactionid = ? ";
		
		List<ATTCosdabactransactionVO> trans = new ArrayList<ATTCosdabactransactionVO>();
		List<ConstraintVO>  attributeslist = null ;
		Connection con = null ;
		PreparedStatement stmt =  null ;
		ResultSet resultTrans = null ; 
		ResultSet resultAtt = null ;
		PreparedStatement attstmt =  null ;


		try
		{
			con = JDBCUtil.getConnection_ConsentMgmt() ;

			stmt = con.prepareStatement(sqlTrans);

			stmt.setString(1, transName);

			resultTrans = stmt.executeQuery();

			while(resultTrans.next())
			{
				attstmt =  null ;
				ATTCosdabactransactionVO attTrans = new ATTCosdabactransactionVO();
				attTrans.setTransactionid(new BigDecimal(resultTrans.getInt("TRANSACTIONID")));
				attTrans.setCreatedby(resultTrans.getString("CREATEDBY"));
				attTrans.setCreatedon(new java.util.Date(resultTrans.getDate("CREATEDON").getTime()));
				attTrans.setDefaultaccess(resultTrans.getString("DEFAULTACCESS"));
				attTrans.setLastupdatedby(resultTrans.getString("LASTUPDATEDBY"));
				attTrans.setLastupdatedon(new java.util.Date(resultTrans.getDate("LASTUPDATEDON").getTime()));
				attTrans.setTransactionname(resultTrans.getString("TRANSACTIONNAME"));
				attTrans.setTransactiontype(resultTrans.getString("TRANSACTIONTYPE"));

				System.out.println("::TRANSACTIONID ::"+ resultTrans.getInt("TRANSACTIONID"));
				try
				{
					attstmt = con.prepareStatement(sqlConstraint);
					attstmt.setInt(1, resultTrans.getInt("grpid"));
					attstmt.setInt(2, resultTrans.getInt("TRANSACTIONID"));
					attributeslist = new<ConstraintVO> ArrayList() ;
					resultAtt = attstmt.executeQuery();
					while(resultAtt.next())
					{
						ConstraintVO attributes = new ConstraintVO();
						attributes.setAttributeconstraintid(new BigDecimal(resultAtt.getInt("ATTRIBUTECONSTRAINTID")));
						attributes.setAccessconstrainttype(resultAtt.getString("ACCESSCONSTRAINTTYPE"));
						attributes.setAttributename(resultAtt.getString("ATTRIBUTENAME"));
						attributes.setAttributevalue(resultAtt.getString("ATTRIBUTEVALUE"));
						attributes.setCreatedby(resultAtt.getString("CREATEDBY"));
						attributes.setLastupdatedby(resultAtt.getString("LASTUPDATEDBY"));
						attributes.setCreatedon(new java.util.Date(resultTrans.getDate("CREATEDON").getTime()));
						attributes.setLastupdatedon(new java.util.Date(resultTrans.getDate("LASTUPDATEDON").getTime()));
						System.out.println("::ATTRIBUTECONSTRAINTID ::"+ resultAtt.getInt("ATTRIBUTECONSTRAINTID"));
						attributeslist.add(attributes);
						//attTrans.setCosdabacconstraintvo(attributes);
					}

					attTrans.setCosdabacconstraintvo(attributeslist);
				}
				finally
				{
					if(resultAtt!=null)
						resultAtt.close();

						if(attstmt!=null)
							attstmt.close();
				}
				trans.add(attTrans);
			}

			allcontrolgroup.setTransaction(trans);
		}
		catch(Exception ex)
		{

		}
		finally
		{
			try
			{
				if(resultTrans!=null)
				resultTrans.close();

				if(stmt!=null)
					stmt.close();

				if(con!=null)
					con.close();
			}
			catch(Exception exsql)
			{

			}
		}

		
		return attributeslist ;
		
	}
	
	public AllControlGroup searchControlData(ControlGroupVO controlGroupVO)
	{
		AllControlGroup allcontrolgroup = new AllControlGroup();
		
		ControlGroupDao controlGroupDao = new ControlGroupDao();
		ControlGroup cosdabaccontrolgroup = controlGroupDao.getById(controlGroupVO.getControlgroupid().longValue());
		Converters conv = new Converters();
		
		allcontrolgroup.setControlGroup(conv.convertToVo(cosdabaccontrolgroup));
		String sqlTrans = "select DISTINCT trans.TRANSACTIONID , trans.CREATEDBY , trans.CREATEDON ,trans.DEFAULTACCESS , trans.LASTUPDATEDBY , trans.LASTUPDATEDON , trans.TRANSACTIONNAME , trans.TRANSACTIONTYPE from COSDABACTRANSACTION trans ,COSDABACCONTROLOBJECT cobj where "
				+ " cobj.TRANSACTIONID = trans.TRANSACTIONID and cobj.CONTROLGROUPID = ? " ;
		
		String sqlConstraint = "select * from COSDABACCONSTRAINT const ,COSDABACCONTROLOBJECT cobj where "
				+ "cobj.attributeconstraintid = const.attributeconstraintid and cobj.CONTROLGROUPID = ? and cobj.transactionid = ? ";
		
		List<ATTCosdabactransactionVO> trans = new ArrayList<ATTCosdabactransactionVO>();
		Connection con = null ;
		PreparedStatement stmt =  null ;
		ResultSet resultTrans = null ; 
		ResultSet resultAtt = null ;
		PreparedStatement attstmt =  null ;
		try
		{
			con = JDBCUtil.getConnection_ConsentMgmt() ;
			
			stmt = con.prepareStatement(sqlTrans);
			
			stmt.setInt(1, cosdabaccontrolgroup.getControlgroupid().intValue());
			
			resultTrans = stmt.executeQuery();
			
			while(resultTrans.next()) 
			{
				attstmt =  null ;
				ATTCosdabactransactionVO attTrans = new ATTCosdabactransactionVO();
				attTrans.setTransactionid(new BigDecimal(resultTrans.getInt("TRANSACTIONID")));
				attTrans.setCreatedby(resultTrans.getString("CREATEDBY"));
				attTrans.setCreatedon(new java.util.Date(resultTrans.getDate("CREATEDON").getTime()));
				attTrans.setDefaultaccess(resultTrans.getString("DEFAULTACCESS"));
				attTrans.setLastupdatedby(resultTrans.getString("LASTUPDATEDBY"));
				attTrans.setLastupdatedon(new java.util.Date(resultTrans.getDate("LASTUPDATEDON").getTime()));
				attTrans.setTransactionname(resultTrans.getString("TRANSACTIONNAME"));
				attTrans.setTransactiontype(resultTrans.getString("TRANSACTIONTYPE"));
				
				System.out.println("::TRANSACTIONID ::"+ resultTrans.getInt("TRANSACTIONID"));
				try
				{
					attstmt = con.prepareStatement(sqlConstraint);
					attstmt.setInt(1, cosdabaccontrolgroup.getControlgroupid().intValue());
					attstmt.setInt(2, resultTrans.getInt("TRANSACTIONID"));
					List<ConstraintVO>  attributeslist = new<ConstraintVO> ArrayList() ;
					resultAtt = attstmt.executeQuery();
					while(resultAtt.next())
					{
						ConstraintVO attributes = new ConstraintVO();
						attributes.setAttributeconstraintid(new BigDecimal(resultAtt.getInt("ATTRIBUTECONSTRAINTID")));
						attributes.setAccessconstrainttype(resultAtt.getString("ACCESSCONSTRAINTTYPE"));
						attributes.setAttributename(resultAtt.getString("ATTRIBUTENAME"));
						attributes.setAttributevalue(resultAtt.getString("ATTRIBUTEVALUE"));
						attributes.setCreatedby(resultAtt.getString("CREATEDBY"));
						attributes.setLastupdatedby(resultAtt.getString("LASTUPDATEDBY"));
						attributes.setCreatedon(new java.util.Date(resultTrans.getDate("CREATEDON").getTime()));
						attributes.setLastupdatedon(new java.util.Date(resultTrans.getDate("LASTUPDATEDON").getTime()));
						System.out.println("::ATTRIBUTECONSTRAINTID ::"+ resultAtt.getInt("ATTRIBUTECONSTRAINTID"));
						attributeslist.add(attributes);
						//attTrans.setCosdabacconstraintvo(attributes);
					}
					
					attTrans.setCosdabacconstraintvo(attributeslist);
				}
				finally
				{
					if(resultAtt!=null)
						resultAtt.close();
						
						if(attstmt!=null)
							attstmt.close();
				}
				trans.add(attTrans);
			}
			
			allcontrolgroup.setTransaction(trans);
		}
		catch(Exception ex)
		{
			
		}
		finally
		{
			try
			{
				if(resultTrans!=null)
				resultTrans.close();
				
				if(stmt!=null)
					stmt.close();
				
				if(con!=null)
					con.close();
			}
			catch(Exception exsql)
			{
				
			}
		}
		
		
		return allcontrolgroup ;
		
	}

	 public void save(ControlGroup cosdaBaControlGroup) {

		entityManager.persist(cosdaBaControlGroup);

       }
	 
	 
	 public  List<ControlGroup> getlist() {
		if (entityManager == null){
			System.out.println(String.format("Entity Manager is null null"));
		}
		 return entityManager.createQuery("select o from ControlGroup o", ControlGroup.class).getResultList();
	    }

	 
	 public  List<ControlGroup> getlistByGroupName(String groupname) {

		 if (entityManager == null){
			 System.out.println(String.format("Entity Manager is null null"));
		 }

		 System.out.println(String.format("Grgp Name : %s",groupname));
		 return entityManager.createQuery("select c from ControlGroup c where c.controlgroupname=:controlgroupname", ControlGroup.class).setParameter("controlgroupname",groupname).getResultList();

	    }
	 
	 
	 public ControlGroup getById(long id)
	 {
		 
		  Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction transaction = null;
	      ControlGroup controlGroup = null ;
	      
		 	try {		 
		 	          session.beginTransaction();		 	             
		              controlGroup = (ControlGroup) session.get(ControlGroup.class, new BigDecimal(id));
		              if(controlGroup != null)
		 	          System.out.println(controlGroup.getControlgroupid() + " - " + controlGroup.getControlgroupname());	 
		 	          session.getTransaction().commit();
		         }		 
		 	catch (HibernateException e) {		 
		 	            e.printStackTrace();		 
		 	            session.getTransaction().rollback();
		 
		 	    }
		 		finally {
	            session.close();
	        }
		 	return controlGroup;
	 }
	 
	 public void deleteChild(ControlObject controlObject )
	 {
		 long txn = 0 ;
		 long constraint = 0 ;
		 ConstraintDao cosdabacconstraintdao = new ConstraintDao();
		 TransactionDao cosdabactransactiondao = new TransactionDao();
		 
		 if(controlObject.getTransaction() != null )
		 {
			 txn = controlObject.getTransaction().getTransactionid().longValue() ;
			 
			// cosdabactransactiondao.delete(txn);
			 
		 }
		 if(controlObject.getConstraint() != null)
		 {
			 constraint = controlObject.getConstraint().getAttributeconstraintid().longValue() ;
			
			 //cosdabacconstraintdao.delete(constraint);
		 }
		
	 }
	 
	 public String delete(long id)
	 {
		  ControlObjectDao controlObjectDao = new ControlObjectDao();
		  
		  Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction transaction = null;
	      ControlGroup controlGroup = null ;	      
		 	try {		 
		 	          session.beginTransaction();		 	             
		              controlGroup = (ControlGroup) session.get(ControlGroup.class, new BigDecimal(id));
		              
		               controlObjectDao = new ControlObjectDao() ;
		               ControlObject controlObject = controlObjectDao.getByGrpId(id) ;
		              
		              if(controlObject !=null && controlObject.getTransaction() !=null )
		              {
		            	  System.out.println("Error-child record present");
		            	  return "Error-child record present" ;
		              }
		              
		              session.delete(controlGroup);       
		              
		              
		 	          System.out.println(controlGroup.getControlgroupid() + " - " + controlGroup.getControlgroupname());	 
		 	          session.getTransaction().commit();
		         }		 
		 	catch (HibernateException e) {		 
		 	            e.printStackTrace();		 
		 	            session.getTransaction().rollback();
		 	           return "Error- while delete operation" ;
		 
		 	    }
		 	 finally {
	            session.close();
	        }
		 	
		 	
		 	return "Record deleted" ;
	 }
	 
	 public String update( ControlGroup controlGroup) {
		 
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        ControlGroup old_controlGroup = null ;
	        ControlGroupDao controlGroupDao = new ControlGroupDao();
	        try {
	              
	            transaction = session.beginTransaction();
	            
	            old_controlGroup = controlGroupDao.getById(controlGroup.getControlgroupid().longValue());
	            
	            if(old_controlGroup == null)
	            return "Group is not present" ;
	            
	            old_controlGroup.setControlgroupname(controlGroup.getControlgroupname());
	            session.update(old_controlGroup);
	            transaction.commit();
	        }catch (HibernateException e) {
	            transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
	        return "Updated" ;
 }
}	 
