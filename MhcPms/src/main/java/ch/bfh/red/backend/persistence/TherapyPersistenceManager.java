package ch.bfh.red.backend.persistence;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import ch.bfh.red.backend.models.ExpositionNote;
import ch.bfh.red.backend.models.GroupSession;
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.models.PatientNote;
import ch.bfh.red.backend.models.SingleSession;
import ch.bfh.red.backend.models.Therapist;
import ch.bfh.red.backend.models.TherapistNote;
import ch.bfh.red.backend.models.Therapy;
import ch.bfh.red.backend.services.IService;
import ch.bfh.red.backend.services.TherapyService;

@Component
public class TherapyPersistenceManager implements IPersistenceManager<Therapy> {

	@Autowired
	private TherapyService service;
	
	@Autowired
	@Lazy
	private PatientPersistenceManager patientManager;
	
	@Autowired
	@Lazy
	private TherapistPersistenceManager therapistManager;
	
	@Autowired
	@Lazy
	private PatientNotePersistenceManager patientNoteManager;
	
	@Autowired
	@Lazy
	private TherapistNotePersistenceManager therapistNoteManager;
	
	@Autowired
	@Lazy
	private ExpositionNotePersistenceManager expositionNoteManager;
	
	@Autowired
	@Lazy
	private SingleSessionPersistenceManager singleSessionManager;
	
	@Autowired
	@Lazy
	private GroupSessionPersistenceManager groupSessionManager;
	
	@Override
	public IService<Therapy> getService() {
		return service;
	}

	@Override
	public Therapy persist(Therapy t, Collection<Class<?>> classes) {
		classes.remove(Therapy.class);
		if (classes.contains(Patient.class))
			patientManager.persist(t.getPatient(), new ArrayList<>(classes));
		if (classes.contains(Therapist.class))
			therapistManager.persist(t.getTherapist(), new ArrayList<>(classes));
		if (classes.contains(PatientNote.class))
			patientNoteManager.persist(t.getPatientNotes(), new ArrayList<>(classes));
		if (classes.contains(TherapistNote.class))
			therapistNoteManager.persist(t.getTherapistNotes(), new ArrayList<>(classes));
//		if (classes.contains(ExpositionNote.class))
//			expositionNoteManager.persist(t.get, classes)
		if (classes.contains(SingleSession.class))
			singleSessionManager.persist(t.getSingleSessions(), new ArrayList<>(classes));
		if (classes.contains(GroupSession.class))
			groupSessionManager.persist(t.getGroupSessions(), new ArrayList<>(classes));
		return service.persist(t);
	}

	@Override
	public Collection<Class<?>> getConnectedClasses() {
		Collection<Class<?>> classes = new ArrayList<>();
		classes.add(Patient.class);
		classes.add(Therapist.class);
		classes.add(PatientNote.class);
		classes.add(TherapistNote.class);
		classes.add(ExpositionNote.class);
		classes.add(SingleSession.class);
		classes.add(GroupSession.class);
		return classes;
	}
	
}
