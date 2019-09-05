/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy2.springalertingsystem.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.philips.casestudy2.springalertingsystem.dal.PatientDAO;
import com.philips.casestudy2.springalertingsystem.domain.Patient;

@Service
public class PatientServiceImpl implements PatientService {

  PatientDAO pd;

  @Autowired
  public void setPd(PatientDAO pd) {
    this.pd = pd;

  }


  @Override
  public  String  addNewPatient(int id, Patient patient) {
    final String savedId = pd.save(id, patient);
    return savedId;

  }



  @Override
  public List<Patient> getAllPatients() {
    return pd.findAll();

  }


  @Override
  public Patient findPatientById(String id) {
    return pd.findById(id);

  }


  @Override
  public void deleteById(String id) {
    pd.deleteById(id);
  }


  @Override
  public int findBedOfPatient(String id) {
    return pd.findBedOfPatient(id);
  }


  @Override
  public Patient checkPatientExistence(String adhaarno) {
    final Patient p = pd.checkPatientExistence(adhaarno);
    if(p!=null) {
      return p;
    } else {
      return null;
    }
  }
}


