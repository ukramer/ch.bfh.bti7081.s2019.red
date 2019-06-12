package ch.bfh.red.backend.factories;


import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.models.Therapist;
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
    private final Random random = new Random();

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
        Patient patient = patientFactory.create();
        Therapist therapist = therapistFactory.create();
        return create(patient, therapist);
    }
    
    public Therapy create(Patient patient, Therapist therapist) {
        Date startDate =  faker.date().past(1000, TimeUnit.DAYS);
        TherapyType therapyType =  createTherapyType();
        Therapy therapy =  new Therapy(startDate, therapyType, patient, therapist);
        therapy.setFinished(random.nextBoolean());
        return therapy;

    }
    
    public TherapyType createTherapyType() {
    	return TherapyType.values()[random.nextInt(TherapyType.values().length)];
    }
    
}
