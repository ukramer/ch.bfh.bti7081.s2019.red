package ch.bfh.red.backend.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ch.bfh.red.backend.models.TherapistNote;

@Repository("therapistNoteRepository")
public interface TherapistNoteRepository extends CrudRepository<TherapistNote, Integer> {

}
