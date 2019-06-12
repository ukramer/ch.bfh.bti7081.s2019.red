package ch.bfh.red.test.model;

import ch.bfh.red.backend.factories.PatientFactory;
import ch.bfh.red.backend.models.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ch.bfh.red.backend.factories.TherapistFactory;
import ch.bfh.red.backend.persistence.TherapistPersistenceManager;
import ch.bfh.red.test.StartupTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.*;

public class TherapistTest extends StartupTest {
    private final PatientFactory patientFactory = new PatientFactory();
    private final TherapistFactory therapistFactory = new TherapistFactory();

    private Patient patient;
    private Therapist therapist1;
    private Therapist therapist2;
    private List<Patient> patients;
    private List<Therapist> therapists;

    @Autowired
    private TherapistPersistenceManager TherapistManager;

    @Before
    public void generateTherapists() {
        patient = patientFactory.create();
        therapist1 = therapistFactory.create();
        therapist2 = therapistFactory.create();
        patients = Arrays.asList(patient);
        therapists = Arrays.asList(therapist1);

        TherapistManager.persistAll(therapist1);
        TherapistManager.persistAll(therapist2);
    }

    @Test
    public void testTherapistCreation() {
        assertThat(therapist1, hasProperty("username"));
        assertThat(therapist1, hasProperty("password"));
        assertThat(therapist1, hasProperty("firstName"));
        assertThat(therapist1, hasProperty("lastName"));
        assertThat(therapist1, hasProperty("address"));
    }

    @Test
    public void testTherapistTitle() {
        therapist1.setAcademicTitle(AcademicTitle.DOCTOR);

        assertEquals(AcademicTitle.DOCTOR, therapist1.getAcademicTitle());
    }

    @Test
    public void testTherapistPatient() {
        assertTrue(therapist1.getPatients().isEmpty());

        therapist1.setPatients(patients);

        assertFalse(therapist1.getPatients().isEmpty());
    }

    @Test
    public void testTherapistSingleSession() {
        assertTrue(therapist1.getSingleSessions().isEmpty());

        SingleSession session = new SingleSession(patient, therapist1, new Date(),
                new Date(), SessionType.TALK);
        therapist1.setSingleSessions(Collections.singleton(session));

        assertFalse(therapist1.getSingleSessions().isEmpty());
    }

    @Test
    public void testTherapistGroupSession() {
        assertTrue(therapist1.getGroupSessions().isEmpty());

        GroupSession session = new GroupSession(patients, therapists, new Date(),
                new Date(), SessionType.EXPOSITION);
        therapist1.setGroupSessions(Collections.singleton(session));

        assertFalse(therapist1.getGroupSessions().isEmpty());
    }

    @Test
    public void testTherapistTherapy() {
        assertTrue(therapist1.getTherapies().isEmpty());

        Therapy therapy = new Therapy(new Date(), TherapyType.PSYCHO, patient, therapist1);
        therapist1.setTherapies(Collections.singleton(therapy));

        assertFalse(therapist1.getTherapies().isEmpty());
    }

    @Test
    public void testTherapistComparison() {
        assertFalse(therapist1.equals(therapist2));
    }
}