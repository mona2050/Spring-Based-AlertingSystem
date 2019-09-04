/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy2.springalertingsystem.service;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestPulseRate {

  VitalCheckServiceImpl test;

  @Test
  public void testPulseRateNormal1() {



    final int result = test.pulseRateCheck(50);
    assertEquals(0,result);
  }

  @Test
  public void testPulseRateNormal2() {



    final int result = test.pulseRateCheck(51);
    assertEquals(0,result);
  }

  @Test
  public void testPulseRateNormal3() {



    final int result = test.pulseRateCheck(99);
    assertEquals(0,result);
  }

  @Test
  public void testPulseRateNormal4() {



    final int result = test.pulseRateCheck(100);
    assertEquals(0,result);
  }

  @Test
  public void testPulseRateLow1() {



    final int result = test.pulseRateCheck(29);
    assertEquals(1,result);
  }

  @Test
  public void testPulseRateLow2() {



    final int result = test.pulseRateCheck(30);
    assertEquals(1,result);
  }

  @Test
  public void testPulseRateLow3() {



    final int result = test.pulseRateCheck(31);
    assertEquals(1,result);
  }

  @Test
  public void testPulseRateLow4() {



    final int result = test.pulseRateCheck(48);
    assertEquals(1,result);
  }

  @Test
  public void testPulseRateLow5() {



    final int result = test.pulseRateCheck(49);
    assertEquals(1,result);
  }

  @Test
  public void testPulseRateHigh1() {



    final int result = test.pulseRateCheck(101);
    assertEquals(1,result);
  }

  @Test
  public void testPulseRateHigh2() {



    final int result = test.pulseRateCheck(102);
    assertEquals(1,result);
  }

  @Test
  public void testPulseRateHigh3() {



    final int result = test.pulseRateCheck(253);
    assertEquals(1,result);
  }

  @Test
  public void testPulseRateHigh4() {



    final int result = test.pulseRateCheck(254);
    assertEquals(1,result);
  }

  @Test
  public void testPulseRateHigh5() {



    final int result = test.pulseRateCheck(255);
    assertEquals(1,result);
  }


}
