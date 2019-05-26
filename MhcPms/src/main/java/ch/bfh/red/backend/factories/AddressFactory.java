package ch.bfh.red.backend.factories;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.github.javafaker.Faker;

import ch.bfh.red.backend.models.Address;

public class AddressFactory {
    private final Faker faker;

    public AddressFactory(){
        faker = new Faker(new Locale("d-ch"));
    }

    public Address generateAddress(){

        String streetName = faker.address().streetName();
        String number = faker.address().buildingNumber();
        String city = faker.address().city();
        //zipCode seems to return "-" even for locale "d-ch". Therefore, numerify is used.
        // Annoying problem to be solved: postalCode has to correspond to city!
        String postalCode = faker.numerify("####");

        Address address = new Address(streetName, number, Integer.parseInt(postalCode), city);
        return address;

    }

    public List<Address> generateAddresses(int count){
        ArrayList<Address> addresses = new ArrayList<>();
        for(int i=0; i<count; ++i){
            addresses.add(generateAddress());
        }
        return addresses;

    }
}
