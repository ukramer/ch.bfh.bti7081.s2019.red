package ch.bfh.red.backend.persistence;

public interface PersistenceManager<T> {
	
	T persist(T model);
	
}
