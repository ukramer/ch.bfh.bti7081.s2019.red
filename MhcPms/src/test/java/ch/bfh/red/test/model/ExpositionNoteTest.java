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
import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class ExpositionNoteTest extends StartupTest {
    private final ExpositionNoteFactory expositionNoteFactory = new ExpositionNoteFactory();
    private final PatientFactory patientFactory = new PatientFactory();

    private Patient patient1;
    private Patient patient2;
    private ExpositionNote expositionNote1;
    private ExpositionNote expositionNote2;

    @Autowired
    private ExpositionNotePersistenceManager expositionNoteManager;

    @Before
    public void generateExpositions() {
        patient1 = patientFactory.create();
        patient2 = patientFactory.create();
        expositionNote1 = expositionNoteFactory.create(patient1);
        expositionNote2 = expositionNoteFactory.create(patient2);

        expositionNoteManager.persistAll(expositionNote1);
        expositionNoteManager.persistAll(expositionNote2);
    }

    @Test
    public void testExpositionNoteCreation() {
        assertThat(expositionNote1, hasProperty("patient"));
        assertThat(expositionNote1, hasProperty("date"));
        assertThat(expositionNote1, hasProperty("text"));
        assertThat(expositionNote1, hasProperty("visibility"));
    }

    @Test
    public void testExpositionNotePatient() {
        assertEquals(patient2, expositionNote2.getPatient());
        assertNotEquals(patient2, expositionNote1.getPatient());

        expositionNote1.setPatient(patient2);

        assertEquals(patient2, expositionNote1.getPatient());
        assertNotEquals(patient1, expositionNote1.getPatient());
    }

    @Test
    public void testExpositionNoteDegreeOfExposure() {
        expositionNote1.setDegreeOfExposure(1);

        assertEquals(new Integer(1), expositionNote1.getDegreeOfExposure());
    }

    @Test
    public void testExpositionNoteComparison() {
        assertFalse(expositionNote1.equals(expositionNote2));
    }
}