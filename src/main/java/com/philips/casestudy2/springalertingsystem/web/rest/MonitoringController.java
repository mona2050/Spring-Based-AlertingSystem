/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy2.springalertingsystem.web.rest;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.philips.casestudy2.springalertingsystem.domain.Patient;
import com.philips.casestudy2.springalertingsystem.domain.PatientVitals;
import com.philips.casestudy2.springalertingsystem.service.AlertRaisingService;
import com.philips.casestudy2.springalertingsystem.service.PatientMonitorSimulatorService;
import com.philips.casestudy2.springalertingsystem.service.PatientServiceImpl;
import com.philips.casestudy2.springalertingsystem.service.VitalCheckService;
import com.philips.casestudy2.springalertingsystem.service.VitalValidationServiceForErrors;

@RestController
@RequestMapping("/hospital")
public class MonitoringController{



  VitalCheckService vitalCheckService;
  VitalValidationServiceForErrors vitalValidationService;
  PatientMonitorSimulatorService patientSimulatorService;
  AlertRaisingService alertRaisingService;
  PatientServiceImpl service;


  @Autowired
  public void setVitalCheckService(VitalCheckService vitalCheckService) {
    this.vitalCheckService = vitalCheckService;
  }

  @Autowired
  public void setVitalValidationService(VitalValidationServiceForErrors vitalValidationService) {
    this.vitalValidationService = vitalValidationService;
  }

  @Autowired
  public void setPatientSimulatorService(PatientMonitorSimulatorService patientSimulatorService) {
    this.patientSimulatorService = patientSimulatorService;
  }

  @Autowired
  public void setAlertRaisingService(AlertRaisingService alertRaisingService) {
    this.alertRaisingService = alertRaisingService;
  }


  public void setService(PatientServiceImpl service) {
    this.service = service;
  }

  @GetMapping(value = "/monitor/{id}")
  public ResponseEntity<List<String>> startPatientMonitoring(@PathVariable("id") String patientId)
  {
    if(patientId!=null) {
      final Patient p = service.findPatientById(patientId);

      if(p!=null) {

        final PatientVitals[] sample = patientSimulatorService.getDetails(patientId);

        final List<String> listOfVitals=new ArrayList<>();
        final String vitalValidationResult;
        final List<String> vitalCheckResult;

        if(sample !=null)
        {
          for (final PatientVitals element : sample) {
            listOfVitals.add("PatientId="+element.patientId);
            listOfVitals.add("OxygenLevel="+element.oxygenLevel);
            listOfVitals.add("PulseRate="+element.pulseRate);
            listOfVitals.add("Temperature="+element.temperature);
          }
          vitalValidationResult=vitalValidationService.validateVitalsData(sample);
          if(vitalValidationResult!=null) {
            listOfVitals.add(vitalValidationResult);
            listOfVitals.add(alertRaisingService.alertingFunc(1).toString());

          } else

          {
            vitalCheckResult = vitalCheckService.checkAllVitals(sample);
            if(vitalCheckResult!=null) {
              listOfVitals.addAll(vitalCheckResult);
              listOfVitals.add(alertRaisingService.alertingFunc(1).toString());
            } else {
              listOfVitals.add(alertRaisingService.alertingFunc(0).toString());
            }

          }
          return new ResponseEntity<>(listOfVitals,HttpStatus.OK);
        } else {
          return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
      }
      else
      {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }


    }
    else {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

  }
}







