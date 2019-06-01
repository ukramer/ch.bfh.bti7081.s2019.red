package ch.bfh.red.test.tests.crud;

import org.springframework.beans.factory.annotation.Autowired;

import ch.bfh.red.backend.factories.GroupSessionFactory;
import ch.bfh.red.backend.models.GroupSession;
import ch.bfh.red.backend.models.SessionType;
import ch.bfh.red.backend.services.GroupSessionService;
import ch.bfh.red.backend.services.IService;

public class GroupSessionCrudTest extends CrudTest<GroupSession> {
	
	@Autowired
	private GroupSessionService service;
	
	private GroupSessionFactory factory;

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
	protected IService<GroupSession> getService() {
		return service;
	}
	
}
