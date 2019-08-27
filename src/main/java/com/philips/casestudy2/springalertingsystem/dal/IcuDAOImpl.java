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
public class IcuDAOImpl implements IcuDAO {

  @PersistenceContext
  EntityManager em;

  @Override
  public Icu save(Icu bed) {

    em.persist(bed);

    return bed;
  }

  @Override
  public List<Icu> findAll() {
    return em.createQuery("select b from Icu as b").getResultList();
  }

  @Override
  public Icu findById(int bedid) {
    return em.find(Icu.class, bedid);
  }

  @Override
  public Patient findPatientByBedId(int bedid) {
    return   (Patient) em.createQuery("select i.patient from Icu as i").getSingleResult();
  }

  @Override
  public List<Icu> findVacantBeds() {
    return em.createQuery("select i from Icu as i where occupancy=0").getResultList();
  }

  @Override
  public List<Icu> findOccupiedBeds() {
    return em.createQuery("select i from Icu as i where occupancy=1").getResultList();
  }
}


