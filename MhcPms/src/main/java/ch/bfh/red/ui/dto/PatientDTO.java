package ch.bfh.red.ui.dto;

import java.util.function.Function;

import ch.bfh.red.common.DTOutils;

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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
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
		PatientDTO other = (PatientDTO) obj;
		if (!equalsOrNull(other, o -> o.getId()))
			return false;
		if (!equalsOrNull(other, o -> o.getFirstName()))
			return false;
		if (!equalsOrNull(other, o -> o.getLastName()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("%s %s", lastName, firstName);
	}
	
	private <T> boolean equalsOrNull(PatientDTO other,
										Function<PatientDTO, T> mapper) {
			return DTOutils.equalsOrNull(this, other, mapper);
		}
	
}
