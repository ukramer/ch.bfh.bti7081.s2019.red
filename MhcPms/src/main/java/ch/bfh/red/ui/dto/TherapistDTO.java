package ch.bfh.red.ui.dto;

import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;

import ch.bfh.red.common.DTOutils;

public class TherapistDTO {
	private Integer id;
	private String prefix;
	private String firstName;
	private String lastName;
	
	public TherapistDTO() {}
	
	public TherapistDTO(String prefix, String firstName, String lastName) {
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
	
	public String getPrefix() {
		return prefix;
	}
	
	public void setPrefix(String prefix) {
		this.prefix = prefix;
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
		result = prime * result + ((prefix == null) ? 0 : prefix.hashCode());
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
		TherapistDTO other = (TherapistDTO) obj;
		if (equalsOrNull(other, o -> o.getId()))
			return false;
		if (equalsOrNull(other, o -> o.getPrefix()))
			return false;
		if (equalsOrNull(other, o -> o.getFirstName()))
			return false;
		if (equalsOrNull(other, o -> o.getLastName()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		if (StringUtils.isBlank(prefix))
			return String.format("%s %s", lastName, firstName);
		return String.format("%s %s %s", prefix, lastName, firstName);
	}
	
	private <T> boolean equalsOrNull(TherapistDTO other,
										Function<TherapistDTO, T> mapper) {
			return DTOutils.equalsOrNull(this, other, mapper);
		}
	
}
