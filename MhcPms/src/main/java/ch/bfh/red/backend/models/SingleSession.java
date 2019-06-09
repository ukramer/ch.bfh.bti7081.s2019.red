package ch.bfh.red.backend.models;

import org.hibernate.annotations.Cascade;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class SingleSession extends AbstractSession<SingleSession> {
    
    /**
     * Removed therapist as leader from AbstractSession
     */
    private static final long serialVersionUID = 8992909023275704028L;

    @ManyToOne
	@Cascade({org.hibernate.annotations.CascadeType.MERGE})
	private Patient patient;
	
	@ManyToOne
    @Cascade({org.hibernate.annotations.CascadeType.MERGE})
    private Therapist therapist;
	
	public SingleSession() {}
	
	public SingleSession(Patient patient, Therapist therapist, Date startDate,
			Date endDate, SessionType sessionType) {
		super(startDate, endDate, sessionType);
		this.patient = patient;
		this.therapist = therapist;
	}
	
	public Patient getPatient() {
		return this.patient;
	}
	
	public void setPatient(Patient patient) {
		this.patient = patient;
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
		int result = super.hashCode();
		result = prime * result + ((therapist == null) ? 0 : therapist.hashCode());
		result = prime * result + ((patient == null) ? 0 : patient.hashCode());
		result = prime * result + super.hashCode();
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		SingleSession other = (SingleSession) obj;
		if (patient == null) {
			if (other.patient != null)
				return false;
		} else if (!patient.equals(other.patient))
			return false;
		if (therapist == null) {
            if (other.therapist != null)
                return false;
        } else if (!therapist.equals(other.therapist))
            return false;
		return super.equals(obj);
	}
	
	@Override
	public int compareTo(SingleSession o) {
		int i;
		i = getTherapist().compareTo(o.getTherapist());
		if (i != 0)
			return i;
		i = patient.compareTo(o.patient);
		if (i != 0)
			return i;
		return super.compareTo(o);
	}
	
	@Override
	public String toString() {
		return "SingleSession [therapist=" + getTherapist() + ", patient="
				+ patient + ", " + super.toString() + "]";
	}
	
}
