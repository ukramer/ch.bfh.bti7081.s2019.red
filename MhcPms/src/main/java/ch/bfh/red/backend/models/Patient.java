package ch.bfh.red.backend.models;

import org.hibernate.annotations.Cascade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

@Entity
public class Patient extends AbstractPerson<Patient> implements Serializable {

	@ManyToMany
	private Collection<Therapist> therapists = new ArrayList<>();
	
	@OneToMany(mappedBy = "patient")
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private Collection<Therapy> therapies = new ArrayList<>();
	
	@OneToMany
	private Collection<SingleSession> singleSessions = new ArrayList<>();
	
	@OneToMany
	private Collection<GroupSession> groupSessions = new ArrayList<>();

	public Patient() {}
	
	public Patient(String firstName, String lastName, Address address) {
		super(firstName, lastName, address);
	}
	
	public Collection<Therapist> getTherapists() {
		return therapists;
	}

	public void setTherapists(Collection<Therapist> therapists) {
		this.therapists = therapists;
	}

	public Collection<Therapy> getTherapies() {
		return therapies;
	}

	public void setTherapies(Collection<Therapy> therapies) {
		this.therapies = therapies;
	}

	public Collection<SingleSession> getSingleSessions() {
		return singleSessions;
	}

	public void setSingleSessions(Collection<SingleSession> singleSessions) {
		this.singleSessions = singleSessions;
	}

	public Collection<GroupSession> getGroupSessions() {
		return groupSessions;
	}

	public void setGroupSessions(Collection<GroupSession> groupSessions) {
		this.groupSessions = groupSessions;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
}