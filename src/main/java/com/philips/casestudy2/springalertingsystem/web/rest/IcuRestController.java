/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy2.springalertingsystem.web.rest;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.philips.casestudy2.springalertingsystem.domain.Icu;
import com.philips.casestudy2.springalertingsystem.domain.Patient;
import com.philips.casestudy2.springalertingsystem.service.IcuService;

@RestController
public class IcuRestController {

  IcuService is;

  @Autowired
  public void setIs(IcuService is) {
    this.is = is;
  }

  @PostMapping(value="/api/addbed")
  public ResponseEntity<Icu> addingBed(@RequestBody Icu bed) {

    try {
      final int id = is.addNewBed(bed);
      final HttpHeaders headers = new HttpHeaders();
      headers.setLocation(URI.create("/api/addbed/"+id));

      return new ResponseEntity<>(headers,HttpStatus.CREATED);
    }catch(final IllegalArgumentException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

  }

  @GetMapping(value = "/api/findallbed")
  public List<Icu> getAllBed(){
    return is.findAll();
  }


  @GetMapping(value = "/api/getBedById/{bedid}")
  public ResponseEntity<Icu> getById(@PathVariable("bedid") int bedid){
    final Icu res= is.findBedById(bedid);
    if(res!=null) {
      return new ResponseEntity<>(res,HttpStatus.OK);
    }
    else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping(value = "/api/getPatientByBedId/{bedid}")
  public ResponseEntity<Patient> getPatientByBedId(@PathVariable("bedid") int bedid){
    final Patient res= is.findPatientByBedId(bedid);
    if(res!=null) {
      return new ResponseEntity<>(res,HttpStatus.OK);
    }
    else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping(value = "/api/getVacantBeds")
  public ResponseEntity<List<Icu>> getVacantBeds(){
    final List<Icu> res= is.findVacantBeds();
    if(res!=null) {
      return new ResponseEntity<>(res,HttpStatus.OK);
    }
    else {
      return new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping(value = "/api/getOccupiedBeds")
  public ResponseEntity<List<Icu>> getOccupiedBeds(){
    final List<Icu> res= is.findOccupiedBeds();
    if(res!=null) {
      return new ResponseEntity<>(res,HttpStatus.OK);
    }
    else {
      return new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
    }
  }
}
