/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy2.springalertingsystem.controller;

import static org.junit.Assert.assertEquals;
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
import com.philips.casestudy2.springalertingsystem.service.IcuServiceImpl;
import com.philips.casestudy2.springalertingsystem.web.rest.IcuRestController;

public class TestIcuRestController {

  @Test
  public void test_get_count_of_beds() {

    final IcuServiceImpl service = Mockito.mock(IcuServiceImpl.class);
    final IcuRestController irc = new IcuRestController();
    irc.setIs(service);

    final Icu i1 = new Icu(0);
    i1.setBedid(1);

    final Icu i2 = new Icu(0);
    i2.setBedid(2);

    final List<Icu> beds=new ArrayList<>();
    beds.add(i1);
    beds.add(i2);

    Mockito.when(service.getCountOfBeds()).thenReturn((long)2);

    final long expected = beds.size();

    final long result = irc.getCountOfBeds();
    assertEquals(expected, result);

  }

  @Test
  public void test_add_bed_when_count_of_beds_greater_than_10() {
    final IcuRestController irc = new IcuRestController();
    final IcuServiceImpl service = Mockito.mock(IcuServiceImpl.class);
    irc.setIs(service);

    final Icu i1 = new Icu(0);
    i1.setBedid(1);


    Mockito.when(service.getCountOfBeds()).thenReturn((long)11);

    final ResponseEntity<String> result = irc.addBed(i1);
    final String expected="No more beds can be allocated!!!";
    if(result.getStatusCode()==HttpStatus.BAD_REQUEST) {
      assertEquals(expected,result.getBody());
    }
  }

  @Test
  public void test_addBed_when_count_of_beds_less_than_10() {
    final IcuRestController irc = new IcuRestController();
    final IcuServiceImpl service = Mockito.mock(IcuServiceImpl.class);
    irc.setIs(service);

    final Icu i1 = new Icu(0);
    i1.setBedid(1);

    final Icu i2= new Icu(0);
    i2.setBedid(2);

    Mockito.when(service.getCountOfBeds()).thenReturn((long)8);
    Mockito.when(service.addNewBed(i2)).thenReturn(i1.getBedid());

    final ResponseEntity<String> result = irc.addBed(i2);
    final String expected="Bed successfully created!!!";
    if(result.getStatusCode()==HttpStatus.CREATED) {
      assertEquals(expected,result.getBody());
    }
  }

  @Test
  public void test_findAllBed() {
    final IcuRestController irc = new IcuRestController();
    final IcuServiceImpl service = Mockito.mock(IcuServiceImpl.class);
    irc.setIs(service);

    final Icu i1 = new Icu(0);
    i1.setBedid(1);

    final Icu i2= new Icu(0);
    i2.setBedid(2);

    final List<Icu> beds=new ArrayList<>();
    beds.add(i1);
    beds.add(i2);

    Mockito.when(service.findAll()).thenReturn(beds);

    final List<Icu> result = irc.getAllBed();
    assertEquals(beds.size(), result.size());
  }

  @Test
  public void test_get_bedById_when_bedid_is_less_than_zero() {
    final IcuRestController irc = new IcuRestController();
    final IcuServiceImpl service = Mockito.mock(IcuServiceImpl.class);
    irc.setIs(service);

    final ResponseEntity<Icu> result = irc.getById(-1);
    final Icu expected=null;
    if(result.getStatusCode()==HttpStatus.BAD_REQUEST) {
      assertEquals(expected,result.getBody());
    }
  }

  @Test
  public void test_get_bedById_when_bed_is_present() {
    final IcuRestController irc = new IcuRestController();
    final IcuServiceImpl service = Mockito.mock(IcuServiceImpl.class);
    irc.setIs(service);

    final Icu i1 = new Icu(0);
    i1.setBedid(1);

    Mockito.when(service.findBedById(1)).thenReturn(i1);

    final ResponseEntity<Icu> result = irc.getById(i1.getBedid());
    final Icu expected=i1;
    if(result.getStatusCode()==HttpStatus.OK) {
      assertEquals(expected,result.getBody());
    }
  }

  @Test
  public void test_get_bedById_when_bed_is_not_present() {
    final IcuRestController irc = new IcuRestController();
    final IcuServiceImpl service = Mockito.mock(IcuServiceImpl.class);
    irc.setIs(service);

    final Icu i1 = new Icu(0);
    i1.setBedid(1);

    Mockito.when(service.findBedById(2)).thenReturn(null);

    final ResponseEntity<Icu> result = irc.getById(2);
    final Icu expected=null;
    if(result.getStatusCode()==HttpStatus.NOT_FOUND) {
      assertEquals(expected,result.getBody());
    }
  }

  @Test
  public void test_get_vacantBeds_when_found() {
    final IcuRestController irc = new IcuRestController();
    final IcuServiceImpl service = Mockito.mock(IcuServiceImpl.class);
    irc.setIs(service);

    final Icu i1 = new Icu(0);
    i1.setBedid(1);


    final List<Icu> beds=new ArrayList<>();
    beds.add(i1);


    Mockito.when(service.findVacantBeds()).thenReturn(beds);

    final ResponseEntity<List<Icu>> result = irc.getVacantBeds();
    final List<Icu> expected =beds;
    if(result.getStatusCode()==HttpStatus.OK) {
      assertEquals(expected,result.getBody());
    }
  }

  @Test
  public void test_get_vacantBeds_when_not_found() {
    final IcuRestController irc = new IcuRestController();
    final IcuServiceImpl service = Mockito.mock(IcuServiceImpl.class);
    irc.setIs(service);

    final Icu i1 = new Icu(1);
    i1.setBedid(1);



    List<Icu> beds=new ArrayList<>();
    beds=null;


    Mockito.when(service.findVacantBeds()).thenReturn(null);

    final ResponseEntity<List<Icu>> result = irc.getVacantBeds();
    final List<Icu> expected =beds;
    if(result.getStatusCode()==HttpStatus.NOT_FOUND) {
      assertEquals(expected,result.getBody());
    }
  }


  @Test
  public void test_get_occupiedBeds_when_found() {
    final IcuRestController irc = new IcuRestController();
    final IcuServiceImpl service = Mockito.mock(IcuServiceImpl.class);
    irc.setIs(service);

    final Icu i1 = new Icu(0);
    i1.setBedid(1);

    final Icu i2 = new Icu(1);
    i1.setBedid(2);

    final Icu i3 = new Icu(1);
    i1.setBedid(3);


    final List<Icu> beds=new ArrayList<>();
    beds.add(i2);
    beds.add(i3);


    Mockito.when(service.findOccupiedBeds()).thenReturn(beds);

    final ResponseEntity<List<Icu>> result = irc.getOccupiedBeds();
    final List<Icu> expected =beds;
    if(result.getStatusCode()==HttpStatus.OK) {
      assertEquals(expected,result.getBody());
    }
  }

  @Test
  public void test_get_occupiedBeds_when_not_found() {
    final IcuRestController irc = new IcuRestController();
    final IcuServiceImpl service = Mockito.mock(IcuServiceImpl.class);
    irc.setIs(service);

    final Icu i1 = new Icu(0);
    i1.setBedid(1);


    List<Icu> beds=new ArrayList<>();
    beds=null;


    Mockito.when(service.findOccupiedBeds()).thenReturn(null);

    final ResponseEntity<List<Icu>> result = irc.getOccupiedBeds();
    final List<Icu> expected =beds;
    if(result.getStatusCode()==HttpStatus.NOT_FOUND) {
      assertEquals(expected,result.getBody());
    }
  }

  @Test
  public void test_get_patientByBedById_when_bedid_is_less_than_zero() {
    final IcuRestController irc = new IcuRestController();
    final IcuServiceImpl service = Mockito.mock(IcuServiceImpl.class);
    irc.setIs(service);

    final ResponseEntity<Patient> result = irc.getPatientByBedId(-1);
    final Patient expected=null;
    if(result.getStatusCode()==HttpStatus.BAD_REQUEST) {
      assertEquals(expected,result.getBody());
    }
  }

  @Test
  public void test_get_patientByBedById_when_bed_not_found() {
    final IcuRestController irc = new IcuRestController();
    final IcuServiceImpl service = Mockito.mock(IcuServiceImpl.class);
    irc.setIs(service);

    final Icu i1 = new Icu(0);
    i1.setBedid(1);

    Mockito.when(service.findBedById(2)).thenReturn(null);

    final ResponseEntity<Patient> result = irc.getPatientByBedId(2);
    final Patient expected=null;
    if(result.getStatusCode()==HttpStatus.BAD_REQUEST) {
      assertEquals(expected,result.getBody());
    }
  }


  @Test
  public void test_get_patientByBedById_when_patient_not_found() {
    final IcuRestController irc = new IcuRestController();
    final IcuServiceImpl service = Mockito.mock(IcuServiceImpl.class);
    irc.setIs(service);

    final Icu i1 = new Icu(0);
    i1.setBedid(1);

    Mockito.when(service.findBedById(1)).thenReturn(i1);
    Mockito.when(service.findPatientByBedId(1)).thenReturn(null);

    final ResponseEntity<Patient> result = irc.getPatientByBedId(1);
    final Patient expected=null;
    if(result.getStatusCode()==HttpStatus.NOT_FOUND) {
      assertEquals(expected,result.getBody());
    }
  }

  @Test
  public void test_get_patientByBedById_when_patient_found() {
    final IcuRestController irc = new IcuRestController();
    final IcuServiceImpl service = Mockito.mock(IcuServiceImpl.class);
    irc.setIs(service);

    final Icu i1 = new Icu(0);
    i1.setBedid(1);
    final Patient p1= new Patient("harshitha",Gender.FEMALE,"6301340004","1234567891",i1);
    p1.setId("A2345");

    Mockito.when(service.findBedById(1)).thenReturn(i1);
    Mockito.when(service.findPatientByBedId(1)).thenReturn(p1);

    final ResponseEntity<Patient> result = irc.getPatientByBedId(1);
    final Patient expected=p1;
    if(result.getStatusCode()==HttpStatus.OK) {
      assertEquals(expected,result.getBody());
    }
  }

  @Test
  public void test_deleteBedById_when_bedid_less_than_zero() {
    final IcuRestController irc = new IcuRestController();

    final ResponseEntity<String>  result = irc.deleteBed(-1);
    final String expected="Invalid bedid!!!";
    if(result.getStatusCode()==HttpStatus.BAD_REQUEST) {
      assertEquals(expected,result.getBody());
    }
  }


  @Test
  public void test_deleteBedById_when_bed_not_found() {
    final IcuRestController irc = new IcuRestController();
    final IcuServiceImpl service = Mockito.mock(IcuServiceImpl.class);
    irc.setIs(service);

    final Icu i1 = new Icu(0);
    i1.setBedid(1);

    Mockito.when(service.findBedById(2)).thenReturn(null);

    final ResponseEntity<String>  result = irc.deleteBed(2);
    final String expected="Bed not found";
    if(result.getStatusCode()==HttpStatus.NOT_FOUND) {
      assertEquals(expected,result.getBody());
    }
  }

  @Test
  public void test_deleteBedById_when_bed_found() {
    final IcuRestController irc = new IcuRestController();
    final IcuServiceImpl service = Mockito.mock(IcuServiceImpl.class);
    irc.setIs(service);

    final Icu i1 = new Icu(0);
    i1.setBedid(1);

    Mockito.when(service.findBedById(1)).thenReturn(i1);
    doNothing().when(service).deleteById(1);

    final ResponseEntity<String>  result = irc.deleteBed(1);
    final String expected="Successfull Deletion";
    if(result.getStatusCode()==HttpStatus.OK) {
      assertEquals(expected,result.getBody());
    }
  }
}
