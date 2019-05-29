package ch.bfh.red.backend.factories;

import ch.bfh.red.backend.models.GroupSession;
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.models.SessionType;
import ch.bfh.red.backend.models.Therapist;
import com.github.javafaker.DateAndTime;
import com.github.javafaker.Faker;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class GroupSessionFactory extends AbstractFactory<GroupSession> {


    private final Faker faker;
    private final TherapistFactory therapistFactory;
    private final PatientFactory patientFactory;

    public GroupSessionFactory(){
        this(new Locale("de-ch"));
    }

    public GroupSessionFactory(Locale locale){
        faker = new Faker();
        therapistFactory = new TherapistFactory(locale);
        patientFactory = new PatientFactory(locale);

    }
    @Override
    public GroupSession create(){
        Random random = new Random();
        List<Therapist> therapists = therapistFactory.create(random.nextInt(4));
        List<Patient> patients  = patientFactory.create(random.nextInt(8));
        Therapist leader = therapists.get(random.nextInt(3));
        Date startDate = faker.date().past(1000, TimeUnit.DAYS);
        Date endDate = faker.date().past(365, TimeUnit.DAYS);
        SessionType sessionType =  SessionType.values()[random.nextInt(SessionType.values().length)];

        return new GroupSession(patients, therapists, leader, startDate, endDate, sessionType);

    }
}
