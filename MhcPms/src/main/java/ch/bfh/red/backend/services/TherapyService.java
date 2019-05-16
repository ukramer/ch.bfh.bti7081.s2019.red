package ch.bfh.red.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.bfh.red.backend.models.Therapy;
import ch.bfh.red.backend.repositories.TherapyRepository;

@Service("therapyService")
public class TherapyService {
	private TherapyRepository therapyRepository;
	
	public TherapyService(@Autowired TherapyRepository therapyRepository) {
		this.therapyRepository = therapyRepository;
	}
	
	public Therapy getTherapyById(int id) {
		return therapyRepository.findById(id).get();
	}
	
	public void saveOrUpdate(Therapy therapy) {
		therapyRepository.save(therapy);
	}
	
	public void delete(int id) {
		therapyRepository.deleteById(id);
	}
	

}
