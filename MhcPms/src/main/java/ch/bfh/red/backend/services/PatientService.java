package ch.bfh.red.backend.services;

import ch.bfh.red.backend.models.GroupSession;
import ch.bfh.red.ui.views.SearchBean.PatientSearchBean;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.repositories.PatientRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service("patientService")
public class PatientService implements IService<Patient> {
	
	@Autowired
	private PatientRepository repository;

	@PersistenceContext
	EntityManager em;

	@Override
	public CrudRepository<Patient, Integer> getRepository() {
		return repository;
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
		Patient patient = getRepository().findById(id).get();
		Hibernate.initialize(patient.getTherapies());
		Hibernate.initialize(patient.getGroupSessions());
		Hibernate.initialize(patient.getSingleSessions());
		Hibernate.initialize(patient.getTherapists());
		return patient;
	}
}