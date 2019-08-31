/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy2.springalertingsystem.service;

import org.springframework.stereotype.Service;

@Service
public class PatientMonitorSimulatorImpl implements PatientMonitorSimulator {
  @Override
  public Sample[] getDetails(String patientId)
  {
    Sample[] arr;
    arr = new Sample[1];

    final RandomValuesGeneratingModule r= new RandomValuesGeneratingModule();
    final int spo2=(int)r.generateFun(50, 100);
    final int hr=(int)r.generateFun(30, 254);
    final double temp= Math.round(r.generateFun(80, 130) * 100.0) /100.0;

    final String spo2_ = Integer.toString(spo2);
    final String pulseRate = Integer.toString(hr);
    final String temperature = Double.toString(temp);
    arr[0] = new Sample(patientId,spo2_,pulseRate,temperature);
    if(patientId!=null && spo2_!=null && pulseRate!=null && temperature!=null) {
      return arr;
    } else {
      return null;
    }
  }






}