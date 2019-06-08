package ch.bfh.red.test.crud;

import org.springframework.beans.factory.annotation.Autowired;

import ch.bfh.red.backend.factories.TherapistFactory;
import ch.bfh.red.backend.models.Therapist;
import ch.bfh.red.backend.persistence.IPersistenceManager;
import ch.bfh.red.backend.persistence.TherapistPersistenceManager;

public class TherapistCrudTest extends CrudTest<Therapist> {
	
	@Autowired
	private TherapistPersistenceManager persistenceManager;
	
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
	protected IPersistenceManager<Therapist> getPersistenceManager() {
		return persistenceManager;
	}
	
}
