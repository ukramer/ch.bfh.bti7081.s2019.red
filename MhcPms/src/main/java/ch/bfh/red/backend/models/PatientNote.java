package ch.bfh.red.backend.models;

import java.util.Date;

public class PatientNote extends Note {
	private Patient patient;

	public PatientNote(Date date, String text) {
		super(date, text);
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
}