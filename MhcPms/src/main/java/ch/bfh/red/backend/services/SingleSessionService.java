package ch.bfh.red.backend.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import ch.bfh.red.backend.models.SingleSession;
import ch.bfh.red.backend.repositories.SingleSessionRepository;
import ch.bfh.red.common.BeanUtils;
import ch.bfh.red.ui.views.searchBeans.SingleSessionSearchBean;

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
	
	@PersistenceContext
	private EntityManager em;

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
    	return repository.save(t);
	}
	
	@Override
	public Boolean exists(SingleSession t) {
		if (t == null || t.getId() == null) return false;
		return existsById(t.getId());
	}
	
	@Override
	public Boolean existsById(Integer id) {
		if (id == null) return false;
		return repository.existsById(id);
	}

	public List<SingleSession> findBySearchBean(SingleSessionSearchBean searchBean) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<SingleSession> cq = cb.createQuery(SingleSession.class);
		Root<SingleSession> root = cq.from(SingleSession.class);
		Join<?,?> patientJoin = root.join("patient");
		List<Predicate> predicates = new ArrayList<>();
		final String firstName = searchBean.getPatient().getFirstName();
		if(StringUtils.isNotBlank(firstName))
			predicates.add(cb.like(patientJoin.get("firstName"), "%" + firstName + "%"));
		final String lastName = searchBean.getPatient().getLastName();
		if(StringUtils.isNotBlank(searchBean.getPatient().getLastName()))
			predicates.add(cb.like(patientJoin.get("lastName"), "%" + lastName + "%"));
		final Date startDate = searchBean.getStartDate();
		if (startDate != null)
			predicates.add(cb.greaterThanOrEqualTo(root.get("startDate"), startDate));
		final Date endDate = searchBean.getEndDate();
		if (endDate != null)
			predicates.add(cb.lessThanOrEqualTo(root.get("startDate"), endDate));
		
		Predicate finalPredicate = cb.and(predicates.toArray(new Predicate[predicates.size()]));
		cq.where(finalPredicate);
		return em.createQuery(cq).getResultList();
	}
		
}