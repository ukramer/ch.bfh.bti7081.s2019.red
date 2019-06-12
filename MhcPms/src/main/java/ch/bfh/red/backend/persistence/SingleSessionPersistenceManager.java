package ch.bfh.red.backend.persistence;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.models.SingleSession;
import ch.bfh.red.backend.models.Therapist;
import ch.bfh.red.backend.models.TherapistNote;
import ch.bfh.red.backend.services.IService;
import ch.bfh.red.backend.services.SingleSessionService;

@Component
public class SingleSessionPersistenceManager implements IPersistenceManager<SingleSession> {

	@Autowired
	private SingleSessionService service;
	
	@Autowired
	@Lazy
	private PatientPersistenceManager patientManager;
	
	@Autowired
	@Lazy
	private TherapistPersistenceManager therapistManager;
	
	@Autowired
	@Lazy
	private TherapistNotePersistenceManager therapistNoteManager;
	
	@Override
	public IService<SingleSession> getService() {
		return service;
	}

	@Override
	public SingleSession persist(SingleSession t, Collection<Class<?>> classes) {
		classes.remove(SingleSession.class);
		if (classes.contains(Patient.class))
			patientManager.persist(t.getPatient(), new ArrayList<>(classes));
		if (classes.contains(Therapist.class))
			therapistManager.persist(t.getTherapist(), new ArrayList<>(classes));
//		if (classes.contains(TherapistNote.class))
//			therapistNoteManager.persist(t., classes)
		return service.persist(t);
	}
	
	@Override
	public Collection<Class<?>> getConnectedClasses() {
		Collection<Class<?>> classes = new ArrayList<>();
		classes.add(Patient.class);
		classes.add(Therapist.class);
		classes.add(TherapistNote.class);
		return classes;
	}
	
}
