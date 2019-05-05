package ch.bfh.red.backend.models;

import java.io.Serializable;

public class AcademicTitle implements Serializable {
	private final String prefix;
	private final String description;

	public AcademicTitle(String prefix, String description) {
		this.prefix = prefix;
		this.description = description;
	}

	@Override
	public String toString() {
		return prefix + " ";
	}
}