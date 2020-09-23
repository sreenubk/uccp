package com.lotus.uccp.consentmgmt.wcm.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lotus.uccp.util.HibernateUtil;
import com.lotus.uccp.consentmgmt.wcm.dto.ApiHist;

public class ApiHistDao {

	@SuppressWarnings({ "unchecked", "unused", "rawtypes" })
	public List<ApiHist> getlistWithReqActionAndRegID(String action, long id) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		List<ApiHist> list = null;

		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("FROM ApiHist WHERE APIACTION = :ACTION AND REQUESTID = :ID");
			query.setParameter("ACTION", action);
			query.setParameter("ID", id);
			list = query.list();
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				ApiHist hist = (ApiHist) iterator.next();
			}
			transaction.commit();

		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return list;
	}
}
