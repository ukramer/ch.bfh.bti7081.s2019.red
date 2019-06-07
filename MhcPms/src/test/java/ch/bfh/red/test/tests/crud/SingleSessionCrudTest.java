package ch.bfh.red.test.tests.crud;

import org.springframework.beans.factory.annotation.Autowired;

import ch.bfh.red.backend.factories.SingleSessionFactory;
import ch.bfh.red.backend.models.SessionType;
import ch.bfh.red.backend.models.SingleSession;
import ch.bfh.red.backend.services.IService;
import ch.bfh.red.backend.services.SingleSessionService;

public class SingleSessionCrudTest extends CrudTest<SingleSession> {
	
	@Autowired
	private SingleSessionService service;
	
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
	protected IService<SingleSession> getService() {
		return service;
	}
	
}
