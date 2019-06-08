package ch.bfh.red.backend.persistence;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.models.PatientNote;
import ch.bfh.red.backend.services.IService;
import ch.bfh.red.backend.services.PatientNoteService;

@Component
public class PatientNotePersistenceManager implements IPersistenceManager<PatientNote> {

	@Autowired
	private PatientNoteService service;
	
	@Autowired
	@Lazy
	private PatientPersistenceManager patientManager;
	
	@Override
	public IService<PatientNote> getService() {
		return service;
	}

	@Override
	public PatientNote persist(PatientNote t, Collection<Class<?>> classes) {
		classes.remove(PatientNote.class);
		if (classes.contains(Patient.class))
			patientManager.persist(t.getPatient(), new ArrayList<>(classes));
		return service.persist(t);
	}

	@Override
	public Collection<Class<?>> getConnectedClasses() {
		Collection<Class<?>> classes = new ArrayList<>();
		classes.add(Patient.class);
		return classes;
	}
	
}
