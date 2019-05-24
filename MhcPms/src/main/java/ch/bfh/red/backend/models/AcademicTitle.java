package ch.bfh.red.backend.models;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class AcademicTitle implements Comparable<AcademicTitle>, Serializable {
	private static final long serialVersionUID = 8562816131184724910L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable = false, unique = true)
	private int id;
	
	@Column(nullable = false)
	private String prefix;
	
	private String description;
	
	public AcademicTitle() {}

	public AcademicTitle(String prefix, String description) {
		this.prefix = prefix;
		this.description = description;
	}

	public String getPrefix() {
		return prefix;
	}

	public String getDescription() {
		return description;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public void setDescription(String description) {
		this.description = description;
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
		result = prime * result + ((prefix == null) ? 0 : prefix.hashCode());
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
		AcademicTitle other = (AcademicTitle) obj;
		if (prefix == null) {
			if (other.prefix != null)
				return false;
		} else if (!prefix.equals(other.prefix))
			return false;
		return true;
	}

	@Override
	public int compareTo(AcademicTitle o) {
		return prefix.compareTo(o.prefix);
	}

	@Override
	public String toString() {
		return "AcademicTitle [prefix=" + prefix + ", description=" + description + "]";
	}
	
}