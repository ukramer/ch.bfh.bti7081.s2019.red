package ch.bfh.red.backend.models;

import org.hibernate.annotations.Cascade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class AbstractNote<T extends AbstractNote<T>> implements Comparable<T>, Serializable  {
	private static final long serialVersionUID = 7401678212507566844L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable = false, unique=true)
	private int id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date date;
	
	@Column(nullable = false)
	private String text;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	@Cascade({org.hibernate.annotations.CascadeType.MERGE})
	private Visibility visibility;

	public AbstractNote() {}
	
	public AbstractNote(Date date, String text, Visibility visibility) {
		this.date = date;
		this.text = text;
		this.visibility = visibility;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + visibility.ordinal();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractNote<?> other = (AbstractNote<?>) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (visibility == null) {
			if (other.visibility != null)
				return false;
		} else if (!visibility.equals(other.visibility))
			return false;
		return true;
	}
	
	@Override
	public int compareTo(T o) {
		int i;
		i = o.getDate().compareTo(date);
		if (i != 0) return i;
		i = visibility.compareTo(o.getVisibility());
		if (i != 0) return i;
		return text.compareTo(o.getText());
	}

	@Override
	public String toString() {
		return "AbstractNote [date=" + date + ", visibility=" + visibility + ", text="
				+ text + "]";
	}
	
}