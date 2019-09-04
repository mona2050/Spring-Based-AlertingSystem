/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy2.springalertingsystem.domain;

public class PatientVitals {

  public String patientId;
  public String oxygenLevel;
  public String pulseRate;
  public String temperature;

  public PatientVitals(String patientId,String spo2,String pulseRate,String temperature){
    this.patientId = patientId;
    this.oxygenLevel = spo2;
    this.pulseRate = pulseRate;
    this.temperature = temperature;

  }

}
