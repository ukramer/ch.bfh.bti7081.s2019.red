package ch.bfh.red.backend.models;

import java.util.ArrayList;
import java.util.Collection;

public class Patient extends AbstractPerson<Patient> {

	private Collection<Therapist> therapists = new ArrayList<>();
	
	private Collection<Therapy> therapies = new ArrayList<>();
	
	private Collection<SingleSession> singleSessions = new ArrayList<>();
	
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