package ch.bfh.red.backend.factories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.github.javafaker.Faker;

import ch.bfh.red.backend.models.Therapist;
import ch.bfh.red.backend.models.TherapistNote;
import ch.bfh.red.backend.models.Visibility;

public class TherapistNoteFactory extends AbstractFactory<TherapistNote> {
    private final Faker faker;
    private final TherapistFactory therapistFactory;
    private final Random random;

    public TherapistNoteFactory(){
        this(Locale.getDefault());
    }

    public TherapistNoteFactory(Locale locale){
        faker = new Faker(locale);
        therapistFactory = new TherapistFactory(locale);
        random = new Random();
    }

    @Override
    public TherapistNote create(){
        Therapist therapist = therapistFactory.create();
        return create(therapist);
    }
    
    public TherapistNote create(Therapist therapist){
        Date date = faker.date().past(100, TimeUnit.DAYS);
        String text = "This is a note by " + therapist.toString();
        Visibility visibility = Visibility.values()[random.nextInt(Visibility.values().length)];
        return new TherapistNote(therapist, date, text, visibility);
    }
    
    public Collection<TherapistNote> create(int count, Therapist therapist) {
    	Collection<TherapistNote> patientNotes = new ArrayList<>();
    	for (int i = 0; i<count; i++)
    		patientNotes.add(create(therapist));
    	return patientNotes;
    }
    
}
