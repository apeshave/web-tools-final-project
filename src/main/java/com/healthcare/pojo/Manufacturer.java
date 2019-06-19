/**
 * @Author: Aditya Peshave
 * Date: Apr 17, 2013
 */
package com.healthcare.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;

import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author ADi
 *
 */
@Entity
public class Manufacturer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NotEmpty
	private String manufacturerName;
	@OneToOne(cascade = CascadeType.ALL)
	@Valid
	private Address officeAddress;
	@NotEmpty
	private String contactPersonName;
	private String contactNo;
	@OneToOne (cascade = CascadeType.ALL)
	@Valid
	private UserAccount userAccount;
	@OneToOne (cascade = CascadeType.ALL)
	@Valid
	private Person person;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "manufacturer", fetch = FetchType.EAGER)
	@Valid
	private Set<InventoryItem> inventory = new HashSet<InventoryItem>();
	
	
	
	public Set<InventoryItem> getInventory() {
		return inventory;
	}
	public void setInventory(Set<InventoryItem> inventory) {
		this.inventory = inventory;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getManufacturerName() {
		return manufacturerName;
	}
	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}
	public Address getOfficeAddress() {
		return officeAddress;
	}
	public void setOfficeAddress(Address officeAddress) {
		this.officeAddress = officeAddress;
	}
	public String getContactPersonName() {
		return contactPersonName;
	}
	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public UserAccount getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
	
}
