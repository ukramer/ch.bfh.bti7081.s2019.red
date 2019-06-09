package ch.bfh.red.ui.presenters;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.models.SessionType;
import ch.bfh.red.backend.models.SingleSession;
import ch.bfh.red.backend.models.Therapist;
import ch.bfh.red.backend.models.Therapy;
import ch.bfh.red.backend.services.PatientService;
import ch.bfh.red.backend.services.SingleSessionService;
import ch.bfh.red.backend.services.TherapistService;
import ch.bfh.red.ui.views.EditSingleSessionView;
import ch.bfh.red.ui.views.EditSingleSessionView.EditSingleSessionListener;

import org.springframework.stereotype.Component;

@Component
public class SingleSessionPresenter implements EditSingleSessionListener {
	
	private EditSingleSessionView singleSessionView;
	
	@Autowired
	private SingleSessionService service;
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private TherapistService therapistService;
	
	public SingleSessionPresenter() {}
	
	public void setView(EditSingleSessionView singleSessionView) {
		this.singleSessionView = singleSessionView;
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
	public void load(Integer therapyId) {
		SingleSession singleSession = service.getById(therapyId);
		singleSessionView.editSingleSession(singleSession);
	}

	@Override
	public void prepareNewObject() {
		singleSessionView.createSingleSession();
	}

	@Override
	public void save(SingleSession singleSession) throws Exception {
		service.persist(singleSession);
	}
	
}
