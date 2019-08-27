/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy2.springalertingsystem.dal;

import java.util.List;
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

  @Override
  public List<Patient> findAll() {

    final List<Patient> p = em.createQuery("select p from Patient p").getResultList();
    return p;
  }

  @Override
  public Patient findById(String id) {
    return em.find(Patient.class, id);
  }

  @Override
  public void deleteById(String id) {
    final int bed_id = (int) em.createQuery("select p.icu.bedid from Patient p where p.id=:id").setParameter("id", id).getSingleResult();
    final Icu bed = em.find(Icu.class, bed_id);
    bed.setOccupancy(0);
    em.createQuery("delete from Patient p  where p.id=:id")
    .setParameter("id", id)
    .executeUpdate();

  }




}
