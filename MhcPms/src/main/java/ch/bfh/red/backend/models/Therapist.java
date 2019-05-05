package ch.bfh.red.backend.models;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
public class Therapist extends User implements Serializable {
	@Id
	@GeneratedValue
	private Long id;
	private AcademicTitle academicTitle;
	private Collection<Therapy> therapies;

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

	public List<Therapist> search(SearchTherapist search) {
		if (search.getId() != null) {
			if (!id.equals(search.getId()))
				return Collections.emptyList();
		}
		return Arrays.asList(this);
	}

	public AcademicTitle getAcademicTitle() {
		return academicTitle;
	}

	public void setAcademicTitle(AcademicTitle academicTitle) {
		this.academicTitle = academicTitle;
	}

	public Collection<Therapy> getTherapies() {
		return therapies;
	}
}