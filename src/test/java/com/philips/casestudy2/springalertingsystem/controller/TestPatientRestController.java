/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy2.springalertingsystem.controller;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.philips.casestudy2.springalertingsystem.domain.Gender;
import com.philips.casestudy2.springalertingsystem.domain.Icu;
import com.philips.casestudy2.springalertingsystem.domain.Patient;
import com.philips.casestudy2.springalertingsystem.service.PatientService;
import com.philips.casestudy2.springalertingsystem.web.rest.PatientRestController;
public class TestPatientRestController {

  @Mock
  private PatientService service;
  private MockMvc mvc;

  @InjectMocks
  private PatientRestController prc;

  @Before
  public void init(){
    MockitoAnnotations.initMocks(this);
    mvc = MockMvcBuilders
        .standaloneSetup(prc)
        .build();
  }

  @Test
  public void getAllPatientsTest_for_successfull_return() throws Exception{

    final Icu i1 = new Icu(0);
    i1.setBedid(1);
    final Patient p1= new Patient("harshitha",25,Gender.FEMALE,"6301340004",i1);
    p1.setId("A2345");
    final Icu i2 = new Icu(0);
    i1.setBedid(2);
    final Patient p2= new Patient("mona",25,Gender.FEMALE,"6301340004",i2);
    p2.setId("A2346");
    final List<Patient> patients=new ArrayList<>();
    patients.add(p1);
    patients.add(p2);

    when(service.getAllPatients()).thenReturn(patients);

    mvc.perform(get("/api/getAllPatients"))
    .andExpect(status().isOk())
    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
    .andExpect(jsonPath("$", hasSize(2)));
    verify(service, times(1)).getAllPatients();
    verifyNoMoreInteractions(service);

  }

}
