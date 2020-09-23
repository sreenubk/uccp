package com.lotus.uccp.consentmgmt.wcm.dao;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lotus.uccp.util.HibernateUtil;
import com.lotus.uccp.consentmgmt.wcm.dto.ReferralPersonLADto;

public class ReferralPersonLADao implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ReferralPersonLADto save(ReferralPersonLADto dto) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {

			transaction = session.beginTransaction();
			session.save(dto);
			transaction.commit();

		} catch (HibernateException e) {
			System.out.println("Exception ..mmm");
			transaction.rollback();
			e.printStackTrace();

		} finally {
			session.close();
		}

		return dto;
	}

	@SuppressWarnings("unchecked")
	public List<ReferralPersonLADto> getlist() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		List<ReferralPersonLADto> list = null;
		try {

			transaction = session.beginTransaction();
			list = session.createQuery("FROM ReferralPersonLADto ").list();
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				ReferralPersonLADto referralPersonLADto = (ReferralPersonLADto) iterator.next();
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

	public List<ReferralPersonLADto> getlistByMemberId(String memberID) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		List<ReferralPersonLADto> referralPersonLADtoList = null;
		
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("from ReferralPersonLADto where memberID = :id ");
			query.setParameter("id", memberID);
			referralPersonLADtoList = query.list();
			
			for (Iterator iterator = referralPersonLADtoList.iterator(); iterator.hasNext();) {
				ReferralPersonLADto referralPersonLADto = (ReferralPersonLADto) iterator.next();
			}
			
			transaction.commit();
			
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
			
		} finally {
			session.close();
		}

		return referralPersonLADtoList;
	}

	public ReferralPersonLADto getById(long personID) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		ReferralPersonLADto referralPersonLADto = null;

		try {
			session.beginTransaction();
			referralPersonLADto = (ReferralPersonLADto) session.get(ReferralPersonLADto.class,
					new BigDecimal(personID));
			session.getTransaction().commit();

		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();

		} finally {
			session.close();
		}
		return referralPersonLADto;
	}

	public String update(ReferralPersonLADto referralPersonLADto) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			session.update(referralPersonLADto);
			transaction.commit();

		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();

		} finally {
			session.close();
		}

		return "Updated";
	}

	public String delete(long personID) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		try {
			session.beginTransaction();
			ReferralPersonLADto referralPersonLADto = (ReferralPersonLADto) session.get(ReferralPersonLADto.class,
					new BigDecimal(personID));
			session.delete(referralPersonLADto);
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