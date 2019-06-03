package ch.bfh.red.ui.views;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;

import ch.bfh.red.MainLayout;

@SuppressWarnings("serial")
@Tag("login-view")
@HtmlImport("frontend://src/views/login/login.html")
@Route(value = LoginView.ROUTE, layout=MainLayout.class)
@PageTitle("Login")
public class LoginView extends PolymerTemplate<TemplateModel> {
	public static final String ROUTE = "login";
	
	/** Listener Interface **/
	public interface LoginViewListener {
		
	}
	
	private LoginViewListener listener;
	
	public void setListener(LoginViewListener listener) {
		this.listener = listener;
	}
	
	
	
}