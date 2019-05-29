package ch.bfh.red.test.tests.crud;

import ch.bfh.red.backend.factories.AddressFactory;
import ch.bfh.red.backend.models.Address;
import ch.bfh.red.backend.persistence.AbstractPersistenceManager;
import ch.bfh.red.backend.persistence.AddressPersistenceManager;

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
    protected AbstractPersistenceManager<Address> getPersistenceManager() {
        return new AddressPersistenceManager(service);
    }

}
