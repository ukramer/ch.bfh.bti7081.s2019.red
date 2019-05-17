package ch.bfh.red.test.tests.crud;

import ch.bfh.red.backend.models.Address;
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.services.IService;
import org.springframework.beans.factory.annotation.Autowired;

public class PatientCrudTest extends CrudTest<Patient> {

	@Autowired
	private IService<Address> addressService;

	@Override
	protected Patient createInstance() {
		Address address = new Address("Langstrasse", "12k", 7777, "Burgdorf");
		addressService.add(address);
		return new Patient("Jürgen", "", address);
	}

	@Override
	protected Integer getId(Patient instance) {
		return instance.getId();
	}

	@Override
	protected void setAnUpdateValue(Patient instance) {
		instance.setFirstName("Karl");
	}
	
}