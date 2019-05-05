package ch.bfh.red.backend.models;

public class SearchTherapist {
	private Long id;

	public SearchTherapist(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}