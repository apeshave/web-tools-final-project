/**
 * @Author: Aditya Peshave
 * Date: Apr 17, 2013
 */
package com.healthcare.DAO;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.healthcare.pojo.Doctor;
 


/**
 * @author ADi
 *
 */
@Repository
public class DoctorDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public void save(Doctor doctor)
	{
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(doctor);
		transaction.commit();
		session.close();
	}

	@Transactional
	public Doctor getDoctorByUsername(String username) {
		
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("from Doctor");
		Doctor doctor = new Doctor();
		System.out.println(query.list());
		for(Object o: query.list())
		{
			if(((Doctor)o).getUserAccount().getUsername().equals(username))
				doctor =  (Doctor)o;
		}
		transaction.commit();
		session.close();
		return doctor;
	}
	
	@Transactional
	public boolean isEmpty()
	{
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("from Doctor");
		boolean flag = false;
		if(query.list().size() == 0)
			flag = true;
		transaction.commit();
		session.close();
		return flag;
	}
	
	@Transactional
	public ArrayList<Doctor> getAllDoctors()
	{
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = (Query) session.createQuery("from Doctor");
		ArrayList<Doctor> doctors = new ArrayList<Doctor>();
		for(Object o: query.list())
		{
			doctors.add((Doctor)o);
		}
		transaction.commit();
		session.close();
		return doctors;
	}

	
}
