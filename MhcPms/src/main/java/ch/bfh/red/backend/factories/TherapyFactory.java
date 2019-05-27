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
    private final Random random;

    public TherapyFactory(){
        this(new Locale("de-ch"));
    }

    public TherapyFactory(Locale locale){
        faker = new Faker(locale);
        therapistFactory = new TherapistFactory(locale);
        patientFactory = new PatientFactory(locale);
        random = new Random();
    }

    public Therapy create(){
        Random random = new Random();
        Date startDate =  faker.date().past(1000, TimeUnit.DAYS);
        TherapyType therapyType =  TherapyType.values()[random.nextInt(TherapyType.values().length)];

        Therapy therapy = new Therapy(startDate, therapyType);
        therapy.setPatient(patientFactory.create());
        therapy.setTherapist(therapistFactory.create());
        return therapy;

    }
}
