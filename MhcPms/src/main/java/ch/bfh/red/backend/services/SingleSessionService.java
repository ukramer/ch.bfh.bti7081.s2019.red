package ch.bfh.red.backend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import ch.bfh.red.backend.models.SingleSession;
import ch.bfh.red.backend.repositories.SingleSessionRepository;
import ch.bfh.red.common.BeanUtils;

@Service("singleSessionService")
public class SingleSessionService implements IService<SingleSession> {
	
	@Autowired
	private SingleSessionRepository repository;
	
	@Autowired
	@Lazy
	private PatientService patientService;
	
	@Autowired
	@Lazy
	private TherapistService therapistService;

	public SingleSessionService() {
		BeanUtils.checkBeanInstantiation(Thread.currentThread().getStackTrace(), 
				SingleSessionService.class);	
	}

	public List<SingleSession> getAll() {
		List<SingleSession> list = new ArrayList<>();
		repository.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public SingleSession getById(Integer id) {
		SingleSession obj = repository.findById(id).get();
		return obj;
	}
	
	@Override
	public void delete(Integer id) {
		delete(getById(id));
	}

	@Override
	public void delete(SingleSession t) {
		repository.delete(t);
	}
	
	@Override
	public SingleSession persist(SingleSession t) {
		patientService.persist(t.getPatient());
    	therapistService.persist(t.getTherapist());
    	
    	return repository.save(t);
	}
	
	@Override
	public Boolean existById(Integer id) {
		return repository.existsById(id);
	}

}