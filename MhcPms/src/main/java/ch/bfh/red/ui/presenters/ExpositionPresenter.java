package ch.bfh.red.ui.presenters;

import ch.bfh.red.backend.models.Address;
import ch.bfh.red.backend.models.ExpositionNote;
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.models.Visibility;
import ch.bfh.red.backend.persistence.ExpositionNotePersistenceManager;
import ch.bfh.red.backend.services.ExpositionNoteService;
import ch.bfh.red.ui.views.ExpositionView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ExpositionPresenter implements ExpositionView.ExpositionViewListener {

    private ExpositionView expositionView;

	@Autowired
	private ExpositionNotePersistenceManager expositionManager;
	private List<ExpositionNote> expositions = new ArrayList<>();


	public void setView(ExpositionView expositionView) {
		this.expositionView = expositionView;
		expositionView.setListener(this);
		expositions = expositionManager.getService().getAll();
		expositionView.setExpositions(expositions);
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
		expositions.add(getService().getById(id));
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

	public ExpositionNoteService getService(){
		return (ExpositionNoteService) expositionManager.getService();
	}



}