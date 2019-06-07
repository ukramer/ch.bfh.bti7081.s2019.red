package ch.bfh.red.backend.factories;

import java.util.Locale;

import com.github.javafaker.Faker;

import ch.bfh.red.backend.models.Address;
import ch.bfh.red.backend.models.Patient;

public class PatientFactory extends AbstractFactory<Patient> {
    private final Faker faker;
    private final AddressFactory addressFactory;
    
    public PatientFactory() {
    	this(Locale.getDefault());
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







