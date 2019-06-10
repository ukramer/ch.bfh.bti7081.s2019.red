package ch.bfh.red.test.model;

import ch.bfh.red.backend.factories.TherapistFactory;
import ch.bfh.red.backend.factories.TherapistNoteFactory;
import ch.bfh.red.backend.models.Therapist;
import ch.bfh.red.backend.models.TherapistNote;
import ch.bfh.red.backend.persistence.TherapistNotePersistenceManager;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ch.bfh.red.test.StartupTest;

import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class TherapistNoteTest extends StartupTest {
    private final TherapistNoteFactory therapistNoteFactory = new TherapistNoteFactory();
    private final TherapistFactory therapistFactory = new TherapistFactory();

    private Therapist therapist1;
    private Therapist therapist2;
    private TherapistNote therapistNote1;
    private TherapistNote therapistNote2;

    @Autowired
    private TherapistNotePersistenceManager therapistNoteManager;

    @Before
    public void generateTherapistNotes() {
        therapist1 = therapistFactory.create();
        therapist2 = therapistFactory.create();
        therapistNote1 = therapistNoteFactory.create(therapist1);
        therapistNote2 = therapistNoteFactory.create(therapist2);

        therapistNoteManager.persistAll(therapistNote1);
        therapistNoteManager.persistAll(therapistNote2);
    }

    @Test
    public void testTherapistNoteCreation() {
        assertThat(therapistNote1, hasProperty("therapist"));
        assertThat(therapistNote1, hasProperty("date"));
        assertThat(therapistNote1, hasProperty("text"));
        assertThat(therapistNote1, hasProperty("visibility"));
    }

    @Test
    public void testTherapistNoteTherapist() {
        assertEquals(therapist2, therapistNote2.getTherapist());
        assertNotEquals(therapist2, therapistNote1.getTherapist());

        therapistNote1.setTherapist(therapist2);

        assertEquals(therapist2, therapistNote1.getTherapist());
        assertNotEquals(therapist1, therapistNote1.getTherapist());
    }

    @Test
    public void testTherapistNoteComparison() {
        assertFalse(therapistNote1.equals(therapistNote2));
    }
}