package ch.bfh.red.test.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import ch.bfh.red.test.tests.beans.AcademicTitleBeanTest;
import ch.bfh.red.test.tests.beans.AddressBeanTest;
import ch.bfh.red.test.tests.beans.GroupSessionBeanTest;
import ch.bfh.red.test.tests.beans.PatientBeanTest;
import ch.bfh.red.test.tests.beans.PatientNoteBeanTest;
import ch.bfh.red.test.tests.beans.SessionTypeBeanTest;
import ch.bfh.red.test.tests.beans.SingleSessionBeanTest;
import ch.bfh.red.test.tests.beans.TherapistBeanTest;
import ch.bfh.red.test.tests.beans.TherapyBeanTest;
import ch.bfh.red.test.tests.beans.TherapyTypeBeanTest;
import ch.bfh.red.test.tests.beans.VisibilityBeanTest;

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
   VisibilityBeanTest.class
})
public class BeanInstationTest {
	
}
