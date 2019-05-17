package ch.bfh.red.test.tests.crud;

import ch.bfh.red.backend.models.Address;
import ch.bfh.red.backend.models.Patient;

public class PatientCrudTest extends CrudTest<Patient> {

	@Override
	protected Patient createInstance() {
		return new Patient("Jürgen", "", new Address("Langstrasse", "12k", 7777, "Burgdorf"));
	}

	@Override
	protected Integer getId(Patient instance) {
		throw new RuntimeException();
	}

	@Override
	protected void setAnUpdateValue(Patient instance) {
		instance.setFirstName("Karl");
	}
	
}
