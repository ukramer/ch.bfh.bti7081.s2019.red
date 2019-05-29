package ch.bfh.red.backend.persistence;

import ch.bfh.red.backend.models.Address;
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.models.SingleSession;
import ch.bfh.red.backend.models.Therapist;
import ch.bfh.red.backend.services.IService;
import ch.bfh.red.backend.services.SingleSessionService;

public class SingleSessionPersistenceManager extends AbstractPersistenceManager<SingleSession> {


    public SingleSessionPersistenceManager() {
        this.service = new SingleSessionService();

    }

    public SingleSessionPersistenceManager(IService<SingleSession> service) {
        this.service = service;

    }

    @Override
    public SingleSession persist(SingleSession model) {

        service.add(model);
        return service.getRepository().findById(model.getId()).get();

    }
}
