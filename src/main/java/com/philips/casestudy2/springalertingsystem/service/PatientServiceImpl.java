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
  public String addNewPatient(int id, Patient patient) {
    final Patient saved = pd.save(id, patient);
    return saved.getId();

  }


  @Override
  public List<Patient> findAll() {
    return pd.findAll();
  }


  @Override
  public Patient findById(String id) {
    return pd.findById(id);
  }


  @Override
  public void deleteById(String id) {
    pd.deleteById(id);
  }






}
