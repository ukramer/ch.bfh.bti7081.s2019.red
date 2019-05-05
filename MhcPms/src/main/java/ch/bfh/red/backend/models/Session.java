package ch.bfh.red.backend.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public abstract class Session implements Serializable {
	@Id
	@GeneratedValue
	private Long id;
	private Date startDate;
	private Date endDate;
	private SessionType sessionType;
	private Therapist therapist;

	public Session(Therapist therapist, Date startDate, Date endDate, SessionType sessionType) {
		this.setTherapist(therapist);
		this.startDate = startDate;
		this.endDate = endDate;
		this.sessionType = sessionType;
	}

	public Session(Collection<Therapist> therapists, Date startDate, Date endDate, SessionType sessionType) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.sessionType = sessionType;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public SessionType getSessionType() {
		return sessionType;
	}

	public void setSessionType(SessionType sessionType) {
		this.sessionType = sessionType;
	}

	public Therapist getTherapist() {
		return therapist;
	}

	public void setTherapist(Therapist therapist) {
		this.therapist = therapist;
	}

	public abstract Therapist getLeader();
}