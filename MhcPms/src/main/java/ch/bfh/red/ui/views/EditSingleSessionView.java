package ch.bfh.red.ui.views;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;

import ch.bfh.red.MainLayout;

@Route(value = "editSingleSession", layout = MainLayout.class)
@Tag("editsinglesession-element")
@HtmlImport("frontend://src/views/session/editSingleSession.html")
public class EditSingleSessionView extends PolymerTemplate<EditSingleSessionView.EditSingleSessionModel> implements View<EditSingleSessionView.EditSingleSessionListener> {
    private static final long serialVersionUID = 1L;
    
    private EditSingleSessionListener listener;

    public interface EditSingleSessionListener {
        
    }
    
    public interface EditSingleSessionModel extends TemplateModel {
        
    }

    @Override
    public void setListener(EditSingleSessionListener listener) {
        this.listener = listener;
    }
}
