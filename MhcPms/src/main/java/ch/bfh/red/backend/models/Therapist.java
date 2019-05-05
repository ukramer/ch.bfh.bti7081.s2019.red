package ch.bfh.red.backend.models;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Entity
public class Therapist extends User implements Serializable {
	@Id
	@GeneratedValue
	private Long id;
	private AcademicTitle academicTitle;

	public Therapist(long id, String username, String password, String firstName, String lastName, Address address) {
		super(username, password, firstName, lastName, address);
		this.id = id;
	}

	public Therapist(long id, String username, String password, AcademicTitle academicTitle, String firstName, String lastName, Address address) {
		super(username, password, firstName, lastName, address);
		this.id = id;
		this.academicTitle = academicTitle;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Therapist> search(SearchTherapist search) {
		if (search.getId() != null) {
			if (!id.equals(search.getId()))
				return Collections.emptyList();
		}
		return Arrays.asList(this);
	}
}