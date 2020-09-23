package com.lotus.uccp.consentmgmt.wcm.dao;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lotus.uccp.util.HibernateUtil;
import com.lotus.uccp.consentmgmt.wcm.dto.ReferralStatusHist;

@SuppressWarnings("serial")
public class ReferralStatusHistDao implements java.io.Serializable {

	public ReferralStatusHist save(ReferralStatusHist obj) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(obj);
			transaction.commit();
			
		} catch (HibernateException e) {
			System.out.println("Exception ..mmm");
			transaction.rollback();
			e.printStackTrace();
			
		} finally {
			session.close();
		}

		return obj;
	}

	@SuppressWarnings("unchecked")
	public List<ReferralStatusHist> getlist() {

		System.out.println(" ");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		List<ReferralStatusHist> list = null;
		try {

			transaction = session.beginTransaction();
			list = session.createQuery("FROM ReferralStatusHist ").list();
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				ReferralStatusHist hist = (ReferralStatusHist) iterator.next();
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
	
	@SuppressWarnings("unchecked")
	public List<ReferralStatusHist> getlistWithRegReqID(long id) {

		System.out.println(" -- getlistWithRegReqID() ---" + id);
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		List<ReferralStatusHist> list = null;
		try {

			transaction = session.beginTransaction();

			Query query = session.createQuery("FROM ReferralStatusHist WHERE reqReqtID = :ID");
			query.setParameter("ID", id);
			list = query.list();
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				ReferralStatusHist hist = (ReferralStatusHist) iterator.next();
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

	public ReferralStatusHist getById(long id) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		ReferralStatusHist referralStatusHist = null;

		try {
			session.beginTransaction();
			referralStatusHist = (ReferralStatusHist) session.get(ReferralStatusHist.class, new BigDecimal(id));
			session.getTransaction().commit();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();

		} finally {
			session.close();
		}
		return referralStatusHist;
	}

	public String update(ReferralStatusHist obj) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			session.update(obj);
			transaction.commit();
			
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
			
		} finally {
			session.close();
		}
		
		return "Updated";
	}
	
	public String delete(long id) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		try {
			session.beginTransaction();
			ReferralStatusHist referralStatusHist = (ReferralStatusHist) session.get(ReferralStatusHist.class, new BigDecimal(id));
			session.delete(referralStatusHist);
			session.getTransaction().commit();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return "Error - in deletion";

		} finally {
			session.close();
		}

		return "deleted";
	}

}
