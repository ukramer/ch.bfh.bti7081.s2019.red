package ch.bfh.red.test.factory;

import ch.bfh.red.backend.factories.TherapistFactory;
import ch.bfh.red.backend.models.Therapist;

public class TherapistFactoryTest extends FactoryTest<Therapist> {

	public TherapistFactoryTest() {
		super(new TherapistFactory());
	}
	
}