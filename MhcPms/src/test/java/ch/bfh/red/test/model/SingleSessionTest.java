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
import static org.junit.Assert.assertThat;

public class SingleSessionTest extends StartupTest {
    private final PatientFactory patientFactory = new PatientFactory();
    private final TherapistFactory therapistFactory = new TherapistFactory();

    private Patient patient;
    private Therapist therapist;
    private SingleSession session1;
    private SingleSession session2;
    private SessionType type;

    @Autowired
    private SingleSessionPersistenceManager singleSessionService;

    @Before
    public void generateSingleSessions() {
        patient = patientFactory.create();
        therapist = therapistFactory.create();

        type = SessionType.TALK;

        SingleSession session1 = new SingleSession(patient, therapist, new Date(),
                new Date(), type);
        SingleSession session2 = new SingleSession(patient, therapist, new Date(),
                new Date(), type);

        singleSessionService.persistAll(session1);
        singleSessionService.persistAll(session2);
    }

    @Test
    public void testSingleSessionCreation() {
        Patient patient = patientFactory.create();
        Therapist therapist = therapistFactory.create();
        SessionType type = SessionType.TALK;
        SingleSession session = new SingleSession(patient, therapist, new Date(),
                new Date(), type);
        singleSessionService.persistAll(session);
        assertThat(session, hasProperty("patient"));
        assertThat(session, hasProperty("therapist"));
        assertThat(session, hasProperty("startDate"));
        assertThat(session, hasProperty("endDate"));
        assertThat(session, hasProperty("sessionType"));
    }
}
