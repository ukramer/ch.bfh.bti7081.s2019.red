package ch.bfh.red.test.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import ch.bfh.red.test.tests.crud.PatientCrudTest;
import ch.bfh.red.test.tests.crud.PatientNoteCrudTest;
import ch.bfh.red.test.tests.crud.SingleSessionCrudTest;
import ch.bfh.red.test.tests.crud.TherapistCrudTest;
import ch.bfh.red.test.tests.crud.TherapistNoteCrudTest;
import ch.bfh.red.test.tests.crud.TherapyCrudTest;
import ch.bfh.red.test.tests.crud.ExpositionNoteCrudTest;
import ch.bfh.red.test.tests.crud.GroupSessionCrudTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PatientCrudTest.class,
        TherapistCrudTest.class,
        PatientNoteCrudTest.class,
        TherapistNoteCrudTest.class,
        ExpositionNoteCrudTest.class,
        SingleSessionCrudTest.class,
        GroupSessionCrudTest.class,
        TherapyCrudTest.class
})
public class DbCrudTest extends StartupTest {

}
