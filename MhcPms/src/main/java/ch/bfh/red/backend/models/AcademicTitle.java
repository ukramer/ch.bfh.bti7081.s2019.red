package ch.bfh.red.backend.models;

public class AcademicTitle {
	private final String prefix;
	private final String description;

	public AcademicTitle(String prefix, String description) {
		this.prefix = prefix;
		this.description = description;
	}

	public String getPrefix() {
		return prefix;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return prefix + " ";
	}
}