package ch.bfh.red.backend.models;

import java.util.ArrayList;
import java.util.Collection;

public class Therapist extends AbstractUser<Therapist> {

	private AcademicTitle academicTitle;
	
	private Collection<Patient> patients = new ArrayList<>();
	
	private Collection<GroupSession> groupSessions = new ArrayList<>();
	
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
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Therapist other = (Therapist) obj;
		if (academicTitle == null) {
			if (other.academicTitle != null)
				return false;
		} else if (!academicTitle.equals(other.academicTitle))
			return false;
		return super.equals(obj);
	}
	
	@Override
	public int compareTo(Therapist o) {
		return super.compareTo(o);
	}

	@Override
	public String toString() {
		return "Therapist [academicTitle=" + academicTitle +", " +super.toString() +"]";
	}
	
}