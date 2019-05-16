package ch.bfh.red.backend.models;

import java.util.Date;

public abstract class AbstractNote<T extends AbstractNote<T>> implements Comparable<T>  {
	private Date date;
	private String text;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((visibility == null) ? 0 : visibility.hashCode());
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