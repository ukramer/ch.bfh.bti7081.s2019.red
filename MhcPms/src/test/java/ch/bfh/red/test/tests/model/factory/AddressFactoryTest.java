package ch.bfh.red.test.tests.model.factory;

import java.util.List;

import org.junit.Test;

import ch.bfh.red.backend.factories.AddressFactory;
import ch.bfh.red.backend.models.Address;

public class AddressFactoryTest {
	
	@Test
	public void test() {
		AddressFactory factory = new AddressFactory();
		List<Address> addresses = factory.generateAddresses(100);
		
		for (Address address: addresses)
			System.out.println(address);
	}
	
}
