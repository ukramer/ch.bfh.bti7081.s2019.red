package ch.bfh.red.backend.persistence;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import ch.bfh.red.backend.models.Therapist;
import ch.bfh.red.backend.models.TherapistNote;
import ch.bfh.red.backend.services.IService;
import ch.bfh.red.backend.services.TherapistNoteService;

@Component
public class TherapistNotePersistenceManager implements IPersistenceManager<TherapistNote> {

	@Autowired
	private TherapistNoteService service;
	
	@Autowired
	@Lazy
	private TherapistPersistenceManager therapistManager;
	
	@Override
	public IService<TherapistNote> getService() {
		return service;
	}

	@Override
	public TherapistNote persist(TherapistNote t, Collection<Class<?>> classes) {
		classes.remove(TherapistNote.class);
		if (classes.contains(Therapist.class))
			therapistManager.persist(t.getTherapist(), new ArrayList<>(classes));
		return service.persist(t);
	}

	@Override
	public Collection<Class<?>> getConnectedClasses() {
		Collection<Class<?>> classes = new ArrayList<>();
		classes.add(Therapist.class);
		return classes;
	}
	
}
