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
import com.healthcare.pojo.ProcessedRequest;
import com.healthcare.pojo.Request;

/**
 * @author ADi
 *
 */
@Repository
public class ProcessedRequestDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public void save(ProcessedRequest request) {
		
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(request);
		transaction.commit();
		session.close();
	}
	
	@Transactional
	public void update(ProcessedRequest request) {
		
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(request);
		transaction.commit();
		session.close();
	}
	
	
	@Transactional
	public ArrayList<ProcessedRequest> getRequestsByReceiver(String receiver) {
		
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = (Query) session.createQuery("from ProcessedRequest");
		ArrayList<ProcessedRequest> requests = new ArrayList<ProcessedRequest>();
		System.out.println("The size is\n"+query.list().size());
		for(Object o: query.list())
		{
			ProcessedRequest request = (ProcessedRequest)o;
			if(request.getRequest().getReceiver().equals(receiver))
			{
				requests.add((ProcessedRequest)o);
			}
			
		}
		transaction.commit();
		session.close();
		return requests;
		
	}
	
	@Transactional
	public ProcessedRequest getRequestById(int id) {
		
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = (Query) session.createQuery("from ProcessedRequest r where r.id =:temp");
		query.setParameter("temp",id);
		ProcessedRequest request =(ProcessedRequest) query.uniqueResult();
		transaction.commit();
		session.close();
		return request;
	}
		
	@Transactional
	public ArrayList<ProcessedRequest> getProcessedRequests() {
		
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = (Query) session.createQuery("from ProcessedRequest pr where pr.inventoryItem.status =:temp");
		query.setParameter("temp",DefaultData.INVENTORY_STATUS.SENT_TO_HOSPITAL.toString());
		ArrayList<ProcessedRequest> requests = new ArrayList<ProcessedRequest>();
		
		for(Object o: query.list())
		{
				requests.add((ProcessedRequest)o);
		}
		
		transaction.commit();
		session.close();
		return requests;
		
	}

	@Transactional
	public ArrayList<ProcessedRequest> getOpenProcessedRequestsByUsername(String username) {
		
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = (Query) session.createQuery("from ProcessedRequest pr where pr.request.sender =:temp1 AND pr.status =:temp2");
		query.setParameter("temp1",username);
		query.setParameter("temp2",DefaultData.STATUS.PR_PENDING.toString());
		ArrayList<ProcessedRequest> requests = new ArrayList<ProcessedRequest>();
		
		for(Object o: query.list())
		{
				requests.add((ProcessedRequest)o);
		}
		
		transaction.commit();
		session.close();
		return requests;
		
	}

	
	
	
	
}
