/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy2.springalertingsystem.service;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestOxygenConcentration {
  VitalCheckServiceImpl test;



  @Test
  public void testOxygenForNormalLevelLowerBound1() {


    final int result = test.oxygenCheck(91);
    assertEquals(0,result);
  }

  @Test
  public void testOxygenForNormalLevelLowerBound2() {

    final int result = test.oxygenCheck(92);
    assertEquals(0,result);
  }

  @Test
  public void testOxygenForNormalLevelUpperBound1() {


    final int result = test.oxygenCheck(99);
    assertEquals(0,result);
  }

  @Test
  public void testOxygenForNormalLevelUpperBound2() {


    final int result = test.oxygenCheck(100);
    assertEquals(0,result);
  }



  @Test
  public void testOxygenForLowLevelLowerBound1() {

    final int result = test.oxygenCheck(69);
    assertEquals(1,result);
  }

  @Test
  public void testOxygenForLowLevelLowerBound2() {

    final int result = test.oxygenCheck(70);
    assertEquals(1,result);
  }

  @Test
  public void testOxygenForLowLevelLowerBound3() {


    final int result = test.oxygenCheck(71);
    assertEquals(1,result);
  }

  @Test
  public void testOxygenForLowLevelUpperBound1() {


    final int result = test.oxygenCheck(89);
    assertEquals(1,result);
  }

  @Test
  public void testOxygenForLowLevelUpperBound2() {


    final int result = test.oxygenCheck(90);
    assertEquals(1,result);
  }


}
