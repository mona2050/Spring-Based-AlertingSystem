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
import com.philips.casestudy2.springalertingsystem.service.IcuService;

@Controller
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

}
