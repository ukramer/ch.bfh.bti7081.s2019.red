package ch.bfh.red.ui.dto;

import ch.bfh.red.backend.models.SessionType;

public class GroupSessionGridDTO {
	private Integer id;
	private String startDate;
	private String patients;
	private String therapists;
	private SessionType sessionType;
	
	public GroupSessionGridDTO() {}
	
	public GroupSessionGridDTO(Integer id, String startDate, String patients, String therapists, SessionType sessionType) {
		this.id = id;
		this.startDate = startDate;
		this.patients = patients;
		this.therapists = therapists;
		this.sessionType = sessionType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getPatients() {
		return patients;
	}

	public void setPatients(String patients) {
		this.patients = patients;
	}

	public String getTherapists() {
		return therapists;
	}

	public void setTherapists(String therapists) {
		this.therapists = therapists;
	}

	public SessionType getSessionType() {
		return sessionType;
	}

	public void setSessionType(SessionType sessionType) {
		this.sessionType = sessionType;
	}
	
}
