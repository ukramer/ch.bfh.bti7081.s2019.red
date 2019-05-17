package ch.bfh.red.test.tests.crud;

import java.util.Date;

import ch.bfh.red.backend.models.AcademicTitle;
import ch.bfh.red.backend.models.Address;
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.models.SessionType;
import ch.bfh.red.backend.models.SingleSession;
import ch.bfh.red.backend.models.Therapist;

public class SingleSessionCrudTest extends CrudTest<SingleSession> {

	@Override
	protected SingleSession createInstance() {
		Patient patient = new Patient("Jürgen", "", new Address("Langstrasse", "12k", 7777, "Burgdorf"));
		AcademicTitle title = new AcademicTitle("Dr.", "");
		Address address = new Address("Winkelstrasse", "A", 2387, "Bülach");
		Therapist therapist = new Therapist("marle34", "1234", title, "Marlies", "Lotti", address);
		SessionType sessionType = new SessionType("SessionType", "");
		return new SingleSession(patient, therapist, new Date(), new Date(), sessionType);
	}

	@Override
	protected Integer getId(SingleSession instance) {
		throw new RuntimeException();
	}

	@Override
	protected void setAnUpdateValue(SingleSession instance) {
		instance.setSessionType(new SessionType("Other", ""));
	}
	
}
