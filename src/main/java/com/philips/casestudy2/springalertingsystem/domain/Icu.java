/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy2.springalertingsystem.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Icu {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  int bedid;

  int occupancy;

  @OneToOne(mappedBy = "icu")
  @JsonIgnore
  Patient patient;


  public Icu() {

  }

  public Icu(int occupancy) {
    super();
    this.occupancy = occupancy;

  }

  public int getBedid() {
    return bedid;
  }

  public void setBedid(int bedid) {
    this.bedid = bedid;
  }

  public int getOccupancy() {
    return occupancy;
  }

  public void setOccupancy(int occupancy) {
    this.occupancy = occupancy;
  }

  public Patient getPatient() {
    return patient;
  }

  public void setPatient(Patient patient) {
    this.patient = patient;
  }

  @Override
  public String toString() {
    return "Icu [bedid=" + bedid + ", occupancy=" + occupancy + "]";
  }



}
