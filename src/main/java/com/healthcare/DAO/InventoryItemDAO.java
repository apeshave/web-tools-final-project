/**
 * @Author: Aditya Peshave
 * Date: Apr 14, 2013
 */
package com.healthcare.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.healthcare.pojo.InventoryItem;
import com.healthcare.pojo.Product;

/**
 * @author ADi
 *
 */
@Repository
public class InventoryItemDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Transactional
	public void save(InventoryItem inventoryItem) {
		
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(inventoryItem);
		transaction.commit();
		session.close();
		
	}
	
	@Transactional
	public void update(InventoryItem inventoryItem) {
		
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(inventoryItem);
		transaction.commit();
		session.close();
		
	}
	
	
	@Transactional
	public ArrayList<InventoryItem> getInventoryItemByProduct(Product product) {
		
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = (Query) session.createQuery("from InventoryItem i where i.product =:temp");
		query.setParameter("temp",product);
		ArrayList<InventoryItem> inventoryItems = new ArrayList<InventoryItem>();
		for(Object o: query.list())
		{
			inventoryItems.add((InventoryItem)o);
		}
				transaction.commit();
		session.close();
		return inventoryItems;
	}
	

}
