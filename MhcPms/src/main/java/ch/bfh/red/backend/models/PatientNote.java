package ch.bfh.red.backend.models;

import java.util.Date;

import javax.persistence.*;

@Entity
public class PatientNote extends AbstractNote<PatientNote> {
	private Patient patient;

	public PatientNote() {}
	
	public PatientNote(Patient patient, Date date, String text, Visibility visibility) {
		super(date, text, visibility);
		this.patient = patient;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((patient == null) ? 0 : patient.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PatientNote other = (PatientNote) obj;
		if (patient == null) {
			if (other.patient != null)
				return false;
		} else if (!patient.equals(other.patient))
			return false;
		return super.equals(obj);
	}

	@Override
	public int compareTo(PatientNote o) {
		int i;
		i = patient.compareTo(o.patient);
		if (i != 0) return i;
		return super.compareTo(o);
	}

	@Override
	public String toString() {
		return "PatientNote [patient=" + patient +", " +super.toString() +"]";
	}
	
}