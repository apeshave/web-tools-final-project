/**
 * @Author: Aditya Peshave
 * Date: Apr 14, 2013
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

import com.healthcare.data.DefaultData;
import com.healthcare.pojo.Request;

/**
 * @author ADi
 * 
 */
@Repository
public class RequestDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public void save(Request request) {

		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(request);
		transaction.commit();
		session.close();
	}

	@Transactional
	public ArrayList<Request> getRequestsByReceiver(String receiver) {

		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = (Query) session
				.createQuery("from Request r where r.receiver =:temp1 AND r.status =:temp2");
		query.setParameter("temp1", receiver);
		query.setParameter("temp2", DefaultData.STATUS.PENDING.toString());
		ArrayList<Request> requests = new ArrayList<Request>();
		System.out.println("The size is\n" + query.list().size());
		for (Object o : query.list()) {
			requests.add((Request) o);
		}
		transaction.commit();
		session.close();
		return requests;

	}

	@Transactional
	public ArrayList<Request> getNurseRequests(String sender) {

		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = (Query) session
				.createQuery("from Request r where r.sender =:temp1 AND (r.status =:temp2 OR r.status =:temp3)");
		query.setParameter("temp1", sender);
		query.setParameter("temp2", DefaultData.STATUS.NURSE_PENDING.toString());
		query.setParameter("temp3", DefaultData.STATUS.NURSE_RESOLVED.toString());
		ArrayList<Request> requests = new ArrayList<Request>();
		System.out.println("The size is\n" + query.list().size());
		for (Object o : query.list()) {
			requests.add((Request) o);
		}
		transaction.commit();
		session.close();
		return requests;
	}

	@Transactional
	public Request getRequestById(int id) {

		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = (Query) session
				.createQuery("from Request r where r.id =:temp");
		query.setParameter("temp", id);
		Request request = (Request) query.uniqueResult();
		transaction.commit();
		session.close();
		return request;
	}

	@Transactional
	public ArrayList<Request> getAllRequests(String receiver) {

		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = (Query) session
				.createQuery("from Request r where r.receiver =:temp");
		query.setParameter("temp", receiver);
		ArrayList<Request> requests = new ArrayList<Request>();
		for(Object o: query.list())
		{
			requests.add((Request)o);
		}
		
		transaction.commit();
		session.close();
		return requests;
	}
	
	
	@Transactional
	public void update(Request request) {

		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(request);
		transaction.commit();
		session.close();

	}

}
