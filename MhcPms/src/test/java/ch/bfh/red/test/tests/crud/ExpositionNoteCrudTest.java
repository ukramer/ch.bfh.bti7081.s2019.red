package ch.bfh.red.test.tests.crud;

import ch.bfh.red.backend.models.*;
import ch.bfh.red.backend.persistence.AbstractPersistenceManager;
import ch.bfh.red.backend.persistence.ExpositionNotePersistenceManager;
import ch.bfh.red.backend.services.IService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class ExpositionNoteCrudTest extends CrudTest<ExpositionNote> {

	// TODO remove
    @Autowired
    private IService<Address> addressIService;
    
    // TODO remove
    @Autowired
    private IService<Patient> patientIService;

    @Override
    protected ExpositionNote createInstance() {
        Address address = new Address("Amselstrasse", "16a", 4104, "Oberwil");
        addressIService.add(address);

        Patient patient = new Patient("Sophie", "", address);
        patientIService.add(patient);

        return new ExpositionNote(patient, new Date(), "Went to bed without washing ritual", Visibility.PRIVATE, 8);
    }

    @Override
    protected Integer getId(ExpositionNote instance) {
        return instance.getId();
    }

    @Override
    protected void setAnUpdateValue(ExpositionNote instance) {
        instance.setDegreeOfExposure(9);
    }

	@Override
	protected AbstractPersistenceManager<ExpositionNote> getPersistenceManager() {
		return new ExpositionNotePersistenceManager(service);
	}

}

