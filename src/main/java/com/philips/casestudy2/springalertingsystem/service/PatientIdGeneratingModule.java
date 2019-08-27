package com.philips.casestudy2.springalertingsystem.service;
public class PatientIdGeneratingModule { 
	
    String getAlphaNumericString() 
    { 
        String charString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numericString = "0123456789";
        StringBuilder sb = new StringBuilder(); 
  
        for (int i = 0; i < 5; i++) { 
  
            int index = (int)(charString.length() * Math.random()); 
            sb.append(charString.charAt(index)); 
        } 
        for (int i = 0; i < 3; i++) { 
            int index = (int)(numericString.length() * Math.random()); 
            sb.append(numericString.charAt(index)); 
        } 
  
        return sb.toString(); 
    } 
  
} 