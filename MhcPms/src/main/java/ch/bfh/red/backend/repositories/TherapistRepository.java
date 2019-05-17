package ch.bfh.red.backend.repositories;

import org.springframework.data.repository.CrudRepository;
import ch.bfh.red.backend.models.Therapist;

public interface TherapistRepository extends CrudRepository<Therapist, Integer> {
}
