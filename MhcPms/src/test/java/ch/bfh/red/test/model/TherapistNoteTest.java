package ch.bfh.red.test.model;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ch.bfh.red.backend.factories.TherapistNoteFactory;
import ch.bfh.red.backend.factories.TherapistFactory;
import ch.bfh.red.backend.models.TherapistNote;
import ch.bfh.red.backend.models.Therapist;
import ch.bfh.red.backend.persistence.TherapistNotePersistenceManager;
import ch.bfh.red.test.StartupTest;

public class TherapistNoteTest extends StartupTest {
    private final TherapistNoteFactory TherapistNoteFactory = new TherapistNoteFactory();
    private final TherapistFactory TherapistFactory = new TherapistFactory();

    @Autowired
    private TherapistNotePersistenceManager TherapistNoteManager;

    @Test
    public void testSessionTypeMapping() {
        Therapist Therapist = TherapistFactory.create();
        TherapistNote Therapist1 = TherapistNoteFactory.create(Therapist);
        TherapistNote Therapist2 = TherapistNoteFactory.create(Therapist);
        TherapistNoteManager.persistAll(Therapist1);
        TherapistNoteManager.persistAll(Therapist2);
    }

}