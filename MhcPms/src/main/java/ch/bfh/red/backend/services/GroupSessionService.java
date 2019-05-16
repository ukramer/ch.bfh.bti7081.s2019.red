package ch.bfh.red.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.bfh.red.backend.models.GroupSession;
import ch.bfh.red.backend.repositories.GroupSessionRepository;

@Service("groupSessionService")
public class GroupSessionService {
	private GroupSessionRepository groupSessionRepository;
	
	public GroupSessionService(@Autowired GroupSessionRepository groupSessionRepository) {
		this.groupSessionRepository = groupSessionRepository;
	}
	
	public GroupSession getGroupSessionById(int id) {
		return groupSessionRepository.findById(id).get();
	}
	
	public void saveOrUpdate(GroupSession groupSession) {
		groupSessionRepository.save(groupSession);
	}
	
	public void delete(int id) {
		groupSessionRepository.deleteById(id);
	}

}
