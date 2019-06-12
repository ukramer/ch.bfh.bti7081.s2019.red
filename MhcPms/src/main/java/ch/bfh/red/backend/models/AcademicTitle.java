package ch.bfh.red.backend.models;

public enum AcademicTitle {
    BACHELOR("BSc", "Bachelor of Science"),
    MASTER("MSc", "Master of Science"),
    DOCTOR("Dr.", "DOCTOR"),
    PROFESSOR("Prof.", "Professor");

    private String code;
    private String title;

    private AcademicTitle(String code, String title) {
        this.code = code;
        this.title = title;
    }

    public String getCode() {
        return code;
    }

	public String getTitle() {
		return title;
	}
    
}
