package ch.bfh.red.test.crud;

import org.springframework.beans.factory.annotation.Autowired;

import ch.bfh.red.backend.factories.ExpositionNoteFactory;
import ch.bfh.red.backend.models.ExpositionNote;
import ch.bfh.red.backend.persistence.ExpositionNotePersistenceManager;
import ch.bfh.red.backend.persistence.IPersistenceManager;

public class ExpositionNoteCrudTest extends CrudTest<ExpositionNote> {

	@Autowired
	private ExpositionNotePersistenceManager persistenceManager;
	
	private ExpositionNoteFactory factory = new ExpositionNoteFactory();
	
    @Override
    protected ExpositionNote createInstance() {
    	return factory.create();
    }

    @Override
    protected Integer getId(ExpositionNote instance) {
        return instance.getId();
    }

    @Override
    protected void setAnUpdateValue(ExpositionNote instance) {
        instance.setDegreeOfExposure(9);
    }

	@Override
	protected IPersistenceManager<ExpositionNote> getPersistenceManager() {
		return persistenceManager;
	}

}

