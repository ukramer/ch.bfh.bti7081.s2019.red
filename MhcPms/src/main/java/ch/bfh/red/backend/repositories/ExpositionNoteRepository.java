package ch.bfh.red.backend.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import ch.bfh.red.backend.models.ExpositionNote;


public interface ExpositionNoteRepository  extends JpaRepository<ExpositionNote, Long> {
	

}
