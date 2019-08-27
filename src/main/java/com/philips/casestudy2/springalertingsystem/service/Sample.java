package com.philips.casestudy2.springalertingsystem.service;

public class Sample {

	public String patientId;
	public String oxygenLevel;
	public String pulseRate;
	public String temperature;
	
	Sample(String patientId,String spo2,String pulseRate,String temperature){
		this.patientId = patientId;
		this.oxygenLevel = spo2;
		this.pulseRate = pulseRate;
		this.temperature = temperature;
		
	}

}
