package ch.bfh.red.backend.repositories;

import org.springframework.data.repository.CrudRepository;

import ch.bfh.red.backend.models.PatientNote;

public interface PatientNoteRepository extends CrudRepository<PatientNote, Integer> {

}
