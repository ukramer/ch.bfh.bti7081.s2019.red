package ch.bfh.red.test.model;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ch.bfh.red.backend.factories.ExpositionNoteFactory;
import ch.bfh.red.backend.factories.PatientFactory;
import ch.bfh.red.backend.models.ExpositionNote;
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.persistence.ExpositionNotePersistenceManager;
import ch.bfh.red.test.StartupTest;

public class ExpositionNoteTest extends StartupTest {
	private final ExpositionNoteFactory expositionNoteFactory = new ExpositionNoteFactory();
	private final PatientFactory patientFactory = new PatientFactory();
    
    @Autowired
    private ExpositionNotePersistenceManager expositionNoteManager;
    
    @Test
    public void testSessionTypeMapping() {
        Patient patient = patientFactory.create();
        ExpositionNote exposition1 = expositionNoteFactory.create(patient);
        ExpositionNote exposition2 = expositionNoteFactory.create(patient);
        expositionNoteManager.persistAll(exposition1);
        expositionNoteManager.persistAll(exposition2);
    }

}