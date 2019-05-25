package ch.bfh.red.test.tests.model;

import java.util.Date;

import ch.bfh.red.backend.models.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ch.bfh.red.backend.services.IService;
import ch.bfh.red.test.tests.StartupTest;

public class ExpositionNoteTest extends StartupTest {

    @Autowired
    private IService<Address> addressService;

    @Autowired
    private IService<Patient> patientService;


    @Test
    public void testSessionTypeMapping() {
        Address address = new Address("Emmentalstrasse", "100", 3414, "Oberburg");
        addressService.add(address);

        Patient patient = new Patient("Anne", "Meier", address);
        patientService.add(patient);

        Visibility visibility  = Visibility.PRIVATE;
        Visibility visibility1 = Visibility.PUBLIC;

        ExpositionNote exposition1 = new ExpositionNote(patient, new Date(), "Am Morgen Herd nicht überprüft",
                visibility, 4);

        ExpositionNote exposition2 = new ExpositionNote(patient, new Date(), "Ins Bett ohne Aufräumen",
                visibility1, 6);


    }

}

