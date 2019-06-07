package ch.bfh.red.test.tests.model.factory;

import ch.bfh.red.backend.factories.SingleSessionFactory;
import ch.bfh.red.backend.models.SingleSession;

public class SingleSessionFactoryTest extends FactoryTest<SingleSession> {

	public SingleSessionFactoryTest() {
		super(new SingleSessionFactory());
	}
	
}

