/**
 * @Author: Aditya Peshave
 * Date: Apr 12, 2013
 */
package com.healthcare.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author ADi
 *
 */

@Entity
@Table(name="Person")
public class Person {
  
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private int id;
  @NotEmpty
  private String firstName;
  @NotEmpty
  private String lastName;
  @OneToOne(cascade = CascadeType.ALL)
  @Valid
  private Address address;
  private String email;
  private String contactNo;
  /**
 * 
 */
public Person() {
	
	address = new Address();
}
public String getFirstName() {
    return firstName;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  public String getLastName() {
    return lastName;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  public Address getAddress() {
    return address;
  }
  public void setAddress(Address address) {
    this.address = address;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getContactNo() {
    return contactNo;
  }
  public void setContactNo(String contactNo) {
    this.contactNo = contactNo;
  }
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  
  
}
