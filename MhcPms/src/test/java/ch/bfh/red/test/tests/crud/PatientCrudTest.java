package ch.bfh.red.test.tests.crud;

import ch.bfh.red.backend.models.Address;
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.persistence.AbstractPersistenceManager;
import ch.bfh.red.backend.persistence.PatientPersistenceManager;
import ch.bfh.red.backend.services.IService;
import org.springframework.beans.factory.annotation.Autowired;

public class PatientCrudTest extends CrudTest<Patient> {


	@Autowired
	private IService<Address> addressService;

	@Override
	protected Patient createInstance() {
		Address address = new Address("Langstrasse", "12k", 7777, "Burgdorf");
		address = addressService.add(address);
		return new Patient("Jürgen", "Müller", address);
	}

	@Override
	protected Integer getId(Patient instance) {
		return instance.getId();
	}

	@Override
	protected void setAnUpdateValue(Patient instance) {
		instance.setFirstName("Karl");
	}

	@Override
	protected AbstractPersistenceManager<Patient> getPersistenceManager() {
		return new PatientPersistenceManager(service, addressService);
	}
	
}
