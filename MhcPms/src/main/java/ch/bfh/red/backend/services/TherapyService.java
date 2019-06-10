package ch.bfh.red.backend.services;

import ch.bfh.red.backend.models.*;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Hibernate;
import org.hibernate.query.criteria.internal.OrderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import ch.bfh.red.backend.repositories.TherapyRepository;
import ch.bfh.red.common.BeanUtils;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("therapyService")
public class TherapyService implements IService<Therapy> {

    @Autowired
    private TherapyRepository repository;

    @Autowired
    @Lazy
    private PatientService patientService;

    @Autowired
    @Lazy
    private SingleSessionService singleSessionService;

    @Autowired
    @Lazy
    private GroupSessionService groupSessionService;

    @Autowired
    @Lazy
    private PatientNoteService patientNoteService;

    @Autowired
    @Lazy
    private TherapistNoteService therapistNoteService;

    @PersistenceContext
    private EntityManager em;

    public TherapyService() {
		BeanUtils.checkBeanInstantiation(Thread.currentThread().getStackTrace(),
				TherapyService.class);
	}

    @Transactional
    public Therapy getByIdWithAllAssociations(Integer id) {
        Therapy therapy = repository.findById(id).get();
        Hibernate.initialize(therapy.getSingleSessions());
        Hibernate.initialize(therapy.getGroupSessions());
        Hibernate.initialize(therapy.getTherapistNotes());
        Hibernate.initialize(therapy.getPatientNotes());

        for (GroupSession groupSession: therapy.getGroupSessions()) {
            Hibernate.initialize(groupSession.getPatients());
            Hibernate.initialize(groupSession.getTherapists());
        }

        return therapy;
    }

    @Override
    public List<Therapy> getAll() {
        return null;
    }

    @Override
	public Therapy getById(Integer id) {
		Therapy obj = repository.findById(id).get();
		return obj;
	}

	@Override
	public void delete(Integer id) {
		delete(getById(id));
	}

	@Override
	public void delete(Therapy t) {
		repository.delete(t);
	}

	@Override
	public Therapy persist(Therapy t) {
		patientService.persist(t.getPatient());
    	singleSessionService.persist(t.getSingleSessions());
    	groupSessionService.persist(t.getGroupSessions());
    	patientNoteService.persist(t.getPatientNotes());
    	therapistNoteService.persist(t.getTherapistNotes());

        return repository.save(t);
	}

    @Override
    public Boolean exists(Therapy t) {
        if (t == null || t.getId() == null) return false;
        return existsById(t.getId());
    }

    @Override
    public Boolean existsById(Integer id) {
        if (id == null) return false;
        return repository.existsById(id);
    }

    public List<Therapy> getBy(boolean finished, String firstName, String lastName, LocalDate startDate, LocalDate endDate) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Therapy> cq = cb.createQuery(Therapy.class);
        Root<Therapy> therapy = cq.from(Therapy.class);

        applyFilter(therapy, cq, finished, firstName, lastName, startDate, endDate);

        boolean filterInactive = StringUtils.isBlank(firstName) && StringUtils.isBlank(lastName) && startDate == null && endDate == null;
        if (filterInactive) {
            cq.orderBy(new OrderImpl(therapy.get("startDate"), false));
        }

        TypedQuery<Therapy> query = em.createQuery(cq);
        if (filterInactive) {
            query.setMaxResults(10);
        }

        return query.getResultList();
    }

    private void applyFilter(Root<Therapy> therapy, CriteriaQuery cq, boolean finished, String firstName, String lastName, LocalDate startDate, LocalDate endDate) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(therapy.get("finished"), finished));

        Join<Patient, String> patient = therapy.join("patient");
        if(StringUtils.isNotBlank(firstName)){
            predicates.add(cb.like(patient.get("firstName"), "%" + firstName + "%"));
        }
        if(StringUtils.isNotBlank(lastName)){
            predicates.add(cb.like(patient.get("lastName"), "%" + lastName + "%"));
        }
        if(startDate != null){
            predicates.add(cb.greaterThanOrEqualTo(therapy.get("startDate"), Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant())));
        }
        if(endDate != null){
            predicates.add(cb.lessThanOrEqualTo(therapy.get("startDate"), Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant())));
        }
        Predicate finalPredicate = cb.and(predicates.toArray(new Predicate[predicates.size()]));
        cq.where(finalPredicate);
    }
}