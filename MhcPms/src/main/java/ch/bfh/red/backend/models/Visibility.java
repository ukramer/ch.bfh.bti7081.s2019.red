package ch.bfh.red.backend.models;

public class Visibility {
	private final String name;
	private final String description;

	public Visibility(String name, String description) {
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