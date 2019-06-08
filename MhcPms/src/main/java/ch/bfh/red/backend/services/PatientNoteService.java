package ch.bfh.red.backend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import ch.bfh.red.backend.models.PatientNote;
import ch.bfh.red.backend.repositories.PatientNoteRepository;
import ch.bfh.red.common.BeanUtils;

@Service("patientNoteService")
public class PatientNoteService implements IService<PatientNote> {
	
	@Autowired
	private PatientNoteRepository repository;
	
	@Autowired
	@Lazy
	private PatientService patientManager;

	public PatientNoteService() {
		BeanUtils.checkBeanInstantiation(Thread.currentThread().getStackTrace(), 
				PatientNoteService.class);	
	}
	
	@Override
	public List<PatientNote> getAll() {
		List<PatientNote> list = new ArrayList<>();
		repository.findAll().iterator().forEachRemaining(list::add);
		return list;
	}
	
	@Override
	public PatientNote getById(Integer id) {
		PatientNote obj = repository.findById(id).get();
		return obj;
	}
	
	@Override
	public void delete(Integer id) {
		delete(getById(id));
	}

	@Override
	public void delete(PatientNote t) {
		repository.delete(t);
	}
	
	@Override
	public PatientNote persist(PatientNote t) {
    	return repository.save(t);
	}
	
	@Override
	public Boolean exists(PatientNote t) {
		if (t == null || t.getId() == null) return false;
		return existsById(t.getId());
	}
	
	@Override
	public Boolean existsById(Integer id) {
		if (id == null) return false;
		return repository.existsById(id);
	}

}