package ch.bfh.red.backend.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class ExpositionNote extends AbstractNote<ExpositionNote> {

	/**
	 * Removed CascadeTypes
	 */
	private static final long serialVersionUID = -3982221341929865452L;

	@ManyToOne
    Patient patient;

    @Column(nullable = false)
    private Integer degreeOfExposure;

    public ExpositionNote() {}

    public ExpositionNote(Patient patient, Date date, String text, Visibility visibility, int degreeOfExposure) {
        super(date, text, visibility);
        this.patient = patient;
        this.degreeOfExposure = degreeOfExposure;
    }

    public Patient getPatient(){
        return patient;
    }

    public void setPatient(Patient patient){
        this.patient = patient;

    }
    public Integer getDegreeOfExposure() {
        return degreeOfExposure;
    }

    public void setDegreeOfExposure(int val) {
        this.degreeOfExposure = val;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((patient == null) ? 0 : patient.hashCode());
        result = prime * result + degreeOfExposure;
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
        ExpositionNote other = (ExpositionNote) obj;
        if (patient == null) {
            if (other.patient != null)
                return false;
        } else if (!patient.equals((other.patient)))
            return false;
        if (degreeOfExposure == null) {
            if (other.degreeOfExposure != null)
                return false;
        } else if (!degreeOfExposure.equals(other.degreeOfExposure))
            return false;
        return super.equals(obj);
    }

    public int compareTo(ExpositionNote o) {
        int i;
        i = patient.compareTo(o.patient);
        if (i != 0) return i;
        else
            i = degreeOfExposure.compareTo(o.degreeOfExposure);
        if (i != 0) return i;
        return super.compareTo(o);
    }

    @Override
    public String toString() {
        return "ExpositionNote [degreeOfExposure=" + degreeOfExposure + ", patient="+ patient.toString()+ ", "
                + super.toString() + "]";
    }

}
