package ch.bfh.red.backend.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public interface IService<T> {
	
	List<T> getAll();
	
	T getById(Integer id);
	
	void delete(Integer id);

	void delete(T t);
	
	T persist(T t);
	
	Boolean existById(Integer id);

	default Collection<T> persist(Collection<T> models) {
		if (models == null)
			return Collections.emptyList();
		for (T model: models)
			if (model == null)
				throw new NullPointerException("At least one item in collection is null");
		
        Collection<T> persistedModels = new ArrayList<>();
        Iterator<T> iterator = models.iterator();
        while (iterator.hasNext()) {
            persistedModels.add(persist(iterator.next()));
        }

        return persistedModels;
    }
	
}
