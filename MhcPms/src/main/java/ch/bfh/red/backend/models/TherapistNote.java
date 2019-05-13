package ch.bfh.red.backend.models;

import java.util.Date;
import java.util.Objects;

public class TherapistNote extends AbstractNote<TherapistNote> {
	
	private Therapist therapist;
	
	public TherapistNote() {}
	
	public TherapistNote(Therapist therapist, Date date, String text,
			Visibility visibility) {
		super(date, text, visibility);
		this.therapist = therapist;
	}
	
	public Therapist getTherapist() {
		return therapist;
	}
	
	public void setTherapist(Therapist therapist) {
		this.therapist = therapist;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(therapist);
		result = prime * result +super.hashCode();
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
		TherapistNote other = (TherapistNote) obj;
		if (!Objects.equals(therapist, other.therapist))
			return false;
		return super.equals(obj);
	}
	
	@Override
	public int compareTo(TherapistNote o) {
		int i;
		i = therapist.compareTo(o.therapist);
		if (i != 0) return i;
		return super.compareTo(o);
	}

	@Override
	public String toString() {
		return "TherapistNote [therapist=" + therapist + ", "
				+ super.toString() + "]";
	}
	
}
