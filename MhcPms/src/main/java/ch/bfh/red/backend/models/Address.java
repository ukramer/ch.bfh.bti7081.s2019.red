package ch.bfh.red.backend.models;

public class Address {
	private final String street;
	private final int plz;
	private final String city;

	public Address(String street, int plz, String city) {
		this.street = street;
		this.plz = plz;
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public int getPlz() {
		return plz;
	}

	public String getCity() {
		return city;
	}

	@Override
	public String toString() {
		return street + "\n" + plz + " " + city;
	}
}