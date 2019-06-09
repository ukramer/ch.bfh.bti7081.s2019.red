package ch.bfh.red.test.crud;

import org.springframework.beans.factory.annotation.Autowired;

import ch.bfh.red.backend.factories.SingleSessionFactory;
import ch.bfh.red.backend.models.SessionType;
import ch.bfh.red.backend.models.SingleSession;
import ch.bfh.red.backend.persistence.IPersistenceManager;
import ch.bfh.red.backend.persistence.SingleSessionPersistenceManager;

public class SingleSessionCrudTest extends CrudTest<SingleSession> {
	
	@Autowired
	private SingleSessionPersistenceManager persistenceManager;
	
	private SingleSessionFactory factory = new SingleSessionFactory();

	@Override
	protected SingleSession createInstance() {
		return factory.create();
	}

	@Override
	protected Integer getId(SingleSession instance) {
		return instance.getId();
	}

	@Override
	protected void setAnUpdateValue(SingleSession instance) {
		SessionType type = SessionType.DISCUSSION;
		instance.setSessionType(type);
	}

	@Override
	protected IPersistenceManager<SingleSession> getPersistenceManager() {
		return persistenceManager;
	}
	
}
