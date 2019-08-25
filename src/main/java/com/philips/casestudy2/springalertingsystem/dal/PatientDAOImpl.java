/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy2.springalertingsystem.dal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.philips.casestudy2.springalertingsystem.domain.Icu;
import com.philips.casestudy2.springalertingsystem.domain.Patient;
@Transactional
@Repository
public class PatientDAOImpl implements PatientDAO {
  @PersistenceContext
  EntityManager em;

  @Override
  public Patient save(int id, Patient patient) {

    final Icu bed = em.find(Icu.class, id);
    patient.setIcu(bed);
    em.persist(patient);
    bed.setOccupancy(1);
    return patient;
  }

}
