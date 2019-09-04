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
import org.springframework.web.bind.annotation.RestController;
import com.philips.casestudy2.springalertingsystem.domain.Patient;
import com.philips.casestudy2.springalertingsystem.service.PatientIdSimulator;
import com.philips.casestudy2.springalertingsystem.service.PatientService;
@RestController
public class PatientRestController {


  PatientService ps;

  @Autowired
  public PatientRestController(PatientService ps) {
    this.ps = ps;
  }


  @PostMapping(value="/api/addpatient")
  public ResponseEntity<String> addingPatient(@RequestBody Patient patient) {
    String message;
    try {

      final com.philips.casestudy2.springalertingsystem.service.PatientIdSimulator pi = new PatientIdSimulator();
      final String patientId = pi.patientIdGenerator();
      patient.setId(patientId);

      final int bedId = patient.getIcu().getBedid();
      final String addedPatientId = ps.addNewPatient(bedId, patient);

      if(addedPatientId!=null) {
        message = "Patient successfully created!!!";
        return new ResponseEntity<>(message,HttpStatus.OK);
      }
      else
      {
        message = "Patient not created!!!";
        return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
      }
    }catch (final IllegalArgumentException e) {
      message = "Illegal Arguments";
      return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
    }

  }

  @GetMapping(value="/api/getAllPatients")
  public ResponseEntity<List<Patient>> getAllPatients(){
    final List<Patient> listOfPatients =  ps.getAllPatients();
    System.out.println("Getting data from database"+listOfPatients);
    return new ResponseEntity<>(listOfPatients,HttpStatus.OK);
  }

  @GetMapping(value="/api/findById/{id}")
  public ResponseEntity<Patient> getPatientById(@PathVariable("id")String id){
    final Patient p = ps.findPatientById(id);
    if(p!=null) {
      return new ResponseEntity<>(p,HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping(value="/api/dischargePatient/{id}")
  public ResponseEntity<Patient> dischargePatient(@PathVariable("id")String id){
    final Patient p = ps.findPatientById(id);
    if(p!=null) {
      ps.deleteById(id);
      return new ResponseEntity<>(HttpStatus.OK);} else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
  }

  @GetMapping(value="/api/findBedOfPatient/{id}")
  public int getBedOfPatient(@PathVariable("id")String id){
    return ps.findBedOfPatient(id);
  }
}
