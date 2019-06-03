package ch.bfh.red.backend.factories;

import ch.bfh.red.backend.models.*;
import com.github.javafaker.Faker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExpositionNoteFactory extends AbstractFactory<ExpositionNote> {
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

    @Override
    public ExpositionNote create(){
        Date date = faker.date().past(100, TimeUnit.DAYS);
        Patient patient = patientFactory.create();
        String text = selectRandomExpositionNote();
        Visibility visibility = Visibility.values()[random.nextInt(Visibility.values().length)];
        return new ExpositionNote(patient, date, text, visibility, random.nextInt(10));
    }

    private String selectRandomExpositionNote() {
        List<String> randomExpositionNoteList = null;
        try (Stream<String> lines = Files.lines(Paths.get("./src/main/java/ch/bfh/red/backend/factories/sampleData/TherapistNoteSampleData"))) {
            randomExpositionNoteList = lines.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String randomExpositionNote = randomExpositionNoteList.get(random.nextInt(randomExpositionNoteList.size()));
        return randomExpositionNote;
    }

}