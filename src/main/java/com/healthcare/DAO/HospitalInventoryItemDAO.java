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

/**
 * @author ADi
 * 
 */
@Repository
public class HospitalInventoryItemDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public void save(HospitalInventoryItem inventoryItem) {

		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(inventoryItem);
		transaction.commit();
		session.close();
	}
	
	@Transactional
	public void delete(HospitalInventoryItem inventoryItem) {

		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(inventoryItem);
		transaction.commit();
		session.close();

	}
	

	@Transactional
	public void update(HospitalInventoryItem inventoryItem) {

		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(inventoryItem);
		transaction.commit();
		session.close();

	}

	@Transactional
	public ArrayList<HospitalInventoryItem> getAllHospitalInventoryItems() {

		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		ArrayList<HospitalInventoryItem> items = new ArrayList<HospitalInventoryItem>();
		Query query = (Query) session.createQuery("from HospitalInventoryItem hi where hi.returned =:temp");
		query.setParameter("temp",false);
		for (Object o : query.list())
			items.add((HospitalInventoryItem) o);

		transaction.commit();
		session.close();
		return items;
	}

	@Transactional
	public HospitalInventoryItem getHospitalInventoryItemById(int id) {

		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		HospitalInventoryItem item = new HospitalInventoryItem();
		Query query = (Query) session
				.createQuery("from HospitalInventoryItem h where h.id =:temp");
		query.setParameter("temp", id);
		item = (HospitalInventoryItem) query.uniqueResult();
		transaction.commit();
		session.close();
		return item;
	}

	@Transactional
	public ArrayList<HospitalInventoryItem> getInventoryByProduct(
			Product product) {

		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = (Query) session
				.createQuery("from HospitalInventoryItem hii where hii.product =:temp");
		query.setParameter("temp", product);
		ArrayList<HospitalInventoryItem> inventoryItems = new ArrayList<HospitalInventoryItem>();
		for (Object o : query.list()) {
			inventoryItems.add((HospitalInventoryItem) o);
		}

		transaction.commit();
		session.close();
		return inventoryItems;
	}

}
