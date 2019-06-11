package ch.bfh.red.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.services.PatientService;
import ch.bfh.red.ui.dto.PatientDTO;

@Component
public class PatientConverter extends AbstractConverter<Patient, PatientDTO> {
	
	@Autowired
	private PatientService patientService;
	
	public PatientConverter() {}
	
	@Override
	public Patient toModel(PatientDTO dto) {
		Integer id = dto.getId();
		Patient model;
		if (id != null)
			model = patientService.getById(dto.getId());
		else
			model = new Patient();
		model.setFirstName(dto.getFirstName());
		model.setLastName(dto.getLastName());
		return model;
	}
	
	@Override
	public PatientDTO toDTO(Patient patient) {
		return new PatientDTO(patient.getId(), patient.getFirstName(), patient.getLastName());
	}
	
}
