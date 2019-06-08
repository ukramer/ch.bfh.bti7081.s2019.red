package ch.bfh.red.test.tests.crud;

import org.springframework.beans.factory.annotation.Autowired;

import ch.bfh.red.backend.factories.PatientFactory;
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.persistence.IPersistenceManager;
import ch.bfh.red.backend.persistence.PatientPersistenceManager;

public class PatientCrudTest extends CrudTest<Patient> {

	@Autowired
	private PatientPersistenceManager persistenceManager;
	
	private PatientFactory factory = new PatientFactory();
	
	@Override
	protected Patient createInstance() {
		return factory.create(); 
	}

	@Override
	protected Integer getId(Patient instance) {
		return instance.getId();
	}

	@Override
	protected void setAnUpdateValue(Patient instance) {
		instance.setFirstName("Karl");
	}

	@Override
	protected IPersistenceManager<Patient> getPersistenceManager() {
		return persistenceManager;
	}
	
}
