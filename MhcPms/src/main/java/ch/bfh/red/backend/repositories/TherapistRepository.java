package ch.bfh.red.backend.repositories;

import ch.bfh.red.backend.models.Therapist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TherapistRepository extends JpaRepository<Therapist, Long> {
}
