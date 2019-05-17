package ch.bfh.red.backend.repositories;

import org.springframework.data.repository.CrudRepository;

import ch.bfh.red.backend.models.Patient;

public interface PatientRepository extends CrudRepository<Patient, Integer> {

}
