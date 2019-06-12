package ch.bfh.red.test.factory;

import ch.bfh.red.backend.factories.PatientNoteFactory;
import ch.bfh.red.backend.models.PatientNote;

public class PatientNoteFactoryTest extends FactoryTest<PatientNote> {

	public PatientNoteFactoryTest() {
		super(new PatientNoteFactory());
	}
	
}