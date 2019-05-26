package ch.bfh.red.test.tests.crud;

import ch.bfh.red.backend.models.SessionType;
import ch.bfh.red.backend.persistence.PersistenceManager;

public class SessionTypeCrudTest extends CrudTest<SessionType> {

	@Override
	protected SessionType createInstance() {
		return new SessionType("Discussion", "");
	}

	@Override
	protected Integer getId(SessionType instance) {
		return instance.getId();
	}

	@Override
	protected void setAnUpdateValue(SessionType instance) {
		instance.setName("Session");
	}

	@Override
	protected PersistenceManager<SessionType> getPersistenceManager() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
