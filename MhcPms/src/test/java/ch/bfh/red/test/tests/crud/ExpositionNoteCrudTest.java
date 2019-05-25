package ch.bfh.red.test.tests.crud;

import ch.bfh.red.backend.models.*;
import ch.bfh.red.backend.services.IService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class ExpositionNoteCrudTest extends CrudTest<ExpositionNote> {

    @Autowired
    private IService<Address> addressIService;
    @Autowired
    private IService<Patient> patientIService;
    @Autowired
    private IService<Visibility> visibilityIService;

    @Override
    protected ExpositionNote createInstance() {

        Address address = new Address("Amselstrasse", "16a", 4104, "Oberwil");
        addressIService.add(address);

        Patient patient = new Patient("Sophie", "", address);
        patientIService.add(patient);

        Visibility visibility = new Visibility("PRIVATE", "only for therapist");
        visibilityIService.add(visibility);

        return new ExpositionNote(patient, new Date(), "Went to bed without washing ritual", visibility, 8);
    }

    @Override
    protected Integer getId(ExpositionNote instance) {
        return instance.getId();
    }

    @Override
    protected void setAnUpdateValue(ExpositionNote instance) {
        instance.setDegreeOfExposure(9);
    }

}
