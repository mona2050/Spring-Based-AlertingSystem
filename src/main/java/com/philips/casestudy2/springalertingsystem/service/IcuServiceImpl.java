/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy2.springalertingsystem.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.philips.casestudy2.springalertingsystem.dal.IcuDAO;
import com.philips.casestudy2.springalertingsystem.domain.Icu;
import com.philips.casestudy2.springalertingsystem.domain.Patient;
@Service
public class IcuServiceImpl implements IcuService {

  IcuDAO ida;

  @Autowired
  public void setIda(IcuDAO ida) {
    this.ida = ida;
  }


  @Override
  public int addNewBed(Icu tobesaved)
  {
    final Icu saved = ida.save(tobesaved);
    return saved.getBedid();
  }


  @Override
  public List<Icu> findAll() {
    return ida.findAll();
  }


  @Override
  public Icu findBedById(int bedid) {
    return ida.findById(bedid);
  }


  @Override
  public Patient findPatientByBedId(int bedid) {
    return ida.findPatientByBedId(bedid);
  }


  @Override
  public List<Icu> findVacantBeds() {
    return ida.findVacantBeds();
  }


  @Override
  public List<Icu> findOccupiedBeds() {
    return ida.findOccupiedBeds();
  }

}
