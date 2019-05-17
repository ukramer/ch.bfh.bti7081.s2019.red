package ch.bfh.red.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ch.bfh.red.backend.models.SingleSession;
import ch.bfh.red.backend.repositories.SingleSessionRepository;

@Service("singleSessionService")
public class SingleSessionService implements IService<SingleSession> {
	
	@Autowired
	private SingleSessionRepository repository;

	@Override
	public CrudRepository<SingleSession, Integer> getRepository() {
		return repository;
	}

}