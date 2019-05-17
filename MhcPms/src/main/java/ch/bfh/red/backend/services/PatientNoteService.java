package ch.bfh.red.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ch.bfh.red.backend.models.PatientNote;
import ch.bfh.red.backend.repositories.PatientNoteRepository;

@Service("patientNoteService")
public class PatientNoteService implements IService<PatientNote> {
	
	@Autowired
	private PatientNoteRepository repository;

	@Override
	public CrudRepository<PatientNote, Integer> getRepository() {
		return repository;
	}

}