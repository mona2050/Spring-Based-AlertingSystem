/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy2.springalertingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MonitoringController{

  @Autowired
  static VitalCheck vitalCheckService;

  @Autowired
  static VitalValidationForErrors vitalValidationService;

  @Autowired
  static PatientMonitorSimulator patientSimulatorService;

  @Autowired
  static AlertRaising alertRaisingService;


  public void setVitalCheckService(VitalCheck vitalCheckService) {
    this.vitalCheckService = vitalCheckService;
  }


  public void setVitalValidationService(VitalValidationForErrors vitalValidationService) {
    this.vitalValidationService = vitalValidationService;
  }


  public void setPatientSimulatorService(PatientMonitorSimulator patientSimulatorService) {
    this.patientSimulatorService = patientSimulatorService;
  }


  public void setAlertRaisingService(AlertRaising alertRaisingService) {
    this.alertRaisingService = alertRaisingService;
  }



  //  static BufferedReader in ;
  //  static int quit=0;
  //  static PatientIdGeneratingModule p= new PatientIdGeneratingModule();
  //  public static String patientId= p.getAlphaNumericString();
  //
  //  @Override
  //  public void run()
  //  {
  //    String msg = null;
  //    while(true)
  //    {
  //      try{
  //        msg=in.readLine();
  //      }catch(final Exception e){}
  //
  //      if(msg.equalsIgnoreCase("Q")) {quit=1;break;}}
  //  }


  @RequestMapping(value = "/startMonitoring/{id}", method = RequestMethod.GET)
  public static void startPatientMonitoring(@PathVariable("id") String patientId) {
    final Sample[] sample = patientSimulatorService.getDetails(patientId);

    if(sample !=null)
    {

      for(int i=0;i<sample.length;i++)
      {
        System.out.println("PatientId = " +sample[i].patientId);
        System.out.println("OxygenLevel = "+sample[i].oxygenLevel);
        System.out.println("PulseRate = "+sample[i].pulseRate);
        System.out.println("Temperature = "+sample[i].temperature);
      }

      if(vitalValidationService.validateVitalsData(sample)) {
        alertRaisingService.alertingNursingStation(1);
      } else
      {
        if(vitalCheckService.checkAllVitals(sample)) {
          alertRaisingService.alertingNursingStation(1);
        } else {
          alertRaisingService.alertingNursingStation(0);
        }

      }
    } else {
      System.out.println("NOT A VALID INPUT!!!!");
    }

  }


  //  public static void main(String args[]) throws Exception{
  //
  //    in=new BufferedReader(new InputStreamReader(System.in));
  //
  //    final Thread t=new Thread(new MonitoringController());
  //    t.start();
  //
  //    System.out.println("PRESS Q THEN ENTER to terminate");
  //    while(true)
  //    {
  //      if(quit==1) {
  //        break;
  //      }
  //      startPatientMonitoring();
  //      System.out.println("********************************************");
  //      t.sleep(1000);
  //
  //    }
  //  }

}