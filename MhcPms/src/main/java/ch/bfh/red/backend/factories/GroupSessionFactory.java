package ch.bfh.red.backend.factories;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.github.javafaker.Faker;

import ch.bfh.red.backend.models.GroupSession;
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.models.SessionType;
import ch.bfh.red.backend.models.Therapist;

public class GroupSessionFactory extends AbstractFactory<GroupSession> {
    private final Faker faker;
    private final TherapistFactory therapistFactory;
    private final PatientFactory patientFactory;
    private final SessionTypeFactory sessionTypeFactory;

    public GroupSessionFactory(){
        this(Locale.getDefault());
    }

    public GroupSessionFactory(Locale locale){
        faker = new Faker(locale);
        therapistFactory = new TherapistFactory(locale);
        patientFactory = new PatientFactory(locale);
        sessionTypeFactory = new SessionTypeFactory();
    }
    
    @Override
    public GroupSession create(){
    	Random random = new Random();
        List<Therapist> therapists = therapistFactory.create(random.nextInt(4));
        List<Patient> patients  = patientFactory.create(random.nextInt(8));
        return create(therapists, patients);
    }
    
    public GroupSession create(List<Therapist> therapists, List<Patient> patients){
    	Random random = new Random();
        Date startDate = faker.date().past(1000, TimeUnit.DAYS);
        Date endDate = faker.date().past(365, TimeUnit.DAYS);
        SessionType sessionType =  sessionTypeFactory.create();
        return new GroupSession(patients, therapists, startDate, endDate, sessionType);
    }
    
}
