package ch.bfh.red.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.bfh.red.backend.models.AcademicTitle;
import ch.bfh.red.backend.models.Therapist;
import ch.bfh.red.backend.services.TherapistService;
import ch.bfh.red.ui.dto.TherapistDTO;

@Component
public class TherapistConverter extends AbstractConverter<Therapist, TherapistDTO> {
	
	@Autowired
	private TherapistService service;
	
	@Override
	public Therapist toModel(TherapistDTO dto) {
		Integer id = dto.getId();
		Therapist model;
		if (id != null)
			model = service.getById(dto.getId());
		else
			model = new Therapist();
		String prefix = dto.getPrefix();
		if (prefix != null)
			model.setAcademicTitle(AcademicTitle.valueOf(prefix));
		model.setFirstName(dto.getFirstName());
		model.setLastName(dto.getLastName());
		return model;
	}
	
	@Override
	public TherapistDTO toDTO(Therapist model) {
		TherapistDTO dto = new TherapistDTO();
		AcademicTitle academicTitle = model.getAcademicTitle();
		if (academicTitle != null)
			dto.setPrefix(academicTitle.getCode());
		dto.setFirstName(model.getFirstName());
		dto.setLastName(model.getLastName());
		return dto;
	}
	
}
