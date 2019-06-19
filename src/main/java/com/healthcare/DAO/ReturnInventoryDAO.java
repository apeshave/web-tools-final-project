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

import com.healthcare.pojo.HospitalInventoryItem;
import com.healthcare.pojo.InventoryItem;
import com.healthcare.pojo.Product;
import com.healthcare.pojo.ReturnInventory;

/**
 * @author ADi
 * 
 */
@Repository
public class ReturnInventoryDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public void save(ReturnInventory inventory) {

		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(inventory);
		transaction.commit();
		session.close();
	}
	
	@Transactional
	public void delete(ReturnInventory inventory) {

		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(inventory);
		transaction.commit();
		session.close();

	}
	
	@Transactional
	public ArrayList<ReturnInventory> getAllReturnedInventoryItems() {

		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		ArrayList<ReturnInventory> items = new ArrayList<ReturnInventory>();
		Query query = (Query) session.createQuery("from ReturnInventory");
		for (Object o : query.list())
			items.add((ReturnInventory) o);

		transaction.commit();
		session.close();
		return items;
	}
 
}
