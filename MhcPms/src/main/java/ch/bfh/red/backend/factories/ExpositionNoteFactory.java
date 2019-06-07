package ch.bfh.red.backend.factories;

import ch.bfh.red.backend.models.*;
import com.github.javafaker.Faker;

import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ExpositionNoteFactory extends AbstractFactory<ExpositionNote> {
    private final Faker faker;
    private final PatientFactory patientFactory;
    private final Random random;

    public ExpositionNoteFactory(){
        this(Locale.getDefault());
    }

    public ExpositionNoteFactory(Locale locale){
        faker = new Faker(locale);
        patientFactory = new PatientFactory(locale);
        random = new Random();
    }

    @Override
    public ExpositionNote create(){
        Date date = faker.date().past(100, TimeUnit.DAYS);
        Patient patient = patientFactory.create();
        String text = "This is an exposition note regarding " + patient.toString();
        Visibility visibility = Visibility.values()[random.nextInt(Visibility.values().length)]; 
        return new ExpositionNote(patient, date, text, visibility, random.nextInt(10));
    }
    
}
