package ch.bfh.red.backend.services;

import ch.bfh.red.backend.models.Address;
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.ui.views.SearchBean.PatientSearchBean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PatientRestService {

    //TODO: Repository instanzieren
    List<Patient> patientList = new ArrayList<>();

    @PostMapping("/searchPatienten")
    List searchPersonen(@RequestBody PatientSearchBean patientSearchBean) {
        System.out.println(patientSearchBean.toString());
        patientList.add(new Patient("cyrill", "meyer", new Address("bethlehem", "7", 3185, "schmitten")));
        patientList.add(new Patient("ueli", "kramer", new Address("thunstrasse", "18", 2499, "thun")));
        return patientList;
    }

    @PostMapping("/deletePatient")
    List deletePatient(@RequestBody Patient patient) {
        System.out.println("delete patient: " + patient.toString());
        patientList.remove(patient);
        return patientList;
    }
}
