package ch.bfh.red.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ch.bfh.red.backend.models.Therapist;
import ch.bfh.red.backend.repositories.TherapistRepository;

@Service("therapistService")
public class TherapistService implements IService<Therapist> {
	
	@Autowired
	private TherapistRepository repository;

	@Override
	public CrudRepository<Therapist, Integer> getRepository() {
		return repository;
	}

}