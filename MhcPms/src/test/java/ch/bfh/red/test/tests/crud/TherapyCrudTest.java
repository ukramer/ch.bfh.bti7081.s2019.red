package ch.bfh.red.test.tests.crud;

import org.springframework.beans.factory.annotation.Autowired;

import ch.bfh.red.backend.factories.TherapyFactory;
import ch.bfh.red.backend.models.Therapy;
import ch.bfh.red.backend.persistence.IPersistenceManager;
import ch.bfh.red.backend.persistence.TherapyPersistenceManager;

public class TherapyCrudTest extends CrudTest<Therapy> {

	@Autowired
	private TherapyPersistenceManager persistenceManager;
	
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
	protected IPersistenceManager<Therapy> getPersistenceManager() {
		return persistenceManager;
	}
	
}
