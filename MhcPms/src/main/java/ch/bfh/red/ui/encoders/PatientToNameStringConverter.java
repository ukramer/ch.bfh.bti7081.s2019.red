package ch.bfh.red.ui.encoders;

import com.vaadin.flow.templatemodel.ModelEncoder;

import ch.bfh.red.backend.models.Patient;

public class PatientToNameStringConverter implements ModelEncoder<Patient, String>{

	@Override
	public String encode(Patient modelValue) {
		if (modelValue == null)
			return null;
		return String.format("%s %s", modelValue.getFirstName(), modelValue.getLastName());
	}

	@Override
	public Patient decode(String value) {
		throw new UnsupportedOperationException();
	}
	
}
