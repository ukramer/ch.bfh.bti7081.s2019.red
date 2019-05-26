package ch.bfh.red.backend.factories;

import ch.bfh.red.backend.models.*;
import com.github.javafaker.DateAndTime;
import com.github.javafaker.Faker;


import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SingleSessionFactory extends AbstractFactory<SingleSession>{

    private Faker faker;
    private final TherapistFactory therapistFactory;
    private final PatientFactory patientFactory;

    public SingleSessionFactory(){
        this(new Locale("de-ch"));
    }
    public SingleSessionFactory(Locale locale){
        faker = new Faker();
        therapistFactory = new TherapistFactory(locale);
        patientFactory = new PatientFactory(locale);


    }
    @Override
    public SingleSession create(){

        Patient patient = patientFactory.create();
        Therapist therapist = therapistFactory.create();

        DateAndTime dateAndTime = faker.date();
        Date startDate = dateAndTime.past(1000, TimeUnit.DAYS);
        Date endDate = dateAndTime.past(365, TimeUnit.DAYS);

        return new SingleSession(patient, therapist, startDate, endDate, getRandomSessionType());

    }

    public static SessionType getRandomSessionType() {
        Random random = new Random();
        return SessionType.values()[random.nextInt(SessionType.values().length)];
    }
}
