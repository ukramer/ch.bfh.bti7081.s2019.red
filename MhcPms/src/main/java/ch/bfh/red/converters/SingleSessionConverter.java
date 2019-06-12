package ch.bfh.red.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import ch.bfh.red.backend.models.SingleSession;
import ch.bfh.red.backend.services.SingleSessionService;
import ch.bfh.red.ui.dto.SingleSessionDTO;

@Component
public class SingleSessionConverter extends AbstractConverter<SingleSession, SingleSessionDTO> {

	@Autowired
	private SingleSessionService service;
	
	@Autowired
	@Lazy
	private PatientConverter patientConverter;
	
	@Autowired
	@Lazy
	private TherapistConverter therapistConverter;
	
	public SingleSessionConverter() {}
	
	@Override
	public SingleSession toModel(SingleSessionDTO dto) {
		Integer id = dto.getId();
		SingleSession session;
		if (id != null)
			session = service.getById(dto.getId());
		else 
			session = new SingleSession();
		session.setStartDate(dto.getStartDate());
		session.setEndDate(dto.getEndDate());
		session.setPatient(patientConverter.toModel(dto.getPatient()));
		session.setTherapist(therapistConverter.toModel(dto.getTherapist()));
		session.setSessionType(dto.getSessionType());
		return session;
	}
	
	@Override
	public SingleSessionDTO toDTO(SingleSession session) {
		SingleSessionDTO dto = new SingleSessionDTO();
		try {
			dto.setId(session.getId());
		} catch (Exception e) {}
		dto.setStartDate(session.getStartDate());
		dto.setEndDate(session.getEndDate());
		dto.setPatient(patientConverter.toDTO(session.getPatient()));
		dto.setTherapist(therapistConverter.toDTO(session.getTherapist()));
		dto.setSessionType(session.getSessionType());
		return dto;
	}
	
}
