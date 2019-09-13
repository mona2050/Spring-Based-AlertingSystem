/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy2.springalertingsystem.service;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.mockito.Mockito;
import com.philips.casestudy2.springalertingsystem.dal.IcuDAO;
import com.philips.casestudy2.springalertingsystem.domain.Gender;
import com.philips.casestudy2.springalertingsystem.domain.Icu;
import com.philips.casestudy2.springalertingsystem.domain.Patient;

public class TestIcuServiceImpl {

  @Test
  public void test_add_new_bed() {
    final IcuServiceImpl impl = new IcuServiceImpl();
    final IcuDAO mockdao = Mockito.mock(IcuDAO.class);

    final Icu i1 = new Icu(0);
    i1.setBedid(1);

    final Icu i2 = new Icu(0);


    Mockito.when(mockdao.save(i2)).thenReturn(i1);
    impl.setIda(mockdao);

    final int id = impl.addNewBed(i2);
    assertEquals(id,i1.getBedid());

  }

  @Test
  public void test_find_all() {
    final IcuServiceImpl impl = new IcuServiceImpl();
    final IcuDAO mockdao = Mockito.mock(IcuDAO.class);

    final Icu i1 = new Icu(0);
    i1.setBedid(1);

    final Icu i2 = new Icu(0);
    i2.setBedid(2);

    final List<Icu> beds=new ArrayList<>();
    beds.add(i1);
    beds.add(i2);

    Mockito.when(mockdao.findAll()).thenReturn(beds);
    impl.setIda(mockdao);

    final List<Icu> result = impl.findAllBed();
    assertEquals(beds,result);

  }

  @Test
  public void test_find_bed_by_id() {
    final IcuServiceImpl impl = new IcuServiceImpl();
    final IcuDAO mockdao = Mockito.mock(IcuDAO.class);

    final Icu i1 = new Icu(0);
    i1.setBedid(1);



    Mockito.when(mockdao.findById(i1.getBedid())).thenReturn(i1);
    impl.setIda(mockdao);

    final Icu icu = impl.findBedById(i1.getBedid());
    assertEquals(i1,icu);

  }

  @Test
  public void test_find_Occupiedbeds() {
    final IcuServiceImpl impl = new IcuServiceImpl();
    final IcuDAO mockdao = Mockito.mock(IcuDAO.class);

    final Icu i1 = new Icu(0);
    i1.setBedid(1);

    final Icu i2 = new Icu(1);
    i1.setBedid(2);

    final Icu i3 = new Icu(1);
    i1.setBedid(3);


    final List<Icu> beds=new ArrayList<>();
    beds.add(i2);
    beds.add(i3);



    Mockito.when(mockdao.findOccupiedBeds()).thenReturn(beds);
    impl.setIda(mockdao);

    final List<Icu> icu = impl.findOccupiedBeds();
    assertEquals(beds,icu);

  }


  @Test
  public void test_find_vacantbeds() {
    final IcuServiceImpl impl = new IcuServiceImpl();
    final IcuDAO mockdao = Mockito.mock(IcuDAO.class);

    final Icu i1 = new Icu(0);
    i1.setBedid(1);

    final List<Icu> beds=new ArrayList<>();
    beds.add(i1);



    Mockito.when(mockdao.findVacantBeds()).thenReturn(beds);
    impl.setIda(mockdao);

    final List<Icu> icu = impl.findVacantBeds();
    assertEquals(beds,icu);

  }

  @Test
  public void test_get_patient_by_bedid_if_bed_is_null(){
    final IcuServiceImpl impl = new IcuServiceImpl();

    final IcuDAO mockdao = Mockito.mock(IcuDAO.class);

    Mockito.when(mockdao.findById(1)).thenReturn(null);
    impl.setIda(mockdao);

    final Patient p = impl.findPatientByBedId(1);
    assertEquals(null,p);

  }

  @Test
  public void test_get_patient_by_bedid_if_bed_is_existing_but_unoccupied(){
    final IcuServiceImpl impl = new IcuServiceImpl();

    final IcuDAO mockdao = Mockito.mock(IcuDAO.class);

    final Icu i1 = new Icu(0);
    i1.setBedid(1);

    Mockito.when(mockdao.findById(1)).thenReturn(i1);
    Mockito.when(mockdao.getOccupancy(1)).thenReturn(0);

    impl.setIda(mockdao);

    final Patient p = impl.findPatientByBedId(1);
    assertEquals(null,p);

  }

  @Test
  public void test_get_patient_by_bedid_if_bed_is_existing_but_occupied(){
    final IcuServiceImpl impl = new IcuServiceImpl();

    final IcuDAO mockdao = Mockito.mock(IcuDAO.class);

    final Icu i1 = new Icu(1);
    i1.setBedid(1);

    final Patient p1= new Patient("harshitha",Gender.FEMALE,"6301340004","1234567891",i1);
    p1.setId("A2345");

    Mockito.when(mockdao.findById(1)).thenReturn(i1);
    Mockito.when(mockdao.getOccupancy(1)).thenReturn(1);
    Mockito.when(mockdao.findPatientByBedId(1)).thenReturn(p1);

    impl.setIda(mockdao);

    final Patient p = impl.findPatientByBedId(1);
    assertEquals(p1,p);

  }

  @Test
  public void get_occupancy_when_bed_doesnot_exist() {
    final IcuServiceImpl impl = new IcuServiceImpl();

    final IcuDAO mockdao = Mockito.mock(IcuDAO.class);

    final Icu i1 = new Icu(0);
    i1.setBedid(1);

    Mockito.when(mockdao.findById(2)).thenReturn(null);
    Mockito.when(mockdao.getOccupancy(2)).thenReturn(0);

    impl.setIda(mockdao);

    final int response =  impl.getOccupancy(2);
    assertEquals(0,response);

  }

  @Test
  public void get_occupancy_when_bed_exist() {
    final IcuServiceImpl impl = new IcuServiceImpl();

    final IcuDAO mockdao = Mockito.mock(IcuDAO.class);

    final Icu i1 = new Icu(0);
    i1.setBedid(1);

    Mockito.when(mockdao.findById(i1.getBedid())).thenReturn(i1);
    Mockito.when(mockdao.getOccupancy(1)).thenReturn(0);

    impl.setIda(mockdao);

    final int response =  impl.getOccupancy(1);
    assertEquals(0,response);

  }

  @Test
  public void test_get_count_of_beds() {
    final IcuServiceImpl impl = new IcuServiceImpl();
    final IcuDAO mockdao = Mockito.mock(IcuDAO.class);

    final Icu i1 = new Icu(0);
    i1.setBedid(1);

    final Icu i2 = new Icu(0);
    i2.setBedid(2);

    final List<Icu> beds=new ArrayList<>();
    beds.add(i1);
    beds.add(i2);
    final long count = beds.size();

    Mockito.when(mockdao.getCountOfBeds()).thenReturn((long) 2);
    impl.setIda(mockdao);

    final long result = impl.getCountOfBeds();
    assertEquals(count, result);
  }




}
