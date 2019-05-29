package ch.bfh.red.backend.persistence;

import ch.bfh.red.backend.models.*;
import ch.bfh.red.backend.services.IService;
import ch.bfh.red.backend.services.TherapyService;

import java.beans.PersistenceDelegate;
import java.util.Collection;

public class TherapyPersistenceManager extends AbstractPersistenceManager<Therapy> {


    public TherapyPersistenceManager() {

    }

    public TherapyPersistenceManager(IService<Therapy> service) {
        this.service = service;

    }

    @Override
    public Therapy persist(Therapy model) {

        service.add(model);

        Patient patient = model.getPatient();
        Therapist therapist = model.getTherapist();
        Collection<PatientNote> patientNotes = model.getPatientNotes();
        Collection<TherapistNote> therapistNotes = model.getTherapistNotes();

        return service.getRepository().findById(model.getId()).get();


    }
}
