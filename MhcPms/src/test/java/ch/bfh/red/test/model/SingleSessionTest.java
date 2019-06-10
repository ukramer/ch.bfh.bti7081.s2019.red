package ch.bfh.red.test.model;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ch.bfh.red.backend.factories.PatientFactory;
import ch.bfh.red.backend.factories.TherapistFactory;
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.models.SessionType;
import ch.bfh.red.backend.models.SingleSession;
import ch.bfh.red.backend.models.Therapist;
import ch.bfh.red.backend.persistence.SingleSessionPersistenceManager;
import ch.bfh.red.test.StartupTest;

import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.*;

public class SingleSessionTest extends StartupTest {
    private final PatientFactory patientFactory = new PatientFactory();
    private final TherapistFactory therapistFactory = new TherapistFactory();

    private Patient patient1;
    private Patient patient2;
    private Therapist therapist1;
    private Therapist therapist2;
    private SingleSession session1;
    private SingleSession session2;
    private SessionType type;

    @Autowired
    private SingleSessionPersistenceManager singleSessionService;

    @Before
    public void generateSingleSessions() {
        patient1 = patientFactory.create();
        patient2 = patientFactory.create();
        therapist1 = therapistFactory.create();
        therapist2 = therapistFactory.create();

        type = SessionType.TALK;

        session1 = new SingleSession(patient1, therapist1, new Date(),
                new Date(), type);
        session2 = new SingleSession(patient2, therapist2, new Date(),
                new Date(), type);

        singleSessionService.persistAll(session1);
        singleSessionService.persistAll(session2);
    }

    @Test
    public void testSingleSessionCreation() {
        assertThat(session1, hasProperty("patient"));
        assertThat(session1, hasProperty("therapist"));
        assertThat(session1, hasProperty("startDate"));
        assertThat(session1, hasProperty("endDate"));
        assertThat(session1, hasProperty("sessionType"));
    }

    @Test
    public void testSingleSessionPatient() {
        assertEquals(patient2, session2.getPatient());
        assertNotEquals(patient2, session1.getPatient());

        session1.setPatient(patient2);

        assertEquals(patient2, session1.getPatient());
        assertNotEquals(patient1, session1.getPatient());
    }

    @Test
    public void testSingleSessionTherapist() {
        assertEquals(therapist2, session2.getTherapist());
        assertNotEquals(therapist2, session1.getTherapist());

        session1.setTherapist(therapist2);

        assertEquals(therapist2, session1.getTherapist());
        assertNotEquals(therapist1, session1.getTherapist());
    }

    @Test
    public void testSingleSessionComparison() {
        assertFalse(session1.equals(session2));
    }
}
