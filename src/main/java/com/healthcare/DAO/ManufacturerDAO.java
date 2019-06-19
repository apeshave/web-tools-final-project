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

import com.healthcare.pojo.Manufacturer;
import com.healthcare.pojo.Person;

/**
 * @author ADi
 *
 */

@Repository
public class ManufacturerDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public void save(Manufacturer manufacturer){
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(manufacturer);
		transaction.commit();
		session.close();
	}
	
	@Transactional
	public Manufacturer getByUsername(String username) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("from Manufacturer");
		//query.setParameter("temp", username);
		Manufacturer manufacturer1 =null;
		Manufacturer manufacturer =null;
		for(Object o: query.list())
		{
			manufacturer = (Manufacturer)o;
			System.out.print("Inside manuDAO"+manufacturer.getManufacturerName());
			if(manufacturer.getUserAccount().getUsername().equals(username))
				manufacturer1 = manufacturer;	
		}
		transaction.commit();
		session.close();	
		return manufacturer1;
	}
	
	@Transactional 
	public void update(Manufacturer manufacturer) {
		
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(manufacturer);
		transaction.commit();
		session.close();
	}
	
	@Transactional
	public boolean isEmpty()
	{
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("from Manufacturer");
		boolean flag = false;
		if(query.list().size() == 0)
			flag = true;
		transaction.commit();
		session.close();
		return flag;
	}
	
	

}
