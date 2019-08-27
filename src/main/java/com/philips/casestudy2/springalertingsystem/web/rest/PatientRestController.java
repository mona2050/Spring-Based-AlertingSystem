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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
      final String charString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
      final String numericString = "0123456789";
      final StringBuilder sb = new StringBuilder();


      for (int i = 0; i < 5; i++) {

        final int index = (int)(charString.length() * Math.random());
        sb.append(charString.charAt(index));
      }
      for (int i = 0; i < 3; i++) {
        final int index = (int)(numericString.length() * Math.random());
        sb.append(numericString.charAt(index));
      }

      final String patientId = sb.toString();
      patient.setId(patientId);
      final String id_ = ps.addNewPatient(id, patient);
      final HttpHeaders headers = new HttpHeaders();
      headers.setLocation(URI.create("/api/addpatient/"+id_));

      return new ResponseEntity<>(headers,HttpStatus.CREATED);
    }catch(final IllegalArgumentException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping(value="/api/findAllPatients")
  public List<Patient> getAllPatients(){
    return ps.findAll();
  }

  @GetMapping(value="/api/findById/{id}")
  public ResponseEntity<Patient> getPatientById(@PathVariable("id")String id){
    final Patient p = ps.findById(id);
    if(p!=null) {
      return new ResponseEntity<>(p,HttpStatus.OK);
    } else {
      return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping(value="/api/dischargePatient/{id}")
  public ResponseEntity<Patient> dischargePatient(@PathVariable("id")String id){
    final Patient p = ps.findById(id);
    if(p!=null) {
      ps.deleteById(id);
      return new ResponseEntity<>(HttpStatus.OK);} else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
  }

}
