package ch.bfh.red.backend.models;

import org.hibernate.annotations.Cascade;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

import javax.persistence.*;

@Entity
public class Therapy implements Comparable<Therapy>, Serializable {
	private static final long serialVersionUID = 3832801113638175718L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private int id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	
	private boolean finished;
	
	@OneToOne
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private TherapyType therapyType;
	
	@ManyToOne
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private Patient patient;
	
	@ManyToOne
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private Therapist therapist;
	
	@ManyToMany
	private Collection<SingleSession> singleSessions = new ArrayList<>();
	
	@ManyToMany
	private Collection<GroupSession> groupSessions = new ArrayList<>();
	
	@ManyToMany
	private Collection<PatientNote> patientNotes = new ArrayList<>();
	
	@ManyToMany
	private Collection<TherapistNote> therapistNotes = new ArrayList<>();
	
	public Therapy() {}
	
	// TODO add patient, therapist, ...
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

	public LocalDate getStartDateAsLocalDate() {
		Instant instant = getStartDate().toInstant();
		return instant.atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public void setStartDateAsLocalDate(LocalDate startDate) {
		setStartDate(Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
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

	public Therapist getTherapist() {
		return therapist;
	}

	public void setTherapist(Therapist therapist) {
		this.therapist = therapist;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
				&& Objects.equals(therapist, other.therapist)
				&& Objects.equals(startDate, other.startDate)
				&& Objects.equals(therapyType, other.therapyType);
	}
	
	@Override
	public int compareTo(Therapy o) {
		int i;
		i = therapist.compareTo(o.therapist);
		if (i != 0) return i;
		i = patient.compareTo(o.patient);
		if (i != 0) return i;
		i = startDate.compareTo(startDate);
		if (i != 0) return i;
		return therapyType.compareTo(o.therapyType);
	}
	
}