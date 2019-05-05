package ch.bfh.red.backend.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Therapy implements Serializable {
	@Id
	@GeneratedValue
	private Long id;
	private Date startDate;
	private boolean finished;
	private TherapyType therapyType;
	private Collection<SingleSession> singleSessions;
	private Collection<GroupSession> groupSessions;
	private Collection<PatientNote> patientNotes;
	private Collection<TherapistNote> therapistNotes;

	public Long getId() {
		return id;
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

	public Collection<SingleSession> getSingleSessions() {
		return singleSessions;
	}

	public Collection<GroupSession> getGroupSessions() {
		return groupSessions;
	}

	public Collection<PatientNote> getPatientNotes() {
		return patientNotes;
	}

	public Collection<TherapistNote> getTherapistNotes() {
		return therapistNotes;
	}
}