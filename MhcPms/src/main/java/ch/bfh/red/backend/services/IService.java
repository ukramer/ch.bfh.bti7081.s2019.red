package ch.bfh.red.backend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface IService<T> {
	
	CrudRepository<T, Integer> getRepository();
	
	default List<T> getAll() {
		List<T> list = new ArrayList<>();
		getRepository().findAll().iterator().forEachRemaining(list::add);
		return list;
	}
	
	default T getById(Integer id) {
		T obj = getRepository().findById(id).get();
		return obj;
	}
	
	default T update(T t) {
		return getRepository().save(t);
	}
	
	default void delete(Integer id) {
		delete(getById(id));
	}

	default void delete(T t) {
		getRepository().delete(t);
	}
	
	default T add(T t) {
		return getRepository().save(t);
	}
	
	default Boolean existById(Integer id) {
		return getRepository().existsById(id);
	}

}
