package ch.bfh.red.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.bfh.red.backend.models.SessionType;
import ch.bfh.red.backend.repositories.SessionTypeRepository;

@Service("sessionTypeService")
public class SessionTypeService {
	private SessionTypeRepository sessionTypeRepository;
	
	public SessionTypeService(@Autowired SessionTypeRepository sessionTypeRepository) {
		this.sessionTypeRepository = sessionTypeRepository;
	}
	
	public SessionType getSessionTypeById(int id) {
		return sessionTypeRepository.findById(id).get();
	}
	
	public void saveOrUpdate(SessionType sessionType) {
		sessionTypeRepository.save(sessionType);
	}
	
	public void delete(int id) {
		sessionTypeRepository.deleteById(id);
	}

}
