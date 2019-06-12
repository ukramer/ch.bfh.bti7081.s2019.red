package ch.bfh.red.test.factory;

import ch.bfh.red.backend.factories.GroupSessionFactory;
import ch.bfh.red.backend.models.GroupSession;

public class GroupSessionFactoryTest extends FactoryTest<GroupSession> {

	public GroupSessionFactoryTest() {
		super(new GroupSessionFactory());
	}
	
}
