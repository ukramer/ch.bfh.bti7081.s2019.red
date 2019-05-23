package ch.bfh.red.backend.models;

import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

@Entity
public class GroupSession extends AbstractSession<GroupSession> {
	private static final long serialVersionUID = -383961605951531556L;

	@ManyToMany(fetch = FetchType.LAZY)
	private Collection<Patient> patients;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private Collection<Therapist> therapists;

	public GroupSession() {}
	
	public GroupSession(Collection<Patient> patients, 
	                    Collection<Therapist> therapists, 
	                    Therapist leader, 
	                    Date startDate, 
	                    Date endDate,
			SessionType sessionType) {
		super(leader, startDate, endDate, sessionType);
		this.patients = patients;
	}

	public Collection<Patient> getPatients() {
		return this.patients;
	}

	public void setPatients(Collection<Patient> patients) {
		this.patients = patients;
	}

	public Collection<Therapist> getTherapists() {
		return this.therapists;
	}

	public void setTherapists(Collection<Therapist> therapists) {
		this.therapists = therapists;
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
		return true;
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