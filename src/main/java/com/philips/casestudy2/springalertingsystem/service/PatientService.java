/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy2.springalertingsystem.service;

import java.util.List;
import com.philips.casestudy2.springalertingsystem.domain.Patient;


public interface PatientService {

  String addNewPatient(int id, Patient patient);

  void deleteById(String id);

  int findBedOfPatient(String id);

  List<Patient> getAllPatients();


  Patient findPatientById(String id);

  Patient checkPatientExistence(String adhaarno);






}
