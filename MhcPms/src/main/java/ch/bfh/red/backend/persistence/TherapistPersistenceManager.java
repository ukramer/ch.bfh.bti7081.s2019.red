package ch.bfh.red.backend.persistence;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import ch.bfh.red.backend.models.GroupSession;
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.models.SingleSession;
import ch.bfh.red.backend.models.Therapist;
import ch.bfh.red.backend.models.Therapy;
import ch.bfh.red.backend.services.IService;
import ch.bfh.red.backend.services.TherapistService;

@Component
public class TherapistPersistenceManager implements IPersistenceManager<Therapist> {

	@Autowired
	private TherapistService service;
	
	@Autowired
	@Lazy
	private PatientPersistenceManager patientManager;
	
	@Autowired
	@Lazy
	private SingleSessionPersistenceManager singleSessionManager;
	
	@Autowired
	@Lazy
	private GroupSessionPersistenceManager groupSessionManager;
	
	@Autowired
	@Lazy
	private TherapyPersistenceManager therapyManager;
	
	@Override
	public IService<Therapist> getService() {
		return service;
	}

	@Override
	public Therapist persist(Therapist t, Collection<Class<?>> classes) {
		classes.remove(Therapist.class);
		if  (classes.contains(Patient.class))
			patientManager.persist(t.getPatients(), new ArrayList<>(classes));
		if (classes.contains(SingleSession.class))
			singleSessionManager.persist(t.getSingleSessions(), new ArrayList<>(classes));
		if (classes.contains(GroupSession.class))
			groupSessionManager.persist(t.getGroupSessions(), new ArrayList<>(classes));
		if (classes.contains(Therapy.class))
			therapyManager.persist(t.getTherapies(), new ArrayList<>(classes));
		return service.persist(t);
	}

	@Override
	public Collection<Class<?>> getConnectedClasses() {
		Collection<Class<?>> classes = new ArrayList<>();
		classes.add(Patient.class);
		classes.add(SingleSession.class);
		classes.add(GroupSession.class);
		classes.add(Therapy.class);
		return classes;
	}
	
}
