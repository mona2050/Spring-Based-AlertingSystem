/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy2.springalertingsystem.service;

import org.springframework.stereotype.Service;

@Service
public class AlertRaisingImpl implements AlertRaising {

  @Override
  public void alertingNursingStation(int alertStatus){

    if(alertStatus == 1) {
      System.out.println("ALERT !!!!!");
    }
  }

}
