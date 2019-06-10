package ch.bfh.red.ui.presenters;

import ch.bfh.red.backend.models.Address;
import ch.bfh.red.backend.models.ExpositionNote;
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.models.Visibility;
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
	private final ExpositionNoteService expositionNoteService;
	private List<ExpositionNote> expositions = new ArrayList<>();

	@Autowired
	public ExpositionPresenter(ExpositionNoteService service){
		this.expositionNoteService = service;

	}
	public void setView(ExpositionView expositionView) {
		this.expositionView = expositionView;
		expositionView.setListener(this);
		expositions = expositionNoteService.getAll();
		expositionView.setExpositions(expositions);
	}

	@Override
	public void delete(ExpositionNote expositionNote){
		getService().delete(getService().getById(expositionNote.getId()));
		expositions = getService().getAll();
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
		return expositionNoteService;
	}

//	public void addMockData(){
//
//		Patient patient1 = new Patient("Stefan", "Mosimann",
//				new Address("Flughafenstrasse", "14", 3123, "Belp"));
//		ExpositionNote note1 = new ExpositionNote(patient1, new Date(), "Herd nicht überpüft", Visibility.PRIVATE, 7);
//		expositionNoteService.persist(note1);
//
//		Patient patient2 = new Patient("Annina", "Eigensatz",
//				new Address("Steinhofstrasse", "34", 3400, "Burgdorf"));
//		ExpositionNote note2 = new ExpositionNote(patient2, new Date(), "Ins Bett ohne Putzritual", Visibility.PRIVATE, 9);
//		expositionNoteService.persist(note2);
//
//		Patient patient3 = new Patient("Samuel", "Frey",
//				new Address("Fuchsmattweg", "54", 1111, "Hindelbank"));
//		ExpositionNote note3 = new ExpositionNote(patient3, new Date(), "Nur 3mal Händewaschen", Visibility.PRIVATE, 6);
//		expositionNoteService.persist(note3);
//
//	}

}