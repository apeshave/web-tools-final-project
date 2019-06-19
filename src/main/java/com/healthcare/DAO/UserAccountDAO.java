package com.healthcare.DAO;

import com.healthcare.pojo.UserAccount;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ADi
 * 
 */

@Repository
public class UserAccountDAO {

  @Autowired
  private SessionFactory sessionFactory;


  @Transactional
  public void save(UserAccount userAccount) {
    Session session = sessionFactory.openSession();
    Transaction trx = session.beginTransaction();
    session.save(userAccount);
    trx.commit();
    session.close();
    
  }
  
  @Transactional
  public void update(UserAccount userAccount) {
    Session session = sessionFactory.openSession();
    Transaction trx = session.beginTransaction();
    session.update(userAccount);
    trx.commit();
    session.close();
    
  }
  

  @Transactional
  public void delete(UserAccount userAccount) {
    Session session = sessionFactory.openSession();
    Transaction trx = session.beginTransaction();
    session.delete(userAccount);
    trx.commit();
    session.close();
  }

  @Transactional
  public UserAccount getUserAccountByID(int userId) {
    Session session = sessionFactory.openSession();
    Transaction trx = session.beginTransaction();
    Query q = session.createQuery("from UserAccount where id =:temp");
    q.setParameter("temp", userId);
    UserAccount userAccount = (UserAccount) q.uniqueResult();
    System.out.print(userAccount.getUsername());
    trx.commit();
    return userAccount;
  }

  @Transactional
  public boolean verify(String username, String password) {
    boolean flag = true;
    Session session = sessionFactory.openSession();
    Transaction trx = session.beginTransaction();
    Query query =
        session.createQuery("from UserAccount where username =:temp1 AND password =:temp2");
    query.setParameter("temp1", username);
    query.setParameter("temp2", password);
    
    if(query.list().size() != 1)
      flag = false;
    trx.commit();
    session.close();
    return flag;
  }
  
  @Transactional
  public UserAccount getUserAccountByUsername(String username) {
    Session session = sessionFactory.openSession();
    Transaction trx = session.beginTransaction();
    Query q = session.createQuery("from UserAccount where username =:temp");
    q.setParameter("temp", username);
    UserAccount userAccount = (UserAccount) q.uniqueResult();
    System.out.print(userAccount.getUsername());
    trx.commit();
    session.close();
    return userAccount;
  }
  
  public boolean isUnique(String username) {
	  
	  Session session = sessionFactory.openSession();
	  Transaction transaction = session.beginTransaction();
	  Query q = session.createQuery("from UserAccount where username =:temp");
	  q.setParameter("temp", username);
	  boolean status;
	  if(q.uniqueResult() == null)
		  status = true;
	  else
		  status = false;
	  transaction.commit();
	  session.close();
	  return status;
  }

}
