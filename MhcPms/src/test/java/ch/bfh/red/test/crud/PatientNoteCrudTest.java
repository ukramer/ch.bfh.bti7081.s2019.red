package ch.bfh.red.test.crud;

import org.springframework.beans.factory.annotation.Autowired;

import ch.bfh.red.backend.factories.PatientNoteFactory;
import ch.bfh.red.backend.models.PatientNote;
import ch.bfh.red.backend.persistence.IPersistenceManager;
import ch.bfh.red.backend.persistence.PatientNotePersistenceManager;

public class PatientNoteCrudTest extends CrudTest<PatientNote> {
	
	@Autowired
	private PatientNotePersistenceManager persistenceManager;
	
	private PatientNoteFactory factory = new PatientNoteFactory();

	@Override
	protected PatientNote createInstance() {
		return factory.create();
	}

	@Override
	protected Integer getId(PatientNote instance) {
		return instance.getId();
	}

	@Override
	protected void setAnUpdateValue(PatientNote instance) {
		instance.setText("new text");
	}

	@Override
	protected IPersistenceManager<PatientNote> getPersistenceManager() {
		return persistenceManager;
	}
	
}
