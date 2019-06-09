package ch.bfh.red.backend.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import ch.bfh.red.backend.services.IService;
import ch.bfh.red.common.EntityUtils;

public interface IPersistenceManager<T> {
	
	IService<T> getService();
	
	T persist(T t, Collection<Class<?>> classes);
	
	Collection<Class<?>> getConnectedClasses();
	
	default Collection<T> persist(Collection<T> models, Collection<Class<?>> classes) {
		if (models == null)
			return Collections.emptyList();
		for (T model: models)
			if (model == null)
				throw new NullPointerException("At least one item in collection is null");
		
        Collection<T> persistedModels = new ArrayList<>();
        for (T model: models)
            persistedModels.add(persist(model, classes));
        return persistedModels;
    }
	
	default T persistAll(T t) {
		Collection<Class<?>> entityClasses = EntityUtils.getEntityClasses();
		return persist(t, entityClasses);
	}
	
	default Collection<T> persistAll(Collection<T> coll) {
		Collection<Class<?>> entityClasses = EntityUtils.getEntityClasses();
		return persist(coll, entityClasses);
	}
	
}
