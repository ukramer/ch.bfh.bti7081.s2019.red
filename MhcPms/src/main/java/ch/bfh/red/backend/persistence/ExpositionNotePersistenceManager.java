package ch.bfh.red.backend.persistence;

import ch.bfh.red.backend.models.Address;
import ch.bfh.red.backend.models.ExpositionNote;
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.services.ExpositionNoteService;
import ch.bfh.red.backend.services.IService;

public class ExpositionNotePersistenceManager extends AbstractPersistenceManager<ExpositionNote> {



    public ExpositionNotePersistenceManager() {

    }

    public ExpositionNotePersistenceManager(IService<ExpositionNote> service) {
        this.service = service;

    }

    @Override
    public ExpositionNote persist(ExpositionNote model) {

        service.add(model);
        return service.getRepository().findById(model.getId()).get();

    }
}
