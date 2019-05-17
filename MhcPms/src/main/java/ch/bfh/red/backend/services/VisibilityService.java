package ch.bfh.red.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ch.bfh.red.backend.models.Visibility;
import ch.bfh.red.backend.repositories.VisibilityRepository;

@Service("visibilityService")
public class VisibilityService implements IService<Visibility> {
	
	@Autowired
	private VisibilityRepository repository;

	@Override
	public CrudRepository<Visibility, Integer> getRepository() {
		return repository;
	}

}
