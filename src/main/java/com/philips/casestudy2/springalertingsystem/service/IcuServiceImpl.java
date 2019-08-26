/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy2.springalertingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.philips.casestudy2.springalertingsystem.dal.IcuDAO;
import com.philips.casestudy2.springalertingsystem.domain.Icu;
@Service
public class IcuServiceImpl implements IcuService {

  IcuDAO ida;

  @Autowired
  public void setIda(IcuDAO ida) {
    this.ida = ida;
  }


  @Override
  public int addNewBed(Icu toBeSaved)
  {
    final Icu saved = ida.save(toBeSaved);
    return saved.getBedid();
  }

}
