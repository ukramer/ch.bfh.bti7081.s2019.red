package ch.bfh.red.backend.models;

public enum AcademicTitle {
	BACHELOR("BSc"), MASTER("MSc"), DOCTOR("Dr."), PROFESSOR("Prof.");

	private String code;

	private AcademicTitle(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
