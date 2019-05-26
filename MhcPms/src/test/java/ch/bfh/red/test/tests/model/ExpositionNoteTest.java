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
    
    @Autowired
    private IService<ExpositionNote> expositionNoteService;

    @Test
    public void testSessionTypeMapping() {
        Address address = new Address("Emmentalstrasse", "100", 3414, "Oberburg");
        addressService.add(address);

        Patient patient = new Patient("Anne", "Meier", address);
        patientService.add(patient);

        ExpositionNote exposition1 = new ExpositionNote(patient, new Date(), "Am Morgen Herd nicht überprüft",
                Visibility.PRIVATE, 4);
        expositionNoteService.add(exposition1);

        ExpositionNote exposition2 = new ExpositionNote(patient, new Date(), "Ins Bett ohne Aufräumen",
                Visibility.PUBLIC, 6);
        expositionNoteService.add(exposition2);
    }

}

