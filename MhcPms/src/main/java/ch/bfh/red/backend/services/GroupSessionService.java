package ch.bfh.red.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ch.bfh.red.backend.models.GroupSession;
import ch.bfh.red.backend.repositories.GroupSessionRepository;

@Service("groupSessionService")
public class GroupSessionService implements IService<GroupSession> {
	
	@Autowired
	private GroupSessionRepository repository;

	@Override
	public CrudRepository<GroupSession, Integer> getRepository() {
		return repository;
	}

}