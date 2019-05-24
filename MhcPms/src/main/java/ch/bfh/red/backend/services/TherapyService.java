package ch.bfh.red.backend.services;

import ch.bfh.red.backend.models.GroupSession;
import ch.bfh.red.backend.models.SingleSession;
import ch.bfh.red.ui.encoders.DateToStringEncoder;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.bfh.red.backend.models.Therapy;
import ch.bfh.red.backend.repositories.TherapyRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("therapyService")
public class TherapyService implements IService<Therapy> {

    @Autowired
    private TherapyRepository repository;

    private static DateToStringEncoder dateToStringEncoder = new DateToStringEncoder();

    @Transactional
    public Therapy getByIdWithAllAssociations(Integer id) {
        Therapy therapy = getRepository().findById(id).get();
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
    public TherapyRepository getRepository() {
        return repository;
    }

    public List<Therapy> getByFinished(boolean finished) {
        return getRepository().findByFinished(finished);
    }

    public List<Therapy> getByFinishedAndPatientName(boolean finished, String firstName, String lastName) {
        return getRepository().findByFinishedAndPatientFirstNameAndPatientLastName(finished, firstName, lastName);
    }

    public List<Therapy> getByFinishedAndPatientNameAndDateRange(boolean finished, String firstName, String lastName, String start, String end) {
        Date startDate = dateToStringEncoder.decode(start);
        Date endDate = dateToStringEncoder.decode(end);
        return getRepository().findByFinishedAndPatientFirstNameAnPatientLastNameAndStartAndEndDate(finished, firstName, lastName, startDate, endDate);
    }

    public List<Therapy> getByFinishedAndPatientNameAndStartDate(boolean finished, String firstName, String lastName, String start) {
        Date startDate = dateToStringEncoder.decode(start);
        return getRepository().findByFinishedAndPatientFirstNameAnPatientLastNameAndStartDate(finished, firstName, lastName, startDate);
    }

    public List<Therapy> getByFinishedAndPatientNameAndEndDate(boolean finished, String firstName, String lastName, String end) {
        Date endDate = dateToStringEncoder.decode(end);
        return getRepository().findByFinishedAndPatientFirstNameAnPatientLastNameAndEndDate(finished, firstName, lastName, endDate);
    }

    public List<Therapy> getByFinishedAndDateRange(boolean finished, String start, String end) {
        Date startDate = dateToStringEncoder.decode(start);
        Date endDate = dateToStringEncoder.decode(end);
        return getRepository().findByFinishedAndStartAndEndDate(finished, startDate, endDate);
    }

    public List<Therapy> getByFinishedAndStartDate(boolean finished, String start) {
        Date startDate = dateToStringEncoder.decode(start);
        return getRepository().findByFinishedAndStartDate(finished, startDate);
    }

    public List<Therapy> getByFinishedAndEndDate(boolean finished, String end) {
        Date endDate = dateToStringEncoder.decode(end);
        return getRepository().findByFinishedAndEndDate(finished, endDate);
    }
}