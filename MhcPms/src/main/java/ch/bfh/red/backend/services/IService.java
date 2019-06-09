package ch.bfh.red.backend.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public interface IService<T> {
	
	T getById(Integer id);
	
	void delete(Integer id);

	void delete(T t);
	
	T persist(T t);
	
//	T persist(T t, Collection<Class<?>> classes);
	
	Boolean exists(T t);
	
	Boolean existsById(Integer id);

	default Collection<T> persist(Collection<T> models) {
		if (models == null)
			return Collections.emptyList();
		for (T model: models)
			if (model == null)
				throw new NullPointerException("At least one item in collection is null");
		
        Collection<T> persistedModels = new ArrayList<>();
        for (T model: models)
            persistedModels.add(persist(model));
        return persistedModels;
    }
	
}
