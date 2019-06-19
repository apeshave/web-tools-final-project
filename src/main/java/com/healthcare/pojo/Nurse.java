/**
 * @Author: Aditya Peshave
 * Date: Apr 17, 2013
 */
package com.healthcare.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.persistence.OneToOne;

/**
 * @author ADi
 *
 */
@Entity
public class Nurse {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@OneToOne(cascade = CascadeType.ALL) 
	@Valid
	private Person person;
	@OneToOne(cascade = CascadeType.ALL) 
	@Valid
	private UserAccount userAccount;
	@ManyToOne(targetEntity = com.healthcare.pojo.Hospital.class)
	@JoinColumn(name = "hospital_id", referencedColumnName = "id")
	private Hospital hospital;
	
	public Hospital getHospital() {
		return hospital;
	}
	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public UserAccount getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
		
}
