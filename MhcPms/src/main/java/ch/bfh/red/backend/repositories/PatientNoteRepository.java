package ch.bfh.red.backend.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ch.bfh.red.backend.models.PatientNote;

@Repository("patientNoteRepository")
public interface PatientNoteRepository extends CrudRepository<PatientNote, Integer> {

}
