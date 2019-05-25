package ch.bfh.red.backend.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class ExpositionNote  extends AbstractNote<ExpositionNote> {
	/*A number indicating the intensity of the exposition from 1 (relatively easy)
	to 10 (extremely challenging and stressful) */

    @Column(nullable = false)
	private Integer degreeOfExposure;   
	
    public ExpositionNote() {}
    
    public ExpositionNote(Date date, String text, Visibility visibility, int degreeOfExposure) {
    	
    	super(date, text, visibility);
    	this.degreeOfExposure = degreeOfExposure;
    }
    
    public Integer getDegreeOfExposure() {
    	
    	return degreeOfExposure;
    }
    
    public void setDegreeOfExposure(int val) {
    	this.degreeOfExposure= (Integer)val;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
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
        if (degreeOfExposure == null) {
            if (other.degreeOfExposure != null)
                return false;
        } else if (!degreeOfExposure.equals(other.degreeOfExposure))
            return false;
        return super.equals(obj);
    }

    @Override
    public int compareTo(ExpositionNote o) {
        int i;
        i = degreeOfExposure.compareTo(o.degreeOfExposure);
        if (i != 0) return i;
        return super.compareTo(o);
    }

    @Override
    public String toString() {
        return "ExpositionNote [Expositionsgrad=" + degreeOfExposure+ ", "
                + super.toString() + "]";
    }


}
