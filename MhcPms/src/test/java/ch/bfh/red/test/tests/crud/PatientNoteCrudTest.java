package ch.bfh.red.test.tests.crud;

import org.springframework.beans.factory.annotation.Autowired;

import ch.bfh.red.backend.factories.PatientNoteFactory;
import ch.bfh.red.backend.models.PatientNote;
import ch.bfh.red.backend.services.IService;
import ch.bfh.red.backend.services.PatientNoteService;

public class PatientNoteCrudTest extends CrudTest<PatientNote> {
	
	@Autowired
	private PatientNoteService service;
	
	private PatientNoteFactory factory;

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
	protected IService<PatientNote> getService() {
		return service;
	}
	
}
