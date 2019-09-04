/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy2.springalertingsystem.service;

import com.philips.casestudy2.springalertingsystem.domain.PatientVitals;

public interface VitalValidationServiceForErrors {

  String validateVitalsData(PatientVitals[] sample);

}
