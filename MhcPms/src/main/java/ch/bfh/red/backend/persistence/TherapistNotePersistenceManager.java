package ch.bfh.red.backend.persistence;

import ch.bfh.red.backend.models.Therapist;
import ch.bfh.red.backend.models.TherapistNote;
import ch.bfh.red.backend.services.IService;
import ch.bfh.red.backend.services.TherapistNoteService;

public class TherapistNotePersistenceManager extends AbstractPersistenceManager<TherapistNote> {



    public TherapistNotePersistenceManager() {
        this.service = new TherapistNoteService();

    }

    public TherapistNotePersistenceManager(IService<TherapistNote> service) {
        this.service = service;

    }

    @Override
    public TherapistNote persist(TherapistNote model) {

        service.add(model);
        return service.getRepository().findById(model.getId()).get();

    }
}
