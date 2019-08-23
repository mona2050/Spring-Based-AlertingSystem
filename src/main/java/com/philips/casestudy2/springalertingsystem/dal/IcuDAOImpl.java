/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy2.springalertingsystem.dal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.philips.casestudy2.springalertingsystem.domain.Icu;

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
}


