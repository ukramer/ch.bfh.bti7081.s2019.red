package ch.bfh.red.test.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

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

public class GroupSessionTest extends StartupTest {
    private final PatientFactory patientFactory = new PatientFactory();
    private final TherapistFactory therapistFactory = new TherapistFactory();

    @Autowired
    private GroupSessionPersistenceManager GroupSessionService;

    @Test
    public void testSessionTypeMapping() {
        Patient patient1 = patientFactory.create();
        Patient patient2 = patientFactory.create();
        List<Patient> patients = new ArrayList();
        Therapist therapist1 = therapistFactory.create();
        Therapist therapist2 = therapistFactory.create();
        List<Therapist> therapists = new ArrayList();

        SessionType type1 = SessionType.TALK;

        GroupSession session1 = new GroupSession(patients, therapists, new Date(),
                new Date(), type1);
        GroupSession session2 = new GroupSession(patients, therapists, new Date(),
                new Date(), type1);
        GroupSessionService.persistAll(session1);
        GroupSessionService.persistAll(session2);
    }

}
