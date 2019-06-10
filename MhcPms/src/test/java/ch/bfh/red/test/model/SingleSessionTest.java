package ch.bfh.red.test.model;

import java.util.Date;

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

public class SingleSessionTest extends StartupTest {
	private final PatientFactory patientFactory = new PatientFactory();
	private final TherapistFactory therapistFactory = new TherapistFactory();
	
    @Autowired
    private SingleSessionPersistenceManager singleSessionService;
    
    @Test
    public void testSessionTypeMapping() {
        Patient patient = patientFactory.create();
        Therapist therapist = therapistFactory.create();

        SessionType type1 = SessionType.TALK;

        SingleSession session1 = new SingleSession(patient, therapist, new Date(),
                new Date(), type1);
        SingleSession session2 = new SingleSession(patient, therapist, new Date(),
                new Date(), type1);
        singleSessionService.persistAll(session1);
        singleSessionService.persistAll(session2);
    }
    
}
