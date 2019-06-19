package com.healthcare.DAO;

import java.util.ArrayList;

import javax.jws.soap.SOAPBinding.Use;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.healthcare.pojo.Address;
import com.healthcare.pojo.UserAccount;
import com.healthcare.pojo.Verification;

/**
 * @author ADi
 *
 */
@Repository
public class VerificationDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public void save(Verification obj){
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(obj);
		transaction.commit();
		session.close();
	}
	
	@Transactional
	public ArrayList<UserAccount> getVerifiedAccounts(){
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = (Query) session.createQuery("from Verification where verified =:temp");
		query.setParameter("temp",true);
		ArrayList<UserAccount> userAccounts = new ArrayList<UserAccount>();
		for(Object o: query.list())
		{
			userAccounts.add((UserAccount)o);
		}
		transaction.commit();
		session.close();
		return userAccounts;
	}
	 
	@Transactional
	public Verification getVerificationByEmail(String email) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = (Query) session.createQuery("from Verification v where v.email =:temp");
		query.setParameter("temp",email);
		Verification verification = (Verification) query.uniqueResult();
		transaction.commit();
		session.close();
		return verification;
	}
	
	@Transactional
	public ArrayList<Verification> getAllVerificationObjects(){
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = (Query) session.createQuery("from Verification");
		ArrayList<Verification> userAccounts = new ArrayList<Verification>();
		for(Object o: query.list())
		{
			userAccounts.add((Verification)o);
		}
		transaction.commit();
		session.close();
		return userAccounts;
	}
	
	
}
