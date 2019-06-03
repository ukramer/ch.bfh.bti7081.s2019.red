package ch.bfh.red.backend.factories;

import ch.bfh.red.backend.models.*;
import com.github.javafaker.Faker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PatientNoteFactory extends AbstractFactory<PatientNote> {
    private final Faker faker;
    private final PatientFactory patientFactory;
    private final Random random;

    public PatientNoteFactory() {
        this(new Locale("de-ch"));
    }

    public PatientNoteFactory(Locale locale) {
        faker = new Faker(locale);
        patientFactory = new PatientFactory(locale);
        random = new Random();
    }

    @Override
    public PatientNote create() {
        Date date = faker.date().past(100, TimeUnit.DAYS);
        Patient patient = patientFactory.create();
        String text = selectRandomPatientNote();
        return new PatientNote(patient, date, text, Visibility.values()[random.nextInt(Visibility.values().length)]);
    }

    private String selectRandomPatientNote() {
        List<String> randomPatientNoteList = null;
        try (Stream<String> lines = Files.lines(Paths.get("./src/main/java/ch/bfh/red/backend/factories/sampleData/PatientNoteSampleData"))) {
            randomPatientNoteList = lines.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String randomPatientNote = randomPatientNoteList.get(random.nextInt(randomPatientNoteList.size()));
        return randomPatientNote;
    }

}
