/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy2.springalertingsystem.controller;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.philips.casestudy2.springalertingsystem.domain.Gender;
import com.philips.casestudy2.springalertingsystem.domain.Icu;
import com.philips.casestudy2.springalertingsystem.domain.Patient;
import com.philips.casestudy2.springalertingsystem.service.PatientServiceImpl;
import com.philips.casestudy2.springalertingsystem.web.rest.PatientRestController;
public class TestPatientRestController {


  @Test
  public void test_patient_existence_when_given_invalid_adhaarno() throws Exception {
    final PatientRestController prc =  new PatientRestController();
    final String actual = "Invalid Adharno";
    final ResponseEntity<String> response = prc.checkPatientExistence("A2345");
    if(response.getStatusCode() == HttpStatus.BAD_REQUEST) {
      assertEquals(response.getBody(),actual);
    }


  }
  @Test
  public void test_patient_existence_when_patient_exists() throws Exception{
    final PatientServiceImpl service = Mockito.mock(PatientServiceImpl.class);
    final PatientRestController prc =  new PatientRestController();

    prc.setPs(service);
    final Icu i1 = new Icu(0);
    i1.setBedid(1);
    final Patient p1= new Patient("harshitha",Gender.FEMALE,"6301340004","1234567891",i1);
    p1.setId("A2345");


    Mockito.when(service.checkPatientExistence("1234567891")).thenReturn(p1);

    final String actual = "Patient Already exists";
    final ResponseEntity<String> response = prc.checkPatientExistence("1234567891");
    if(response.getStatusCode() == HttpStatus.OK) {
      assertEquals(response.getBody(),actual);
    }
  }

  @Test
  public void test_patient_existence_when_patient_not_exist() throws Exception{
    final PatientServiceImpl service = Mockito.mock(PatientServiceImpl.class);
    final PatientRestController prc =  new PatientRestController();

    prc.setPs(service);
    final Icu i1 = new Icu(0);
    i1.setBedid(1);
    final Patient p1= new Patient("harshitha",Gender.FEMALE,"6301340004","1234567891",i1);
    p1.setId("A2345");


    Mockito.when(service.checkPatientExistence("1234567893")).thenReturn(null);

    final String actual = "Patient doesnot exists";
    final ResponseEntity<String> response = prc.checkPatientExistence("1234567893");
    if(response.getStatusCode() == HttpStatus.OK) {
      assertEquals(response.getBody(),actual);
    }
  }

  @Test
  public void test_add_new_patient_when_returned_id_is_null() throws Exception{
    final PatientServiceImpl service = Mockito.mock(PatientServiceImpl.class);
    final PatientRestController prc =  new PatientRestController();

    prc.setPs(service);
    final Icu i1 = new Icu(0);
    i1.setBedid(1);
    final Patient p1= new Patient("harshitha",Gender.FEMALE,"6301340004","1234567891",i1);

    Mockito.when(service.addNewPatient(p1.getIcu().getBedid(), p1)).thenReturn(null);
    final String actual ="Patient cannot be null!!!";
    final ResponseEntity<String> response = prc.addingPatient(p1);

    if(response.getStatusCode() == HttpStatus.BAD_REQUEST) {
      assertEquals(response.getBody(),actual);
    }

  }

  @Test
  public void test_add_new_patient_when_patient_already_exists() throws Exception{
    final PatientServiceImpl service = Mockito.mock(PatientServiceImpl.class);
    final PatientRestController prc =  new PatientRestController();

    prc.setPs(service);
    final Icu i1 = new Icu(0);
    i1.setBedid(1);
    final Patient p1= new Patient("harshitha",Gender.FEMALE,"6301340004","1234567891",i1);


    Mockito.when(service.addNewPatient(p1.getIcu().getBedid(), p1)).thenReturn("1");
    final String actual =  "Patient already exists!!!";
    final ResponseEntity<String> response = prc.addingPatient(p1);

    if(response.getStatusCode() == HttpStatus.BAD_REQUEST) {
      assertEquals(response.getBody(),actual);
    }
  }

  @Test
  public void test_add_new_patient_when_bed_already_occupied() throws Exception{
    final PatientServiceImpl service = Mockito.mock(PatientServiceImpl.class);
    final PatientRestController prc =  new PatientRestController();


    prc.setPs(service);
    final Icu i1 = new Icu(0);
    i1.setBedid(1);
    final Patient p1= new Patient("harshitha",Gender.FEMALE,"6301340004","1234567891",i1);
    p1.setId("A2345");

    prc.setPs(service);
    final Icu i2 = new Icu(0);
    i1.setBedid(1);
    final Patient p2= new Patient("mona",Gender.FEMALE,"6301340004","1234567897",i2);

    Mockito.when(service.addNewPatient(p2.getIcu().getBedid(), p2)).thenReturn("0");
    final String actual = "Bed already occupied!!!";
    final ResponseEntity<String> response = prc.addingPatient(p2);

    if(response.getStatusCode() == HttpStatus.BAD_REQUEST) {
      assertEquals(response.getBody(),actual);
    }



  }

  @Test
  public void test_add_new_patient_for_successfull_creation() throws Exception{
    final PatientServiceImpl service = Mockito.mock(PatientServiceImpl.class);
    final PatientRestController prc =  new PatientRestController();


    prc.setPs(service);
    final Icu i1 = new Icu(0);
    i1.setBedid(1);
    final Patient p1= new Patient("harshitha",Gender.FEMALE,"6301340004","1234567891",i1);
    p1.setId("A2345");

    i1.setBedid(1);
    final Patient p2= new Patient("harshitha",Gender.FEMALE,"6301340004","1234567891",i1);

    Mockito.when(service.addNewPatient(p2.getIcu().getBedid(), p2)).thenReturn("A2345");
    final String actual = "Patient successfully created!!!";
    final ResponseEntity<String> response = prc.addingPatient(p2);

    if(response.getStatusCode() == HttpStatus.OK) {
      assertEquals(response.getBody(),actual);

    }


  }

  @Test
  public void test_get_all_patients() throws Exception{
    final PatientServiceImpl service = Mockito.mock(PatientServiceImpl.class);
    final PatientRestController prc =  new PatientRestController();


    prc.setPs(service);
    final Icu i1 = new Icu(0);
    i1.setBedid(1);
    final Patient p1= new Patient("harshitha",Gender.FEMALE,"6301340004","1234567891",i1);
    p1.setId("A2345");

    prc.setPs(service);
    final Icu i2 = new Icu(0);
    i1.setBedid(2);
    final Patient p2= new Patient("mona",Gender.FEMALE,"6301340004","1234567895",i2);
    p2.setId("B3456");

    final List<Patient> patients=new ArrayList<>();
    patients.add(p1);
    patients.add(p2);

    Mockito.when(service.getAllPatients()).thenReturn(patients);
    final List<Patient> actual = patients;
    final ResponseEntity<List<Patient>> response = prc.getAllPatients();

    if(response.getStatusCode()==HttpStatus.OK) {
      assertEquals(response.getBody(),actual);
    }


  }

  @Test
  public void test_get_patient_by_id_for_succesfull_return() throws Exception{
    final PatientServiceImpl service = Mockito.mock(PatientServiceImpl.class);
    final PatientRestController prc =  new PatientRestController();


    prc.setPs(service);
    final Icu i1 = new Icu(0);
    i1.setBedid(1);
    final Patient p1= new Patient("harshitha",Gender.FEMALE,"6301340004","1234567891",i1);
    p1.setId("A2345");

    Mockito.when(service.findPatientById("A2345")).thenReturn(p1);


    final ResponseEntity<Patient> response = prc.getPatientById("A2345");

    if(response.getStatusCode()==HttpStatus.OK) {
      assertEquals(response.getBody(),p1);
    }

  }

  @Test
  public void test_get_patient_by_id_when_patient_not_found() throws Exception{
    final PatientServiceImpl service = Mockito.mock(PatientServiceImpl.class);
    final PatientRestController prc =  new PatientRestController();


    prc.setPs(service);
    final Icu i1 = new Icu(0);
    i1.setBedid(1);
    final Patient p1= new Patient("harshitha",Gender.FEMALE,"6301340004","1234567891",i1);
    p1.setId("A2345");

    Mockito.when(service.findPatientById("A2348")).thenReturn(null);
    final Patient actual = null;

    final ResponseEntity<Patient> response = prc.getPatientById("A2348");

    if(response.getStatusCode()==HttpStatus.NOT_FOUND) {
      assertEquals(response.getBody(),actual);
    }

  }

  @Test
  public void test_discharge_patient_when_patient_found() throws Exception{
    final PatientServiceImpl service = Mockito.mock(PatientServiceImpl.class);
    final PatientRestController prc =  new PatientRestController();


    prc.setPs(service);
    final Icu i1 = new Icu(0);
    i1.setBedid(1);
    final Patient p1= new Patient("harshitha",Gender.FEMALE,"6301340004","1234567891",i1);
    p1.setId("A2345");

    Mockito.when(service.findPatientById("A2345")).thenReturn(p1);
    doNothing().when(service).deleteById("A2345");
    final String actual = "Patient Successfully deleted!!";
    final ResponseEntity<String> response = prc.dischargePatient("A2345");

    if(response.getStatusCode()==HttpStatus.OK) {
      assertEquals(response.getBody(),actual);
    }

  }

  @Test
  public void test_discharge_patient_when_patient_not_found() throws Exception{
    final PatientServiceImpl service = Mockito.mock(PatientServiceImpl.class);
    final PatientRestController prc =  new PatientRestController();


    prc.setPs(service);
    final Icu i1 = new Icu(0);
    i1.setBedid(1);
    final Patient p1= new Patient("harshitha",Gender.FEMALE,"6301340004","1234567891",i1);
    p1.setId("A2345");

    Mockito.when(service.findPatientById("A2348")).thenReturn(null);
    final String actual = "Patient deletion failed!!";
    final ResponseEntity<String> response = prc.dischargePatient("A2348");

    if(response.getStatusCode()==HttpStatus.NOT_FOUND) {
      assertEquals(response.getBody(),actual);
    }

  }

  @Test
  public void test_get_bed_of_patient_for_null_patientid() throws Exception{
    final PatientRestController prc =  new PatientRestController();

    final Icu i1 = new Icu(0);
    i1.setBedid(1);
    final Patient p1= new Patient("harshitha",Gender.FEMALE,"6301340004","1234567891",i1);
    p1.setId("A2345");

    final int response = prc.getBedOfPatient(null);

    assertTrue(response<0);


  }

  @Test
  public void test_get_bed_of_patient_if_patient_doesnot_exist() throws Exception{
    final PatientRestController prc =  new PatientRestController();
    final PatientServiceImpl service = Mockito.mock(PatientServiceImpl.class);
    prc.setPs(service);

    final Icu i1 = new Icu(0);
    i1.setBedid(1);
    final Patient p1= new Patient("harshitha",Gender.FEMALE,"6301340004","1234567891",i1);
    p1.setId("A2345");

    Mockito.when(service.findPatientById("A2346")).thenReturn(null);

    final int response = prc.getBedOfPatient("A2346");
    assertTrue(response<0);

  }

  @Test
  public void test_get_bed_of_patient_if_patient_exist() throws Exception{
    final PatientRestController prc =  new PatientRestController();
    final PatientServiceImpl service = Mockito.mock(PatientServiceImpl.class);
    prc.setPs(service);

    final Icu i1 = new Icu(0);
    i1.setBedid(1);
    final Patient p1= new Patient("harshitha",Gender.FEMALE,"6301340004","1234567891",i1);
    p1.setId("A2345");

    Mockito.when(service.findPatientById("A2345")).thenReturn(p1);
    Mockito.when(service.findBedOfPatient("A2345")).thenReturn(1);

    final int response = prc.getBedOfPatient("A2345");
    assertTrue(response>0);



  }




}
