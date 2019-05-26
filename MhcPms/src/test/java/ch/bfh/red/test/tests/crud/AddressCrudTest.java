package ch.bfh.red.test.tests.crud;

import ch.bfh.red.backend.factories.AddressFactory;
import ch.bfh.red.backend.models.Address;
import ch.bfh.red.backend.persistence.PersistenceManager;

public class AddressCrudTest extends CrudTest<Address> {

	AddressFactory factory = new AddressFactory();
	
	@Override
	protected Address createInstance() {
		return factory.create();
	}

	@Override
	protected Integer getId(Address instance) {
		return instance.getId();
	}

	@Override
	protected void setAnUpdateValue(Address instance) {
		instance.setPostalCode(2198);
	}

	@Override
	protected PersistenceManager<Address> getPersistenceManager() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
