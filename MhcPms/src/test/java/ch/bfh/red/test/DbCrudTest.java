package ch.bfh.red.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import ch.bfh.red.test.crud.ExpositionNoteCrudTest;
import ch.bfh.red.test.crud.GroupSessionCrudTest;
import ch.bfh.red.test.crud.PatientCrudTest;
import ch.bfh.red.test.crud.PatientNoteCrudTest;
import ch.bfh.red.test.crud.SingleSessionCrudTest;
import ch.bfh.red.test.crud.TherapistCrudTest;
import ch.bfh.red.test.crud.TherapistNoteCrudTest;
import ch.bfh.red.test.crud.TherapyCrudTest;

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
