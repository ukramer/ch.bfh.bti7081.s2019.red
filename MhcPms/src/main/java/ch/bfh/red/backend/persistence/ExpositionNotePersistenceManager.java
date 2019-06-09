package ch.bfh.red.backend.persistence;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import ch.bfh.red.backend.models.ExpositionNote;
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.services.ExpositionNoteService;
import ch.bfh.red.backend.services.IService;

@Component
public class ExpositionNotePersistenceManager implements IPersistenceManager<ExpositionNote>{

	@Autowired
	private ExpositionNoteService service;
	
	@Autowired
	@Lazy
	private PatientPersistenceManager patientManager;
	
	@Override
	public IService<ExpositionNote> getService() {
		return service;
	}

	@Override
	public ExpositionNote persist(ExpositionNote t, Collection<Class<?>> classes) {
		classes.remove(ExpositionNote.class);
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
