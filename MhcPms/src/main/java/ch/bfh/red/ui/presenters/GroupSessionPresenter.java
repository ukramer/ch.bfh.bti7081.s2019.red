package ch.bfh.red.ui.presenters;

import java.util.ArrayList;
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
import ch.bfh.red.ui.dto.GroupSessionDTO;
import ch.bfh.red.ui.dto.GroupSessionGridDTO;
import ch.bfh.red.ui.dto.GroupSessionSearchDTO;
import ch.bfh.red.ui.dto.PersonDTO;
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
	
	public void setView(ListGroupSessionView listView) {
		this.listView = listView;
		
		List<GroupSession> models = service.getAll();
		List<GroupSessionDTO> dtos = new ArrayList<>();
		for (GroupSession model: models)
			dtos.add(GroupSessionDTO.toDTO(model));
		listView.setGroupSessions(dtos);
//		List<Patient> patients = patientService.getAll();
//		List<PersonDTO> patientsDTO = new ArrayList<>();
//		for (Patient patient: patients) {
//			patientsDTO.add(new PersonDTO(patient.getFirstName(), patient.getLastName()));
//		}
//		listView.setPatients(patientsDTO);
//		
//		List<Therapist> therapists = therapistService.getAll();
//		List<TherapistDTO> therapistsDTO = new ArrayList<>();
//		for (Therapist therapist: therapists) {
//			therapistsDTO.add(new TherapistDTO(therapist.getAcademicTitle().getCode(), therapist.getFirstName(), therapist.getLastName()));
//		}
//		listView.setTherapists(therapistsDTO);
	}
	
	@Override
    public void applyFilter(GroupSessionSearchDTO searchBean) {
		List<GroupSession> models = service.findByDTO(searchBean);
		List<GroupSessionDTO> dtos = new ArrayList<>();
		for (GroupSession model: models)
			dtos.add(GroupSessionDTO.toDTO(model));
        listView.setFilteredSessions(dtos);
    }
	
	@Override
	public void delete(GroupSessionGridDTO singleSession) {
		service.delete(service.getById(singleSession.getId()));
	}

	@Override
	public Collection<Patient> getPatients() {
		return patientService.getAll();
	}

	@Override
	public Collection<Therapist> getTherapist() {
		return therapistService.getAll();
	}

	@Override
	public Collection<SessionType> getSessionTypes() {
		return Arrays.asList(SessionType.values());
	}

	@Override
	public GroupSessionDTO load(Integer therapyId) {
		GroupSession model = service.getById(therapyId);
		GroupSessionDTO dto = GroupSessionDTO.toDTO(model);
		return dto;
	}

	@Override
	public GroupSessionDTO save(GroupSessionDTO dto) throws Exception {
		GroupSession model = service.persist(GroupSessionDTO.toModel(dto));
		GroupSessionDTO dto2 = GroupSessionDTO.toDTO(model);
		return dto2;
	}
	
}
