package ch.bfh.red.test.model;

import ch.bfh.red.backend.models.Therapy;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ch.bfh.red.backend.factories.TherapyFactory;
import ch.bfh.red.backend.persistence.TherapyPersistenceManager;
import ch.bfh.red.test.StartupTest;

import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertThat;

public class TherapyTest extends StartupTest {
    private final TherapyFactory TherapyFactory = new TherapyFactory();

    private Therapy therapy;

    @Autowired
    private TherapyPersistenceManager TherapyManager;

    @Before
    public void generateTherapies() {
        therapy = TherapyFactory.create();
        TherapyManager.persistAll(therapy);
    }

    @Test
    public void testTherapyCreation() {
        assertThat(therapy, hasProperty("startDate"));
        assertThat(therapy, hasProperty("therapyType"));
        assertThat(therapy, hasProperty("patient"));
        assertThat(therapy, hasProperty("therapist"));
    }
}