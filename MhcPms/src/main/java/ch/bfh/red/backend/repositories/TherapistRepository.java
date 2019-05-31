package ch.bfh.red.backend.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ch.bfh.red.backend.models.Therapist;

@Repository("therapistRepository")
public interface TherapistRepository extends CrudRepository<Therapist, Integer> {
	
}
