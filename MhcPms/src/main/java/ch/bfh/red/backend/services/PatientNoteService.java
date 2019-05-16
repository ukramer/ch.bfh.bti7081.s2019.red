package ch.bfh.red.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.bfh.red.backend.models.PatientNote;
import ch.bfh.red.backend.repositories.PatientNoteRepository;

@Service("patientNoteService")
public class PatientNoteService {
	private PatientNoteRepository patientNoteRepository;
	
	public PatientNoteService(@Autowired PatientNoteRepository patientNoteRepository) {
		this.patientNoteRepository = patientNoteRepository;
	}
	
	public PatientNote getPatientNoteById(int id) {
		return patientNoteRepository.findById(id).get();
	}
	
	public void saveOrUpdate(PatientNote patientNote) {
		patientNoteRepository.save(patientNote);
	}
	
	public void delete(int id) {
		patientNoteRepository.deleteById(id);
	}

}
