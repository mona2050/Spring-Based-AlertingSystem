/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy2.springalertingsystem.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MonitoringController implements Runnable {


  VitalCheckImpl vitalCheckService;


  static BufferedReader in ;
  static int quit=0;
  static PatientIdGeneratingModule p= new PatientIdGeneratingModule();
  public static String patientId= p.getAlphaNumericString();

  @Override
  public void run()
  {
    String msg = null;
    while(true)
    {
      try{
        msg=in.readLine();
      }catch(final Exception e){}

      if(msg.equalsIgnoreCase("Q")) {quit=1;break;}}
  }


  //@RequestMapping(value = "/startMonitoring", method = RequestMethod.GET)
  public static void controllerFunction() {

    final InputGeneratingModule ig = new InputGeneratingModule();
    final AlertRaisingModule ar = new AlertRaisingModule();
    final VitalValidationForErrors vm = new VitalValidationForErrorsImpl();
    final VitalCheck vc = new VitalCheckImpl();

    final Sample[] sample = ig.getDetails(patientId);

    if(sample !=null)
    {

      for(int i=0;i<1;i++)
      {
        System.out.println("PatientId = " +sample[i].patientId);
        System.out.println("OxygenLevel = "+sample[i].oxygenLevel);
        System.out.println("PulseRate = "+sample[i].pulseRate);
        System.out.println("Temperature = "+sample[i].temperature);
      }

      if(vm.validateDetails(sample)) {
        ar.alertingFunc(1);
      } else
      {
        if(vc.checkAllVitals(sample)) {
          ar.alertingFunc(1);
        } else {
          ar.alertingFunc(0);
        }

      }
    } else {
      System.out.println("NOT A VALID INPUT!!!!");
    }

  }


  public static void main(String args[]) throws Exception{

    in=new BufferedReader(new InputStreamReader(System.in));

    final Thread t=new Thread(new MonitoringController());
    t.start();

    System.out.println("PRESS Q THEN ENTER to terminate");
    while(true)
    {
      if(quit==1) {
        break;
      }
      controllerFunction();
      System.out.println("********************************************");
      t.sleep(1000);

    }
  }
}