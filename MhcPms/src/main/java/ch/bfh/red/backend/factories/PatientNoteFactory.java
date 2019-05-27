package ch.bfh.red.backend.factories;

import ch.bfh.red.backend.models.*;
import com.github.javafaker.Faker;

import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class PatientNoteFactory {


    private final Faker faker;
    private final PatientFactory patientFactory;
    private final Random random;

    public PatientNoteFactory(){
        this(new Locale("de-ch"));
    }
    public PatientNoteFactory(Locale locale){
        faker = new Faker(locale);
        patientFactory = new PatientFactory(locale);
        random = new Random();
    }

    public PatientNote create(){

        Date date = faker.date().past(100, TimeUnit.DAYS);
        Patient patient = patientFactory.create();
        String text = "This is a note regarding " + patient.toString();
        return new PatientNote(patient, date, text, Visibility.values()[random.nextInt(Visibility.values().length)]);
    }
}
