package com.healthcare.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.healthcare.pojo.Address;

/**
 * @author ADi
 *
 */
@Repository
public class AddressDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public void save(Address address){
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(address);
		transaction.commit();
		session.close();
	}
}
