package ch.bfh.red.ui.presenters;

import ch.bfh.red.backend.services.ExpositionNoteService;
import ch.bfh.red.ui.views.ExpositionView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExpositionPresenter implements ExpositionView.ExpositionViewListener {

	private ExpositionView view;

	@Autowired
	private ExpositionNoteService expositionNoteService;

	public void setView(ExpositionView expositionView) {
		view = expositionView;
		view.setListener(this);
	}
	
	public ExpositionPresenter() {
	}

}