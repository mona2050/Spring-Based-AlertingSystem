/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */

package com.philips.casestudy2.springalertingsystem.service;

import org.springframework.stereotype.Service;
import com.philips.casestudy2.springalertingsystem.domain.PatientVitals;

@Service
public class VitalValidationServiceForErrorsImpl implements VitalValidationServiceForErrors  {

  @Override
  public String validateVitalsData(PatientVitals[] sample) {

    String patientId = null;
    String spo2 = null;
    String pulserate = null;
    String temp = null;



    for (final PatientVitals element : sample) {
      patientId= element.patientId;
      spo2 = element.oxygenLevel;
      pulserate = element.pulseRate;
      temp = element.temperature;

    }
    final int spo2_=Integer.parseInt(spo2);
    final int pulseRate = Integer.parseInt(pulserate);
    final double temperature = Double.parseDouble(temp);
    String cause=null;

    if(patientId == null) {
      cause="INVALID PATIENTID";
    } else if(checkParams(spo2_)==true) {
      cause="INVALID OXYGEN LEVELS";
    } else if(checkParams(pulseRate)==true) {
      cause="INVALID HEART RATE";
    } else if(checkParams(temperature)==true) {
      cause="INVALID TEMPERATURE";
    } else if(checkSPO2(spo2_) == false) {
      cause="OXYGEN LEVEL IS OUT OF RANGE";
    } else if(checkPulseRate(pulseRate)==false) {
      cause="PULSERATE IS OUT OF RANGE";
    } else if(checkTemp(temperature) == false) {
      cause="TEMPERATURE IS OUT OF RANGE";
    } else {
      cause=null;
    }



    return cause;

  }



  // Method1
  public static boolean checkParams(double x)
  {
    if(x<=0) {
      return true;
    } else {
      return false;
    }
  }

  //Method 2
  public static boolean checkSPO2(double spo2)
  {
    if(spo2>=70 && spo2<=100) {
      return true;
    } else {
      return false;
    }

  }


  //Method 3
  public static boolean checkTemp(double temperature)
  {
    if(temperature>=93 && temperature<=113) {
      return true;
    } else {
      return false;
    }

  }

  //Method 4
  public static boolean checkPulseRate(int pulseRate)
  {
    if(pulseRate>=30 && pulseRate<=254) {
      return true;
    } else {
      return false;
    }
  }




}



