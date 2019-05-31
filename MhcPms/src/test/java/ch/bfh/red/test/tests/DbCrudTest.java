package ch.bfh.red.test.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import ch.bfh.red.test.tests.crud.PatientCrudTest;
import ch.bfh.red.test.tests.crud.TherapistCrudTest;
import ch.bfh.red.test.tests.crud.ExpositionNoteCrudTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PatientCrudTest.class,
        TherapistCrudTest.class,
        ExpositionNoteCrudTest.class
})
public class DbCrudTest extends StartupTest {

}
