package ch.bfh.red.backend.persistence;

import ch.bfh.red.backend.models.Address;
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.models.PatientNote;
import ch.bfh.red.backend.services.IService;
import ch.bfh.red.backend.services.PatientNoteService;

public class PatientNotePersistenceManager extends AbstractPersistenceManager<PatientNote> {


    public PatientNotePersistenceManager() {
        this.service = new PatientNoteService();

    }

    public PatientNotePersistenceManager(IService<PatientNote> service) {
        this.service = service;

    }


    @Override
    public PatientNote persist(PatientNote model) {


        service.add(model);

        return service.getRepository().findById(model.getId()).get();

    }
}
