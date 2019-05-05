package ch.bfh.red.backend.models;

import java.util.Date;

public class SingleSession extends Session {
	private Patient patient;
	private Therapist therapist;

	public SingleSession(Patient patient, Therapist therapist, Date startDate, Date endDate, SessionType sessionType) {
		super(therapist, startDate, endDate, sessionType);
		this.patient = patient;
	}

	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Therapist getTherapist() {
		return this.therapist;
	}

	public void setTherapist(Therapist therapist) {
		this.therapist = therapist;
	}

	@Override
	public Therapist getLeader() {
		return this.getTherapist();
	}
}