/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy2.springalertingsystem.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.mockito.Mockito;
import com.philips.casestudy2.springalertingsystem.dal.PatientDAO;
import com.philips.casestudy2.springalertingsystem.domain.Gender;
import com.philips.casestudy2.springalertingsystem.domain.Icu;
import com.philips.casestudy2.springalertingsystem.domain.Patient;
public class TestPatientServiceImpl {


  @Test
  public void test_get_allpatients() {
    final Icu i1 = new Icu(0);
    i1.setBedid(1);
    final Patient p1= new Patient("harshitha",Gender.FEMALE,"6301340004","1234567891",i1);
    p1.setId("A2345");
    final Icu i2 = new Icu(0);
    i1.setBedid(2);
    final Patient p2= new Patient("mona",Gender.FEMALE,"6301340004","5674532345",i2);
    p2.setId("A2346");
    final List<Patient> patients=new ArrayList<>();
    patients.add(p1);
    patients.add(p2);

    final PatientServiceImpl service = new PatientServiceImpl();
    final PatientDAO mockDAO = Mockito.mock(PatientDAO.class);

    Mockito.when(mockDAO.findAll()).thenReturn(patients);

    service.setPd(mockDAO);

    final List<Patient> listOfPatients = service.getAllPatients();

    assertEquals(listOfPatients.size(),patients.size());
  }

  @Test
  public void test_get_patientById_when() {
    final Icu i1 = new Icu(0);
    i1.setBedid(1);
    final Patient p1= new Patient("harshitha",Gender.FEMALE,"6301340004","1234567891",i1);
    p1.setId("A2345");

    final PatientServiceImpl service = new PatientServiceImpl();
    final PatientDAO mockDAO = Mockito.mock(PatientDAO.class);

    Mockito.when(mockDAO.findById("A2345")).thenReturn(p1);

    service.setPd(mockDAO);
    final Patient p = service.findPatientById("A2345");
    assertEquals(p, p1);

  }

  @Test
  public void test_add_new_patient() {
    final Icu i1 = new Icu(0);
    i1.setBedid(1);
    final Patient p1= new Patient("harshitha",Gender.FEMALE,"6301340004","1234567891",i1);
    final Patient p2= new Patient("harshitha",Gender.FEMALE,"6301340004","1234567891",i1);
    p2.setId("A2345");

    final PatientServiceImpl service = new PatientServiceImpl();
    final PatientDAO mockDAO = Mockito.mock(PatientDAO.class);

    Mockito.when(mockDAO.save(p1.getIcu().getBedid(), p1)).thenReturn(p2.getId());
    service.setPd(mockDAO);

    final String id = service.addNewPatient(p1.getIcu().getBedid(), p1);
    assertEquals(id,p2.getId());

  }

  @Test
  public void test_delete_by_id() {
    final Icu i1 = new Icu(0);
    i1.setBedid(1);
    final Patient p1= new Patient("harshitha",Gender.FEMALE,"6301340004","1234567891",i1);
    p1.setId("A2345");
    final Icu i2 = new Icu(0);
    i1.setBedid(2);
    final Patient p2= new Patient("mona",Gender.FEMALE,"6301340004","5674532345",i2);
    p2.setId("A2346");
    final List<Patient> patients=new ArrayList<>();
    patients.add(p1);
    patients.add(p2);

    final PatientServiceImpl service = new PatientServiceImpl();
    final PatientDAO mockDAO = Mockito.mock(PatientDAO.class);

    doNothing().when(mockDAO).deleteById("A2345");
    service.setPd(mockDAO);

    patients.remove(p1);

    service.deleteById("A2345");
    assertEquals(patients.size(),1);

  }

  @Test
  public void test_get_bed_of_patient() {
    final Icu i1 = new Icu(0);
    i1.setBedid(1);
    final Patient p1= new Patient("harshitha",Gender.FEMALE,"6301340004","1234567891",i1);
    p1.setId("A2345");

    final PatientServiceImpl service = new PatientServiceImpl();
    final PatientDAO mockDAO = Mockito.mock(PatientDAO.class);

    Mockito.when(mockDAO.findBedOfPatient("A2345")).thenReturn(1);
    service.setPd(mockDAO);

    final int id = service.findBedOfPatient("A2345");
    assertEquals(id, i1.getBedid());

  }

  @Test
  public void test_check_patient_existence() {
    final Icu i1 = new Icu(0);
    i1.setBedid(1);
    final Patient p1= new Patient("harshitha",Gender.FEMALE,"6301340004","1234567891",i1);
    p1.setId("A2345");

    final PatientServiceImpl service = new PatientServiceImpl();
    final PatientDAO mockDAO = Mockito.mock(PatientDAO.class);

    Mockito.when(mockDAO.checkPatientExistence("1234567891")).thenReturn(p1);
    service.setPd(mockDAO);

    final Patient p = service.checkPatientExistence("1234567891");
    assertEquals(p, p1);
  }

}
