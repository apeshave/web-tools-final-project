/**
 * @Author: Aditya Peshave
 * Date: Apr 21, 2013
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
import javax.validation.Valid;

import org.hibernate.annotations.Proxy;

/**
 * @author ADi
 *
 */
@Entity
public class Hospital {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String hospitalName;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "hospital", fetch = FetchType.EAGER)
	@Valid
	private Set<Doctor> doctors = new HashSet<Doctor>();
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "hospital", fetch = FetchType.EAGER)
	@Valid
	private Set<Nurse> nurses = new HashSet<Nurse>();
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "hospital", fetch = FetchType.EAGER) 
	private Set<HospitalInventoryItem> inventoryItems = new HashSet<HospitalInventoryItem>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public Set<Doctor> getDoctors() {
		return doctors;
	}
	public void setDoctors(Set<Doctor> doctors) {
		this.doctors = doctors;
	}
	public Set<Nurse> getNurses() {
		return nurses;
	}
	public void setNurses(Set<Nurse> nurses) {
		this.nurses = nurses;
	}
	public Set<HospitalInventoryItem> getInventoryItems() {
		return inventoryItems;
	}
	public void setInventoryItems(Set<HospitalInventoryItem> inventoryItems) {
		this.inventoryItems = inventoryItems;
	}
	
	
	
}
