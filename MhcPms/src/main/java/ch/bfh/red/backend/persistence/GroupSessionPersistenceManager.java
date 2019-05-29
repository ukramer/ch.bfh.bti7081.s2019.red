package ch.bfh.red.backend.persistence;

import ch.bfh.red.backend.models.*;
import ch.bfh.red.backend.services.GroupSessionService;
import ch.bfh.red.backend.services.IService;

import java.util.Collection;

public class GroupSessionPersistenceManager extends AbstractPersistenceManager<GroupSession> {



    public GroupSessionPersistenceManager() {
    }

    public GroupSessionPersistenceManager(IService<GroupSession> service) {
        this.service = service;

    }



    @Override
    public GroupSession persist(GroupSession model) {

        service.add(model);


        return service.getRepository().findById(model.getId()).get();


    }
}
