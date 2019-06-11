package ch.bfh.red.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import ch.bfh.red.backend.models.GroupSession;
import ch.bfh.red.backend.services.GroupSessionService;
import ch.bfh.red.ui.dto.GroupSessionDTO;

@Component
public class GroupSessionConverter extends AbstractConverter<GroupSession, GroupSessionDTO> {
	
	@Autowired
	private GroupSessionService service;
	
	@Autowired
	@Lazy
	private PatientConverter patientConverter;
	
	@Autowired
	@Lazy
	private TherapistConverter therapistConverter;
	
	public GroupSessionConverter() {}
	
	public GroupSession toModel(GroupSessionDTO dto) {
		Integer id = dto.getId();
		GroupSession session;
		if (id != null)
			session = service.getById(dto.getId());
		else 
			session = new GroupSession();
		session.setStartDate(dto.getStartDate());
		session.setEndDate(dto.getEndDate());
		session.setPatients(patientConverter.toModelList(dto.getPatients()));
		session.setTherapists(therapistConverter.toModelList(dto.getTherapists()));
		session.setSessionType(dto.getSessionType());
		return session;
	}
	
	public GroupSessionDTO toDTO(GroupSession session) {
		GroupSessionDTO dto = new GroupSessionDTO();
		try {
			dto.setId(session.getId());
		} catch (Exception e) {}
		dto.setStartDate(session.getStartDate());
		dto.setEndDate(session.getEndDate());
		dto.setPatients(patientConverter.toDTOList(session.getPatients()));
		dto.setTherapists(therapistConverter.toDTOList(session.getTherapists()));
		dto.setSessionType(session.getSessionType());
		return dto;
	}
	
}
