package ch.bfh.red.test.tests.crud;

import java.util.Date;

import ch.bfh.red.backend.models.AcademicTitle;
import ch.bfh.red.backend.models.Address;
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.models.SessionType;
import ch.bfh.red.backend.models.SingleSession;
import ch.bfh.red.backend.models.Therapist;
import ch.bfh.red.backend.persistence.PersistenceManager;
import ch.bfh.red.backend.services.IService;
import org.springframework.beans.factory.annotation.Autowired;

public class SingleSessionCrudTest extends CrudTest<SingleSession> {

	// TODO remove
	@Autowired
	private IService<Therapist> therapistIService;

	// TODO remove
	@Autowired
	private IService<SessionType> sessionTypeIService;

	// TODO remove
	@Autowired
	private IService<AcademicTitle> academicTitleIService;

	// TODO remove
	@Autowired
	private IService<Patient> patientIService;

	// TODO remove
	@Autowired
	private IService<Address> addressIService;

	@Override
	protected SingleSession createInstance() {
		Address address = new Address("Winkelstrasse", "A", 2387, "Bülach");
		addressIService.add(address);

		Address address2 = new Address("Langstrasse", "12k", 7777, "Burgdorf");
		addressIService.add(address2);

		Patient patient = new Patient("Jürgen", "", address2);
		patientIService.add(patient);

		AcademicTitle title = new AcademicTitle("Dr.", "");
		academicTitleIService.add(title);

		Therapist therapist = new Therapist("marle34", "1234", title, "Marlies", "Lotti", address);
		therapistIService.add(therapist);

		SessionType sessionType = new SessionType("SessionType", "");
		sessionTypeIService.add(sessionType);
		return new SingleSession(patient, therapist, new Date(), new Date(), sessionType);
	}

	@Override
	protected Integer getId(SingleSession instance) {
		return instance.getId();
	}

	@Override
	protected void setAnUpdateValue(SingleSession instance) {
		SessionType sessionType = new SessionType("Other", "");
		sessionTypeIService.add(sessionType);
		instance.setSessionType(sessionType);
	}

	@Override
	protected PersistenceManager<SingleSession> getPersistenceManager() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
