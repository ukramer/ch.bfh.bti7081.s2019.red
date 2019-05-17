package ch.bfh.red.test.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import ch.bfh.red.test.tests.crud.AcademicTitleCrudTest;
import ch.bfh.red.test.tests.crud.AddressCrudTest;
import ch.bfh.red.test.tests.crud.PatientCrudTest;
import ch.bfh.red.test.tests.crud.SessionTypeCrudTest;
import ch.bfh.red.test.tests.crud.SingleSessionCrudTest;
import ch.bfh.red.test.tests.crud.TherapistCrudTest;
import ch.bfh.red.test.tests.crud.VisibilityCrudTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({
   AddressCrudTest.class,
   AcademicTitleCrudTest.class, 
   PatientCrudTest.class,
   SessionTypeCrudTest.class,
   SingleSessionCrudTest.class,
   TherapistCrudTest.class,
   VisibilityCrudTest.class,
})
public class DbCrudTest extends StartupTest {
	
}
