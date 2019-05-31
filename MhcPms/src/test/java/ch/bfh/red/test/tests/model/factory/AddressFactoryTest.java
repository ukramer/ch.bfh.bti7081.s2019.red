package ch.bfh.red.test.tests.model.factory;

import java.util.List;

import org.junit.Test;

import ch.bfh.red.backend.factories.AddressFactory;
import ch.bfh.red.backend.models.Address;

public class AddressFactoryTest extends FactoryTest<Address> {

	public AddressFactoryTest() {
		super();
		this.factory = new AddressFactory();
	}
	
}
