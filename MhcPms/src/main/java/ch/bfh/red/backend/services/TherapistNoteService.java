package ch.bfh.red.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.bfh.red.backend.models.TherapistNote;
import ch.bfh.red.backend.repositories.TherapistNoteRepository;

@Service("therapistNoteService")
public class TherapistNoteService {
	private TherapistNoteRepository therapistNoteRepository;
	
	public TherapistNoteService(@Autowired TherapistNoteRepository therapistNoteRepository) {
		this.therapistNoteRepository = therapistNoteRepository;
	}
	
	public TherapistNote getTherapistNoteById(int id) {
		return therapistNoteRepository.findById(id).get();
	}
	
	public void saveOrUpdate(TherapistNote therapistNote) {
		therapistNoteRepository.save(therapistNote);
	}
	
	public void delete(int id) {
		therapistNoteRepository.deleteById(id);
	}

}
