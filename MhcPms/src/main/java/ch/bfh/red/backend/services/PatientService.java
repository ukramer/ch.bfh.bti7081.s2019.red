package ch.bfh.red.backend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.repositories.PatientRepository;

@Service("patientService")
public class PatientService {
	private PatientRepository patientRepository;
	
	public PatientService(@Autowired PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}
	
	public List<Patient> getAllPatients() {
		List<Patient> patients = new ArrayList<Patient>();
		patientRepository.findAll().forEach(patient -> patients.add(patient));
		return patients;
	}
	
	public Patient getPatientById(int id) {
		return patientRepository.findById(id).get();
	}
	
	public void saveOrUpdate(Patient patient) {
		patientRepository.save(patient);
	}
	
	public void delete(int id) {
		patientRepository.deleteById(id);
	}

}
