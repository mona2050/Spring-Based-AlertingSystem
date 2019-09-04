/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy2.springalertingsystem.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.philips.casestudy2.springalertingsystem.domain.PatientVitals;

@Service
public class VitalCheckServiceImpl implements VitalCheckService {

  @Override
  public List<String> checkAllVitals(PatientVitals[] sample) {
    int result1=0;
    int result2=0;
    int result3=0;
    String pr = null;
    String tc = null;
    String oc = null;
    for(int i=0;i<1;i++) {
      pr = sample[i].pulseRate;
      tc = sample[i].temperature;
      oc = sample[i].oxygenLevel;
    }
    final int oc_=Integer.parseInt(oc);
    final int pr_ = Integer.parseInt(pr);
    final double tc_ = Double.parseDouble(tc);

    final List<String> causes=new ArrayList<>();
    result1 = pulseRateCheck(pr_);
    result2 = temperatureCheck(tc_);
    result3 = oxygenCheck(oc_);
    if(result1==1) {
      causes.add("CRITICAL PULSE RATE!!!!");
    }

    if(result2==1) {
      causes.add("CRITICAL TEMPERTAURE!!!!");
    }

    if(result3==1) {
      causes.add("CRITICAL OXYGEN LEVELS!!!!");
    }

    if(result1==1 || result2==1 || result3==1) {
      return causes;
    } else {
      return null;
    }

  }

  @Override
  public int pulseRateCheck(int pulseRate) {

    final int pulseRateNormalLimit = 0;
    final int lowPulseRateWarningLimit = 1;
    final int lowPulseRateCriticalLimit = 1;
    final int highPulseRateWarningLimit = 1;
    final int highPulseRateCriticalLimit = 1;
    final int pulseRateMachineError = 1;
    int flag = 1;

    if( pulseRate >=50 && pulseRate <= 100 ) {

      flag = pulseRateNormalLimit;

    }



    else if( pulseRate >=40 && pulseRate < 50 ){
      flag = lowPulseRateWarningLimit;

    }


    else if( pulseRate >=30 && pulseRate < 40) {
      flag = lowPulseRateCriticalLimit;

    }

    else if( pulseRate >100 && pulseRate < 120 ){
      flag = highPulseRateWarningLimit;

    }


    else if( pulseRate >=120 && pulseRate < 254) {
      flag = highPulseRateCriticalLimit;

    }


    else
    {
      flag = pulseRateMachineError;
    }


    return flag;

  }


  @Override
  public int temperatureCheck(double tempratureF) {

    final int tempratureNormalLimit = 0;
    final int lowTempratureWarningLimit = 1;
    final int lowTempratureCriticalLimit = 1;
    final int highTempratureWarningLimit = 1;
    final int highTempratureCriticalLimit = 1;
    final int tempratureMachineError = 1;
    int flag = 1;

    if( tempratureF >=97.0 && tempratureF <= 99.00 ) {

      flag = tempratureNormalLimit;

    }



    else if( tempratureF >=95.00 && tempratureF < 97.00 ){
      flag = lowTempratureWarningLimit;

    }


    else if( tempratureF >=93.00 && tempratureF < 95.00) {
      flag = lowTempratureCriticalLimit;

    }

    else if( tempratureF >99.00 && tempratureF < 104.00 ){
      flag = highTempratureWarningLimit;

    }


    else if( tempratureF >=104.00 && tempratureF < 108.00) {
      flag = highTempratureCriticalLimit;

    }


    else
    {
      flag = tempratureMachineError;
    }


    return flag;


  }


  @Override
  public int oxygenCheck(int oxygenConcentration) {

    final int oxygenNormalLimit = 0;
    final int oxygenWarningLimit = 1;
    final int oxygenCriticalLimit = 1;
    final int oxygenMachineError = 1;
    int flag = 0;

    if( oxygenConcentration >=91 && oxygenConcentration <= 100 ) {

      flag = oxygenNormalLimit;

    }



    else if( oxygenConcentration >=81 && oxygenConcentration < 91 ){
      flag = oxygenWarningLimit;

    }


    else if( oxygenConcentration >=70 && oxygenConcentration < 81) {
      flag = oxygenCriticalLimit;

    }


    else
    {
      flag = oxygenMachineError;
    }

    return flag;


  }


}
