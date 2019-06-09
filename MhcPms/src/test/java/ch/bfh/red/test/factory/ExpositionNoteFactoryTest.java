package ch.bfh.red.test.factory;

import ch.bfh.red.backend.factories.ExpositionNoteFactory;
import ch.bfh.red.backend.models.ExpositionNote;

public class ExpositionNoteFactoryTest extends FactoryTest<ExpositionNote> {

	public ExpositionNoteFactoryTest() {
		super(new ExpositionNoteFactory());
	}
	
}
