package ch.bfh.red.backend.factories;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.github.javafaker.Faker;

import ch.bfh.red.backend.models.Address;

public class AddressFactory extends AbstractFactory<Address> {
    private final Faker faker;

    public AddressFactory(){
        this(new Locale("de-ch"));
    }
    
    public AddressFactory(Locale locale) {
    	faker = new Faker(locale);
    }

    @Override
    public Address create(){

        String streetName = faker.address().streetName();
        String number = faker.address().buildingNumber();
        String city = faker.address().city();
        //zipCode seems to return "-" even for locale "d-ch". Therefore, numerify is used.
        String postalCode = faker.numerify("####");

        Address address = new Address(streetName, number, Integer.parseInt(postalCode), city);
        return address;

    }

}
