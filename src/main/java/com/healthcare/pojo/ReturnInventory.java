/**
 * @Author: Aditya Peshave
 * Date: Apr 24, 2013
 */
package com.healthcare.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * @author ADi
 *
 */
@Entity
public class ReturnInventory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@OneToOne(cascade = CascadeType.ALL)
	private HospitalInventoryItem item;
	private String date;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public HospitalInventoryItem getItem() {
		return item;
	}
	public void setItem(HospitalInventoryItem item) {
		this.item = item;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

}
