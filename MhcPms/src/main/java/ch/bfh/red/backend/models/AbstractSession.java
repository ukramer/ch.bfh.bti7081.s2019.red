package ch.bfh.red.backend.models;

import java.util.Date;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractSession<T extends AbstractSession<T>> implements Comparable<T> {
	private Therapist therapist;
	private Date startDate;
	private Date endDate;
	private SessionType sessionType;

	public AbstractSession() {}
	
	public AbstractSession(Therapist therapist, Date startDate, Date endDate, SessionType sessionType) {
		this.therapist = therapist;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((therapist == null) ? 0 : therapist.hashCode());
		result = prime * result + ((sessionType == null) ? 0 : sessionType.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractSession<?> other = (AbstractSession<?>) obj;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (therapist == null) {
			if (other.therapist != null)
				return false;
		} else if (!therapist.equals(other.therapist))
			return false;
		if (sessionType == null) {
			if (other.sessionType != null)
				return false;
		} else if (!sessionType.equals(other.sessionType))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}
	
	@Override
	public int compareTo(T o) {
		int i;
		i = startDate.compareTo(o.getStartDate());
		if (i != 0) return i;
		i = sessionType.compareTo(o.getSessionType());
		if (i != 0) return i;
		return therapist.compareTo(o.getTherapist());
	}

	@Override
	public String toString() {
		return "AbstractSession [therapist=" + therapist + ", sessionType=" + sessionType
				+ ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	
}