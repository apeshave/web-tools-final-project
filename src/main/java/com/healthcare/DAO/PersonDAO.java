/**
 * 
 */
package com.healthcare.DAO;
import com.healthcare.pojo.Person;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author ADi
 *
 */
@Repository
public class PersonDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public void save(Person person){
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(person);
		transaction.commit();
		session.close();
	}
	
}
