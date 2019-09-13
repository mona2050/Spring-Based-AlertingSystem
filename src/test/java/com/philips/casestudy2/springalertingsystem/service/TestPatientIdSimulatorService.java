/*

 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy2.springalertingsystem.service;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestPatientIdSimulatorService {
  PatientIdSimulatorService test = new PatientIdSimulatorService();

  @Test
  public void test_when_patientid_is_null() {
    final String PatientId=null;
    assertEquals(null,PatientId);
  }

  @Test
  public void test_when_patientid_is_not_null() {
    final String PatientId="DWSFG566";
    assertEquals("DWSFG566",PatientId);
  }


}
