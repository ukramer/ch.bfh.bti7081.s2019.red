package ch.bfh.red.test.model;

import ch.bfh.red.backend.factories.*;
import ch.bfh.red.backend.models.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ch.bfh.red.backend.persistence.TherapyPersistenceManager;
import ch.bfh.red.test.StartupTest;

import java.util.Collections;
import java.util.Date;

import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.*;

public class TherapyTest extends StartupTest {
    private final TherapyFactory TherapyFactory = new TherapyFactory();
    private final PatientFactory patientFactory = new PatientFactory();
    private final TherapistFactory therapistFactory = new TherapistFactory();
    private final PatientNoteFactory patientNoteFactory = new PatientNoteFactory();
    private final TherapistNoteFactory therapistNoteFactory = new TherapistNoteFactory();
    private final ExpositionNoteFactory expositionNoteFactory = new ExpositionNoteFactory();

    private Therapy therapy1;
    private Therapy therapy2;
    private Patient patient;
    private Therapist therapist;

    @Autowired
    private TherapyPersistenceManager TherapyManager;

    @Before
    public void generateTherapies() {
        therapy1 = TherapyFactory.create();
        therapy2 = TherapyFactory.create();
        patient = patientFactory.create();
        therapist = therapistFactory.create();
        TherapyManager.persistAll(therapy1);
        TherapyManager.persistAll(therapy2);
    }

    @Test
    public void testTherapyCreation() {
        assertThat(therapy1, hasProperty("startDate"));
        assertThat(therapy1, hasProperty("therapyType"));
        assertThat(therapy1, hasProperty("patient"));
        assertThat(therapy1, hasProperty("therapist"));
    }

    @Test
    public void testTherapySessions() {
        assertNull(therapy1.getSingleSessions());
        assertNull(therapy1.getGroupSessions());

        therapy1.setSingleSessions(Collections.singletonList(new SingleSessionFactory().create()));
        therapy1.setGroupSessions(Collections.singletonList(new GroupSessionFactory().create()));

        assertNotNull(therapy1.getSingleSessions());
        assertNotNull(therapy1.getGroupSessions());
    }

    @Test
    public void testTherapyPatient() {
        assertNotNull(therapy1.getPatient());
    }

    @Test
    public void testTherapyTherapist() {
        assertNotNull(therapy1.getTherapist());
    }

    @Test
    public void testTherapyPatientNotes() {
        assertNull(therapy1.getPatientNotes());

        PatientNote patientNote = patientNoteFactory.create();
        therapy1.setPatientNotes(Collections.singletonList(patientNote));

        assertFalse(therapy1.getPatientNotes().isEmpty());
    }

    @Test
    public void testTherapyTherapistNotes() {
        assertNull(therapy1.getTherapistNotes());

        TherapistNote therapistNote = therapistNoteFactory.create();
        therapy1.setTherapistNotes(Collections.singletonList(therapistNote));

        assertFalse(therapy1.getTherapistNotes().isEmpty());
    }

    @Test
    public void testTherapyExpositionNotes() {
        assertNull(therapy1.getExpositionNotes());

        ExpositionNote expositionNote = expositionNoteFactory.create();
        therapy1.setExpositionNotes(Collections.singletonList(expositionNote));

        assertFalse(therapy1.getExpositionNotes().isEmpty());
    }

    @Test
    public void testTherapyType() {
        therapy1.setTherapyType(TherapyType.PSYCHO);

        assertEquals(TherapyType.PSYCHO, therapy1.getTherapyType());
    }

    @Test
    public void testTherapyState() {
        assertFalse(therapy1.isFinished());

        therapy1.setFinished(true);

        assertTrue(therapy1.isFinished());
    }

    @Test
    public void testTherapyStartDate() {
        Date therapyDate = new Date();
        Date expectedDate = new Date();
        therapy1.setStartDate(therapyDate);

        assertTrue((expectedDate.getTime() / 1000) == (therapy1.getStartDate().getTime() / 1000));
    }
}