/* Licensed Materials - 
 Property of IBM 6949 - 67L 
 Copyright IBM Corp. 2017, 2018 All Rights Reserved */
package  com.lotus.uccp.consentmgmt.dao;

import java.util.List;

import org.hibernate.Session;

import com.lotus.uccp.util.HibernateUtil;

public class HQLJoinDemo {
	
	/*public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		String hql = "from Cosdabacconstraint as const inner join const.cosdabaccontrolobjects as obj";
		List<?> list = session.createQuery(hql).list();
		for(int i=0; i<list.size(); i++) {
			Object[] row = (Object[]) list.get(i);
			Company company = (Company)row[0];
			Employee employee = (Employee)row[1];
			System.out.println("CompId:"+company.getCompId()+", CompName:"+ company.getCompName()+
					   ", EmpId:"+ employee.getEmpId()+", EmpName:"+ employee.getEmpName());
			
			System.out.println(":: ::" + i);
		}		
		session.close();
	}*/

}
