/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy2.springalertingsystem.web.rest;

import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.philips.casestudy2.springalertingsystem.domain.Icu;
import com.philips.casestudy2.springalertingsystem.domain.Patient;
import com.philips.casestudy2.springalertingsystem.service.IcuService;
import com.philips.casestudy2.springalertingsystem.service.PatientService;
@Controller
public class PatientRestController {

  IcuService is;
  PatientService ps;

  @Autowired
  public void setIs(IcuService is) {
    this.is = is;
  }

  @Autowired
  public void setPs(PatientService ps) {
    this.ps = ps;
  }


  @PostMapping(value="/api/addbed")
  public ResponseEntity<Icu> addingBed(@RequestBody Icu bed) {

    try {
      final int id = is.addNewBed(bed);
      final HttpHeaders headers = new HttpHeaders();
      headers.setLocation(URI.create("/api/addbed/"+id));

      return new ResponseEntity<>(headers,HttpStatus.CREATED);
    }catch(final IllegalArgumentException e) { // when business logic fails, bad parameters are given by the user
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

  }


  @PostMapping(value="/api/addpatient")
  public ResponseEntity<Patient> addingPatient(@RequestBody Patient patient) {

    try {

      final int id = patient.getIcu().getBedid();
      final String id_ = ps.addNewPatient(id, patient);
      final HttpHeaders headers = new HttpHeaders();
      headers.setLocation(URI.create("/api/addpatient/"+id_));

      return new ResponseEntity<>(headers,HttpStatus.CREATED);
    }catch(final IllegalArgumentException e) { // when business logic fails, bad parameters are given by the user
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

}
