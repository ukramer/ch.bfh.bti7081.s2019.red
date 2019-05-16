package ch.bfh.red.backend.models;

import javax.persistence.*;

@Entity
public class TherapyType implements Comparable<TherapyType> {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String description;

	public TherapyType() {}
	
	public TherapyType(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		TherapyType other = (TherapyType) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	@Override
	public int compareTo(TherapyType o) {
		return name.compareTo(o.name);
	}

	@Override
	public String toString() {
		return "TherapyType [name=" + name + ", description=" + description + "]";
	}
	
}