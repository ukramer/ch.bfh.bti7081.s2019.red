package ch.bfh.red.backend.factories;

import ch.bfh.red.backend.models.Address;
import ch.bfh.red.backend.models.Patient;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PatientFactory extends AbstractFactory<Patient> {

    private final Faker faker;
    private final AddressFactory addressFactory;

    
    public PatientFactory() {
    	this(new Locale("de-ch"));
    }

    public PatientFactory(Locale locale) {
        this.faker = new Faker(locale);
        this.addressFactory = new AddressFactory(locale);

    }
    
	@Override
	public Patient create() {
		Address address = addressFactory.create();
    	String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();

        Patient patient = new Patient(firstName, lastName, address);
        return patient;
	}

}







