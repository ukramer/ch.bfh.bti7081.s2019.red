package ch.bfh.red.test.model;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ch.bfh.red.backend.factories.TherapistNoteFactory;
import ch.bfh.red.backend.factories.TherapistFactory;
import ch.bfh.red.backend.models.TherapistNote;
import ch.bfh.red.backend.models.Therapist;
import ch.bfh.red.backend.persistence.TherapistNotePersistenceManager;
import ch.bfh.red.test.StartupTest;

import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertThat;

public class TherapistNoteTest extends StartupTest {
    private final TherapistNoteFactory therapistNoteFactory = new TherapistNoteFactory();
    private final TherapistFactory therapistFactory = new TherapistFactory();

    private Therapist therapist;
    TherapistNote therapistNote1;
    TherapistNote therapistNote2;

    @Autowired
    private TherapistNotePersistenceManager therapistNoteManager;

    @Before
    public void generateTherapistNotes() {
        therapist = therapistFactory.create();
        therapistNote1 = therapistNoteFactory.create(therapist);
        therapistNote2 = therapistNoteFactory.create(therapist);

        therapistNoteManager.persistAll(therapistNote1);
        therapistNoteManager.persistAll(therapistNote2);
    }

    @Test
    public void testPatientNoteCreation() {
        assertThat(therapistNote1, hasProperty("therapist"));
        assertThat(therapistNote1, hasProperty("date"));
        assertThat(therapistNote1, hasProperty("text"));
        assertThat(therapistNote1, hasProperty("visibility"));
    }
}