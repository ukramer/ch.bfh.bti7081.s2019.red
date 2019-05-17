package ch.bfh.red.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ch.bfh.red.backend.models.TherapistNote;
import ch.bfh.red.backend.repositories.TherapistNoteRepository;

@Service("therapistNoteService")
public class TherapistNoteService implements IService<TherapistNote> {
	
	@Autowired
	private TherapistNoteRepository repository;

	@Override
	public CrudRepository<TherapistNote, Integer> getRepository() {
		return repository;
	}

}