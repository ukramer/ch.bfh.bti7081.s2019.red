package ch.bfh.red.backend.models;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class SessionType implements Comparable<SessionType>, Serializable {
	private static final long serialVersionUID = -588062357147068741L;

	@Id
	@GeneratedValue
	@Column(nullable = false)
	private int id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String description;

	public SessionType() {}
	
	public SessionType(String name, String description) {
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
		SessionType other = (SessionType) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public int compareTo(SessionType o) {
		return name.compareTo(name);
	}

	@Override
	public String toString() {
		return "SessionType [name=" + name + ", description=" + description + "]";
	}
	
}