package ch.bfh.red.test.tests.crud;

import ch.bfh.red.backend.models.Address;

public class AddressCrudTest extends CrudTest<Address> {

	@Override
	protected Address createInstance() {
		return new Address("Kirchbergstrasse", "23", 3456, "Thun");
	}

	@Override
	protected Integer getId(Address instance) {
		return instance.getId();
	}

	@Override
	protected void setAnUpdateValue(Address instance) {
		instance.setPostalCode(2198);
	}
	
}
