/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy2.springalertingsystem.dal;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
  public String save(int id, Patient patient) {


    final Icu bed = em.find(Icu.class, id);
    final Patient p = checkPatientExistence(patient.getAdhaarno());
    if(p==null) {
      if(bed.getOccupancy()==0) {
        patient.setIcu(bed);
        em.persist(patient);
        bed.setOccupancy(1);
        return patient.getId();} else {
          return "0";
        }
    } else {
      return "1";
    }
  }




  @Override
  public List<Patient> findAll() {

    @SuppressWarnings("unchecked")
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

  @Override
  public int findBedOfPatient(String id) {

    try {
      return (int) em.createQuery("select p.icu.bedid from Patient p where p.id=:id").setParameter("id",id).getSingleResult();
    }catch (final NoResultException e) {
      return -1;
    }


  }


  @Override
  public Patient checkPatientExistence(String adhaarno) {
    try {
      final Patient p = (Patient) em.createQuery("select p from Patient p where p.adhaarno=:adhaarno").setParameter("adhaarno", adhaarno).getSingleResult();

      if(p!=null) {
        return p;
      } else {
        return null;
      }
    }catch(final Exception e )
    {
      return null;
    }

  }
}
