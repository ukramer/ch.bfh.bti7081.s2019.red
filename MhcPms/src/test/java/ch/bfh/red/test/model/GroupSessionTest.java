package ch.bfh.red.test.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import org.junit.Before;
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
import static org.junit.Assert.assertThat;

public class GroupSessionTest extends StartupTest {
    private final PatientFactory patientFactory = new PatientFactory();
    private final TherapistFactory therapistFactory = new TherapistFactory();

    private Patient patient1;
    private Patient patient2;
    private List<Patient> patients;
    private Therapist therapist1;
    private Therapist therapist2;
    private List<Therapist> therapists;
    private SessionType type;
    private GroupSession session1;
    private GroupSession session2;

    @Autowired
    private GroupSessionPersistenceManager GroupSessionService;

    @Before
    public void generateGroupSessions() {
        patient1 = patientFactory.create();
        patient2 = patientFactory.create();
        patients = new ArrayList();
        therapist1 = therapistFactory.create();
        therapist2 = therapistFactory.create();
        therapists = new ArrayList();
        type = SessionType.TALK;

        patients.add(patient1);
        patients.add(patient2);
        therapists.add(therapist1);
        therapists.add(therapist1);

        session1 = new GroupSession(patients, therapists, new Date(),
                new Date(), type);
        session2 = new GroupSession(patients, therapists, new Date(),
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
}
