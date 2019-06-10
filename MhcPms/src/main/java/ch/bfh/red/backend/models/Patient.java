package ch.bfh.red.backend.models;

import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

@Entity
public class Patient extends AbstractPerson<Patient> {
	private static final long serialVersionUID = -6431476383345860703L;

    @OneToMany(mappedBy = "patient")
    @Cascade({org.hibernate.annotations.CascadeType.MERGE, org.hibernate.annotations.CascadeType.REMOVE})
    private Collection<Therapy> therapies = new ArrayList<>();

    @OneToMany(mappedBy = "patient")
    @Cascade({org.hibernate.annotations.CascadeType.REMOVE})
    private Collection<SingleSession> singleSessions = new ArrayList<>();

    @ManyToMany(mappedBy = "patients")
    @Cascade({org.hibernate.annotations.CascadeType.MERGE})
    private Collection<GroupSession> groupSessions = new ArrayList<>();

    @OneToMany(mappedBy = "patient")
    @Cascade({org.hibernate.annotations.CascadeType.REMOVE})
    private Collection<PatientNote> patientNotes = new ArrayList<>();

    @OneToMany(mappedBy = "patient")
    @Cascade({org.hibernate.annotations.CascadeType.REMOVE})
    private Collection<ExpositionNote> expositionNotes = new ArrayList<>();

    public Patient() {}

    public Patient(String firstName, String lastName, Address address) {
        super(firstName, lastName, address);
    }

    public Collection<Therapy> getTherapies() {
        return therapies;
    }

    public void setTherapies(Collection<Therapy> therapies) {
        this.therapies = therapies;
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

    @Override
    public int hashCode() {
        return super.hashCode();
    }

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return getLastName() + " " + getFirstName();
	}
	
}