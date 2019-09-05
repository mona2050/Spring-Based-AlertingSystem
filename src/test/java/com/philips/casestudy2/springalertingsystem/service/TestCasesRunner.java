/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy2.springalertingsystem.service;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestCasesRunner {


  public static void main(String[] args) {
    final Result result = JUnitCore.runClasses(TestSuite.class);
    for (final Failure failure : result.getFailures()) {
      System.out.println(failure.toString());
    }
    System.out.println("RESULT=="+result.wasSuccessful());
    System.out.println("All TEST CASES PASSED");
    System.out.println("**************************************");

  }
}
