package ch.bfh.red.backend.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public abstract class Note implements Serializable {
	@Id
	@GeneratedValue
	private Long id;
	private Date date;
	private String text;
	private Visibility visibility;

	public Note(Date date, String text, Visibility visibility) {
		this.date = date;
		this.text = text;
		this.visibility = visibility;
	}
	
	public Note(Date date, String text) {
		this.date = date;
		this.text = text;
	}

	public Long getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Visibility getVisibility() {
		return visibility;
	}

	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}
}