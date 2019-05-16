package ch.bfh.red.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.bfh.red.backend.models.TherapyType;
import ch.bfh.red.backend.repositories.TherapyTypeRepository;

@Service("therapyTypeService")
public class TherapyTypeService {
	private TherapyTypeRepository therapyTypeRepository;
	
	public TherapyTypeService(@Autowired TherapyTypeRepository therapyTypeRepository) {
		this.therapyTypeRepository = therapyTypeRepository;
	}
	
	public TherapyType getTherapyTypeById(int id) {
		return therapyTypeRepository.findById(id).get();
	}
	
	public void saveOrUpdate(TherapyType therapyType) {
		therapyTypeRepository.save(therapyType);
	}
	
	public void delete(int id) {
		therapyTypeRepository.deleteById(id);
	}

}
