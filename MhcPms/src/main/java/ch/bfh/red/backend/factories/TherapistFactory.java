package ch.bfh.red.backend.factories;

import ch.bfh.red.backend.models.AcademicTitle;
import ch.bfh.red.backend.models.Address;
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.models.Therapist;
import com.github.javafaker.Faker;

import java.util.Locale;
import java.util.Random;

public class TherapistFactory extends AbstractFactory<Therapist> {

    private final Faker faker;
    private final AddressFactory addressFactory;


    public TherapistFactory() {
        this(new Locale("de-ch"));
    }

    public TherapistFactory(Locale locale) {
        this.faker = new Faker(locale);
        this.addressFactory = new AddressFactory(locale);

    }

    @Override
    public Therapist create() {
        Address address = addressFactory.create();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        AcademicTitle title = getRandomAcademicTitle();
        String username = lastName.toLowerCase();
        String password = faker.regexify("[a-z1-9]{8}");


        return new Therapist(username, password, title, firstName, lastName, address);
    }

    public static AcademicTitle getRandomAcademicTitle() {
        Random random = new Random();
        return AcademicTitle.values()[random.nextInt(AcademicTitle.values().length)];
    }
}