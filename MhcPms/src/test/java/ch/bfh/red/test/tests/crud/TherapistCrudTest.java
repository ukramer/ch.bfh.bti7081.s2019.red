package ch.bfh.red.test.tests.crud;

import ch.bfh.red.backend.models.AcademicTitle;
import ch.bfh.red.backend.models.Address;
import ch.bfh.red.backend.models.Therapist;
import ch.bfh.red.backend.persistence.PersistenceManager;
import ch.bfh.red.backend.services.IService;
import org.springframework.beans.factory.annotation.Autowired;

public class TherapistCrudTest extends CrudTest<Therapist> {

	// TODO remove
	@Autowired
	private IService<Address> addressIService;

	// TODO remove
	@Autowired
	private IService<AcademicTitle> academicTitleIService;

	@Override
	protected Therapist createInstance() {
		AcademicTitle title = AcademicTitle.DOCTOR;
		academicTitleIService.add(title);

		Address address = new Address("Winkelstrasse", "A", 2387, "Bülach");
		addressIService.add(address);
		return new Therapist("marle34", "1234", title, "Marlies", "Lotti", address);
	}

	@Override
	protected Integer getId(Therapist instance) {
		return instance.getId();
	}

	@Override
	protected void setAnUpdateValue(Therapist instance) {
		instance.setUsername("ott81");
	}

	@Override
	protected PersistenceManager<Therapist> getPersistenceManager() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
