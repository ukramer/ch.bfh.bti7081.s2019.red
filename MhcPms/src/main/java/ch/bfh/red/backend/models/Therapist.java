package ch.bfh.red.backend.models;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

@Entity
public class Therapist extends AbstractUser<Therapist> {
    private static final long serialVersionUID = 3328637872951953081L;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private AcademicTitle academicTitle;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private Collection<Patient> patients = new ArrayList<>();
	
	@OneToMany(mappedBy = "therapist")
    private Collection<SingleSession> singleSessions = new ArrayList<>();
	
	@ManyToMany
	private Collection<GroupSession> groupSessions = new ArrayList<>();
	
	@OneToMany(mappedBy = "therapist")
	private Collection<Therapy> therapies = new ArrayList<>();
	
	public Therapist() {}

	public Therapist(String username, String password, AcademicTitle academicTitle, String firstName,
			String lastName, Address address) {
		super(username, password, firstName, lastName, address);
		this.academicTitle = academicTitle;
	}

	public AcademicTitle getAcademicTitle() {
		return academicTitle;
	}

	public void setAcademicTitle(AcademicTitle academicTitle) {
		this.academicTitle = academicTitle;
	}

	public Collection<Patient> getPatients() {
		return patients;
	}

	public void setPatients(Collection<Patient> patients) {
		this.patients = patients;
	}

	public Collection<SingleSession> getSingleSessions() {
        return singleSessions;
    }

    public void setSingleSessions(Collection<SingleSession> singleSessions) {
        this.singleSessions = singleSessions;
    }

    public Collection<GroupSession> getGroupSessions() {
		return groupSessions;
	}

	public void setGroupSessions(Collection<GroupSession> groupSessions) {
		this.groupSessions = groupSessions;
	}

	public Collection<Therapy> getTherapies() {
		return therapies;
	}

	public void setTherapies(Collection<Therapy> therapies) {
		this.therapies = therapies;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((academicTitle == null) ? 0 : academicTitle.hashCode());
		result = prime * result
				+ ((groupSessions == null) ? 0 : groupSessions.hashCode());
		result = prime * result + ((patients == null) ? 0 : patients.hashCode());
		result = prime * result + ((therapies == null) ? 0 : therapies.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		Therapist other = (Therapist) obj;
		if (academicTitle == null && other.academicTitle != null)
			return false;
		if (!academicTitle.equals(other.academicTitle))
			return false;
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return (academicTitle != null ? academicTitle.getCode() + " " : "") + getLastName() + " " + getFirstName();
	}
	
}
