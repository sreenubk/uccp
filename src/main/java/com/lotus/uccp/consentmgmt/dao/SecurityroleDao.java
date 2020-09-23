package com.lotus.uccp.consentmgmt.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lotus.uccp.util.HibernateUtil;
import com.lotus.uccp.authentication.model.SecurityRole;

public class SecurityroleDao {

	public SecurityRole save(SecurityRole dto) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			session.save(dto);
			transaction.commit();

		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
			return null;

		} finally {
			session.close();
		}
		return dto;
	}

	public List<SecurityRole> getlist() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		List<SecurityRole> dtoList = null;

		try {
			transaction = session.beginTransaction();
			dtoList = session.createQuery("from SecurityRole").list();
			for (Iterator iterator = dtoList.iterator(); iterator.hasNext();) {
				SecurityRole dto = (SecurityRole) iterator.next();
				System.out.println(":: Role Name ::" + dto.getRoleName());
			}
			transaction.commit();

		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();

		} finally {
			session.close();
		}

		return dtoList;
	}

	public List<SecurityRole> getlistByGroupName(String name) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		List<SecurityRole> dtoList = null;

		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("from SecurityRole where  ROLENAME LIKE :name ");
			query.setParameter("name", "%" + name + "%");
			dtoList = query.list();
			// Cosdabaccontrolgrouplist = session.createQuery("from Cosdabaccontrolgroup
			// where controlgroupname = :code").list();
			for (Iterator iterator = dtoList.iterator(); iterator.hasNext();) {
				SecurityRole dto = (SecurityRole) iterator.next();
				System.out.println("Name " + dto.getRoleName());
			}
			transaction.commit();

		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return dtoList;
	}

	public SecurityRole getById(String name) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		SecurityRole dto = null;

		try {
			transaction = session.beginTransaction();
			dto = (SecurityRole) session.get(SecurityRole.class, name);

			if (dto != null)
				System.out.println(dto.getRoleName());
			transaction.commit();

		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();

		} finally {
			session.close();
		}
		return dto;
	}

	public Boolean update(SecurityRole dto) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			session.update(dto);
			transaction.commit();

		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
			return false;

		} finally {
			session.close();
		}

		return true;
	}

	public Boolean delete(String id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		SecurityRole dto = null;

		try {
			transaction = session.beginTransaction();
			dto = (SecurityRole) session.get(SecurityRole.class, id);
			session.delete(dto);
			System.out.println(":: RoleName ::" + dto.getRoleName());
			transaction.commit();

		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
			return false;

		} finally {
			session.close();
		}

		return true;

	}

}
