package ch.bfh.red.ui.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.flow.templatemodel.TemplateModel;

import ch.bfh.red.MainLayout;
import ch.bfh.red.ui.presenters.GroupSessionPresenter;

@Route(value = "groupSession/list", layout = MainLayout.class)
@Tag("group-session-list")
@HtmlImport("frontend://src/views/session/listGroupSession.html")
@Component
@UIScope
public class ListGroupSessionView 
		extends PolymerTemplate<ListGroupSessionView.ListGroupSessionModel> {
	
	private GroupSessionPresenter presenter;
	
	@Id("header")
    private H2 header;
	
	@Autowired
	public ListGroupSessionView(GroupSessionPresenter presenter) {
		this.presenter = presenter;
		
		
	}
	
	public interface ListGroupSessionListener {
		
	}
	
	public interface ListGroupSessionModel extends TemplateModel {
		
	}
	
}
