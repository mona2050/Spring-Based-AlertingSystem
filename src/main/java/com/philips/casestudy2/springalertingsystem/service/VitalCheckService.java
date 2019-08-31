package com.philips.casestudy2.springalertingsystem.service;



public interface VitalCheck {

  boolean checkAllVitals(Sample[] sample);

  int pulseRateCheck(int pulseRate);

  int temperatureCheck(double tempratureF);

  int oxygenCheck(int oxygenConcentration);

}
