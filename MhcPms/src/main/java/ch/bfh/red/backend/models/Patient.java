package ch.bfh.red.backend.models;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

@Entity
public class Patient extends AbstractPerson<Patient> {
    private static final long serialVersionUID = 7386032293356521767L;

    @ManyToMany
    private Collection<Therapist> therapists = new ArrayList<>();

    @OneToMany(mappedBy = "patient")
    private Collection<Therapy> therapies = new ArrayList<>();

    @OneToMany(mappedBy = "patient")
    private Collection<SingleSession> singleSessions = new ArrayList<>();

    @ManyToMany
    private Collection<GroupSession> groupSessions = new ArrayList<>();

    public Patient() {
    }

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