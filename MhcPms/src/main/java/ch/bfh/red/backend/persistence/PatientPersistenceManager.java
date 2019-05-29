package ch.bfh.red.backend.persistence;

import ch.bfh.red.backend.models.Address;
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.services.IService;
import ch.bfh.red.backend.services.PatientService;

public class PatientPersistenceManager extends AbstractPersistenceManager<Patient> {


    private AddressPersistenceManager addressPersistenceManager;

    public PatientPersistenceManager() {
        this.service = new PatientService();
        this.addressPersistenceManager = new AddressPersistenceManager();

    }

    public PatientPersistenceManager(IService<Patient> service, IService<Address> addressIService) {
        this.service = service;
        this.addressPersistenceManager = new AddressPersistenceManager(addressIService);
    }

    @Override
    public Patient persist(Patient model) {

        service.add(model);

        Address address = model.getAddress();
        addressPersistenceManager.persist(address);


        return service.getRepository().findById(model.getId()).get();

    }

}
