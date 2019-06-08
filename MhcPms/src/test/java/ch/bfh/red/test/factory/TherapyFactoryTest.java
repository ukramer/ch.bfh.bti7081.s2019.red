package ch.bfh.red.test.factory;

import ch.bfh.red.backend.factories.TherapyFactory;
import ch.bfh.red.backend.models.Therapy;

public class TherapyFactoryTest extends FactoryTest<Therapy> {

	public TherapyFactoryTest() {
		super(new TherapyFactory());
	}
	
}
