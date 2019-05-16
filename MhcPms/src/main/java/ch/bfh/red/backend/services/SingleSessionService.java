package ch.bfh.red.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.bfh.red.backend.models.SingleSession;
import ch.bfh.red.backend.repositories.SingleSessionRepository;

@Service("singleSessionService")
public class SingleSessionService {
	private SingleSessionRepository singleSessionRepository;
	
	public SingleSessionService(@Autowired SingleSessionRepository singleSessionRepository) {
		this.singleSessionRepository = singleSessionRepository;
	}
	
	public SingleSession getSingleSessionById(int id) {
		return singleSessionRepository.findById(id).get();
	}
	
	public void saveOrUpdate(SingleSession singleSession) {
		singleSessionRepository.save(singleSession);
	}
	
	public void delete(int id) {
		singleSessionRepository.deleteById(id);
	}

}
