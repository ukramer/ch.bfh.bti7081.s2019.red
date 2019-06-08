package ch.bfh.red.test.crud;

import org.springframework.beans.factory.annotation.Autowired;

import ch.bfh.red.backend.factories.GroupSessionFactory;
import ch.bfh.red.backend.models.GroupSession;
import ch.bfh.red.backend.models.SessionType;
import ch.bfh.red.backend.persistence.GroupSessionPersistenceManager;
import ch.bfh.red.backend.persistence.IPersistenceManager;

public class GroupSessionCrudTest extends CrudTest<GroupSession> {
	
	@Autowired
	private GroupSessionPersistenceManager persistenceManager;
	
	private GroupSessionFactory factory = new GroupSessionFactory();

	@Override
	protected GroupSession createInstance() {
		return factory.create();
	}

	@Override
	protected Integer getId(GroupSession instance) {
		return instance.getId();
	}

	@Override
	protected void setAnUpdateValue(GroupSession instance) {
		SessionType type = SessionType.DISCUSSION;
		instance.setSessionType(type);
	}

	@Override
	protected IPersistenceManager<GroupSession> getPersistenceManager() {
		return persistenceManager;
	}
	
}
