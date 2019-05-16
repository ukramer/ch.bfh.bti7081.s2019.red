package ch.bfh.red.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.bfh.red.backend.models.AcademicTitle;
import ch.bfh.red.backend.repositories.AcademicTitleRepository;

@Service("academicTitleService")
public class AcademicTitleService {
	private AcademicTitleRepository academicTitleRepository;
	
	public AcademicTitleService(@Autowired AcademicTitleRepository academicTitleRepository) {
		this.academicTitleRepository = academicTitleRepository;
	}
	
	public AcademicTitle getAcademicTitleById(int id) {
		return academicTitleRepository.findById(id).get();
	}
	
	public void saveOrUpdate(AcademicTitle academicTitle) {
		academicTitleRepository.save(academicTitle);
	}
	
	public void delete(int id) {
		academicTitleRepository.deleteById(id);
	}

}
