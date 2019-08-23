package com.philips.casestudy2.springalertingsystem.dal;

import com.philips.casestudy2.springalertingsystem.domain.Patient;


public interface PatientDAO {

  Patient save(int id, Patient patient);

}
