package ch.bfh.red.backend.models;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Visibility implements Comparable<Visibility>, Serializable {
	private static final long serialVersionUID = -6591104470141483754L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable = false, unique = true)
	private int id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = true)
	private String description;

	public Visibility() {}
	
	public Visibility(String name, String description) {
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
		result = prime * result + ((description == null) ? 0 : description.hashCode());
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
		Visibility other = (Visibility) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	@Override
	public int compareTo(Visibility o) {
		return name.compareTo(o.name);
	}

	@Override
	public String toString() {
		return "Visibility [name=" + name + ", description=" + description + "]";
	}
	
}