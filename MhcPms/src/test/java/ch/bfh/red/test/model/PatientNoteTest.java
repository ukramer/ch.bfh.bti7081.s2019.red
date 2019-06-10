package ch.bfh.red.test.model;

import ch.bfh.red.backend.models.PatientNote;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ch.bfh.red.backend.factories.PatientNoteFactory;
import ch.bfh.red.backend.factories.PatientFactory;
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.persistence.PatientNotePersistenceManager;
import ch.bfh.red.test.StartupTest;

import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.*;

public class PatientNoteTest extends StartupTest {
    private final PatientNoteFactory patientNoteFactory = new PatientNoteFactory();
    private final PatientFactory patientFactory = new PatientFactory();

    private Patient patient1;
    private Patient patient2;
    private PatientNote patientNote1;
    private PatientNote patientNote2;

    @Autowired
    private PatientNotePersistenceManager patientNoteManager;

    @Before
    public void generatePatientNotes() {
        patient1 = patientFactory.create();
        patient2 = patientFactory.create();
        patientNote1 = patientNoteFactory.create(patient1);
        patientNote2 = patientNoteFactory.create(patient2);

        patientNoteManager.persistAll(patientNote1);
        patientNoteManager.persistAll(patientNote2);
    }

    @Test
    public void testPatientNoteCreation() {
        assertThat(patientNote1, hasProperty("patient"));
        assertThat(patientNote1, hasProperty("date"));
        assertThat(patientNote1, hasProperty("text"));
        assertThat(patientNote1, hasProperty("visibility"));
    }

    @Test
    public void testPatientNotePatient() {
        assertEquals(patient2, patientNote2.getPatient());
        assertNotEquals(patient2, patientNote1.getPatient());

        patientNote1.setPatient(patient2);

        assertEquals(patient2, patientNote1.getPatient());
        assertNotEquals(patient1, patientNote1.getPatient());
    }

    @Test
    public void testPatientNoteComparison() {
        assertFalse(patientNote1.equals(patientNote2));
    }
}