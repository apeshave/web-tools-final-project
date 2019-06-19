/**
 * @Author: Aditya Peshave
 * Date: Apr 18, 2013
 */
package com.healthcare.data;

import javax.validation.Valid;

import com.healthcare.pojo.Doctor;
import com.healthcare.pojo.Manufacturer;
import com.healthcare.pojo.Nurse;


/**
 * @author ADi
 *
 */
public class CollectiveData {
	
	@Valid
	private Doctor doctor;
	@Valid
	private Nurse nurse;
	@Valid
	private Manufacturer manufacturer;
	
	public CollectiveData() {
		
		doctor = new Doctor();
		nurse = new Nurse();
		manufacturer = new Manufacturer();
	}
	
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public Nurse getNurse() {
		return nurse;
	}
	
	public void setNurse(Nurse nurse) {
		this.nurse = nurse;
	}
	public Manufacturer getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}
	
}
