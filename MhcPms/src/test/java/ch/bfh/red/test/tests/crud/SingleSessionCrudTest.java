//package ch.bfh.red.test.tests.crud;
//
//import java.util.Date;
//
//import ch.bfh.red.backend.models.AcademicTitle;
//import ch.bfh.red.backend.models.Address;
//import ch.bfh.red.backend.models.Patient;
//import ch.bfh.red.backend.models.SessionType;
//import ch.bfh.red.backend.models.SingleSession;
//import ch.bfh.red.backend.models.Therapist;
//import ch.bfh.red.backend.persistence.PersistenceManager;
//import ch.bfh.red.backend.persistence.SingleSessionPersistenceManager;
//import ch.bfh.red.backend.services.IService;
//import org.springframework.beans.factory.annotation.Autowired;
//
//public class SingleSessionCrudTest extends CrudTest<SingleSession> {
//
//	// TODO remove
//	@Autowired
//	private IService<Therapist> therapistIService;
//
//	// TODO remove
//	@Autowired
//	private IService<Patient> patientIService;
//
//	@Override
//	protected SingleSession createInstance() {
//		Address address = new Address("Winkelstrasse", "A", 2387, "Bülach");
//
//		Address address2 = new Address("Langstrasse", "12k", 7777, "Burgdorf");
//
//		Patient patient = new Patient("Jürgen", "", address2);
//		patientIService.add(patient);
//
//		AcademicTitle title = AcademicTitle.DOCTOR;
//
//
//		Therapist therapist = new Therapist("marle34", "1234", title, "Marlies", "Lotti", address);
//		therapistIService.add(therapist);
//
//		SessionType sessionType = SessionType.TALK;
//
//		return new SingleSession(patient, therapist, new Date(), new Date(), sessionType);
//	}
//
//	@Override
//	protected Integer getId(SingleSession instance) {
//		return instance.getId();
//	}
//
//	@Override
//	protected void setAnUpdateValue(SingleSession instance) {
//		SessionType type = SessionType.DISCUSSION;
//		instance.setSessionType(type);
//	}
//
//	@Override
//	protected PersistenceManager<SingleSession> getPersistenceManager() {
//		return new SingleSessionPersistenceManager();
//	}
//	
//}
