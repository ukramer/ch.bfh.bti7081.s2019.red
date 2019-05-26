package ch.bfh.red.ui.encoders;

import com.vaadin.flow.templatemodel.ModelEncoder;

import ch.bfh.red.backend.models.Therapist;

public class TherapistToNameStringConverter implements ModelEncoder<Therapist, String> {

	@Override
	public String encode(Therapist modelValue) {
		if (modelValue == null)
			return null;
		return String.format("%s %s %s", modelValue.getAcademicTitle().getPrefix(), modelValue.getFirstName(), modelValue.getLastName());
	}

	@Override
	public Therapist decode(String value) {
		throw new UnsupportedOperationException();
	}
	
}
