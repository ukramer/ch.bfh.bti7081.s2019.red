package ch.bfh.red.ui.presenters;


import ch.bfh.red.ui.views.ExpositionView;


public class ExpositionPresenter implements ExpositionView.ExpositionViewListener{

	private ExpositionView view;
	
	public ExpositionPresenter(ExpositionView view) {
		
		this.view = view;
		view.addListener(this);
	}

}