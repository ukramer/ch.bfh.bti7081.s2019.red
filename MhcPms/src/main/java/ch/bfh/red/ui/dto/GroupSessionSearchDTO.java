package ch.bfh.red.ui.dto;

import java.util.Date;

public class GroupSessionSearchDTO {
	private PatientDTO patient;
	private PatientDTO therapist;
	private Date startDate;
	private Date endDate;
	
	public GroupSessionSearchDTO() {}

	public PatientDTO getPatient() {
		return patient;
	}

	public void setPatient(PatientDTO patient) {
		this.patient = patient;
	}

	public PatientDTO getTherapist() {
		return therapist;
	}

	public void setTherapist(PatientDTO therapist) {
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
