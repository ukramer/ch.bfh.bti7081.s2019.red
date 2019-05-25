package ch.bfh.red.test.tests;

import ch.bfh.red.test.tests.beans.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)

@Suite.SuiteClasses({
        AddressBeanTest.class,
        PatientBeanTest.class,
        AcademicTitleBeanTest.class,
        TherapistBeanTest.class,
        SingleSessionBeanTest.class,
        GroupSessionBeanTest.class,
        PatientNoteBeanTest.class,
        TherapistBeanTest.class,
        TherapyBeanTest.class,
        TherapyTypeBeanTest.class,
        SessionTypeBeanTest.class,
        ExpositionNoteBeanTest.class




})
public class BeanInstationTest {

}
