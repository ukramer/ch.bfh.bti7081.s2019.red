package ch.bfh.red.test.tests.crud;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import ch.bfh.red.backend.models.Address;
import ch.bfh.red.backend.models.ExpositionNote;
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.models.Visibility;
import ch.bfh.red.backend.services.ExpositionNoteService;
import ch.bfh.red.backend.services.IService;

public class ExpositionNoteCrudTest extends CrudTest<ExpositionNote> {

	@Autowired
	private ExpositionNoteService service;
	
    @Override
    protected ExpositionNote createInstance() {
        Address address = new Address("Amselstrasse", "16a", 4104, "Oberwil");
        Patient patient = new Patient("Sophie", "", address);

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
	protected IService<ExpositionNote> getService() {
		return service;
	}

}

