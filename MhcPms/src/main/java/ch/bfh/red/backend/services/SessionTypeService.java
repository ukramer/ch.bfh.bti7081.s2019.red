package ch.bfh.red.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ch.bfh.red.backend.models.SessionType;
import ch.bfh.red.backend.repositories.SessionTypeRepository;

@Service("sessionTypeService")
public class SessionTypeService implements IService<SessionType> {
	
	@Autowired
	private SessionTypeRepository repository;

	@Override
	public CrudRepository<SessionType, Integer> getRepository() {
		return repository;
	}

}