package ch.bfh.red.backend.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class AbstractSession<T extends AbstractSession<T>> implements Comparable<T>, Serializable {
	private static final long serialVersionUID = -5765831140092626255L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable = false, unique=true)
	private int id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date startDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date endDate;
	
	@ManyToOne
	private SessionType sessionType;

	public AbstractSession() {}
	
	public AbstractSession(Date startDate, Date endDate, SessionType sessionType) {
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
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
		return sessionType.compareTo(o.getSessionType());
	}

	@Override
	public String toString() {
		return "AbstractSession [therapist=" + ", sessionType=" + sessionType
				+ ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	
}