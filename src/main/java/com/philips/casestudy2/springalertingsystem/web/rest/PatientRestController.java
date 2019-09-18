/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy2.springalertingsystem.web.rest;

import java.util.List;
import java.util.regex.Pattern;
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
import com.philips.casestudy2.springalertingsystem.domain.Patient;
import com.philips.casestudy2.springalertingsystem.service.PatientIdSimulatorService;
import com.philips.casestudy2.springalertingsystem.service.PatientService;
@RestController
@RequestMapping("/hospital")
public class PatientRestController {

  PatientService ps;

  @Autowired
  public void setPs(PatientService ps) {
    this.ps = ps;
  }


  @GetMapping(value="/patient/existsornot/{adhaarno}")
  public ResponseEntity<String> checkPatientExistence(@PathVariable("adhaarno")String adhaarno) {
    String message = null;
    boolean flag = false;

    if(Pattern.matches("[0-9]{10}", adhaarno)) {
      flag=true;
    }

    if(flag) {
      final Patient p = ps.checkPatientExistence(adhaarno);
      if(p!=null) {
        message="Patient Already exists";
        return new ResponseEntity<>(message,HttpStatus.OK);
      }
      else
      {
        message="Patient doesnot exists";
        return new ResponseEntity<>(message,HttpStatus.OK);
      }
    }
    else {
      message="Invalid Adharno";
      return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
    }

  }


  @PostMapping(value="/patient")
  public ResponseEntity<String> addingPatient(@RequestBody Patient patient) {
    String message=null;


    final com.philips.casestudy2.springalertingsystem.service.PatientIdSimulatorService pi = new PatientIdSimulatorService();
    final String patientId = pi.patientIdGenerator();
    patient.setId(patientId);


    final int bedId = patient.getIcu().getBedid();
    final String addedPatientId = ps.addNewPatient(bedId, patient);


    if(addedPatientId !=null && !"1".equals(addedPatientId) && !"0".equals(addedPatientId)) {
      message="Patient successfully created!!!";
      return new ResponseEntity<>(message,HttpStatus.OK);
    }

    if(addedPatientId==null)
    {
      message = "Patient cannot be null!!!";

    }
    else if(addedPatientId.equals("1"))
    {
      message = "Patient already exists!!!";

    }
    else if(addedPatientId.equals("0")) {

      message = "Bed already occupied!!!";

    }

    return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);



  }

  @GetMapping(value="/patient")
  public ResponseEntity<List<Patient>> getAllPatients(){
    final List<Patient> listOfPatients =  ps.getAllPatients();
    return new ResponseEntity<>(listOfPatients,HttpStatus.OK);
  }


  @GetMapping(value="/patient/{id}")
  public ResponseEntity<Patient> getPatientById(@PathVariable("id")String id){
    final Patient p = ps.findPatientById(id);
    if(p!=null) {
      return new ResponseEntity<>(p,HttpStatus.OK);
    } else {
      return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }
  }


  @DeleteMapping(value="/patient/{id}")
  public ResponseEntity<String> dischargePatient(@PathVariable("id")String id){
    String message=null;

    final Patient p = ps.findPatientById(id);
    if(p!=null) {
      ps.deleteById(id);
      message = "Patient Successfully deleted!!";
      return new ResponseEntity<>(message,HttpStatus.OK);}
    else {
      message = "Patient deletion failed!!";
      return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
    }
  }


  @GetMapping(value="/bedOfPatient/{id}")
  public int getBedOfPatient(@PathVariable("id")String id){
    if(id!=null) {
      final Patient p = ps.findPatientById(id);
      if(p!=null) {
        return  ps.findBedOfPatient(id);
      } else {
        return -1;
      }
    } else {
      return -1;
    }


  }
}
