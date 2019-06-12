package ch.bfh.red.backend.models;

public enum Visibility {
	PUBLIC("PUBLIC"), PRIVATE("PRIVATE");

	private String code;

	private Visibility(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	}
