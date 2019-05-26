package ch.bfh.red.backend.factories;

import ch.bfh.red.backend.models.Address;
import ch.bfh.red.backend.models.Patient;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PatientFactory {

    private Faker faker;


    public PatientFactory() {
        faker = new Faker(new Locale("de-ch"));

    }


    public Patient generatePatient(Address address){

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();

        Patient patient = new Patient(firstName, lastName, address);
        return patient;

    }

    public List<Patient> generatePatients(List<Address> addresses) {
        ArrayList<Patient> patients = new ArrayList<>();
        for (int i = 0; i < addresses.size(); i++) {

            patients.add(generatePatient(addresses.get(i)));

        }
        return patients;
    }
}







