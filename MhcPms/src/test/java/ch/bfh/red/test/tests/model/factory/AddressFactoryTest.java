package ch.bfh.red.test.tests.model.factory;

import ch.bfh.red.backend.factories.AddressFactory;
import ch.bfh.red.backend.models.Address;

public class AddressFactoryTest extends FactoryTest<Address> {

	public AddressFactoryTest() {
		super(new AddressFactory());
	}
	
}