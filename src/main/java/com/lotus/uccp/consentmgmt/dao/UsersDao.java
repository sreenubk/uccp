/* Licensed Materials - 
 Property of IBM 6949 - 67L 
 Copyright IBM Corp. 2017, 2018 All Rights Reserved */
package com.lotus.uccp.consentmgmt.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lotus.uccp.util.HibernateUtil;
import com.lotus.uccp.authentication.model.Users;

public class UsersDao {

	public void save(Users users) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.save(users);
			transaction.commit();
			
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
			
		} finally {
			session.close();			
		}		
	}
	
	@SuppressWarnings("unchecked")
	public List<Users> getlist() {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		List<Users> userslist = null;
		
		try {
			transaction = session.beginTransaction();
			userslist = session.createQuery("from Users").list();
			
			for (Iterator iterator = userslist.iterator(); iterator.hasNext();) {
				Users users = (Users) iterator.next();
				System.out.println(":: Unit Name ::" + users.getRolename());
			}
			transaction.commit();
			
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
			
		} finally {
			session.close();			
		}

		return userslist;
	}
	
	public Users getById(String name) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Users users = null;

		try {
			transaction = session.beginTransaction();
			users = (Users) session.get(Users.class, name);

			if (users != null)
				System.out.println(":: Rolename ::" + users.getRolename());
			transaction.commit();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		}
		
		return users;
	}

	@SuppressWarnings("unchecked")
	public List<Users> getlistByName(String name) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		List<Users> userlist = null;
		
		try {
			transaction = session.beginTransaction();

			Query query = session.createQuery("from Users where  USERNAME LIKE :name ");
			query.setParameter("name", "%" + name + "%");

			userlist = query.list();
			
			for (Iterator iterator = userlist.iterator(); iterator.hasNext();) {
				Users users = (Users) iterator.next();
				System.out.println("Name ::-" + users.getUsername());
			}
			transaction.commit();
			
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
			
		} finally {
			session.close();
		}

		return userlist;
	}

	public Boolean update(Users Users) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
            
            session.update(Users);
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

		try {
			transaction = session.beginTransaction();
			
			Users users = (Users) session.get(Users.class, id);
			session.delete(users);
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
