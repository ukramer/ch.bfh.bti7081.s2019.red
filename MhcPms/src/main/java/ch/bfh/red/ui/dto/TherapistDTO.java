package ch.bfh.red.ui.dto;

import org.apache.commons.lang3.StringUtils;

public class TherapistDTO {
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

	@Override
	public String toString() {
		if (StringUtils.isBlank(prefix))
			return String.format("%s %s", lastName, firstName);
		return String.format("%s %s %s", prefix, lastName, firstName);
	}
	
}
