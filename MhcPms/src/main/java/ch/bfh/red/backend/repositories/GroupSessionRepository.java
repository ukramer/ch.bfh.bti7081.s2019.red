package ch.bfh.red.backend.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ch.bfh.red.backend.models.GroupSession;

@Repository("groupSessionRepository")
public interface GroupSessionRepository extends CrudRepository<GroupSession, Integer> {

}
