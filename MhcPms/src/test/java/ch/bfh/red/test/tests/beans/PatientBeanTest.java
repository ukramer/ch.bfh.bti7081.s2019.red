package ch.bfh.red.test.tests.beans;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.repositories.PatientRepository;
import ch.bfh.red.backend.services.IService;
import ch.bfh.red.backend.services.PatientService;
import ch.bfh.red.test.tests.StartupTest;

public class PatientBeanTest extends StartupTest {
	
	@Autowired
	protected PatientRepository repository;

	@Autowired
	protected PatientService service;
	
	@Test
	public void testAutowiringProcess() {
		assertNotNull(repository);
		assertNotNull(service);
	}
	
}