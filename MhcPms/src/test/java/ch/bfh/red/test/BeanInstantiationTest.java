package ch.bfh.red.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import ch.bfh.red.test.beans.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PatientBeanTest.class,
        TherapistBeanTest.class,
        SingleSessionBeanTest.class,
        GroupSessionBeanTest.class,
        PatientNoteBeanTest.class,
        TherapistBeanTest.class,
        TherapyBeanTest.class,
        ExpositionNoteBeanTest.class
})
public class BeanInstantiationTest {

}
