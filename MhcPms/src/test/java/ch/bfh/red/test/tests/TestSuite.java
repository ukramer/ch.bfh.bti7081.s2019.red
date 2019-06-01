package ch.bfh.red.test.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;; 

@RunWith(Suite.class)
@Suite.SuiteClasses({
   StartupTest.class,
   FactoryTest.class,
   BeanInstantiationTest.class,
   DbCrudTest.class
})
public class TestSuite {
	
}
