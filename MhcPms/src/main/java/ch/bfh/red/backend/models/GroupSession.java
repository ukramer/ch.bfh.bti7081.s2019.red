package ch.bfh.red.backend.models;

import java.util.Collection;
import java.util.Date;

public class GroupSession extends Session {
	private Collection<Patient> patients;
	private Collection<Therapist> therapists;
	private Therapist leader;

	public GroupSession(Collection<Patient> patients, Collection<Therapist> therapists, Date startDate, Date endDate,
			SessionType sessionType) {
		super(therapists, startDate, endDate, sessionType);
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
	public Therapist getLeader() {
		return leader;
	}

	public void setLeader(Therapist leader) {
		this.leader = leader;
	}
}