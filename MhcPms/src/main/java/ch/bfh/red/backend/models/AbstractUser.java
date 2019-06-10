package ch.bfh.red.backend.models;

import javax.persistence.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class AbstractUser<T extends AbstractUser<T>> extends AbstractPerson<T> {
	private static final long serialVersionUID = 7516376909524646114L;

	@Column(nullable = false)
	private String username;
	
	@Column(nullable = false)
	private String password;

	public AbstractUser() {}
	
	public AbstractUser(String username, String password, String firstName, 
	                    String lastName, Address address) {
		super(firstName, lastName, address);
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) // Dereferenced Null value is only checked not executed
			return true;
		if (getClass() != obj.getClass())
			return false;

		AbstractUser<?> other = (AbstractUser<?>) obj;
		if (username == null && other.username != null)
			return false;
		if (!username.equals(other.username)) // Dereferenced Null value is only checked not executed
			return false;
		return super.equals(obj);
	}
	
	@Override
	public int compareTo(T o) {
		int i;
		i = username.compareTo(o.getUsername());
		if (i != 0) return i;
		return super.compareTo(o);
	}

	@Override
	public String toString() {
		return "AbstractUser [username=" + username + ", password=" + password + "]";
	}

}