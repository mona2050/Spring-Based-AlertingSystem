/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy2.springalertingsystem.service;

public class PatientIdSimulatorService {


  public String patientIdGenerator() {
    final String charString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    final String numericString = "0123456789";
    final StringBuilder sb = new StringBuilder();


    for (int i = 0; i < 5; i++) {

      final int index = (int)(charString.length() * Math.random());
      sb.append(charString.charAt(index));
    }
    for (int i = 0; i < 3; i++) {
      final int index = (int)(numericString.length() * Math.random());
      sb.append(numericString.charAt(index));
    }

    final String patientId = sb.toString();
    if(patientId!=null) {
      return patientId;
    } else {
      return null;
    }
  }
}
