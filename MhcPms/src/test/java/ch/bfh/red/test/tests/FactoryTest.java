package ch.bfh.red.test.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import ch.bfh.red.test.tests.model.factory.ExpositionNoteFactoryTest;
import ch.bfh.red.test.tests.model.factory.GroupSessionFactoryTest;
import ch.bfh.red.test.tests.model.factory.PatientFactoryTest;
import ch.bfh.red.test.tests.model.factory.PatientNoteFactoryTest;
import ch.bfh.red.test.tests.model.factory.SingleSessionFactoryTest;
import ch.bfh.red.test.tests.model.factory.TherapistFactoryTest;
import ch.bfh.red.test.tests.model.factory.TherapistNoteFactoryTest;
import ch.bfh.red.test.tests.model.factory.TherapyFactoryTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	PatientFactoryTest.class,
	TherapistFactoryTest.class,
	PatientNoteFactoryTest.class,
	TherapistNoteFactoryTest.class,
	ExpositionNoteFactoryTest.class,
	SingleSessionFactoryTest.class,
	GroupSessionFactoryTest.class,
	TherapyFactoryTest.class
})
public class FactoryTest extends StartupTest {
	
}
