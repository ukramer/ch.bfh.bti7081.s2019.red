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

import ch.bfh.red.backend.models.GroupSession;
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.models.Therapist;
import ch.bfh.red.backend.models.Therapy;
import ch.bfh.red.backend.repositories.GroupSessionRepository;
import ch.bfh.red.common.BeanUtils;
import ch.bfh.red.ui.dto.GroupSessionSearchDTO;
import ch.bfh.red.ui.dto.PersonDTO;

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
	
	@Autowired
	@Lazy
	private TherapyService therapyService;
	
	@PersistenceContext
	private EntityManager em;

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
		t = getById(t.getId());
		
		List<Therapy> therapies = t.getTherapies();
		for (Therapy therapy: therapies) {
			therapy.getGroupSessions().remove(t);
		}
		therapyService.persist(therapies);
		
		List<Patient> patients = t.getPatients();
		for (Patient patient: patients)
			patient.getGroupSessions().remove(t);
		patientService.persist(patients);
		
		List<Therapist> therapists = t.getTherapists();
		for (Therapist therapist: therapists)
			therapist.getGroupSessions().remove(t);
		
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

	public List<GroupSession> findByDTO(GroupSessionSearchDTO searchBean) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<GroupSession> cq = cb.createQuery(GroupSession.class);
		Root<GroupSession> root = cq.from(GroupSession.class);
		Join<?, ?> patientJoin = root.join("patients");
		Join<?, ?> therapistJoin = root.join("therapists");
		List<Predicate> predicates = new ArrayList<>();
		
		final PersonDTO patientBean = searchBean.getPatient();
		if (patientBean != null) {
			final String firstName = patientBean.getFirstName();
			if (StringUtils.isNotBlank(firstName))
				predicates.add(
						cb.like(patientJoin.get("firstName"), "%" + firstName + "%"));
			final String lastName = patientBean.getLastName();
			if (StringUtils.isNotBlank(patientBean.getLastName()))
				predicates
						.add(cb.like(patientJoin.get("lastName"), "%" + lastName + "%"));
		}
		final PersonDTO therapistBean = searchBean.getTherapist();
		if (therapistBean != null) {
			final String firstName = therapistBean.getFirstName();
			if (StringUtils.isNotBlank(firstName))
				predicates.add(
						cb.like(therapistJoin.get("firstName"), "%" + firstName + "%"));
			final String lastName = therapistBean.getLastName();
			if (StringUtils.isNotBlank(therapistBean.getLastName()))
				predicates
						.add(cb.like(therapistJoin.get("lastName"), "%" + lastName + "%"));
		}
		
		final Date startDate = searchBean.getStartDate();
		if (startDate != null)
			predicates.add(cb.greaterThanOrEqualTo(root.get("startDate"), startDate));
		final Date endDate = searchBean.getEndDate();
		if (endDate != null)
			predicates.add(cb.lessThanOrEqualTo(root.get("startDate"), endDate));
		
		Predicate finalPredicate = cb
				.and(predicates.toArray(new Predicate[predicates.size()]));
		cq.where(finalPredicate).distinct(true);
		return em.createQuery(cq).getResultList();
	}

}
