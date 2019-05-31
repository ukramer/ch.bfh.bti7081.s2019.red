package ch.bfh.red.test.tests.crud;

import org.springframework.beans.factory.annotation.Autowired;

import ch.bfh.red.backend.factories.TherapyFactory;
import ch.bfh.red.backend.models.Therapy;
import ch.bfh.red.backend.services.IService;
import ch.bfh.red.backend.services.TherapyService;

public class TherapyCrudTest extends CrudTest<Therapy> {

	@Autowired
	private TherapyService service;
	
	private TherapyFactory factory = new TherapyFactory();
	
	@Override
	protected Therapy createInstance() {
		return factory.create();
	}

	@Override
	protected Integer getId(Therapy instance) {
		return instance.getId();
	}

	@Override
	protected void setAnUpdateValue(Therapy instance) {
		instance.setFinished(true);
	}

	@Override
	protected IService<Therapy> getService() {
		return service;
	}
	
}
