/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy2.springalertingsystem.service;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import com.philips.casestudy2.springalertingsystem.service.AlertRaisingServiceImpl.Result;

public class TestAlertRaisingService {
  AlertRaisingServiceImpl test = new AlertRaisingServiceImpl();

  @Test
  public void test_alertingFunc_when_1_is_passed() throws Exception  {

    assertEquals(Result.ALERT,test.alertingFunc(1));
  }

  @Test
  public void test_alertingFunc_when_0_is_passed()throws Exception {
    assertEquals(Result.NOALERT,test.alertingFunc(0));
  }

}
