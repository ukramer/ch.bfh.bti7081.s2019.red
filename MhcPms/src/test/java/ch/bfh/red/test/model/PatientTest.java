package ch.bfh.red.test.model;

import ch.bfh.red.backend.factories.TherapistFactory;
import ch.bfh.red.backend.models.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ch.bfh.red.backend.factories.PatientFactory;
import ch.bfh.red.backend.persistence.PatientPersistenceManager;
import ch.bfh.red.test.StartupTest;

import java.util.*;

import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.*;

public class PatientTest extends StartupTest {
    private final PatientFactory patientFactory = new PatientFactory();
    private final TherapistFactory therapistFactory = new TherapistFactory();

    private Patient patient1;
    private Patient patient2;
    private Therapist therapist;
    private List<Patient> patients;
    private List<Therapist> therapists;

    @Autowired
    private PatientPersistenceManager PatientManager;

    @Before
    public void generateTestData() {
        patient1 = patientFactory.create();
        patient2 = patientFactory.create();
        therapist = therapistFactory.create();
        patients = Arrays.asList(patient1);
        therapists = Arrays.asList(therapist);

        PatientManager.persistAll(patient1);
        PatientManager.persistAll(patient2);
    }

    @Test
    public void testPatientCreation() {
        assertThat(patient1, hasProperty("firstName"));
        assertThat(patient1, hasProperty("lastName"));
        assertThat(patient1, hasProperty("address"));
    }

    @Test
    public void testPatientSingleSession() {
        assertTrue(patient1.getSingleSessions().isEmpty());

        SingleSession session = new SingleSession(patient1, therapist, new Date(),
                new Date(), SessionType.TALK);
        patient1.setSingleSessions(Collections.singleton(session));

        assertFalse(patient1.getSingleSessions().isEmpty());
    }

    @Test
    public void testPatientGroupSession() {
        assertTrue(patient1.getGroupSessions().isEmpty());

        GroupSession session = new GroupSession(patients, therapists, new Date(),
                new Date(), SessionType.EXPOSITION);
        patient1.setGroupSessions(Collections.singleton(session));

        assertFalse(patient1.getGroupSessions().isEmpty());
    }

    @Test
    public void testPatientTherapy() {
        assertTrue(patient1.getTherapies().isEmpty());

        Therapy therapy = new Therapy(new Date(), TherapyType.PSYCHO, patient1, therapist);
        patient1.setTherapies(Collections.singleton(therapy));

        assertFalse(patient1.getTherapies().isEmpty());
    }

    @Test
    public void testPatientComparison() {
        assertFalse(patient1.equals(patient2));
    }
}