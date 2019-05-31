package ch.bfh.red.test.tests.crud;

import org.springframework.beans.factory.annotation.Autowired;

import ch.bfh.red.backend.factories.PatientFactory;
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.services.IService;
import ch.bfh.red.backend.services.PatientService;

public class PatientCrudTest extends CrudTest<Patient> {

	@Autowired
	private PatientService service;
	
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
	protected IService<Patient> getService() {
		return service;
	}
	
}
