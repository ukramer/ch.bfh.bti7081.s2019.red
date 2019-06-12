package ch.bfh.red.backend.factories;

import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import com.github.javafaker.DateAndTime;
import com.github.javafaker.Faker;

import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.models.SessionType;
import ch.bfh.red.backend.models.SingleSession;
import ch.bfh.red.backend.models.Therapist;

public class SingleSessionFactory extends AbstractFactory<SingleSession>{
    private final Faker faker;
    private final TherapistFactory therapistFactory;
    private final PatientFactory patientFactory;
    private final SessionTypeFactory sessionTypeFactory;

    public SingleSessionFactory(){
        this(Locale.getDefault());
    }

    public SingleSessionFactory(Locale locale){
        this.faker = new Faker(locale);
        this.therapistFactory = new TherapistFactory(locale);
        this.patientFactory = new PatientFactory(locale);
        this.sessionTypeFactory = new SessionTypeFactory();
    }
    
    @Override
    public SingleSession create(){
        Patient patient = patientFactory.create();
        Therapist therapist = therapistFactory.create();
        return create(patient, therapist);
    }
    
    public SingleSession create(Patient patient, Therapist therapist){
        DateAndTime dateAndTime = faker.date();
        Date startDate = dateAndTime.past(1000, TimeUnit.DAYS);
        Date endDate = dateAndTime.past(365, TimeUnit.DAYS);
        SessionType type = sessionTypeFactory.create();
        return new SingleSession(patient, therapist, startDate, endDate, type);
    }

}
