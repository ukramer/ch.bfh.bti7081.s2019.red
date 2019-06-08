package ch.bfh.red.backend.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.models.Therapist;
import ch.bfh.red.backend.repositories.TherapistRepository;
import ch.bfh.red.common.BeanUtils;

@Service("therapistService")
public class TherapistService implements IService<Therapist> {

	@Autowired
	private TherapistRepository repository;
	
	@Autowired
	@Lazy
	private PatientService patientService;

	public TherapistService() {
		BeanUtils.checkBeanInstantiation(Thread.currentThread().getStackTrace(), 
				TherapistService.class);	
	}
	
	public List<Therapist> getAll() {
		List<Therapist> list = new ArrayList<>();
		repository.findAll().iterator().forEachRemaining(list::add);
		return list;
	}
	
	@Override
	public Therapist getById(Integer id) {
		Therapist obj = repository.findById(id).get();
		return obj;
	}
	
	@Override
	public void delete(Integer id) {
		delete(getById(id));
	}

	@Override
	public void delete(Therapist t) {
		repository.delete(t);
	}
	
	@Override
	@Transactional
	public Therapist persist(Therapist t) {
		Collection<Patient> patients = t.getPatients();
		for (Patient patient: patients)
			if (!patientService.existById(patient.getId())) {
				patientService.persist(patient);
			}
		return repository.save(t);
	}
	
	@Override
	public Boolean existById(Integer id) {
		return repository.existsById(id);
	}

}