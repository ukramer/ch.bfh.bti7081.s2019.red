package ch.bfh.red.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import ch.bfh.red.test.factory.ExpositionNoteFactoryTest;
import ch.bfh.red.test.factory.GroupSessionFactoryTest;
import ch.bfh.red.test.factory.PatientFactoryTest;
import ch.bfh.red.test.factory.PatientNoteFactoryTest;
import ch.bfh.red.test.factory.SingleSessionFactoryTest;
import ch.bfh.red.test.factory.TherapistFactoryTest;
import ch.bfh.red.test.factory.TherapistNoteFactoryTest;
import ch.bfh.red.test.factory.TherapyFactoryTest;

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
