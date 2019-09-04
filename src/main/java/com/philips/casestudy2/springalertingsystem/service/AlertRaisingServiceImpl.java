/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy2.springalertingsystem.service;

import org.springframework.stereotype.Service;

@Service
public class AlertRaisingServiceImpl implements AlertRaisingService {

  public enum Result{
    ALERT("ALERT"), NOALERT("NOALERT");

    String key;

    Result(String key) { this.key = key; }

    @Override
    public String toString(){
      return key;
    }
  }

  @Override
  public Result alertingFunc(int alertStatus){
    if(alertStatus==1) {
      return Result.ALERT;
    } else {
      return Result.NOALERT;
    }

  }

}
