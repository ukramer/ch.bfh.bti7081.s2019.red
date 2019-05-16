package ch.bfh.red.backend.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

public class Therapy implements Comparable<Therapy> {
	private Date startDate;
	private boolean finished;
	private TherapyType therapyType;
	private Patient patient;
	private Collection<SingleSession> singleSessions = new ArrayList<>();
	private Collection<GroupSession> groupSessions = new ArrayList<>();
	private Collection<PatientNote> patientNotes = new ArrayList<>();
	private Collection<TherapistNote> therapistNotes = new ArrayList<>();
	
	public Therapy() {}
	
	public Therapy(Date startDate, TherapyType therapyType) {
		this.startDate = startDate;
		this.therapyType = therapyType;
		this.finished = false;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public TherapyType getTherapyType() {
		return therapyType;
	}

	public void setTherapyType(TherapyType therapyType) {
		this.therapyType = therapyType;
	}
	
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
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

	public Collection<PatientNote> getPatientNotes() {
		return patientNotes;
	}

	public void setPatientNotes(Collection<PatientNote> patientNotes) {
		this.patientNotes = patientNotes;
	}

	public Collection<TherapistNote> getTherapistNotes() {
		return therapistNotes;
	}

	public void setTherapistNotes(Collection<TherapistNote> therapistNotes) {
		this.therapistNotes = therapistNotes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(patient, startDate, therapyType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Therapy other = (Therapy) obj;
		return Objects.equals(patient, other.patient)
				&& Objects.equals(startDate, other.startDate)
				&& Objects.equals(therapyType, other.therapyType);
	}
	
	@Override
	public int compareTo(Therapy o) {
		int i;
		i = patient.compareTo(o.patient);
		if (i != 0) return i;
		i = startDate.compareTo(startDate);
		if (i != 0) return i;
		return therapyType.compareTo(o.therapyType);
	}
	
	
	
}