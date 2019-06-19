/**
 * @Author: Aditya Peshave Date: Apr 13, 2013
 */
package com.healthcare.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Digits;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author ADi
 * 
 */
@Entity
public class Request {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String status;
	@OneToOne
	private Product product;
	@NotEmpty
	private String sender;
	@Digits(fraction = 0, integer = 4, message = "Enter Valid No")
	private int quantity;
	@NotEmpty
	private String receiver;
	private String comments;
	private String date;

	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
