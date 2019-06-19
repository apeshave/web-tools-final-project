/**
 * @Author: Aditya Peshave
 * Date: Apr 16, 2013
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
 
import com.healthcare.pojo.Manufacturer;
import com.healthcare.pojo.Product;

/**
 * @author ADi
 *
 */
@Repository
public class ProductDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public void save(Product product){
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(product);
		transaction.commit();
		session.close();
	}
	
	@Transactional
	public ArrayList<Product> getProductListByManufacturer(String manufacturerName) {
		
		Session session = sessionFactory.openSession();
		Query query = (Query)session.createQuery("from Product where manufacturerName =:temp");
		query.setParameter("temp", manufacturerName);
		List productList = query.list();
		ArrayList<Product> list = new ArrayList<Product>();
		for(Object o: productList)
		{
			list.add((Product)o);
		}
		session.close();
		return list;
	}
	
	public boolean delete(Product product) {
		
		boolean status = false;
		Session session = sessionFactory.openSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		session.delete(product);	
		transaction.commit();
		session.close();
		return status;
	}
	
	public Product getProductByName(String name) {
		
		Session session = sessionFactory.openSession();
		Query query = (Query)session.createQuery("from Product where productName =:temp");
		query.setParameter("temp",name);
		Product p = (Product)query.list().get(0);
		session.close();
		return p;
		
	}
	
	public ArrayList<Product> getAllProducts() {
		
		Session session = sessionFactory.openSession();
		Query query = (Query)session.createQuery("from Product");
		ArrayList<Product> products = new ArrayList<Product>();
		
		for(Object o: query.list())
		{
			products.add((Product)o);
		}
		return products;
	}
	
	
}
