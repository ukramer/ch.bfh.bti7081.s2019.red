package ch.bfh.red.backend.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ch.bfh.red.backend.models.SingleSession;

@Repository("singleSessionRepository")
public interface SingleSessionRepository extends CrudRepository<SingleSession, Integer> {

}
