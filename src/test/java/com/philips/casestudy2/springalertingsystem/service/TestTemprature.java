/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy2.springalertingsystem.service;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestTemprature {
  VitalCheckServiceImpl test = new VitalCheckServiceImpl() ;

  @Test
  public void testTempratureNormal1() {
    final int result = test.temperatureCheck(97.00);
    assertEquals(0,result);
  }


  @Test
  public void testTempratureNormal2() {
    final int result = test.temperatureCheck(97.01);
    assertEquals(0,result);
  }

  @Test
  public void testTempratureNormal3() {
    final int result = test.temperatureCheck(98.99);
    assertEquals(0,result);
  }

  @Test
  public void testTempratureNormal4() {
    final int result = test.temperatureCheck(99.00);
    assertEquals(0,result);
  }

  @Test
  public void testTempratureLow1() {
    final int result = test.temperatureCheck(92.99);
    assertEquals(1,result);
  }

  @Test
  public void testTempratureLow2() {
    final int result = test.temperatureCheck(93.00);
    assertEquals(1,result);
  }

  @Test
  public void testTempratureLow3() {
    final int result = test.temperatureCheck(93.01);
    assertEquals(1,result);
  }

  @Test
  public void testTempratureLow4() {
    final int result = test.temperatureCheck(96.98);
    assertEquals(1,result);
  }

  @Test
  public void testTempratureLow5() {



    final int result = test.temperatureCheck(96.99);
    assertEquals(1,result);
  }

  @Test
  public void testTempratureHigh1() {
    final int result = test.temperatureCheck(99.01);
    assertEquals(1,result);
  }

  @Test
  public void testTempratureHigh2() {
    final int result = test.temperatureCheck(99.02);
    assertEquals(1,result);
  }

  @Test
  public void testTempratureHigh3() {
    final int result = test.temperatureCheck(107.98);
    assertEquals(1,result);
  }

  @Test
  public void testTempratureHigh4() {
    final int result = test.temperatureCheck(107.99);
    assertEquals(1,result);
  }

  @Test
  public void testTempratureHigh5() {
    final int result = test.temperatureCheck(108.00);
    assertEquals(1,result);
  }

}
