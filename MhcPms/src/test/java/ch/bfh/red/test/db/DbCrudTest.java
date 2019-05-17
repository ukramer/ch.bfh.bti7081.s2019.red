package ch.bfh.red.test.db;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import ch.bfh.red.MhcPmsApplication;
import ch.bfh.red.backend.models.Address;
import ch.bfh.red.backend.services.AddressService;
import ch.bfh.red.backend.services.AddressServiceImpl;

import org.springframework.test.context.support.AnnotationConfigContextLoader;

//@ComponentScan(basePackages = {"org.observer"})
//@RunWith(SpringRunner.class)
////@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@SpringBootTest
//@DataJpaTest

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MhcPmsApplication.class)
public class DbCrudTest {
	
//	
	@Configuration
    static class ContextConfiguration {

        // this bean will be injected into the OrderServiceTest class
        @Bean
        public AddressService orderService() {
        	AddressServiceImpl orderService = new AddressServiceImpl();
            // set properties, etc.
        	
            return orderService;
        }
    }

    @Autowired
    private CrudRepository<Address,Integer> orderService;
    
    @Autowired
    private AddressServiceImpl service;
    
    

    @Test
    public void testOrderService() {
        // test the orderService
    	
    	
    	System.out.println(orderService == null);
    	
    	System.out.println("if service == null: " +(service == null));
    	
    	service.saveOrUpdate(new Address("Kirchbergstrasse", "55", 4444, "Belp"));
    	
    	System.out.println(" :)");
    	
    }
	
	
	
//	
//	
//	
//	
//	
//	@Autowired
//	private CrudRepository<Address,Integer> service;
//	
//	
//	@Before
//	public void startApplication() {
////		MhcPmsApplication.start(new String[] {});
//	}
//	
//	@Test
//	public void testAddress() {
////		new AddressTest().createTest();
////		service.saveOrUpdate(new Address("Kirchbergstrasse", "55", 4444, "Zollikofen"));
//		System.out.println("");
//	}
//	
//	
//	private class AddressTest {
//		
//		
//		
//		public void createTest() {
////			service.saveOrUpdate(new Address("Kirchbergstrasse", "55", 4444, "Zollikofen"));
//			System.out.println("");
//		}
//		
//	}
	
}
