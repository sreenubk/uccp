package com.lotus.uccp.consentmgmt.wcm.dao;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lotus.uccp.util.HibernateUtil;
import com.lotus.uccp.consentmgmt.wcm.dto.AlertStatusHist;
import com.lotus.uccp.consentmgmt.wcm.dto.ReferralStatusHist;
import com.lotus.uccp.consentmgmt.wcm.dto.Target;

public class AlertStatusHistDao {

	public AlertStatusHist save(AlertStatusHist obj) {
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
	public List<AlertStatusHist> getlist() {

		System.out.println(" ");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		List<AlertStatusHist> list = null;
		try {

			transaction = session.beginTransaction();
			list = session.createQuery("FROM AlertStatusHist ").list();
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				AlertStatusHist hist = (AlertStatusHist) iterator.next();
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
	public List<AlertStatusHist> getlistWithRegReqID(long id) {

		System.out.println(" -- getlistWithRegReqID() ---" + id);
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		List<AlertStatusHist> list = null;
		try {

			transaction = session.beginTransaction();

			Query query = session.createQuery("FROM AlertStatusHist WHERE ALERTREQTID = :ID");
			query.setParameter("ID", id);
			list = query.list();
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				AlertStatusHist hist = (AlertStatusHist) iterator.next();
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

	public AlertStatusHist getById(long id) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		AlertStatusHist alertStatusHist = null;

		try {
			session.beginTransaction();
			alertStatusHist = (AlertStatusHist) session.get(AlertStatusHist.class, new BigDecimal(id));
			session.getTransaction().commit();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();

		} finally {
			session.close();
		}
		return alertStatusHist;
	}

	public String update(AlertStatusHist obj) {

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
			AlertStatusHist alertStatusHist = (AlertStatusHist) session.get(AlertStatusHist.class, new BigDecimal(id));
			session.delete(alertStatusHist);
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
/*
 * -- Table: ALERT_STATUS_HIST CREATE TABLE ALERT_STATUS_HIST ( alertreqReqtID
 * number(20Date statusDate ;,2) NOT NULL, Date statusDate ; status varchar2(40)
 * NOT NULL, statusUser varchar2(40) NOT NULL, statusComments varchar2(100) NOT
 * NULL ) ;
 * 
 */
