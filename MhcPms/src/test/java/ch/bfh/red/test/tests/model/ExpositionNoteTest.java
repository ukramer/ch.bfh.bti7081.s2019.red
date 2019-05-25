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
    private IService<Visibility> visibilityIService;

    @Autowired
    private IService<ExpositionNote> expositionNoteService;
    
    @Test
    public void testSessionTypeMapping() {
        Address address = new Address("Emmentalstrasse", "100", 3414, "Oberburg");
        addressService.add(address);

        Patient patient = new Patient("Anne", "Meier", address);
        patientService.add(patient);

        Visibility visibility1 = new Visibility("PRIVATE", "only for therapist's eyes");
        visibilityIService.add(visibility1);

        Visibility visibility2= new Visibility("PUBLIC", "for everyone to see");
        visibilityIService.add(visibility2);

        ExpositionNote exposition1 = new ExpositionNote(patient, new Date(), "Am Morgen Herd nicht überprüft",
                visibility1, 4);
        expositionNoteService.add(exposition1);

        ExpositionNote exposition2 = new ExpositionNote(patient, new Date(), "Ins Bett ohne Aufräumen",
                visibility2, 6);
        expositionNoteService.add(exposition2);

    }

}
