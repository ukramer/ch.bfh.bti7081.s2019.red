package ch.bfh.red.backend.factories;

import ch.bfh.red.backend.models.Therapist;
import ch.bfh.red.backend.models.TherapistNote;
import ch.bfh.red.backend.models.Visibility;
import com.github.javafaker.Faker;

import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TherapistNoteFactory extends AbstractFactory<TherapistNote> {
    private final Faker faker;
    private final TherapistFactory therapistFactory;
    private final Random random;

    public TherapistNoteFactory(){
        this(new Locale("de-ch"));
    }

    public TherapistNoteFactory(Locale locale){
        faker = new Faker(locale);
        therapistFactory = new TherapistFactory(locale);
        random = new Random();
    }

    @Override
    public TherapistNote create(){
        Date date = faker.date().past(100, TimeUnit.DAYS);
        Therapist therapist = therapistFactory.create();
        String text = "This is a note by " + therapist.toString();
        Visibility visibility = Visibility.values()[random.nextInt(Visibility.values().length)];
        return new TherapistNote(therapist, date, text, visibility);
    }
    
}
