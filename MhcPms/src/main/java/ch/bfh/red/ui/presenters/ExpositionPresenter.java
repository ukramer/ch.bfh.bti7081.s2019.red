package ch.bfh.red.ui.presenters;

import ch.bfh.red.backend.models.Address;
import ch.bfh.red.backend.models.ExpositionNote;
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.models.Visibility;
import ch.bfh.red.backend.persistence.ExpositionNotePersistenceManager;
import ch.bfh.red.backend.persistence.PatientPersistenceManager;
import ch.bfh.red.backend.services.ExpositionNoteService;
import ch.bfh.red.ui.views.ExpositionDetailView;
import ch.bfh.red.ui.views.ExpositionView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExpositionPresenter implements ExpositionView.ExpositionViewListener, ExpositionDetailView.ExpositionDetailViewListener {

    private ExpositionView expositionView;
    private ExpositionDetailView expositionDetailView;

	@Autowired
	private ExpositionNotePersistenceManager expositionManager;
	@Autowired
	private PatientPersistenceManager patientManager;
	private List<ExpositionNote> expositions = new ArrayList<>();

	private ExpositionNote loadedExposition;

	public void setView(ExpositionView expositionView) {
		this.expositionView = expositionView;
		expositionView.setListener(this);


		expositions = expositionManager.getService().getAll();
		expositionView.setExpositions(expositions);


		List<Patient> patients = expositions.stream().map(ExpositionNote::getPatient).distinct().sorted().collect(Collectors.toList());
		expositionView.setPatients(patients);
	}

	public void setView(ExpositionDetailView expositionDetailView){
		this.expositionDetailView = expositionDetailView;
		expositionDetailView.setListener(this);

		List<Patient> patients = expositions.stream().map(ExpositionNote::getPatient).distinct().sorted().collect(Collectors.toList());
		expositionDetailView.setPatients(patients);
	}

	@Override
	public void delete(ExpositionNote expositionNote){
		getService().delete(getService().getById(expositionNote.getId()));

	}

	@Override
	public void deleteById(int id){
		getService().delete(id);
		expositions= getService().getAll();
	}

	@Override
	public void load(Integer id){
		loadedExposition = getService().getById(id);
		expositionDetailView.setExposition(loadedExposition);

	}
	@Override
	public void updateListByFilter(Patient patient){
		expositions = getService().getByPatientName(patient.getFirstName(), patient.getLastName());
		expositionView.setExpositions(expositions);
	}
	@Override
	public void updateList(){
		expositions.clear();
		expositions = getService().getAll();
		expositionView.setExpositions(expositions);
	}
	@Override
	public void save(ExpositionNote expositionNote){
		getService().persist(expositionNote);
	}

	@Override
	public void prepareNewObject(){
		expositionDetailView.setExposition(new ExpositionNote());
	}
	public ExpositionNoteService getService(){
		return (ExpositionNoteService) expositionManager.getService();
	}



}