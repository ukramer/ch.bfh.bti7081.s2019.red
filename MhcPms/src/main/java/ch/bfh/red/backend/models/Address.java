package ch.bfh.red.backend.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address implements Comparable<Address>, Serializable {
	private static final long serialVersionUID = 8153106662017090155L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable = false, unique = true)
	private Integer id;
	
	@Column(nullable = false)
	private String street;
	
	@Column(nullable = false)
	private String streetNumber;
	
	@Column(nullable = false)
	private int postalCode;
	
	@Column(nullable = false)
	private String city;
	
	public Address() {}

	public Address(String street, String streetNumber, int postalCode, String city) {
		this.street = street;
		this.streetNumber = streetNumber;
		this.postalCode = postalCode;
		this.city = city;
	}

	public String getStreet() {
		return street;
	}
	
	public String getStreetNumber() {
		return streetNumber;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setStreet(String street) {
		this.street = street;
	}
	
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + postalCode;
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		result = prime * result + ((streetNumber == null) ? 0 : streetNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		Address other = (Address) obj;
		if (city == null && other.city != null)
			return false;
		if (!city.equals(other.city))
			return false;

		if (postalCode != other.postalCode)
			return false;

		if (street == null && other.street != null)
			return false;
		if (!street.equals(other.street))
			return false;

		if (streetNumber == null && other.streetNumber != null)
			return false;
		if (!streetNumber.equals(other.streetNumber))
			return false;
		return true;
	}

	@Override
	public int compareTo(Address o) {
		int i;
		i = city.compareTo(o.getCity());
		if (i != 0) return i;
		i = street.compareTo(o.street);
		if (i != 0) return i;
		i = streetNumber.compareTo(o.streetNumber);
		return i;
	}

	@Override
	public String toString() {
		return "Address [street=" + street + ", streetNumber=" + streetNumber
				+ ", postalCode=" + postalCode + ", city=" + city + "]";
	}
	
}