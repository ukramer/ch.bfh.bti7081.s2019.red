package ch.bfh.red.test.tests.crud;

import ch.bfh.red.backend.models.AcademicTitle;
import ch.bfh.red.backend.models.Address;
import ch.bfh.red.backend.models.Therapist;

public class TherapistCrudTest extends CrudTest<Therapist> {

	@Override
	protected Therapist createInstance() {
		AcademicTitle title = new AcademicTitle("Dr.", "");
		Address address = new Address("Winkelstrasse", "A", 2387, "Bülach");
		return new Therapist("marle34", "1234", title, "Marlies", "Lotti", address);
	}

	@Override
	protected Integer getId(Therapist instance) {
		throw new RuntimeException();
	}

	@Override
	protected void setAnUpdateValue(Therapist instance) {
		instance.setUsername("ott81");
	}
	
}
