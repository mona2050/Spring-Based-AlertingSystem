/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy2.springalertingsystem.service;

import static org.junit.Assert.assertEquals;
import org.junit.Assert;
import org.junit.Test;
import com.philips.casestudy2.springalertingsystem.domain.PatientVitals;

public class TestPatientMonitorSimulatorServiceImpl {

  PatientMonitorSimulatorServiceImpl test = new PatientMonitorSimulatorServiceImpl();



  @Test
  public void test_get_details_for_successfull_return() {
    final PatientVitals[] arr1 = {new PatientVitals("DWSFG566","70","90","99.5")};

    final PatientVitals[] arr2= {new PatientVitals("DWSFG566","70","90","99.5")};


    Assert.assertEquals(arr1.length,arr2.length);
    for (int i = 0; i < arr1.length; i++) {
      assertEquals(arr1[i], arr2[i]);
    }
  }
}

