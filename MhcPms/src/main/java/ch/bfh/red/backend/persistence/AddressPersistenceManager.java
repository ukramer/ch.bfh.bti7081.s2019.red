package ch.bfh.red.backend.persistence;

import ch.bfh.red.backend.models.Address;
import ch.bfh.red.backend.services.AddressServiceImpl;
import ch.bfh.red.backend.services.IService;

import java.util.Collection;

public class AddressPersistenceManager extends AbstractPersistenceManager<Address> {

    public AddressPersistenceManager() {
        this.service = new AddressServiceImpl();
    }


    public AddressPersistenceManager(IService<Address> service) {
        this.service = service;
    }

    @Override
    public Address persist(Address model) {

        service.add(model);
        return service.getRepository().findById(model.getId()).get();

    }


}
