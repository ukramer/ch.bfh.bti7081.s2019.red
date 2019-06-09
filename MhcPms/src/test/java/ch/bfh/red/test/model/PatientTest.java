package ch.bfh.red.test.model;

import ch.bfh.red.backend.models.Patient;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ch.bfh.red.backend.factories.PatientFactory;
import ch.bfh.red.backend.persistence.PatientPersistenceManager;
import ch.bfh.red.test.StartupTest;

import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertThat;

public class PatientTest extends StartupTest {
    private final PatientFactory patientFactory = new PatientFactory();

    private Patient patient;

    @Autowired
    private PatientPersistenceManager PatientManager;

    @Before
    public void generatePatients() {
        patient = patientFactory.create();

        PatientManager.persistAll(patient);
    }

    @Test
    public void testPatientCreation() {
        assertThat(patient, hasProperty("firstName"));
        assertThat(patient, hasProperty("lastName"));
        assertThat(patient, hasProperty("address"));
    }
}