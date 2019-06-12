package ch.bfh.red.test.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ch.bfh.red.backend.factories.PatientFactory;
import ch.bfh.red.backend.factories.TherapistFactory;
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.models.SessionType;
import ch.bfh.red.backend.models.GroupSession;
import ch.bfh.red.backend.models.Therapist;
import ch.bfh.red.backend.persistence.GroupSessionPersistenceManager;
import ch.bfh.red.test.StartupTest;

import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class GroupSessionTest extends StartupTest {
    private final PatientFactory patientFactory = new PatientFactory();
    private final TherapistFactory therapistFactory = new TherapistFactory();

    private Patient patient1;
    private Patient patient2;
    private List<Patient> patients1;
    private List<Patient> patients2;
    private Therapist therapist1;
    private Therapist therapist2;
    private List<Therapist> therapists1;
    private List<Therapist> therapists2;
    private SessionType type;
    private GroupSession session1;
    private GroupSession session2;

    @Autowired
    private GroupSessionPersistenceManager GroupSessionService;

    @Before
    public void generateGroupSessions() {
        patient1 = patientFactory.create();
        patient2 = patientFactory.create();
        patients1 = new ArrayList();
        patients2 = new ArrayList();
        therapist1 = therapistFactory.create();
        therapist2 = therapistFactory.create();
        therapists1 = new ArrayList();
        therapists2 = new ArrayList();
        type = SessionType.TALK;

        patients1.add(patient1);
        patients1.add(patient2);
        patients2.add(patient2);
        therapists1.add(therapist1);
        therapists1.add(therapist2);
        therapists2.add(therapist2);

        session1 = new GroupSession(patients1, therapists1, new Date(),
                new Date(), type);
        session2 = new GroupSession(patients2, therapists2, new Date(),
                new Date(), type);

        GroupSessionService.persistAll(session1);
        GroupSessionService.persistAll(session2);
    }

    @Test
    public void testGroupSessionCreation() {
        assertThat(session1, hasProperty("patients"));
        assertThat(session1, hasProperty("therapists"));
        assertThat(session1, hasProperty("startDate"));
        assertThat(session1, hasProperty("endDate"));
        assertThat(session1, hasProperty("sessionType"));
    }

    @Test
    public void testGroupSessionPatients() {
        assertEquals(patients2, session2.getPatients());
        assertNotEquals(patients2, session1.getPatients());

        session1.setPatients(patients2);

        assertEquals(patients2, session1.getPatients());
    }

    @Test
    public void testGroupSessionTherapists() {
        assertEquals(therapists2, session2.getTherapists());
        assertNotEquals(therapists2, session1.getTherapists());

        session1.setTherapists(therapists2);

        assertEquals(therapists2, session1.getTherapists());
    }

    @Test
    public void testGroupSessionComparison() {
        assertTrue(session1.equals(session2));
    }
}
