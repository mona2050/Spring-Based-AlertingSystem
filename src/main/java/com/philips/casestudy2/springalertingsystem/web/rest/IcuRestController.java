/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy2.springalertingsystem.web.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.philips.casestudy2.springalertingsystem.domain.Icu;
import com.philips.casestudy2.springalertingsystem.domain.Patient;
import com.philips.casestudy2.springalertingsystem.service.IcuService;

@RestController
@RequestMapping("/hospital")
public class IcuRestController {

  IcuService is;

  @Autowired

  public void setIs(IcuService is) {
    this.is = is;
  }

  @GetMapping(value="/countOfBeds")
  public long getCountOfBeds() {
    return is.getCountOfBeds();
  }



  @PostMapping(value="/bed")
  public ResponseEntity<String> addBed(@RequestBody Icu bed) {
    String message=null;
    final long num = is.getCountOfBeds();
    if(num<10) {
      final int id = is.addNewBed(bed);
      if(id>0) {
        message="Bed successfully created!!!";

      }
      return new ResponseEntity<>(message,HttpStatus.CREATED);
    }
    else
    {
      message="No more beds can be allocated!!!";
      return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
    }
  }


  @GetMapping(value = "/bed")
  public List<Icu> getAllBed(){
    return is.findAllBed();
  }


  @GetMapping(value = "/bed/{bedid}")
  public ResponseEntity<Icu> getById(@PathVariable("bedid") int bedid){
    Icu res;
    if(bedid>0) {
      res= is.findBedById(bedid);
      if(res!=null) {
        return new ResponseEntity<>(res,HttpStatus.OK);
      }
      else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }
    else
    {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping(value = "/patientByBedId/{bedid}")
  public ResponseEntity<Patient> getPatientByBedId(@PathVariable("bedid") int bedid){
    Patient res;
    if(bedid>0) {
      final Icu output= is.findBedById(bedid);
      if(output!=null) {
        res= is.findPatientByBedId(bedid);
        if(res!=null) {
          return new ResponseEntity<>(res,HttpStatus.OK);
        }
        else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
      } else {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
    }
    else
    {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping(value = "/vacantBeds")
  public ResponseEntity<List<Icu>> getVacantBeds(){
    final List<Icu> res= is.findVacantBeds();
    return new ResponseEntity<>(res,HttpStatus.OK);
  }

  @GetMapping(value = "/occupiedBeds")
  public ResponseEntity<List<Icu>> getOccupiedBeds(){
    final List<Icu> res= is.findOccupiedBeds();
    return new ResponseEntity<>(res,HttpStatus.OK);
  }

  @DeleteMapping(value="/bed/{bedid}")
  public ResponseEntity<String> deleteBed(@PathVariable("bedid") int bedid) {
    String message=null;
    if(bedid>0) {
      final Icu bed = is.findBedById(bedid);
      if(bed!=null) {
        is.deleteById(bedid);
        message="Successfull Deletion";
        return new ResponseEntity<>(message,HttpStatus.OK);
      }
      else {
        message="Bed not found";
        return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
      }

    }
    else {
      message = "Invalid bedid!!!";
      return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
    }

  }
}
