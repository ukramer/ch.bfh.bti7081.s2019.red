package ch.bfh.red.backend.repositories;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ch.bfh.red.backend.models.ExpositionNote;

import java.util.List;

@Repository("expositionNoteRepository")
public interface ExpositionNoteRepository  extends CrudRepository<ExpositionNote, Integer> {



    @Query(value = "SELECT e FROM ExpositionNote e WHERE  patient.firstName = ?1 AND patient.lastName = ?2")
    List<ExpositionNote> findByPatientFirstNameAndPatientLastName(String firstName, String lastName);

}
