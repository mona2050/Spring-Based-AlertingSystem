/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy2.springalertingsystem.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Patient {

  @Id
  String id;
  String name;
  int age;
  Gender gender;
  String contact;


  @OneToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name="bedid")
  Icu icu;
  public Patient() {

  }




  public Patient(String id,String name, Gender gender, String contact,int age) {
    super();
    this.id=id;
    this.name = name;
    this.age = age;
    this.gender = gender;
    this.contact = contact;
  }






  public Patient(String id, String name, int age, Gender gender, String contact, Icu icu) {
    super();
    this.id = id;
    this.name = name;
    this.age = age;
    this.gender = gender;
    this.contact = contact;
    this.icu = icu;
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


  public int getAge() {
    return age;
  }




  public void setAge(int age) {
    this.age = age;
  }




  @Override
  public String toString() {
    return "Patient [id=" + id + ", name=" + name + ", age=" + age + ", gender=" + gender
        + ", contact=" + contact + ", icu=" + icu.getOccupancy() + "]";
  }






}
