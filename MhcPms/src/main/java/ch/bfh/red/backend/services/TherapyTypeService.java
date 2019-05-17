package ch.bfh.red.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ch.bfh.red.backend.models.TherapyType;
import ch.bfh.red.backend.repositories.TherapyTypeRepository;

@Service("therapyTypeService")
public class TherapyTypeService implements IService<TherapyType> {
	
	@Autowired
	private TherapyTypeRepository repository;

	@Override
	public CrudRepository<TherapyType, Integer> getRepository() {
		return repository;
	}

}