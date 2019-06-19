/**
 * @Author: Aditya Peshave
 * Date: Apr 15, 2013
 */
package com.healthcare.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * @author ADi
 *
 */
@Entity
public class ProcessedRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@OneToOne
	@JoinColumn(name="request_id",referencedColumnName = "id")
	private Request request;
	private String processedDate;
	@OneToOne
	@JoinColumn(name = "inventory_item_id", referencedColumnName = "id")
	private InventoryItem inventoryItem;
	private String status;
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public InventoryItem getInventoryItem() {
		return inventoryItem;
	}
	public void setInventoryItem(InventoryItem inventoryItem) {
		this.inventoryItem = inventoryItem;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Request getRequest() {
		return request;
	}
	public void setRequest(Request request) {
		this.request = request;
	}
	public String getProcessedDate() {
		return processedDate;
	}
	public void setProcessedDate(String processedDate) {
		this.processedDate = processedDate;
	}

}
