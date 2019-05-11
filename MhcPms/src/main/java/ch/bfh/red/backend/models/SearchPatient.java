package ch.bfh.red.backend.models;

public class SearchPatient {
	private Long id;

	public SearchPatient(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}