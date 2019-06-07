package ch.bfh.red.test.tests.crud;

import org.springframework.beans.factory.annotation.Autowired;

import ch.bfh.red.backend.factories.TherapistNoteFactory;
import ch.bfh.red.backend.models.TherapistNote;
import ch.bfh.red.backend.services.IService;
import ch.bfh.red.backend.services.TherapistNoteService;

public class TherapistNoteCrudTest extends CrudTest<TherapistNote> {
	
	@Autowired
	private TherapistNoteService service;
	
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
	protected IService<TherapistNote> getService() {
		return service;
	}
	
}
