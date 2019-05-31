package ch.bfh.red.backend.services;

import ch.bfh.red.backend.models.*;
import ch.bfh.red.ui.encoders.DateToStringEncoder;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import ch.bfh.red.backend.repositories.TherapyRepository;
import ch.bfh.red.common.BeanUtils;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    
    public TherapyService() {
		BeanUtils.checkBeanInstantiation(Thread.currentThread().getStackTrace(), 
				TherapyService.class);	
	}
    
    // TODO remove
    private static DateToStringEncoder dateToStringEncoder = new DateToStringEncoder();

    // TODO remove
    @PersistenceContext
    private EntityManager entityManager;

    // TODO remove
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
		List<Therapy> list = new ArrayList<>();
		repository.findAll().iterator().forEachRemaining(list::add);
		return list;
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
	public Boolean existById(Integer id) {
		return repository.existsById(id);
	}

    public List<Therapy> getByFinished(boolean finished) {
        return repository.findByFinished(finished);
    }

    public List<Therapy> getByFinishedAndPatientName(boolean finished, String firstName, String lastName) {
        return repository.findByFinishedAndPatientFirstNameAndPatientLastName(finished, firstName, lastName);
    }

    public List<Therapy> getByFinishedAndPatientNameAndDateRange(boolean finished, String firstName, String lastName, String start, String end) {
        Date startDate = dateToStringEncoder.decode(start);
        Date endDate = dateToStringEncoder.decode(end);
        return repository.findByFinishedAndPatientFirstNameAnPatientLastNameAndStartAndEndDate(finished, firstName, lastName, startDate, endDate);
    }

    public List<Therapy> getByFinishedAndPatientNameAndStartDate(boolean finished, String firstName, String lastName, String start) {
        Date startDate = dateToStringEncoder.decode(start);
        return repository.findByFinishedAndPatientFirstNameAnPatientLastNameAndStartDate(finished, firstName, lastName, startDate);
    }

    public List<Therapy> getByFinishedAndPatientNameAndEndDate(boolean finished, String firstName, String lastName, String end) {
        Date endDate = dateToStringEncoder.decode(end);
        return repository.findByFinishedAndPatientFirstNameAnPatientLastNameAndEndDate(finished, firstName, lastName, endDate);
    }

    public List<Therapy> getByFinishedAndDateRange(boolean finished, String start, String end) {
        Date startDate = dateToStringEncoder.decode(start);
        Date endDate = dateToStringEncoder.decode(end);
        return repository.findByFinishedAndStartAndEndDate(finished, startDate, endDate);
    }

    public List<Therapy> getByFinishedAndStartDate(boolean finished, String start) {
        Date startDate = dateToStringEncoder.decode(start);
        return repository.findByFinishedAndStartDate(finished, startDate);
    }

    public List<Therapy> getByFinishedAndEndDate(boolean finished, String end) {
        Date endDate = dateToStringEncoder.decode(end);
        return repository.findByFinishedAndEndDate(finished, endDate);
    }
    
}