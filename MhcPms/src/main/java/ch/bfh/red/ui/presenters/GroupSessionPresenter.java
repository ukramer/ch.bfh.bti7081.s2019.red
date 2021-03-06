package ch.bfh.red.ui.presenters;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.bfh.red.backend.models.GroupSession;
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.models.SessionType;
import ch.bfh.red.backend.models.Therapist;
import ch.bfh.red.backend.services.GroupSessionService;
import ch.bfh.red.backend.services.PatientService;
import ch.bfh.red.backend.services.TherapistService;
import ch.bfh.red.converters.GroupSessionConverter;
import ch.bfh.red.converters.PatientConverter;
import ch.bfh.red.converters.TherapistConverter;
import ch.bfh.red.ui.dto.GroupSessionDTO;
import ch.bfh.red.ui.dto.GroupSessionGridDTO;
import ch.bfh.red.ui.dto.GroupSessionSearchDTO;
import ch.bfh.red.ui.dto.PatientDTO;
import ch.bfh.red.ui.dto.TherapistDTO;
import ch.bfh.red.ui.views.EditGroupSessionView.EditGroupSessionListener;
import ch.bfh.red.ui.views.ListGroupSessionView;
import ch.bfh.red.ui.views.ListGroupSessionView.ListGroupSessionListener;

@Component
public class GroupSessionPresenter implements ListGroupSessionListener, EditGroupSessionListener {
	
	private ListGroupSessionView listView;
	
	@Autowired
	private GroupSessionService service;
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private TherapistService therapistService;
	
	@Autowired
	private GroupSessionConverter groupSessionConverter;
	
	@Autowired
	private PatientConverter patientConverter;
	
	@Autowired
	private TherapistConverter therapistConverter;
	
	public void setView(ListGroupSessionView listView) {
		this.listView = listView;
		
		List<GroupSession> models = service.getAll();
		List<GroupSessionDTO> dtos = groupSessionConverter.toDTOList(models);
		listView.setGroupSessions(dtos);
	}
	
	@Override
    public void applyFilter(GroupSessionSearchDTO searchBean) {
		List<GroupSession> models = service.findByDTO(searchBean);
		List<GroupSessionDTO> dtos = groupSessionConverter.toDTOList(models);
        listView.setFilteredSessions(dtos);
    }
	
	@Override
	public GroupSessionDTO load(Integer therapyId) {
		GroupSession model = service.getById(therapyId);
		GroupSessionDTO dto = groupSessionConverter.toDTO(model);
		return dto;
	}

	@Override
	public GroupSessionDTO save(GroupSessionDTO dto) throws Exception {
		GroupSession model = groupSessionConverter.toModel(dto);
		model = service.persist(model);
		GroupSessionDTO dto2 = groupSessionConverter.toDTO(model);
		return dto2;
	}
	
	@Override
	public void delete(GroupSessionGridDTO singleSession) {
		service.delete(service.getById(singleSession.getId()));
	}

	@Override
	public Collection<PatientDTO> getPatients() {
		Collection<Patient> models = patientService.getAll();
		Collection<PatientDTO> dtos = patientConverter.toDTOList(models);
		return dtos;
	}

	@Override
	public Collection<TherapistDTO> getTherapist() {
		Collection<Therapist> models = therapistService.getAll();
		Collection<TherapistDTO> dtos = therapistConverter.toDTOList(models);
		return dtos;
	}

	@Override
	public Collection<SessionType> getSessionTypes() {
		return Arrays.asList(SessionType.values());
	}

	
	
}
