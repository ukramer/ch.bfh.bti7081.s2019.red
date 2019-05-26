package ch.bfh.red.backend.models;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.io.Serializable;

import javax.persistence.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class AbstractPerson<T extends AbstractPerson<T>> implements Comparable<T>, Serializable {
	private static final long serialVersionUID = 6579309230709538479L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable = false, unique=true)
	private int id;
	
	@Column(nullable = false)
	private String firstName;
	
	@Column(nullable = false)
	private String lastName;
	
	@OneToOne
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private Address address;

	public AbstractPerson() {}
	
	public AbstractPerson(String firstName, String lastName, Address address) {
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

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAddress(Address address) {
		this.address = address;
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
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
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
		AbstractPerson<?> other = (AbstractPerson<?>) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}
	
	@Override
	public int compareTo(T o) {
		int i;
		i = lastName.compareTo(o.getLastName());
		if (i != 0) return i;
		i = firstName.compareTo(o.getFirstName());
		if (i != 0) return i;
		return address.compareTo(o.getAddress());
	}

	@Override
	public String toString() {
		return "AbstractPerson [firstName=" + firstName + ", lastName=" + lastName
				+ ", address=" + address + "]";
	}

}