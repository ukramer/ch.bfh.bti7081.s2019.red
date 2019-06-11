package ch.bfh.red.ui.dto;

public class PatientDTO {
	private Integer id;
	private String firstName;
	private String lastName;
	
	public PatientDTO() {}
	
	public PatientDTO(Integer id, String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return String.format("%s %s", lastName, firstName);
	}
	
}
