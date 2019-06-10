package ch.bfh.red.backend.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import ch.bfh.red.backend.models.GroupSession;
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.models.SingleSession;
import ch.bfh.red.backend.models.Therapist;
import ch.bfh.red.backend.models.Therapy;
import ch.bfh.red.backend.services.IService;
import ch.bfh.red.backend.services.PatientService;

@Component
public class PatientPersistenceManager implements IPersistenceManager<Patient> {

	@Autowired
	private PatientService service;
	
	@Autowired
	@Lazy
	private TherapistPersistenceManager therapistManager;
	
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
	public IService<Patient> getService() {
		return service;
	}

	@Override
	public Patient persist(Patient t, Collection<Class<?>> classes) {
		classes.remove(Patient.class);
		if (classes.contains(SingleSession.class))
			singleSessionManager.persist(t.getSingleSessions(), new ArrayList<>(classes));
		if (classes.contains(GroupSession.class))
			groupSessionManager.persist(t.getGroupSessions(), new ArrayList<>(classes));
		if (classes.contains(Therapy.class))
			therapyManager.persist(t.getTherapies(), new ArrayList<>( classes));
		return service.persist(t);
	}

	@Override
	public Collection<Class<?>> getConnectedClasses() {
		List<Class<?>> classes = new ArrayList<>();
		classes.add(Therapist.class);
		classes.add(SingleSession.class);
		classes.add(GroupSession.class);
		classes.add(Therapy.class);
		return classes;
	}
	
}
