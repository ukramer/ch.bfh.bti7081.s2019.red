package ch.bfh.red.ui.presenters;

import ch.bfh.red.backend.models.Therapist;
import ch.bfh.red.ui.views.LoginView;
import ch.bfh.red.ui.views.LoginView.LoginViewListener;

public class LoginPresenter implements LoginViewListener {
	private Therapist therapist;
	private LoginView view;
	
	public LoginPresenter(LoginView view) {
		this.view = view;
		
		view.setListener(this);
	}

}
