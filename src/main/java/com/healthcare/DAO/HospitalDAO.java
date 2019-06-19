/**
 * @Author: Aditya Peshave
 * Date: Apr 21, 2013
 */
package com.healthcare.DAO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.healthcare.pojo.Hospital;
import com.healthcare.pojo.InventoryItem;
 

/**
 * @author ADi
 *
 */
@Repository
public class HospitalDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Transactional
	public void save(Hospital hospital) {
		
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(hospital);
		transaction.commit();
		session.close();
		
	}

	@Transactional
	public void update(Hospital hospital) {
		
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(hospital);
		transaction.commit();
		session.close();
	}
	
	@Transactional
	public Hospital getHospital() {
		
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = (Query) session.createQuery("from Hospital where hospitalName =:name");
		query.setParameter("name","iHealthCare");
		Hospital hospital = (Hospital)query.uniqueResult();
		transaction.commit();
		session.close();
		return hospital;
		
	}
	
}
