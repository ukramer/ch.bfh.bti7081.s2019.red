package ch.bfh.red.backend.persistence;

import ch.bfh.red.backend.models.Address;
import ch.bfh.red.backend.models.Patient;

public class PatientPersistenceManager implements PersistenceManager<Patient> {

	private AddressPersistenceManager addressPersistenceManager;
	
	public PatientPersistenceManager() {
		this.addressPersistenceManager = new AddressPersistenceManager();
	}
	
	@Override
	public Patient persist(Patient model) {
		Address address = model.getAddress();
		addressPersistenceManager.persist(address);
		
		// TODO Auto-generated method stub
		return null;
	} 
	
}
