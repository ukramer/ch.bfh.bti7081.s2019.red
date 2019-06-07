package ch.bfh.red.test.tests.model.factory;

import ch.bfh.red.backend.factories.TherapistFactory;
import ch.bfh.red.backend.models.Therapist;

public class TherapistFactoryTest extends FactoryTest<Therapist> {

	public TherapistFactoryTest() {
		super(new TherapistFactory());
	}
	
}