package ch.bfh.red.test.factory;

import ch.bfh.red.backend.factories.TherapistNoteFactory;
import ch.bfh.red.backend.models.TherapistNote;

public class TherapistNoteFactoryTest extends FactoryTest<TherapistNote> {

	public TherapistNoteFactoryTest() {
		super(new TherapistNoteFactory());
	}
	
}
