package ch.bfh.red.backend.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

@Entity
public class GroupSession extends AbstractSession<GroupSession> {

	/**
	 * Removed CascadeTypes
	 */
	private static final long serialVersionUID = -4809341956248106723L;

	@ManyToMany(fetch = FetchType.LAZY)
	private List<Patient> patients;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Therapist> therapists;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Therapy> therapies;

	public GroupSession() {}
	
	public GroupSession(List<Patient> patients, List<Therapist> therapists, Date startDate, Date endDate,
			SessionType sessionType) {
		super(startDate, endDate, sessionType);
		this.patients = patients;
		this.therapists = therapists;
	}

	public List<Patient> getPatients() {
		return this.patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

	public List<Therapist> getTherapists() {
	    return therapists;
	}

	public void setTherapists(List<Therapist> therapists) {
		this.therapists = therapists;
	}	

	public List<Therapy> getTherapies() {
		return therapies;
	}

	public void setTherapies(List<Therapy> therapies) {
		this.therapies = therapies;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((patients == null) ? 0 : patients.hashCode());
		result = prime * result + ((therapists == null) ? 0 : therapists.hashCode());
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
		GroupSession other = (GroupSession) obj;
		if (patients == null) {
			if (other.patients != null)
				return false;
		} else if (!patients.equals(other.patients))
			return false;
		if (therapists == null) {
			if (other.therapists != null)
				return false;
		} else if (!therapists.equals(other.therapists))
			return false;
		return super.equals(obj);
	}

	@Override
	public int compareTo(GroupSession o) {
		int i;
		i = Integer.compare(o.therapists.size(), therapists.size());
		if (i != 0) return i;
		return Integer.compare(o.patients.size(), patients.size());
	}

	@Override
	public String toString() {
		return "GroupSession [therapists=" + therapists + ", patients=" 
				+patients + ", " +super.toString() +"]";
	}

}