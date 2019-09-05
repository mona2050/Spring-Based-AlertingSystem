/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy2.springalertingsystem.dal;

import java.util.List;
import com.philips.casestudy2.springalertingsystem.domain.Patient;


public interface PatientDAO {

  String save(int id, Patient patient);

  List<Patient> findAll();

  Patient findById(String id);

  void deleteById(String id);

  int findBedOfPatient(String id);

  Patient checkPatientExistence(String adhaarno);







}
