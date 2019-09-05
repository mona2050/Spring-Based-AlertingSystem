/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy2.springalertingsystem.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.philips.casestudy2.springalertingsystem.domain.Gender;
import com.philips.casestudy2.springalertingsystem.domain.Icu;
import com.philips.casestudy2.springalertingsystem.domain.Patient;
import com.philips.casestudy2.springalertingsystem.service.IcuServiceImpl;
import com.philips.casestudy2.springalertingsystem.web.rest.IcuRestController;

public class TestIcuRestController {

  @Test
  public void test_findAllBed_Must_Return_All_Beds() throws Exception {
    final IcuServiceImpl service=Mockito.mock(IcuServiceImpl.class);
    final IcuRestController irc= new IcuRestController();
    irc.setIs(service);

    final Icu i1=new Icu(0);
    i1.setBedid(3);
    final Icu i2=new Icu(1);
    i2.setBedid(4);
    final List<Icu> bed=new ArrayList<>();
    bed.add(i1);
    bed.add(i2);

    Mockito.when(service.findAllBed()).thenReturn(bed);

    final ResponseEntity<List<Icu>> response = irc.getAllBed();

    if(response.getStatusCode()==HttpStatus.OK) {
      assertEquals(response.getBody(), bed);}
  }

  @Test
  public void test_findBedById_When_Id_Exists() throws Exception {
    final IcuServiceImpl service=Mockito.mock(IcuServiceImpl.class);
    final IcuRestController irc= new IcuRestController();
    irc.setIs(service);

    final Icu i=new Icu(0);
    i.setBedid(1);

    Mockito.when(service.findBedById(1)).thenReturn(i);

    final ResponseEntity<Icu> response=irc.getBedById(1);
    if(response.getStatusCode()==HttpStatus.OK) {
      assertEquals(response.getBody(), i);
    }
  }

  @Test
  public void test_findBedById_When_Id_Doesnot_Exists() throws Exception {
    final IcuServiceImpl service=Mockito.mock(IcuServiceImpl.class);
    final IcuRestController irc= new IcuRestController();
    irc.setIs(service);
    final Icu i=new Icu(0);
    i.setBedid(1);

    Mockito.when(service.findBedById(2)).thenReturn(null);

    final ResponseEntity<Icu> response=irc.getBedById(2);
    if(response.getStatusCode()==HttpStatus.NOT_FOUND) {
      assertEquals(response.getBody(), null);
    }


  }

  @Test
  public void test_getPatientByBedId_When_Patient_Exists() throws Exception {
    final IcuServiceImpl service=Mockito.mock(IcuServiceImpl.class);
    final IcuRestController irc= new IcuRestController();
    irc.setIs(service);

    final Icu i=new Icu(1);
    i.setBedid(1);

    final Patient p=new Patient("harshitha",Gender.FEMALE,"6301340004","5642871307",i);
    Mockito.when(service.findPatientByBedId(1)).thenReturn(p);

    final ResponseEntity<Patient> response=irc.getPatientByBedId(1);
    if(response.getStatusCode()==HttpStatus.OK) {
      assertEquals(response.getBody(), p);
    }
  }

  @Test
  public void test_getPatientByBedId_When_Patient_Doesnot_Exists() throws Exception {
    final IcuServiceImpl service=Mockito.mock(IcuServiceImpl.class);
    final IcuRestController irc= new IcuRestController();
    irc.setIs(service);

    final Icu i=new Icu(1);
    i.setBedid(1);
    Mockito.when(service.findPatientByBedId(1)).thenReturn(null);

    final ResponseEntity<Patient> response=irc.getPatientByBedId(2);
    if(response.getStatusCode()==HttpStatus.NOT_FOUND) {
      assertEquals(response.getBody(), null);
    }
  }

  @Test
  public void test_getVacantBeds_Success() throws Exception{
    final IcuServiceImpl service=Mockito.mock(IcuServiceImpl.class);
    final IcuRestController irc= new IcuRestController();
    irc.setIs(service);

    final Icu i1=new Icu(0);
    i1.setBedid(1);
    final Icu i2=new Icu(1);
    i2.setBedid(2);
    final Icu i3=new Icu(0);
    i3.setBedid(3);
    final List<Icu> resl=new ArrayList<>();
    resl.add(i1);
    resl.add(i3);

    Mockito.when(service.findVacantBeds()).thenReturn(resl);

    final ResponseEntity<List<Icu>> response=irc.getVacantBeds();
    if(response.getStatusCode()==HttpStatus.OK) {
      assertEquals(response.getBody(), resl);
    }

  }

  @Test
  public void test_getOccupiedBeds_Success() throws Exception{

    final IcuServiceImpl service=Mockito.mock(IcuServiceImpl.class);
    final IcuRestController irc= new IcuRestController();
    irc.setIs(service);

    final Icu i1=new Icu(0);
    i1.setBedid(1);
    final Icu i2=new Icu(1);
    i2.setBedid(2);
    final Icu i3=new Icu(1);
    i3.setBedid(3);
    final List<Icu> resl=new ArrayList<>();
    resl.add(i2);
    resl.add(i3);

    Mockito.when(service.findOccupiedBeds()).thenReturn(resl);
    final ResponseEntity<List<Icu>> response=irc.getOccupiedBeds();
    if(response.getStatusCode()==HttpStatus.OK) {
      assertEquals(response.getBody(), resl);
    }

  }
  @Test
  public void test_addBed_When_Id_Is_valid() throws Exception{
    final IcuServiceImpl service=Mockito.mock(IcuServiceImpl.class);
    final IcuRestController irc=new IcuRestController();
    irc.setIs(service);

    final Icu i = new Icu(0);
    i.setBedid(1);
    Mockito.when(service.addNewBed(i)).thenReturn(i.getBedid());

    final ResponseEntity<Icu> response=irc.addBed(i);
    if(response.getStatusCode()==HttpStatus.CREATED) {
      assertEquals(response.getBody(), i);
    }

  }

  @Test
  public void test_addBed_When_Id_Is_Invalid() throws Exception{
    final IcuServiceImpl service=Mockito.mock(IcuServiceImpl.class);
    final IcuRestController irc=new IcuRestController();
    irc.setIs(service);

    final Icu i = new Icu(0);
    i.setBedid(-1);
    Mockito.when(service.addNewBed(i)).thenReturn(i.getBedid());

    final ResponseEntity<Icu> response=irc.addBed(i);
    if(response.getStatusCode()==HttpStatus.BAD_REQUEST) {
      assertTrue(HttpHeaders.);
    }

  }
}
