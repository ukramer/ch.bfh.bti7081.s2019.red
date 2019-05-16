package ch.bfh.red.backend.repositories;

import org.springframework.data.repository.CrudRepository;

import ch.bfh.red.backend.models.SingleSession;

public interface SingleSessionRepository extends CrudRepository<SingleSession, Integer> {

}
