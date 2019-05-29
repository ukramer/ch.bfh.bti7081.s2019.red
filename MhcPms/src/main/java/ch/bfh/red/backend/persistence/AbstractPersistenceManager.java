package ch.bfh.red.backend.persistence;

import ch.bfh.red.backend.services.IService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public abstract class AbstractPersistenceManager<T> {
    protected IService<T> service;


    public abstract T persist(T model);

    Collection<T> persist(Collection<T> models) {
        ArrayList<T> persistedModels = new ArrayList<>();
        Iterator<T> iterator = models.iterator();
        while (iterator.hasNext()) {
            persistedModels.add(persist(iterator.next()));
        }

        return persistedModels;
    }

    public IService<T> getService() {
        return service;
    }
}
