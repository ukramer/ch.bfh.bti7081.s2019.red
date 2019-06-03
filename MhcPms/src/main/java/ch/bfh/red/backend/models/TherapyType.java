package ch.bfh.red.backend.models;

public enum TherapyType {
	PSYCHO("Psychotherapy", "Psychotherapy session with therapist"),
	EXPOSITION("Exposition","Exposition therapy for OCD patients"),
	PHYSIO("Physical", "Physical and body therapy"),
	ART("Art", "Art and music therapy");

	private String code;
	private String description;

	private TherapyType(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public String getDescription(){
		return description;
	}
}
