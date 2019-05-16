package ch.bfh.red.backend.repositories;

import org.springframework.data.repository.CrudRepository;

import ch.bfh.red.backend.models.Therapy;

public interface TherapyRepository extends CrudRepository<Therapy, Integer> {

}
