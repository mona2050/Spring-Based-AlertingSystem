/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy2.springalertingsystem.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Patient {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  String id;
  String name;
  //int age;
  Gender gender;
  String contact;


  //@OneToOne(cascade = CascadeType.ALL)
  // @JsonIgnore
  //@JoinColumn(name="bedid")
  @OneToOne(cascade = CascadeType.ALL)
  Icu icu;
  public Patient() {

  }




  public Patient(String name, Gender gender, String contact) {
    super();
    this.name = name;
    //this.age = age;
    this.gender = gender;
    this.contact = contact;
  }






  public String getId() {
    return id;
  }




  public void setId(String id) {
    this.id = id;
  }




  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }



  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }

  public Icu getIcu() {
    return icu;
  }

  public void setIcu(Icu icu) {
    this.icu = icu;
  }

  @Override
  public String toString() {
    return "Patient [id=" + id + ", name=" + name + ", gender=" + gender
        + ", contact=" + contact + "]";
  }


}
