/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy2.springalertingsystem.web.rest;

import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.philips.casestudy2.springalertingsystem.domain.Patient;
import com.philips.casestudy2.springalertingsystem.service.PatientService;
@RestController
public class PatientRestController {


  PatientService ps;


  @Autowired
  public void setPs(PatientService ps) {
    this.ps = ps;
  }


  @PostMapping(value="/api/addpatient")
  public ResponseEntity<Patient> addingPatient(@RequestBody Patient patient) {

    try {

      final int id = patient.getIcu().getBedid();
      final String id_ = ps.addNewPatient(id, patient);
      final HttpHeaders headers = new HttpHeaders();
      headers.setLocation(URI.create("/api/addpatient/"+id_));

      return new ResponseEntity<>(headers,HttpStatus.CREATED);
    }catch(final IllegalArgumentException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }



}
