package ch.bfh.red.backend.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ch.bfh.red.ui.views.SearchBean.PatientSearchBean;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.models.Therapist;
import ch.bfh.red.backend.repositories.PatientRepository;
import ch.bfh.red.common.BeanUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;

@Service("patientService")
public class PatientService implements IService<Patient> {

	@Autowired
	private PatientRepository repository;
	
	@Autowired
	@Lazy
	private TherapistService therapistService;

	@PersistenceContext
	private EntityManager em;
	
	public PatientService() {
		BeanUtils.checkBeanInstantiation(Thread.currentThread().getStackTrace(), 
				PatientService.class);	
	}
	
	public List<Patient> getAll() {
		List<Patient> list = new ArrayList<>();
		repository.findAll().iterator().forEachRemaining(list::add);
		return list;
	}
	
	@Override
	public Patient getById(Integer id) {
		Patient obj = repository.findById(id).get();
		return obj;
	}
	
	@Override
	public void delete(Integer id) {
		delete(getById(id));
	}

	@Override
	public void delete(Patient t) {
		repository.delete(t);
	}
	
	@Override
	public Patient persist(Patient t) {
		Collection<Therapist> therapists = t.getTherapists();
		for (Therapist therapist: therapists)
			if (!therapistService.existById(therapist.getId())) {
				therapistService.persist(therapist);
			}
		therapistService.persist(t.getTherapists());
		return repository.save(t);
	}
	
	@Override
	public Boolean existById(Integer id) {
		return repository.existsById(id);
	}

	public List<Patient> findByPatientSearchBean(PatientSearchBean patientSearchBean){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Patient> cq = cb.createQuery(Patient.class);
		Root<Patient> patient = cq.from(Patient.class);
		List<Predicate> predicates = new ArrayList<>();
		if(StringUtils.isNotBlank(patientSearchBean.getFirstName())){
			predicates.add(cb.like(patient.get("firstName"), "%" + patientSearchBean.getFirstName() + "%"));
		}
		if(StringUtils.isNotBlank(patientSearchBean.getLastName())){
			predicates.add(cb.like(patient.get("lastName"), "%" + patientSearchBean.getLastName() + "%"));
		}
		Join address = patient.join("address");
		if(StringUtils.isNotBlank(patientSearchBean.getStreet())){
			predicates.add(cb.like(address.get("street"), "%" + patientSearchBean.getStreet() + "%"));
		}
		if(StringUtils.isNotBlank(patientSearchBean.getStreetNr())){
			predicates.add(cb.like(address.get("streetNumber"), "%" + patientSearchBean.getStreetNr() + "%"));
		}
		if(patientSearchBean.getPostalCode() != null){
			predicates.add(cb.equal(address.get("postalCode"), patientSearchBean.getPostalCode()));
		}
		if(StringUtils.isNotBlank(patientSearchBean.getCity())){
			predicates.add(cb.like(address.get("city"), "%" + patientSearchBean.getCity() + "%"));
		}
		Predicate finalPredicate = cb.and(predicates.toArray(new Predicate[predicates.size()]));
		cq.where(finalPredicate);
		return em.createQuery(cq).getResultList();
	}

	@Transactional
	public Patient getByIdWithAssociations(Integer id){
		Patient patient = repository.findById(id).get();
		Hibernate.initialize(patient.getTherapies());
		Hibernate.initialize(patient.getGroupSessions());
		Hibernate.initialize(patient.getSingleSessions());
		Hibernate.initialize(patient.getTherapists());
		return patient;
	}
}