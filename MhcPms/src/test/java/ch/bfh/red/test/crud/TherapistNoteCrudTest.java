package ch.bfh.red.test.crud;

import org.springframework.beans.factory.annotation.Autowired;

import ch.bfh.red.backend.factories.TherapistNoteFactory;
import ch.bfh.red.backend.models.TherapistNote;
import ch.bfh.red.backend.persistence.IPersistenceManager;
import ch.bfh.red.backend.persistence.TherapistNotePersistenceManager;

public class TherapistNoteCrudTest extends CrudTest<TherapistNote> {
	
	@Autowired
	private TherapistNotePersistenceManager persistenceManager;
	
	private TherapistNoteFactory factory = new TherapistNoteFactory();

	@Override
	protected TherapistNote createInstance() {
		return factory.create();
	}

	@Override
	protected Integer getId(TherapistNote instance) {
		return instance.getId();
	}

	@Override
	protected void setAnUpdateValue(TherapistNote instance) {
		instance.setText("new text");
	}

	@Override
	protected IPersistenceManager<TherapistNote> getPersistenceManager() {
		return persistenceManager;
	}
	
}
