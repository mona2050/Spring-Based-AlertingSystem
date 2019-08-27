package com.philips.casestudy2.springalertingsystem.service;


public class AlertRaisingModule {
	
	public static void alertingFunc(int alertStatus){
		
		if(alertStatus == 1) {
			System.out.println("ALERT !!!!!");
		}
	}

}
