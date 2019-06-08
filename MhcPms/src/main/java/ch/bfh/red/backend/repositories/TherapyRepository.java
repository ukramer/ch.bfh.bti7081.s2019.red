package ch.bfh.red.backend.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ch.bfh.red.backend.models.Therapy;

@Repository("therapyRepository")
public interface TherapyRepository extends CrudRepository<Therapy, Integer> {
}
