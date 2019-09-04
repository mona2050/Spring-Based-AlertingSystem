/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy2.springalertingsystem.service;

import java.util.List;
import com.philips.casestudy2.springalertingsystem.domain.PatientVitals;

public interface VitalCheckService {

  List<String> checkAllVitals(PatientVitals[] sample);

  int pulseRateCheck(int pulseRate);

  int temperatureCheck(double tempratureF);

  int oxygenCheck(int oxygenConcentration);

}
