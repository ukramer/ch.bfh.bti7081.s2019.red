package ch.bfh.red.ui.presenters;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.models.SessionType;
import ch.bfh.red.backend.models.SingleSession;
import ch.bfh.red.backend.models.Therapist;
import ch.bfh.red.backend.persistence.SingleSessionPersistenceManager;
import ch.bfh.red.backend.services.PatientService;
import ch.bfh.red.backend.services.SingleSessionService;
import ch.bfh.red.backend.services.TherapistService;
import ch.bfh.red.converters.PatientConverter;
import ch.bfh.red.converters.SingleSessionConverter;
import ch.bfh.red.converters.TherapistConverter;
import ch.bfh.red.ui.dto.PatientDTO;
import ch.bfh.red.ui.dto.SingleSessionDTO;
import ch.bfh.red.ui.dto.SingleSessionSearchDTO;
import ch.bfh.red.ui.dto.TherapistDTO;
import ch.bfh.red.ui.views.EditSingleSessionView.EditSingleSessionListener;
import ch.bfh.red.ui.views.ListSingleSessionView;
import ch.bfh.red.ui.views.ListSingleSessionView.ListSingleSessionListener;

@Component
public class SingleSessionPresenter implements EditSingleSessionListener, ListSingleSessionListener {
	
	private ListSingleSessionView listView;
	
	@Autowired
	private SingleSessionService service;
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private TherapistService therapistService;
	
	@Autowired
	private SingleSessionConverter singleSessionConverter;
	
	@Autowired
	private PatientConverter patientConverter;
	
	@Autowired
	private TherapistConverter therapistConverter;
	
	@Autowired
	private SingleSessionPersistenceManager persistenceManager;
	
	public SingleSessionPresenter() {}
	
	public void setView(ListSingleSessionView listView) {
		this.listView = listView;
		List<SingleSession> models = service.getAll();
		List<SingleSessionDTO> dtos = singleSessionConverter.toDTOList(models);
		listView.setSingleSessions(dtos);
	}
	
	@Override
    public void applyFilter(SingleSessionSearchDTO searchBean) {
		List<SingleSession> models = service.findByDTO(searchBean);
		List<SingleSessionDTO> dtos = singleSessionConverter.toDTOList(models);
        listView.setFilteredSessions(dtos);
    }
	
	@Override
	public SingleSessionDTO load(Integer therapyId) {
		SingleSession singleSession = service.getById(therapyId);
		SingleSessionDTO dto = singleSessionConverter.toDTO(singleSession);
		return dto;
	}
	
	@Override
	public SingleSessionDTO save(SingleSessionDTO singleSession) throws Exception {
		SingleSession singleSession2 = persistenceManager.persistAll(singleSessionConverter.toModel(singleSession));
		SingleSessionDTO dto = singleSessionConverter.toDTO(singleSession2);
		return dto;
	}

	@Override
	public void delete(SingleSessionDTO singleSession) {
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
