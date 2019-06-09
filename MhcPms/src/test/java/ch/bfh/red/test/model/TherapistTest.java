package ch.bfh.red.test.model;

import ch.bfh.red.backend.models.Therapist;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ch.bfh.red.backend.factories.TherapistFactory;
import ch.bfh.red.backend.persistence.TherapistPersistenceManager;
import ch.bfh.red.test.StartupTest;

import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertThat;

public class TherapistTest extends StartupTest {
    private final TherapistFactory TherapistFactory = new TherapistFactory();

    private Therapist therapist;

    @Autowired
    private TherapistPersistenceManager TherapistManager;

    @Before
    public void generateTherapists() {
        therapist = TherapistFactory.create();

        TherapistManager.persistAll(therapist);
    }

    @Test
    public void testTherapistCreation() {
        assertThat(therapist, hasProperty("username"));
        assertThat(therapist, hasProperty("password"));
        assertThat(therapist, hasProperty("firstName"));
        assertThat(therapist, hasProperty("lastName"));
        assertThat(therapist, hasProperty("address"));
    }
}