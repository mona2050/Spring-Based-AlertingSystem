/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy2.springalertingsystem;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import com.philips.casestudy2.springalertingsystem.controller.TestMonitoringController;
import com.philips.casestudy2.springalertingsystem.controller.TestPatientRestController;
import com.philips.casestudy2.springalertingsystem.service.TestAlertRaisingService;
import com.philips.casestudy2.springalertingsystem.service.TestIcuServiceImpl;
import com.philips.casestudy2.springalertingsystem.service.TestOxygenConcentration;
import com.philips.casestudy2.springalertingsystem.service.TestPatientIdSimulatorService;
import com.philips.casestudy2.springalertingsystem.service.TestPatientServiceImpl;
import com.philips.casestudy2.springalertingsystem.service.TestPulseRate;
import com.philips.casestudy2.springalertingsystem.service.TestTemprature;
import com.philips.casestudy2.springalertingsystem.service.TestValidatingModule;

@RunWith(Suite.class)
@Suite.SuiteClasses({TestIcuServiceImpl.class,TestMonitoringController.class,TestPatientRestController.class,com.philips.casestudy2.springalertingsystem.controller.TestIcuRestController.class,TestAlertRaisingService.class,TestPatientIdSimulatorService.class,TestPatientServiceImpl.class,TestOxygenConcentration.class,TestPulseRate.class,TestTemprature.class,TestValidatingModule.class})
public class TestSuite{

}
