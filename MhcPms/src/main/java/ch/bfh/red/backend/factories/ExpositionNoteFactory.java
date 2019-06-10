package ch.bfh.red.backend.factories;

import ch.bfh.red.backend.models.*;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.Collection;
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
    	return create(patientFactory.create());
    }
    
    public ExpositionNote create(Patient patient){
        Date date = faker.date().past(100, TimeUnit.DAYS);
        String text = "This is an exposition note regarding " + patient.toString();
        Visibility visibility = Visibility.values()[random.nextInt(Visibility.values().length)]; 
        return new ExpositionNote(patient, date, text, visibility, random.nextInt(10)+1);
    }
    
    public Collection<ExpositionNote> create(int count, Patient patient) {
    	Collection<ExpositionNote> patientNotes = new ArrayList<>();
    	for (int i = 0; i<count; i++)
    		patientNotes.add(create(patient));
    	return patientNotes;
    }
    
}
