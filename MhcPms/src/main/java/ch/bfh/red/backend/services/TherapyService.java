package ch.bfh.red.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ch.bfh.red.backend.models.Therapy;
import ch.bfh.red.backend.repositories.TherapyRepository;

@Service("therapyService")
public class TherapyService implements IService<Therapy> {
	
	@Autowired
	private TherapyRepository repository;

	@Override
	public CrudRepository<Therapy, Integer> getRepository() {
		return repository;
	}

}