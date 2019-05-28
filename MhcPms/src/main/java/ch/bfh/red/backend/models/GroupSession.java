package ch.bfh.red.backend.models;

import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class GroupSession extends AbstractSession<GroupSession> {
	private static final long serialVersionUID = -383961605951531556L;

	@ManyToMany(fetch = FetchType.LAZY)
	@Cascade({org.hibernate.annotations.CascadeType.MERGE})
	private List<Patient> patients;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@Cascade({org.hibernate.annotations.CascadeType.MERGE})
	private List<Therapist> therapists;

	public GroupSession() {}
	
	public GroupSession(List<Patient> patients, List<Therapist> therapists, Therapist leader, Date startDate, Date endDate,
			SessionType sessionType) {
		super(startDate, endDate, sessionType, leader);
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
		List<Therapist> therapists = new ArrayList<>();
		for (Therapist therapist : this.therapists) {
			// iff therapist is the leader, don't want him in this List anymore
			if (therapist.equals(this.getTherapist())) {
				continue;
			}
			therapists.add(therapist);
		}
		return therapists;
	}

	public void setTherapists(List<Therapist> therapists) {
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