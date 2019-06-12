package ch.bfh.red;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import ch.bfh.red.test.BeanInstantiationTest;
import ch.bfh.red.test.DbCrudTest;
import ch.bfh.red.test.FactoryTest;
import ch.bfh.red.test.StartupTest;; 

@RunWith(Suite.class)
@Suite.SuiteClasses({
   StartupTest.class,
   FactoryTest.class,
   BeanInstantiationTest.class,
   DbCrudTest.class
})
public class TestSuite {
	
}
