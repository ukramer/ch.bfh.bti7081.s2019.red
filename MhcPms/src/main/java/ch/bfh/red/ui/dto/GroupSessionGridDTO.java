package ch.bfh.red.ui.dto;

public class GroupSessionGridDTO {
	private Integer id;
	private String startDate;
	private String patients;
	private String therapists;
	
	public GroupSessionGridDTO() {}
	
	public GroupSessionGridDTO(Integer id, String startDate, String patients, String therapists) {
		this.id = id;
		this.startDate = startDate;
		this.patients = patients;
		this.therapists = therapists;
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
	
}
