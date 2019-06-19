/**
 * @Author: Aditya Peshave
 * Date: Apr 17, 2013
 */
package com.healthcare.DAO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.healthcare.pojo.Doctor;
import com.healthcare.pojo.Nurse;

/**
 * @author ADi
 *
 */
@Repository
public class NurseDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public void save(Nurse nurse)
	{
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(nurse);
		transaction.commit();
		session.close();
	}
	
	@Transactional
	public boolean isEmpty()
	{
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("from Nurse");
		boolean flag = false;
		if(query.list().size() == 0)
			flag = true;
		transaction.commit();
		session.close();
		return flag;
	}
	
}
