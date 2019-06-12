package ch.bfh.red.backend.models;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Patient extends AbstractPerson<Patient> {

    /**
	 * Removed CascadeTypes
	 */
	private static final long serialVersionUID = 7882260288158996060L;

	@OneToMany(mappedBy = "patient")
    private Collection<Therapy> therapies = new ArrayList<>();

    @OneToMany(mappedBy = "patient")
    private Collection<SingleSession> singleSessions = new ArrayList<>();

    @ManyToMany(mappedBy = "patients")
    private Collection<GroupSession> groupSessions = new ArrayList<>();

    @OneToMany(mappedBy = "patient")
    private Collection<PatientNote> patientNotes = new ArrayList<>();

    @OneToMany(mappedBy = "patient")
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