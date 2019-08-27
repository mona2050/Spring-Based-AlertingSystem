package com.philips.casestudy2.springalertingsystem.service;



public class InputGeneratingModule {
    	public Sample[] getDetails(String patientId)
    	{
			Sample[] arr;
    		arr = new Sample[1];
    		
			RandomValuesGeneratingModule r= new RandomValuesGeneratingModule();
    		int spo2=(int)r.generateFun(50, 100);
    		int hr=(int)r.generateFun(30, 254);
			double temp= Math.round(r.generateFun(80, 130) * 100.0) /100.0;
        	
        	String spo2_ = Integer.toString(spo2);
        	String pulseRate = Integer.toString(hr);
        	String temperature = Double.toString(temp);
        	arr[0] = new Sample(patientId,spo2_,pulseRate,temperature);
        	if(patientId!=null && spo2_!=null && pulseRate!=null && temperature!=null) 
			     return arr;
        	else       	
        		return null;			
			}
		


    
	

}