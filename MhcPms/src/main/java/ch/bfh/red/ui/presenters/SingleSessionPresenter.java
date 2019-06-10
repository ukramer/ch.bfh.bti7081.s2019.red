package ch.bfh.red.ui.presenters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.models.SessionType;
import ch.bfh.red.backend.models.SingleSession;
import ch.bfh.red.backend.models.Therapist;
import ch.bfh.red.backend.services.PatientService;
import ch.bfh.red.backend.services.SingleSessionService;
import ch.bfh.red.backend.services.TherapistService;
import ch.bfh.red.ui.dto.SingleSessionDTO;
import ch.bfh.red.ui.dto.SingleSessionSearchDTO;
import ch.bfh.red.ui.views.session.EditSingleSessionView.EditSingleSessionListener;
import ch.bfh.red.ui.views.session.ListSingleSessionView;
import ch.bfh.red.ui.views.session.ListSingleSessionView.ListSingleSessionListener;

@Component
public class SingleSessionPresenter implements EditSingleSessionListener, ListSingleSessionListener {
	
	private ListSingleSessionView listView;
	
	@Autowired
	private SingleSessionService service;
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private TherapistService therapistService;
	
	public SingleSessionPresenter() {}
	
	public void setView(ListSingleSessionView listView) {
		this.listView = listView;
		
		List<SingleSession> models = service.getAll();
		List<SingleSessionDTO> dtos = new ArrayList<>();
		for (SingleSession model: models)
			dtos.add(SingleSessionDTO.fromModel(model));
		listView.setSingleSessions(dtos);
		listView.setPatients(patientService.getAll());
	}
	
	@Override
    public void applyFilter(SingleSessionSearchDTO searchBean) {
		List<SingleSession> models = service.findByDTO(searchBean);
		List<SingleSessionDTO> dtos = new ArrayList<>();
		for (SingleSession model: models)
			dtos.add(SingleSessionDTO.fromModel(model));
        listView.setSingleSessions(dtos);
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
	public SingleSessionDTO load(Integer therapyId) {
		SingleSession singleSession = service.getById(therapyId);
		SingleSessionDTO dto = SingleSessionDTO.fromModel(singleSession);
		return dto;
	}
	
	@Override
	public SingleSessionDTO save(SingleSessionDTO singleSession) throws Exception {
		SingleSession singleSession2 = service.persist(SingleSessionDTO.toModel(singleSession));
		SingleSessionDTO dto = SingleSessionDTO.fromModel(singleSession2);
		return dto;
	}

	@Override
	public void delete(SingleSessionDTO singleSession) {
		service.delete(service.getById(singleSession.getId()));
	}
	
}
