package ch.bfh.red.backend.models;

public class SessionType {
	private final String name;
	private final String description;

	public SessionType(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
}