package ch.bfh.red.backend.models;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "ADDRESSES")
public class Address implements Comparable<Address>, Serializable {
	private static final long serialVersionUID = 8153106662017090155L;
	
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;
	
	@Column(name = "STREET")
	private String street;
	
	@Column(name = "STREET_NUMBER")
	private String streetNumber;
	
	@Column(name = "POSTAL_CODE")
	private int postalCode;
	
	@Column(name = "CITY")
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
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
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
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (postalCode != other.postalCode)
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		if (streetNumber == null) {
			if (other.streetNumber != null)
				return false;
		} else if (!streetNumber.equals(other.streetNumber))
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