/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy2.springalertingsystem.dal;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import com.philips.casestudy2.springalertingsystem.domain.Gender;
import com.philips.casestudy2.springalertingsystem.domain.Icu;
import com.philips.casestudy2.springalertingsystem.domain.Patient;

public class TestPatientDAOImpl {


  @Test
  public void test_find_all() {
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

    final PatientDAOImpl pd = new PatientDAOImpl();


    final List<Patient> ListOfPatients = pd.findAll();

    assertEquals(patients.size(),ListOfPatients.size());


  }

}
