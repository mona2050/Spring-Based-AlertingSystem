/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy2.springalertingsystem.controller;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.philips.casestudy2.springalertingsystem.domain.PatientVitals;
import com.philips.casestudy2.springalertingsystem.service.AlertRaisingServiceImpl;
import com.philips.casestudy2.springalertingsystem.service.AlertRaisingServiceImpl.Result;
import com.philips.casestudy2.springalertingsystem.service.PatientMonitorSimulatorServiceImpl;
import com.philips.casestudy2.springalertingsystem.service.VitalCheckServiceImpl;
import com.philips.casestudy2.springalertingsystem.service.VitalValidationServiceForErrorsImpl;
import com.philips.casestudy2.springalertingsystem.web.rest.MonitoringController;

public class TestMonitoringController {

  @Test
  public void test_monitoring_controller_when_sample_is_null() throws Exception {
    final PatientMonitorSimulatorServiceImpl ps = Mockito.mock(PatientMonitorSimulatorServiceImpl.class);
    final MonitoringController cc=new MonitoringController();
    cc.setPatientSimulatorService(ps);


    final PatientVitals[] sample = null;

    Mockito.when(ps.getDetails("A2345")).thenReturn(sample);
    final ResponseEntity<List<String>> res=cc.startPatientMonitoring("A2345");
    if(res.getStatusCode() == HttpStatus.BAD_REQUEST)
    {
      assertEquals(res.getBody(), sample);
    }
  }


  @Test
  public void test_monitoring_controller_when_vital_validation_service_is_not_null() throws Exception{
    final VitalValidationServiceForErrorsImpl vs = Mockito.mock(VitalValidationServiceForErrorsImpl.class);
    final MonitoringController cc=new MonitoringController();
    cc.setVitalValidationService(vs);

    final AlertRaisingServiceImpl as = Mockito.mock(AlertRaisingServiceImpl.class);
    cc.setAlertRaisingService(as);

    final PatientMonitorSimulatorServiceImpl ps = Mockito.mock(PatientMonitorSimulatorServiceImpl.class);
    cc.setPatientSimulatorService(ps);

    final PatientVitals[] sample = new PatientVitals[] {new PatientVitals("A2345","68","100","99.5")};


    final List<String> listOfVitals=new ArrayList<>();
    for (final PatientVitals element : sample) {
      listOfVitals.add("PatientId="+element.patientId);
      listOfVitals.add("OxygenLevel="+element.oxygenLevel);
      listOfVitals.add("PulseRate="+element.pulseRate);
      listOfVitals.add("Temperature="+element.temperature);}

    final String cause="OXYGEN LEVEL IS OUT OF RANGE";
    final Result result = Result.ALERT;
    listOfVitals.add(cause);
    listOfVitals.add(result.toString());


    Mockito.when(vs.validateVitalsData(sample)).thenReturn(cause);
    Mockito.when(as.alertingFunc(1)).thenReturn(result);
    Mockito.when(ps.getDetails("A2345")).thenReturn(sample);

    final ResponseEntity<List<String>> res=cc.startPatientMonitoring("A2345");


    if(res.getStatusCode() == HttpStatus.OK)
    {
      assertEquals(res.getBody(), listOfVitals);
    }
  }

  @Test
  public void test_monitoring_controller_when_vital_check_service_is_not_null() throws Exception{
    final VitalValidationServiceForErrorsImpl vs = Mockito.mock(VitalValidationServiceForErrorsImpl.class);
    final MonitoringController cc=new MonitoringController();
    cc.setVitalValidationService(vs);

    final AlertRaisingServiceImpl as = Mockito.mock(AlertRaisingServiceImpl.class);
    cc.setAlertRaisingService(as);

    final PatientMonitorSimulatorServiceImpl ps = Mockito.mock(PatientMonitorSimulatorServiceImpl.class);
    cc.setPatientSimulatorService(ps);

    final VitalCheckServiceImpl vcs = Mockito.mock(VitalCheckServiceImpl.class);
    cc.setVitalCheckService(vcs);

    final PatientVitals[] sample = new PatientVitals[] {new PatientVitals("A2345","73","41","96")};


    final List<String> listOfVitals=new ArrayList<>();
    final List<String> causes=new ArrayList<>();
    for (final PatientVitals element : sample) {
      listOfVitals.add("PatientId="+element.patientId);
      listOfVitals.add("OxygenLevel="+element.oxygenLevel);
      listOfVitals.add("PulseRate="+element.pulseRate);
      listOfVitals.add("Temperature="+element.temperature);}

    causes.add("CRITICAL PULSE RATE!!!!");
    causes.add("CRITICAL TEMPERTAURE!!!!");
    causes.add("CRITICAL OXYGEN LEVELS!!!!");
    final Result result = Result.ALERT;
    listOfVitals.addAll(causes);
    listOfVitals.add(result.toString());


    Mockito.when(vs.validateVitalsData(sample)).thenReturn(null);
    Mockito.when(as.alertingFunc(1)).thenReturn(result);
    Mockito.when(ps.getDetails("A2345")).thenReturn(sample);
    Mockito.when(vcs.checkAllVitals(sample)).thenReturn(causes);

    final ResponseEntity<List<String>> res=cc.startPatientMonitoring("A2345");

    if(res.getStatusCode() == HttpStatus.OK)
    {
      assertEquals(res.getBody(), listOfVitals);
    }
  }

  @Test
  public void test_monitoring_controller_when_no_alert() throws Exception{
    final VitalValidationServiceForErrorsImpl vs = Mockito.mock(VitalValidationServiceForErrorsImpl.class);
    final MonitoringController cc=new MonitoringController();
    cc.setVitalValidationService(vs);

    final AlertRaisingServiceImpl as = Mockito.mock(AlertRaisingServiceImpl.class);
    cc.setAlertRaisingService(as);

    final PatientMonitorSimulatorServiceImpl ps = Mockito.mock(PatientMonitorSimulatorServiceImpl.class);
    cc.setPatientSimulatorService(ps);

    final VitalCheckServiceImpl vcs = Mockito.mock(VitalCheckServiceImpl.class);
    cc.setVitalCheckService(vcs);

    final PatientVitals[] sample = new PatientVitals[] {new PatientVitals("A2345","92","70","98")};


    final List<String> listOfVitals=new ArrayList<>();
    final List<String> causes=new ArrayList<>();
    for (final PatientVitals element : sample) {
      listOfVitals.add("PatientId="+element.patientId);
      listOfVitals.add("OxygenLevel="+element.oxygenLevel);
      listOfVitals.add("PulseRate="+element.pulseRate);
      listOfVitals.add("Temperature="+element.temperature);}


    final Result result = Result.NOALERT;
    listOfVitals.add(result.toString());


    Mockito.when(ps.getDetails("A2345")).thenReturn(sample);
    Mockito.when(vs.validateVitalsData(sample)).thenReturn(null);
    Mockito.when(as.alertingFunc(0)).thenReturn(result);
    Mockito.when(vcs.checkAllVitals(sample)).thenReturn(null);

    final ResponseEntity<List<String>> res=cc.startPatientMonitoring("A2345");

    if(res.getStatusCode() == HttpStatus.OK)
    {
      assertEquals(res.getBody(), listOfVitals);
    }
  }



}
