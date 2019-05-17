package ch.bfh.red.backend.repositories;

import org.springframework.data.repository.CrudRepository;

import ch.bfh.red.backend.models.TherapyType;

public interface TherapyTypeRepository extends CrudRepository<TherapyType, Integer> {

}
