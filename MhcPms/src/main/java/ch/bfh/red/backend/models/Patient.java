package ch.bfh.red.backend.models;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Patient extends Person implements Serializable {
	@Id
	@GeneratedValue
	private Long id;

	public Patient(long id, String firstName, String lastName, Address address) {
		super(firstName, lastName, address);
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Patient> search(SearchPatient search) {
		if (search.getId() != null) {
			if (!id.equals(search.getId()))
				return Collections.emptyList();
		}
		return Arrays.asList(this);
	}
}