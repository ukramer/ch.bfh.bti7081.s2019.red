package ch.bfh.red.backend.persistence;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import ch.bfh.red.backend.models.GroupSession;
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.models.Therapist;
import ch.bfh.red.backend.models.TherapistNote;
import ch.bfh.red.backend.models.Therapy;
import ch.bfh.red.backend.services.GroupSessionService;
import ch.bfh.red.backend.services.IService;

@Component
public class GroupSessionPersistenceManager implements IPersistenceManager<GroupSession> {

	@Autowired
	private GroupSessionService service;
	
	@Autowired
	@Lazy
	private PatientPersistenceManager patientManager;
	
	@Autowired
	@Lazy
	private TherapistPersistenceManager therapistManager;
	
	@Autowired
	@Lazy
	private TherapistNotePersistenceManager therapistNoteManager;
	
	@Autowired
	@Lazy
	private TherapyPersistenceManager therapyManager;
	
	@Override
	public IService<GroupSession> getService() {
		return service;
	}

	@Override
	public GroupSession persist(GroupSession t, Collection<Class<?>> classes) {
		classes.remove(GroupSession.class);
		if (classes.contains(Patient.class))
			patientManager.persist(t.getPatients(), new ArrayList<>(classes));
		if (classes.contains(Therapist.class))
			therapistManager.persist(t.getTherapists(), new ArrayList<>(classes));
//		if (classes.contains(TherapistNote.class))
//			therapistNoteManager.persist(t.get, classes)
//		if (classes.contains(Therapy.class))
//			therapyManager.persist(t.get, classes)
		return service.persist(t);
	}

	@Override
	public Collection<Class<?>> getConnectedClasses() {
		Collection<Class<?>> classes = new ArrayList<>();
		classes.add(Patient.class);
		classes.add(Therapist.class);
		classes.add(TherapistNote.class);
		classes.add(Therapy.class);
		return classes;
	}
	
}
