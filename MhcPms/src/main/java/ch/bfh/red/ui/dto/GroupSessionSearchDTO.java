package ch.bfh.red.ui.dto;

import java.util.Date;

public class GroupSessionSearchDTO {
	private PersonDTO patient;
	private PersonDTO therapist;
	private Date startDate;
	private Date endDate;
	
	public GroupSessionSearchDTO() {}

	public PersonDTO getPatient() {
		return patient;
	}

	public void setPatient(PersonDTO patient) {
		this.patient = patient;
	}

	public PersonDTO getTherapist() {
		return therapist;
	}

	public void setTherapist(PersonDTO therapist) {
		this.therapist = therapist;
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

}
