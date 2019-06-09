package ch.bfh.red.test.model;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ch.bfh.red.backend.factories.ExpositionNoteFactory;
import ch.bfh.red.backend.factories.PatientFactory;
import ch.bfh.red.backend.models.ExpositionNote;
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.persistence.ExpositionNotePersistenceManager;
import ch.bfh.red.test.StartupTest;

import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertThat;

public class ExpositionNoteTest extends StartupTest {
    private final ExpositionNoteFactory expositionNoteFactory = new ExpositionNoteFactory();
    private final PatientFactory patientFactory = new PatientFactory();

    private Patient patient;
    private ExpositionNote exposition1;
    private ExpositionNote exposition2;

    @Autowired
    private ExpositionNotePersistenceManager expositionNoteManager;

    @Before
    public void generateExpositions() {
        patient = patientFactory.create();
        exposition1 = expositionNoteFactory.create(patient);
        exposition2 = expositionNoteFactory.create(patient);

        expositionNoteManager.persistAll(exposition1);
        expositionNoteManager.persistAll(exposition2);
    }

    @Test
    public void testExpositionNoteCreation() {
        assertThat(exposition1, hasProperty("patient"));
        assertThat(exposition1, hasProperty("date"));
        assertThat(exposition1, hasProperty("text"));
        assertThat(exposition1, hasProperty("visibility"));
    }
}