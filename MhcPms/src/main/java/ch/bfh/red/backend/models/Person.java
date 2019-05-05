package ch.bfh.red.backend.models;

public class Person {
	private final String firstName;
	private final String lastName;
	private final Address address;

	public Person(String firstName, String lastName, Address address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Address getAddress() {
		return address;
	}

	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Person person = (Person) o;
		if (firstName != null ? !firstName.equals(person.firstName) : person.firstName != null) {
			return false;
		}
		if (lastName != null ? !lastName.equals(person.lastName) : person.lastName != null) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return firstName + lastName + "\n" + address.toString();
	}

}