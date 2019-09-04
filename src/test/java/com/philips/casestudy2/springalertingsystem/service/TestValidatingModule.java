/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy2.springalertingsystem.service;


import static org.junit.Assert.assertEquals;
import org.junit.Test;



public class TestValidatingModule {

  VitalValidationServiceForErrorsImpl ValidatingService;

  //  @Test
  //  public void testCheckparams()
  //  {
  //    assertEquals(true,ValidatingService.validateVitalsData(-1));
  //    assertEquals(true,ValidatingaService.validateVitalsData(0));
  //    assertEquals(false,ValidatingService.validateVitalsData(1));
  //  }

  @Test
  public void testCheckSPO2()
  {
    assertEquals(false,ValidatingService.checkSPO2(69));
    assertEquals(true,ValidatingService.checkSPO2(70));
    assertEquals(true,ValidatingService.checkSPO2(71));
    assertEquals(true,ValidatingService.checkSPO2(99));
    assertEquals(true,ValidatingService.checkSPO2(100));
    assertEquals(false,ValidatingService.checkSPO2(101));

  }

  @Test
  public void testCheckTemp()
  {
    assertEquals(false,ValidatingService.checkTemp(92));
    assertEquals(true,ValidatingService.checkTemp(93));
    assertEquals(true,ValidatingService.checkTemp(94));
    assertEquals(true,ValidatingService.checkTemp(112));
    assertEquals(true,ValidatingService.checkTemp(113));
    assertEquals(false,ValidatingService.checkTemp(114));

  }


  @Test
  public void testCheckPulseRate()
  {
    assertEquals(false,ValidatingService.checkPulseRate(29));
    assertEquals(true,ValidatingService.checkPulseRate(30));
    assertEquals(true,ValidatingService.checkPulseRate(31));
    assertEquals(true,ValidatingService.checkPulseRate(253));
    assertEquals(true,ValidatingService.checkPulseRate(254));
    assertEquals(false,ValidatingService.checkPulseRate(255));

  }

}
