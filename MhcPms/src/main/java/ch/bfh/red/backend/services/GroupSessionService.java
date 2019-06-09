package ch.bfh.red.backend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import ch.bfh.red.backend.models.GroupSession;
import ch.bfh.red.backend.repositories.GroupSessionRepository;
import ch.bfh.red.common.BeanUtils;

@Service("groupSessionService")
public class GroupSessionService implements IService<GroupSession> {
	
	@Autowired
	private GroupSessionRepository repository;
	
	@Autowired
	@Lazy
	private PatientService patientService;
	
	@Autowired
	@Lazy
	private TherapistService therapistService;

	public GroupSessionService() {
		BeanUtils.checkBeanInstantiation(Thread.currentThread().getStackTrace(), 
				GroupSessionService.class);	
	}
	
	@Override
	public List<GroupSession> getAll() {
		List<GroupSession> list = new ArrayList<>();
		repository.findAll().iterator().forEachRemaining(list::add);
		return list;
	}
	
	@Override
	public GroupSession getById(Integer id) {
		GroupSession obj = repository.findById(id).get();
		return obj;
	}
	
	@Override
	public void delete(Integer id) {
		delete(getById(id));
	}

	@Override
	public void delete(GroupSession t) {
		repository.delete(t);
	}
	
	@Override
	public GroupSession persist(GroupSession t) {
    	return repository.save(t);
	}
	
	@Override
	public Boolean exists(GroupSession t) {
		if (t == null || t.getId() == null) return false;
		return existsById(t.getId());
	}
	
	@Override
	public Boolean existsById(Integer id) {
		if (id == null) return false;
		return repository.existsById(id);
	}

}
