package ch.bfh.red.backend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface IService<T> {
	
	CrudRepository<T, Integer> getRepository();
	
	default List<T> getAll() {
		List<T> list = new ArrayList<>();
		getRepository().findAll().forEach(e -> list.add(e));
		return list;
	}
	
	default T getById(Integer id) {
		System.out.println("IService: " +id.getClass().getSimpleName());
		
		T obj = getRepository().findById(id).get();
		return obj;
	}
	
	default void update(T t) {
		getRepository().save(t);
		
	}
	
	default void delete(Integer id) {
		getRepository().delete(getById(id));
	}
	
	default boolean add(T t) {
		getRepository().save(t);
		return true;
	}
	
	default Boolean existById(Integer id) {
		return getRepository().existsById(id);
	}

}
