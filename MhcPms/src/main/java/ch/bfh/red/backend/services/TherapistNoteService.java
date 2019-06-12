package ch.bfh.red.backend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import ch.bfh.red.backend.models.TherapistNote;
import ch.bfh.red.backend.repositories.TherapistNoteRepository;
import ch.bfh.red.common.BeanUtils;

@Service("therapistNoteService")
public class TherapistNoteService implements IService<TherapistNote> {
	
	@Autowired
	private TherapistNoteRepository repository;
	
	@Autowired
	@Lazy
	private TherapistService therapistService;

	public TherapistNoteService() {
		BeanUtils.checkBeanInstantiation(Thread.currentThread().getStackTrace(), 
				TherapistNoteService.class);	
	}
	
	@Override
	public List<TherapistNote> getAll() {
		List<TherapistNote> list = new ArrayList<>();
		repository.findAll().iterator().forEachRemaining(list::add);
		return list;
	}
	
	@Override
	public TherapistNote getById(Integer id) {
		TherapistNote obj = repository.findById(id).get();
		return obj;
	}
	
	@Override
	public void delete(Integer id) {
		delete(getById(id));
	}

	@Override
	public void delete(TherapistNote t) {
		repository.delete(t);
	}
	
	@Override
	public TherapistNote persist(TherapistNote t) {
    	return repository.save(t);
	}
	
	@Override
	public Boolean exists(TherapistNote t) {
		if (t == null || t.getId() == null) return false;
		return existsById(t.getId());
	}
	
	@Override
	public Boolean existsById(Integer id) {
		if (id == null) return false;
		return repository.existsById(id);
	}
}
