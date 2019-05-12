package ch.bfh.red.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.bfh.red.backend.models.Therapist;

public interface TherapistRepository extends JpaRepository<Therapist, Long> {
}
