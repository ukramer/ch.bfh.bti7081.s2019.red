package ch.bfh.red.backend.factories;


import ch.bfh.red.backend.models.Therapy;
import ch.bfh.red.backend.models.TherapyType;
import com.github.javafaker.Faker;

import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TherapyFactory extends AbstractFactory<Therapy> {
    private final Faker faker;
    private final TherapistFactory therapistFactory;
    private final PatientFactory patientFactory;

    public TherapyFactory(){
        this(Locale.getDefault());
    }

    public TherapyFactory(Locale locale){
        faker = new Faker(locale);
        therapistFactory = new TherapistFactory(locale);
        patientFactory = new PatientFactory(locale);
    }

    @Override
    public Therapy create(){
        Random random = new Random();
        Date startDate =  faker.date().past(1000, TimeUnit.DAYS);
        TherapyType therapyType =  TherapyType.values()[random.nextInt(TherapyType.values().length)];

        // TODO use new constructor
        Therapy therapy = new Therapy(startDate, therapyType);
        therapy.setPatient(patientFactory.create());
        therapy.setTherapist(therapistFactory.create());
        return therapy;

    }
}
