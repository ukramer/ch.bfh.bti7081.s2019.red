package ch.bfh.red.ui.views;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;

import ch.bfh.red.MainLayout;
import ch.bfh.red.backend.models.SingleSession;

@Route(value = "editSingleSession", layout = MainLayout.class)
@Tag("editsinglesession-element")
@HtmlImport("frontend://src/views/session/editSingleSession.html")
public class EditSingleSessionView extends PolymerTemplate<EditSingleSessionView.EditSingleSessionModel> implements View<EditSingleSessionView.EditSingleSessionListener> {
    private static final long serialVersionUID = 1L;
    
    private EditSingleSessionListener listener;
    
    @Id("header")
    private H2 header;
    
    @Id
    private TextField therapist; 
    
    public EditSingleSessionView() {
    	 header.setText("Edit Single Session");
    }
    
    public interface EditSingleSessionListener {
        
    }
    
    public interface EditSingleSessionModel extends TemplateModel {
        
    	
    	void setSingleSession(SingleSession singleSession);
    	
    }

    @Override
    public void setListener(EditSingleSessionListener listener) {
        this.listener = listener;
    }
}
