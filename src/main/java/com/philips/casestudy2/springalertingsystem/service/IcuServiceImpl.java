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
  public long getCountOfBeds() {
    return ida.getCountOfBeds();
  }


  @Override
  public int addNewBed(Icu toBeSaved)
  {
    final Icu saved = ida.save(toBeSaved);
    return saved.getBedid();
  }


  @Override
  public List<Icu> findAllBed() {
    return ida.findAll();
  }


  @Override
  public Icu findBedById(int bedid) {
    return ida.findById(bedid);
  }

  @Override
  public int getOccupancy(int bedid) {
    final Icu bed = ida.findById(bedid);
    if(bed!=null) {
      return ida.getOccupancy(bedid);} else {
        return 0;
      }
  }


  @Override
  public Patient findPatientByBedId(int bedid) {
    final Icu bed = ida.findById(bedid);
    if(bed!=null) {
      final int occupancy=bed.getOccupancy();
      if(occupancy==1) {
        return ida.findPatientByBedId(bedid);} else {
          return null;
        }
    } else {
      return null;
    }
  }



  @Override
  public List<Icu> findVacantBeds() {
    return ida.findVacantBeds();
  }


  @Override
  public List<Icu> findOccupiedBeds() {
    return ida.findOccupiedBeds();
  }


  @Override
  public void deleteById(int bedid) {
    ida.deleteBedById(bedid);

  }






}
