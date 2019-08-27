/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy2.springalertingsystem.dal;

import java.util.List;
import com.philips.casestudy2.springalertingsystem.domain.Icu;
import com.philips.casestudy2.springalertingsystem.domain.Patient;


public interface IcuDAO {

  Icu save(Icu bed);

  List<Icu> findAll();

  Icu findById(int bedid);

  Patient findPatientByBedId(int bedid);

  List<Icu> findVacantBeds();

  List<Icu> findOccupiedBeds();
}
