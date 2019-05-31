package ch.bfh.red.test.tests.crud;

import org.springframework.beans.factory.annotation.Autowired;

import ch.bfh.red.backend.factories.TherapistFactory;
import ch.bfh.red.backend.models.Therapist;
import ch.bfh.red.backend.services.IService;
import ch.bfh.red.backend.services.TherapistService;

public class TherapistCrudTest extends CrudTest<Therapist> {
	
	@Autowired
	private TherapistService service;
	
	private TherapistFactory factory = new TherapistFactory();
	
	@Override
	protected Therapist createInstance() {
		return factory.create();
	}

	@Override
	protected Integer getId(Therapist instance) {
		return instance.getId();
	}

	@Override
	protected void setAnUpdateValue(Therapist instance) {
		instance.setUsername("ott81");
	}

	@Override
	protected IService<Therapist> getService() {
		return service;
	}
	
}
