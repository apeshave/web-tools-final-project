/**
 * @Author: Aditya Peshave
 * Date: Apr 12, 2013
 */
package com.healthcare.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author ADi
 *
 */
@Entity
public class Address {
  
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private int id;
  @NotEmpty(message = "Line 1 Can not be empty")
  private String line1;
  private String line2;
  @NotEmpty(message = "City Can not be empty")
  private String city;
  @NotEmpty(message = "State Can not be empty")
  private String state;
  @NotEmpty(message = "Country Can not be empty")
  private String country;
  private String zip;
  
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public String getLine1() {
    return line1;
  }
  public void setLine1(String line1) {
    this.line1 = line1;
  }
  public String getLine2() {
    return line2;
  }
  public void setLine2(String line2) {
    this.line2 = line2;
  }
  public String getCity() {
    return city;
  }
  public void setCity(String city) {
    this.city = city;
  }
  public String getState() {
    return state;
  }
  public void setState(String state) {
    this.state = state;
  }
  public String getCountry() {
    return country;
  }
  public void setCountry(String country) {
    this.country = country;
  }
  public String getZip() {
    return zip;
  }
  public void setZip(String zip) {
    this.zip = zip;
  }

}
