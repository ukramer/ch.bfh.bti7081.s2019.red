package ch.bfh.red.backend.models;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

@Entity
public class ExpositionNote  extends AbstractNote<ExpositionNote> {
	/*A number indicating the intensity of the exposition from 1 (relatively easy)
	to 10 (extremely challenging and stressful) */
	
	
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
}
