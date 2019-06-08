package ch.bfh.red.test.factory;

import ch.bfh.red.backend.factories.PatientFactory;
import ch.bfh.red.backend.models.Patient;

public class PatientFactoryTest extends FactoryTest<Patient> {

	public PatientFactoryTest() {
		super(new PatientFactory());
	}
	
}
