package ch.bfh.red.backend.models;

public enum SessionType {
	TALK("Talk"),
	EXPOSITION("Exposition"),
	DISCUSSION("Discussion");


	private String code;

	private SessionType(String code) {
		this.code = code;

	}

	public String getCode() {
		return code;
	}


}
