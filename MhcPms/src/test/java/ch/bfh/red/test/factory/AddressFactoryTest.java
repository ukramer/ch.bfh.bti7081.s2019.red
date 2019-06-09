package ch.bfh.red.test.factory;

import ch.bfh.red.backend.factories.AddressFactory;
import ch.bfh.red.backend.models.Address;

public class AddressFactoryTest extends FactoryTest<Address> {

	public AddressFactoryTest() {
		super(new AddressFactory());
	}
	
}