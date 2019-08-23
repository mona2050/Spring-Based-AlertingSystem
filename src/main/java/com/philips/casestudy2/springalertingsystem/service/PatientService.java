/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy2.springalertingsystem.service;

import com.philips.casestudy2.springalertingsystem.domain.Patient;


public interface PatientService {

  String addNewPatient(int id, Patient patient);

}
