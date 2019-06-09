package ch.bfh.red.test.model;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ch.bfh.red.backend.factories.PatientNoteFactory;
import ch.bfh.red.backend.factories.PatientFactory;
import ch.bfh.red.backend.models.PatientNote;
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.persistence.PatientNotePersistenceManager;
import ch.bfh.red.test.StartupTest;

public class PatientNoteTest extends StartupTest {
    private final PatientNoteFactory PatientNoteFactory = new PatientNoteFactory();
    private final PatientFactory patientFactory = new PatientFactory();

    @Autowired
    private PatientNotePersistenceManager PatientNoteManager;

    @Test
    public void testSessionTypeMapping() {
        Patient patient = patientFactory.create();
        PatientNote Patient1 = PatientNoteFactory.create(patient);
        PatientNote Patient2 = PatientNoteFactory.create(patient);
        PatientNoteManager.persistAll(Patient1);
        PatientNoteManager.persistAll(Patient2);
    }

}