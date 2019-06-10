package ch.bfh.red.backend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import ch.bfh.red.backend.models.ExpositionNote;
import ch.bfh.red.backend.repositories.ExpositionNoteRepository;
import ch.bfh.red.common.BeanUtils;

@Service("expositionNoteService")
public class ExpositionNoteService implements IService<ExpositionNote> {

    @Autowired
    private ExpositionNoteRepository repository;
    
    @Autowired
    @Lazy
    private PatientService patientService;

    public ExpositionNoteService() {
		BeanUtils.checkBeanInstantiation(Thread.currentThread().getStackTrace(), 
				ExpositionNoteService.class);	
	}
    
	public List<ExpositionNote> getAll() {
		List<ExpositionNote> list = new ArrayList<>();
		repository.findAll().iterator().forEachRemaining(list::add);
		return list;
	}
	
	@Override
	public ExpositionNote getById(Integer id) {
		ExpositionNote obj = repository.findById(id).get();
		return obj;
	}
	
	@Override
	public void delete(Integer id) {
		delete(getById(id));
	}

	@Override
	public void delete(ExpositionNote t) {
		repository.delete(t);
	}
	
	@Override
	public ExpositionNote persist(ExpositionNote t) {
    	return repository.save(t);
	}
	
	@Override
	public Boolean exists(ExpositionNote t) {
		if (t == null || t.getId() == null) return false;
		return existsById(t.getId());
	}
	
	@Override
	public Boolean existsById(Integer id) {
		if (id == null) return false;
		return repository.existsById(id);
	}

}