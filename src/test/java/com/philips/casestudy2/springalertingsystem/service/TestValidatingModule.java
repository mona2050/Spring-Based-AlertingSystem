/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy2.springalertingsystem.service;


import static org.junit.Assert.assertEquals;
import org.junit.Test;
import com.philips.casestudy2.springalertingsystem.domain.PatientVitals;

public class TestValidatingModule {

  static VitalValidationServiceForErrorsImpl ValidatingService = new VitalValidationServiceForErrorsImpl();

  @Test
  public void test_validate_vitals_data_when_patientd_is_null() {
    final PatientVitals[] sample = {new PatientVitals(null,"70","93","99.5")};
    final String cause = ValidatingService.validateVitalsData(sample);
    assertEquals("INVALID PATIENTID", cause);
  }
  @Test
  public void test_validate_vitals_data_when_spo2_is_zero(){
    final PatientVitals[] sample = {new PatientVitals(" DWSFG566","0","93","99.5")};
    final String cause = ValidatingService.validateVitalsData(sample);
    assertEquals("INVALID OXYGEN LEVELS", cause);
  }


  @Test
  public void test_validate_vitals_data_when_spo2_is_negative(){
    final PatientVitals[] sample = {new PatientVitals(" DWSFG566","-1","93","99.5")};
    final String cause = ValidatingService.validateVitalsData(sample);
    assertEquals("INVALID OXYGEN LEVELS", cause);
  }

  @Test
  public void test_validate_vitals_data_when_heartRate_is_zero(){
    final PatientVitals[] sample = {new PatientVitals(" DWSFG566","70","0","99.5")};
    final String cause = ValidatingService.validateVitalsData(sample);
    assertEquals("INVALID HEART RATE", cause);
  }


  @Test
  public void test_validate_vitals_data_when_heartRate_is_negative(){
    final PatientVitals[] sample = {new PatientVitals(" DWSFG566","70","-1","99.5")};
    final String cause = ValidatingService.validateVitalsData(sample);
    assertEquals("INVALID HEART RATE", cause);
  }


  @Test
  public void test_validate_vitals_data_when_temperature_is_zero(){
    final PatientVitals[] sample = {new PatientVitals(" DWSFG566","70","93","0")};
    final String cause = ValidatingService.validateVitalsData(sample);
    assertEquals("INVALID TEMPERATURE", cause);
  }


  @Test
  public void test_validate_vitals_data_when_temperature_is_negative(){
    final PatientVitals[] sample = {new PatientVitals(" DWSFG566","70","93","-1")};
    final String cause = ValidatingService.validateVitalsData(sample);
    assertEquals("INVALID TEMPERATURE", cause);
  }

  @Test
  public void test_validate_vitals_data_when_spo2_is_69() {
    final PatientVitals[] sample = {new PatientVitals(" DWSFG566","69","93","99.5")};
    final String cause = ValidatingService.validateVitalsData(sample);
    assertEquals("OXYGEN LEVEL IS OUT OF RANGE", cause);
  }
  @Test
  public void test_validate_vitals_data_when_spo2_is_101() {
    final PatientVitals[] sample = {new PatientVitals(" DWSFG566","101","93","99.5")};
    final String cause = ValidatingService.validateVitalsData(sample);
    assertEquals("OXYGEN LEVEL IS OUT OF RANGE", cause);
  }

  @Test
  public void test_validate_vitals_data_when_hr_is_29() {
    final PatientVitals[] sample = {new PatientVitals(" DWSFG566","70","29","93")};
    final String cause = ValidatingService.validateVitalsData(sample);
    assertEquals("PULSERATE IS OUT OF RANGE", cause);
  }
  @Test
  public void test_validate_vitals_data_when_hr_is_255() {
    final PatientVitals[] sample = {new PatientVitals(" DWSFG566","70","255","93")};
    final String cause = ValidatingService.validateVitalsData(sample);
    assertEquals("PULSERATE IS OUT OF RANGE", cause);
  }

  @Test
  public void test_validate_vitals_data_when_temp_is_92() {
    final PatientVitals[] sample = {new PatientVitals(" DWSFG566","70","93","92")};
    final String cause = ValidatingService.validateVitalsData(sample);
    assertEquals("TEMPERATURE IS OUT OF RANGE", cause);
  }
  @Test
  public void test_validate_vitals_data_when_temp_is_114() {
    final PatientVitals[] sample = {new PatientVitals(" DWSFG566","70","93","114")};
    final String cause = ValidatingService.validateVitalsData(sample);
    assertEquals("TEMPERATURE IS OUT OF RANGE", cause);
  }

  @Test
  public void test_validate_vitals_data_when_everything_is_fine() {
    final PatientVitals[] sample = {new PatientVitals(" DWSFG566","71","94","112")};
    final String cause = ValidatingService.validateVitalsData(sample);
    assertEquals(null, cause);
  }




  @Test
  public void testCheckparams()
  {
    assertEquals(true,VitalValidationServiceForErrorsImpl.checkLessThanZeroValue(-1));
    assertEquals(true,VitalValidationServiceForErrorsImpl.checkLessThanZeroValue(0));
    assertEquals(false,VitalValidationServiceForErrorsImpl.checkLessThanZeroValue(1));
  }

  @Test
  public void testCheckSPO2()
  {
    assertEquals(false,VitalValidationServiceForErrorsImpl.checkSPO2(69));
    assertEquals(true,VitalValidationServiceForErrorsImpl.checkSPO2(70));
    assertEquals(true,VitalValidationServiceForErrorsImpl.checkSPO2(71));
    assertEquals(true,VitalValidationServiceForErrorsImpl.checkSPO2(99));
    assertEquals(true,VitalValidationServiceForErrorsImpl.checkSPO2(100));
    assertEquals(false,VitalValidationServiceForErrorsImpl.checkSPO2(101));

  }

  @Test
  public void testCheckTemp()
  {
    assertEquals(false,VitalValidationServiceForErrorsImpl.checkTemp(92));
    assertEquals(true,VitalValidationServiceForErrorsImpl.checkTemp(93));
    assertEquals(true,VitalValidationServiceForErrorsImpl.checkTemp(94));
    assertEquals(true,VitalValidationServiceForErrorsImpl.checkTemp(112));
    assertEquals(true,VitalValidationServiceForErrorsImpl.checkTemp(113));
    assertEquals(false,VitalValidationServiceForErrorsImpl.checkTemp(114));

  }


  @Test
  public void testCheckPulseRate()
  {
    assertEquals(false,VitalValidationServiceForErrorsImpl.checkPulseRate(29));
    assertEquals(true,VitalValidationServiceForErrorsImpl.checkPulseRate(30));
    assertEquals(true,VitalValidationServiceForErrorsImpl.checkPulseRate(31));
    assertEquals(true,VitalValidationServiceForErrorsImpl.checkPulseRate(253));
    assertEquals(true,VitalValidationServiceForErrorsImpl.checkPulseRate(254));
    assertEquals(false,VitalValidationServiceForErrorsImpl.checkPulseRate(255));

  }

}
