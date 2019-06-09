package ch.bfh.red.backend.factories;

import ch.bfh.red.backend.models.*;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class PatientNoteFactory extends AbstractFactory<PatientNote> {
    private final Faker faker;
    private final PatientFactory patientFactory;
    private final Random random;

    public PatientNoteFactory(){
        this(Locale.getDefault());
    }
    
    public PatientNoteFactory(Locale locale){
        faker = new Faker(locale);
        patientFactory = new PatientFactory(locale);
        random = new Random();
    }

    @Override
    public PatientNote create(){
        Patient patient = patientFactory.create();
        return create(patient);
    }
    
    public PatientNote create(Patient patient){
        Date date = faker.date().past(100, TimeUnit.DAYS);
        String text = "This is a note regarding " + patient.toString();
        Visibility visibility = Visibility.values()[random.nextInt(Visibility.values().length)];
        return new PatientNote(patient, date, text, visibility);
    }
    
    public Collection<PatientNote> create(int count, Patient patient) {
    	Collection<PatientNote> patientNotes = new ArrayList<>();
    	for (int i = 0; i<count; i++)
    		patientNotes.add(create(patient));
    	return patientNotes;
    }
    
}
