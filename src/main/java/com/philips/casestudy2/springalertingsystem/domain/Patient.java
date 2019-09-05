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
  Gender gender;
  String contact;
  String adhaarno;
  //  @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
  //  Date dateOfBirth;


  @OneToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name="bedid")
  Icu icu;
  public Patient() {

  }




  public Patient(String name, Gender gender, String contact, String adhaarno,
      Icu icu) {
    super();
    this.name = name;
    this.gender = gender;
    this.contact = contact;
    this.adhaarno = adhaarno;
    // this.dateOfBirth = dateOfBirth;
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

  public String getAdhaarno() {
    return adhaarno;
  }

  public void setAdhaarno(String adhaarno) {
    this.adhaarno = adhaarno;
  }


  //  public Date getDateOfBirth() {
  //    return dateOfBirth;
  //  }
  //
  //  public void setDateOfBirth(Date dateOfBirth) {
  //    this.dateOfBirth = dateOfBirth;
  //  }

  @Override
  public String toString() {
    return "Patient [id=" + id + ", name=" + name + ", gender=" + gender + ", contact=" + contact
        + ", adhaarno=" + adhaarno + ", icu=" + icu + "]";
  }







}
