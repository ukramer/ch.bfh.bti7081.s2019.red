package ch.bfh.red.backend.persistence;

import ch.bfh.red.backend.models.*;
import ch.bfh.red.backend.services.IService;
import ch.bfh.red.backend.services.TherapistService;
import ch.bfh.red.backend.services.TherapyService;

import java.security.acl.Group;
import java.util.Collection;

public class TherapistPersistenceManager extends AbstractPersistenceManager<Therapist> {


    public TherapistPersistenceManager(IService<Therapist> service) {
        this.service = service;
    }

    public TherapistPersistenceManager() {}





    @Override
    public Therapist persist(Therapist model) {

        service.add(model);
        return service.getRepository().findById(model.getId()).get();

    }
}
