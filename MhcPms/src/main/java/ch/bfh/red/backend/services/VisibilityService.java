package ch.bfh.red.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.bfh.red.backend.models.Visibility;
import ch.bfh.red.backend.repositories.VisibilityRepository;

@Service("visibilityService")
public class VisibilityService {
	private VisibilityRepository visibilityRepository;
	
	public VisibilityService(@Autowired VisibilityRepository visibilityRepository) {
		this.visibilityRepository = visibilityRepository;
	}
	
	public Visibility getVisibilityById(int id) {
		return visibilityRepository.findById(id).get();
	}
	
	public void saveOrUpdate(Visibility visibility) {
		visibilityRepository.save(visibility);
	}
	
	public void delete(int id) {
		visibilityRepository.deleteById(id);
	}

}
