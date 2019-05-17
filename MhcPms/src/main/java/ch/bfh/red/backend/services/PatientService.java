package ch.bfh.red.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.repositories.PatientRepository;

@Service("patientService")
public class PatientService implements IService<Patient> {
	
	@Autowired
	private PatientRepository repository;

	@Override
	public CrudRepository<Patient, Integer> getRepository() {
		return repository;
	}

}