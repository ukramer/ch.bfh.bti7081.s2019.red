package ch.bfh.red.backend.models;

import org.hibernate.annotations.Cascade;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import javax.persistence.*;

@Entity
public class Therapy implements Comparable<Therapy>, Serializable {

	/**
	 * Added attribute expositionNotes
	 */
	private static final long serialVersionUID = -4519216661077289951L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, unique = true)
	private int id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate = new Date();
	
	private boolean finished;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TherapyType therapyType;
	
	@ManyToOne
	@Cascade({org.hibernate.annotations.CascadeType.MERGE})
	private Patient patient;
	
	@ManyToOne
	@Cascade({org.hibernate.annotations.CascadeType.MERGE})
	private Therapist therapist;

	@OneToMany
	@JoinColumn(name="THERAPY_ID") // necessary to avoid join table
	@Cascade({org.hibernate.annotations.CascadeType.MERGE})
	private List<SingleSession> singleSessions;

	@ManyToMany
	@Cascade({org.hibernate.annotations.CascadeType.MERGE})
	private List<GroupSession> groupSessions;

	@OneToMany
	@JoinColumn(name="THERAPY_ID") // necessary to avoid join table
	@Cascade({org.hibernate.annotations.CascadeType.MERGE})
	private List<PatientNote> patientNotes;

	@OneToMany
	@JoinColumn(name="THERAPY_ID") // necessary to avoid join table
	@Cascade({org.hibernate.annotations.CascadeType.MERGE})
	private List<TherapistNote> therapistNotes;
	
	@OneToMany
	@JoinColumn(name="THERAPY_ID") // necessary to avoid join table
	@Cascade({org.hibernate.annotations.CascadeType.MERGE})
	private List<ExpositionNote> expositionNotes;
	
	public Therapy() {}
	
	public Therapy(Date startDate, TherapyType therapyType, Patient patient, Therapist therapist) {
		this.startDate = startDate;
		this.therapyType = therapyType;
		this.patient = patient;
		this.therapist = therapist;
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

	public List<SingleSession> getSingleSessions() {
		return singleSessions;
	}

	public void setSingleSessions(List<SingleSession> singleSessions) {
		this.singleSessions = singleSessions;
	}

	public List<GroupSession> getGroupSessions() {
		return groupSessions;
	}

	public void setGroupSessions(List<GroupSession> groupSessions) {
		this.groupSessions = groupSessions;
	}

	public List<PatientNote> getPatientNotes() {
		return patientNotes;
	}

	public void setPatientNotes(List<PatientNote> patientNotes) {
		this.patientNotes = patientNotes;
	}

	public List<TherapistNote> getTherapistNotes() {
		return therapistNotes;
	}

	public void setTherapistNotes(List<TherapistNote> therapistNotes) {
		this.therapistNotes = therapistNotes;
	}

	public List<ExpositionNote> getExpositionNotes() {
		return expositionNotes;
	}

	public void setExpositionNotes(List<ExpositionNote> expositionNotes) {
		this.expositionNotes = expositionNotes;
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
		return Objects.hash(patient, therapist, startDate, therapyType);
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

	@Override
	public String toString() {
		return "Therapy [startDate=" + startDate + ", finished=" + finished
				+ ", therapyType=" + therapyType + ", patient=" + patient + ", therapist="
				+ therapist + "]";
	}
	
}
