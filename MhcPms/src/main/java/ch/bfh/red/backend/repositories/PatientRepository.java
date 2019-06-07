package ch.bfh.red.backend.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ch.bfh.red.backend.models.Patient;

@Repository("patientRepository")
public interface PatientRepository extends CrudRepository<Patient, Integer> {

}
