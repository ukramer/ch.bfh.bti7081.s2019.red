package ch.bfh.red.backend.factories;

import ch.bfh.red.backend.models.*;
import com.github.javafaker.Faker;

import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ExpositionNoteFactory {


    private final Faker faker;
    private final PatientFactory patientFactory;
    private final Random random;

    public ExpositionNoteFactory(){
        this(new Locale("d-ch"));
    }

    public ExpositionNoteFactory(Locale locale){
        faker = new Faker(locale);
        patientFactory = new PatientFactory(locale);
        random = new Random();
    }

    public ExpositionNote create(){

        Date date = faker.date().past(100, TimeUnit.DAYS);
        Patient patient = patientFactory.create();
        String text = "This is an exposition note regarding " + patient.toString();
        return new ExpositionNote(patient, date, text,
                Visibility.values()[random.nextInt(Visibility.values().length)], random.nextInt(10));
    }
}
