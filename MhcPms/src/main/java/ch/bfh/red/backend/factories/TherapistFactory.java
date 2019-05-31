package ch.bfh.red.backend.factories;

import java.util.Locale;
import java.util.Random;

import com.github.javafaker.Faker;

import ch.bfh.red.backend.models.AcademicTitle;
import ch.bfh.red.backend.models.Address;
import ch.bfh.red.backend.models.Therapist;

public class TherapistFactory extends AbstractFactory<Therapist> {
    private final Faker faker;
    private final AddressFactory addressFactory;
    private Random random;

    public TherapistFactory() {
        this(new Locale("de-ch"));
    }

    public TherapistFactory(Locale locale) {
        this.faker = new Faker(locale);
        this.addressFactory = new AddressFactory(locale);
        this.random = new Random();
    }

    @Override
    public Therapist create() {
        Address address = addressFactory.create();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String username = lastName.toLowerCase();
        String password = faker.regexify("[a-z1-9]{8}");
        AcademicTitle title = AcademicTitle.values()[random.nextInt(AcademicTitle.values().length)];
        return new Therapist(username, password, title, firstName, lastName, address);
    }

}